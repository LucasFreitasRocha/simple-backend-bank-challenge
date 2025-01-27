package io.github.lucasfreitasrocha.simplebank.entrryPoint.worker.consumer;

import io.github.lucasfreitasrocha.simplebank.core.domain.TransferDomain;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class PaymentProcessingConsumer {


    @KafkaListener(topics = "processing-payment", groupId = "grupo")
    public void process(TransferDomain domain){
        System.out.println(domain.toString());
    }
}
