package com.rs.bootcoin.buy.service;

import com.rs.bootcoin.account.service.AccountService;
import com.rs.bootcoin.buy.dto.ConfirmPayment;
import com.rs.bootcoin.buy.dto.PaymentRequest;
import com.rs.bootcoin.buy.entity.Buy;
import com.rs.bootcoin.buy.message.producer.KafkaSender;
import com.rs.bootcoin.buy.repository.BuyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.function.Predicate;

@Service
public class BuyServiceImpl implements BuyService{

    @Autowired
    private KafkaSender kafkaSender;

    @Autowired
    private AccountService accountService;

    @Autowired
    private BuyRepository buyRepository;

    @Override
    public Mono<Buy> buyRequest(Buy buy) {
        /*
         * They must have some payment method
         */
        if(paymentMethod.test(buy.getMethodPayment()) && buy.getAccountNumber()!= null){
           /*return accountService.findAccount(buy.getUserDocBuy())
                   .flatMap(balance -> {
                       if(balance.getBalance() >= buy.getAmount()){
                           return buyRepository.save(buy);
                       }
                       return Mono.empty();
                   });*/
            return buyRepository.save(buy);
        }
        return Mono.empty();
    }

    @Override
    public Mono<Buy> acceptPurchaseRequest(String codeRequestBuy) {
        return buyRepository.findByCodeRequestBuy(codeRequestBuy)
                .filter(buyStatus ->buyStatus.getRequestStatus().equals(false))
                .flatMap(status -> {
                    status.setRequestStatus(true);
                    status.setTransactionNumber(java.util.UUID.randomUUID().toString());
                    return buyRepository.save(status).flatMap(message -> {
                        var messageContent = new PaymentRequest();
                        messageContent.setCodeRequestBuy(message.getCodeRequestBuy());
                        messageContent.setAmount(message.getAmount());
                        messageContent.setAccountNumber(message.getAccountNumber());
                        messageContent.setUserDocBuy(message.getUserDocBuy());
                        kafkaSender.sendMessage("paymentRequest-bootcoin", messageContent);
                        return Mono.just(message);
                    });
                });
    }

    @Override
    public Mono<Void> processPaymentBootCoin(ConfirmPayment confirmPayment) {
        var operationConfirm =  buyRepository.findByCodeRequestBuy(confirmPayment.getCodeRequestBuy())
                .flatMap(request -> {
                    request.setPaymentStatus(true);
                    return accountService.findAccount(confirmPayment.getUserDocBuy())
                            .map(account -> {
                                account.setBalance(account.getBalance()+confirmPayment.getAmount());
                                return account;
                            })
                            .flatMap(balanceUpdate -> accountService.saveAccount(balanceUpdate))
                            .then(buyRepository.save(request));
                    //return buyRepository.save(request);
                });
        return Mono.just(operationConfirm.block()).then();
    }

    Predicate<String> paymentMethod = (doc)-> doc.equals("yanki") || doc.equals("transfer");
}
