package com.rs.bootcoin.buy.repository;

import com.rs.bootcoin.buy.entity.Buy;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface BuyRepository extends ReactiveMongoRepository<Buy, String> {
    Mono<Buy> findByCodeRequestBuy(String codeRequestBuy);

}
