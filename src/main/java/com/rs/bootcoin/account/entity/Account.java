package com.rs.bootcoin.account.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "account")
public class Account {
    @Id
    private String id;

    private String documentNumber;
    private String documentType;
    private String phoneNumber;
    private String email;

    @Builder.Default
    private Double balance = 0.0;
}
