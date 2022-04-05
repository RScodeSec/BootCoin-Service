package com.rs.bootcoin.buy.service;

import com.rs.bootcoin.buy.entity.Buy;
import reactor.core.publisher.Mono;

public interface BuyService {
    Mono<Buy> buyRequest(Buy buy);
    Mono<Buy> acceptPurchaseRequest(String codeRequestBuy);
}
