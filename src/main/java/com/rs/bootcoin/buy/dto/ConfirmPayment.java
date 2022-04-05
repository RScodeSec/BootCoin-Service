package com.rs.bootcoin.buy.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConfirmPayment {
    private String codeRequestBuy;
    private Double amount;
    private String accountNumber;
    private String userDocBuy;
}
