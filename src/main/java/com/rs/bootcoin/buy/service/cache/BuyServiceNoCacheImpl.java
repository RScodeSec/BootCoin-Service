package com.rs.bootcoin.buy.service.cache;

import com.rs.bootcoin.buy.entity.Buy;
import com.rs.bootcoin.buy.repository.BuyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@ConditionalOnProperty(name = "cache.enabled", havingValue = "false")
public class BuyServiceNoCacheImpl implements BuyServiceCache{

    @Autowired
    private BuyRepository buyRepository;

    @Override
    public Mono<Buy> checkPurchaseStatus(String codeRequestBuy) {
        return buyRepository.findByCodeRequestBuy(codeRequestBuy);
    }
}
