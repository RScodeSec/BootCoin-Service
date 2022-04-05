package com.rs.bootcoin.buy.message.producer;

import com.rs.bootcoin.buy.dto.PaymentRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Log4j2
@Service
public class KafkaSenderImpl implements KafkaSender{

    @Autowired
    KafkaTemplate<String, PaymentRequest> kafkaTemplate;

    @Override
    public void sendMessage(String topic, PaymentRequest paymentRequest) {
        ListenableFuture<SendResult<String, PaymentRequest>> future = kafkaTemplate.send(topic, paymentRequest);
        future.addCallback(new ListenableFutureCallback<>() {
            @Override
            public void onFailure(Throwable ex) {
                log.info("fail topic submission" + ex.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, PaymentRequest> result) {
                log.info("successful topic submission");
                System.out.println(result.getProducerRecord().value().getAmount() + " --" +result.getRecordMetadata().topic());
            }
        });

    }
}
