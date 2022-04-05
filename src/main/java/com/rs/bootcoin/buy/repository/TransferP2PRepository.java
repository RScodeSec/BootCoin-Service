package com.rs.bootcoin.buy.repository;

import com.rs.bootcoin.buy.entity.TransferP2P;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface TransferP2PRepository extends ReactiveMongoRepository<TransferP2P, String> {

    Mono<TransferP2P> findByCodeRequestBuy(String codeRequest);

}
