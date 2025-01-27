package io.github.lucasfreitasrocha.simplebank.dataprovider.kafka.producer;

import io.github.lucasfreitasrocha.simplebank.core.domain.TransferDomain;
import io.github.lucasfreitasrocha.simplebank.core.gateway.EventGateway;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class KafkaSend implements EventGateway {

    private final KafkaTemplate<String, TransferDomain> kafkaTemplate;

    @Override
    public void processTransfer(TransferDomain domain) {
        kafkaTemplate.send("processing-payment", domain.getId().toString(), domain);
    }
}
