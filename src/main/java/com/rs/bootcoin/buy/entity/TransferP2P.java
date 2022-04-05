package com.rs.bootcoin.buy.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "p2p")
public class TransferP2P {
    @Id
    private String idP2P;

    private String userDocBuyer;
    private String userDocSeller;
    private Double amount;
    //private String methodPayment;
    //private String accountNumber;
    @Builder.Default
    private Boolean requestStatus = false;

    @Builder.Default
    private Boolean paymentStatus = false;

    @Builder.Default
    private String codeRequestBuy = java.util.UUID.randomUUID().toString();

    private String transactionNumber;

}
