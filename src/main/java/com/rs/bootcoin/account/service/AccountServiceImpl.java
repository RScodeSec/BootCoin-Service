package com.rs.bootcoin.account.service;

import com.rs.bootcoin.account.entity.Account;
import com.rs.bootcoin.account.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.function.Predicate;

@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Mono<Account> saveAccount (Account account) {
        if(documentAccept.test(account.getDocumentType())){
            return accountRepository.save(account);
        }
        return Mono.empty();
    }

    @Override
    public Mono<Account> findAccount(String accountNumber) {
        return accountRepository.findByDocumentNumber(accountNumber);
    }

    Predicate<String> documentAccept = (doc)-> doc.equals("DNI") || doc.equals("CEX") || doc.equals("PASAPORTE");

}
