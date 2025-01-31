package io.github.lucasfreitasrocha.simplebank.entrryPoint.worker.service;

import io.github.lucasfreitasrocha.simplebank.core.domain.TransferDomain;
import io.github.lucasfreitasrocha.simplebank.core.gateway.ProcessTransferGateway;
import io.github.lucasfreitasrocha.simplebank.core.gateway.UserGateway;
import io.github.lucasfreitasrocha.simplebank.entrryPoint.dto.TransferDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class HandlerReceivePaymentService {

    private final ProcessTransferGateway processTransferGateway;
    private final UserGateway userGateway;

    public void process(TransferDto transferDto) {
        TransferDomain domain = new TransferDomain();
        domain.setPayer(userGateway.findById(transferDto.getPayer()).getAccount());
        domain.setPayee(userGateway.findById(transferDto.getPayee()).getAccount());
        domain.setValue(transferDto.getValue());
        processTransferGateway.process(domain, transferDto.getIdTransaction());
    }
}
