package com.rs.bootcoin.buy.service.cache;

import com.rs.bootcoin.buy.entity.Buy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.redis.core.ReactiveHashOperations;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@ConditionalOnProperty(name = "cache.enabled", havingValue = "true")
public class BuyServiceCacheImpl extends BuyServiceNoCacheImpl {

    private static final String KEY = "bootCoin";

    @Autowired
    private ReactiveHashOperations<String, String, Buy> hashOperations;

    @Override
    public Mono<Buy> checkPurchaseStatus(String codeRequestBuy) {
        return hashOperations.get(KEY, codeRequestBuy)
                .switchIfEmpty(this.findByOperationNumberOfDBandCache(codeRequestBuy));
    }

    /**
     * cache always when the payment has been confirmed because it will no longer be modified
     * @param opNumber String unique code
     * @return cache -> Buy
     */
    public Mono<Buy> findByOperationNumberOfDBandCache(String opNumber) {
        return super.checkPurchaseStatus(opNumber)
                .filter(status -> status.getPaymentStatus().equals(true))
                .flatMap(dto -> this.hashOperations.put(KEY, opNumber, dto).thenReturn(dto));

    }

}
