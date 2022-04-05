package com.rs.bootcoin.buy.message.producer;

import com.rs.bootcoin.buy.dto.PaymentRequest;

public interface KafkaSender {
    void sendMessage (String topic, PaymentRequest paymentRequest);
}
