package com.rs.bootcoin.account.controller;

import com.rs.bootcoin.account.entity.Account;
import com.rs.bootcoin.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/bootcoin")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/save")
    public Mono<Account> createAccount(@RequestBody Account account){
        return accountService.saveAccount(account);
    }
}
