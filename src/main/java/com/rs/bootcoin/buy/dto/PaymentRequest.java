package com.rs.bootcoin.buy.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PaymentRequest {
    private String codeRequestBuy;
    private Double amount;
    private String accountNumber;
    private String userDocBuy;

}
