package io.github.lucasfreitasrocha.simplebank.entrryPoint.worker.consumer;

import io.github.lucasfreitasrocha.simplebank.core.gateway.ProcessTransferGateway;
import io.github.lucasfreitasrocha.simplebank.entrryPoint.dto.TransferDto;
import io.github.lucasfreitasrocha.simplebank.entrryPoint.worker.service.HandlerReceivePaymentService;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class PaymentProcessingConsumer {
    private final HandlerReceivePaymentService handler;

    @KafkaListener(topics = "processing-payment", groupId = "grupo")
    public void process(TransferDto transferDto) {
        handler.process(transferDto);
    }
}
