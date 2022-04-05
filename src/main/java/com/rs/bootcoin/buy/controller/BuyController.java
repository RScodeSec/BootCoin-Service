package com.rs.bootcoin.buy.controller;

import com.rs.bootcoin.buy.entity.Buy;
import com.rs.bootcoin.buy.service.BuyService;
import com.rs.bootcoin.buy.service.cache.BuyServiceCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/request")
public class BuyController {

    @Autowired
    private BuyService buyService;

    @Autowired
    private BuyServiceCache buyServiceCache;

    @PostMapping("/create")
    public Mono<Buy> createBuyRequest(@RequestBody Buy buy){
        return buyService.buyRequest(buy);
    }

    @GetMapping("/accept/{requestCode}")
    public Mono<Buy> acceptPurchaseRequest(@PathVariable("requestCode") String requestCode){
        return buyService.acceptPurchaseRequest(requestCode);
    }
    @GetMapping("/status/{transactionNumber}")
    public Mono<Buy> checkPurchaseStatus(@PathVariable("transactionNumber") String transactionNumber) {
        return buyServiceCache.checkPurchaseStatus(transactionNumber);
    }

}
