package com.rs.bootcoin.account.repository;

import com.rs.bootcoin.account.entity.Account;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface AccountRepository extends ReactiveMongoRepository<Account, String> {
    Mono<Account> findByDocumentNumber(String document);
}
