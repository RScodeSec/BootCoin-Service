package com.rs.bootcoin.buy.service;

import com.rs.bootcoin.buy.entity.TransferP2P;
import com.rs.bootcoin.buy.repository.TransferP2PRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class TransferP2PService {
    @Autowired
    private TransferP2PRepository transferP2PRepository;

    private Mono<TransferP2P> createRequestTransferP2p(TransferP2P transferP2P) {
        return transferP2PRepository.save(transferP2P);
    }

    Mono<TransferP2P> createRequestTransferP2p(String codeRequestBuy){
        return transferP2PRepository.findByCodeRequestBuy(codeRequestBuy)
                .filter(code -> code.getPaymentStatus() != null)
                .flatMap(req -> {
                    req.setTransactionNumber(java.util.UUID.randomUUID().toString());
                    return transferP2PRepository.save(req);
                });
    }
    

}
