package com.rs.bootcoin.buy.service.cache;

import com.rs.bootcoin.buy.entity.Buy;
import reactor.core.publisher.Mono;

public interface BuyServiceCache {
    Mono<Buy> checkPurchaseStatus(String codeRequestBuy);
}
