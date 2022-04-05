package com.rs.bootcoin.buy.message.consumer;

import com.rs.bootcoin.buy.dto.ConfirmPayment;
import com.rs.bootcoin.buy.service.BuyService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Log4j2
@Component
public class KafkaConfirmPayment {


    @Autowired
    BuyService buyService;

    @KafkaListener(id = "confirmPayBoot", topics = "paymentConfirm-bootcoin", groupId = "groupId2", containerFactory = "factory")
    public Mono<Void> listenTopic(ConfirmPayment confirmPayment) {
        if(confirmPayment.getCodeRequestBuy() !=null){
            return buyService.processPaymentBootCoin(confirmPayment);
        }
        else {
            log.info("We were unable to process your payment request");
        }
        return Mono.empty();
    }
}
