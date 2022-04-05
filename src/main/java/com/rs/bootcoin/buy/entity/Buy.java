package com.rs.bootcoin.buy.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "request-buy")
public class Buy {
    @Id
    private String idBuy;

    private String userDocBuy;
    private Double amount;
    private String methodPayment;
    private String accountNumber;

    @Builder.Default
    private Boolean requestStatus = false;

    @Builder.Default
    private Boolean paymentStatus = false;

    @Builder.Default
    private String codeRequestBuy = java.util.UUID.randomUUID().toString();

    private String transactionNumber;

}
