package io.github.lucasfreitasrocha.simplebank.dataprovider.kafka.producer;

import io.github.lucasfreitasrocha.simplebank.core.domain.TransferDomain;
import io.github.lucasfreitasrocha.simplebank.core.gateway.EventGateway;
import io.github.lucasfreitasrocha.simplebank.entrryPoint.dto.TransferDto;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class KafkaSend implements EventGateway {

    private final KafkaTemplate<String, TransferDto> kafkaTemplate;

    @Override
    public void processTransfer(TransferDomain domain) {
        kafkaTemplate.send("processing-payment", domain.getId().toString(), TransferDto.builder()
                .payer(domain.getPayer().getId())
                .payee(domain.getPayee().getId())
                .value(domain.getValue())
                .build());
    }
}
