package com.rs.bootcoin.account.service;

import com.rs.bootcoin.account.entity.Account;
import reactor.core.publisher.Mono;

public interface AccountService {
    Mono<Account> saveAccount (Account account);
    Mono<Account> findAccount (String accountNumber);
}
