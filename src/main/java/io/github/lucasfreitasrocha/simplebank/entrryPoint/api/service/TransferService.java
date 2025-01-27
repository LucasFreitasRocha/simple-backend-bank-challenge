package io.github.lucasfreitasrocha.simplebank.entrryPoint.api.service;

import io.github.lucasfreitasrocha.simplebank.core.domain.TransferDomain;
import io.github.lucasfreitasrocha.simplebank.core.gateway.InitTranferGateway;
import io.github.lucasfreitasrocha.simplebank.core.gateway.UserGateway;
import io.github.lucasfreitasrocha.simplebank.entrryPoint.dto.TransferDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TransferService {

    private final InitTranferGateway initTranferGateway;
    private final UserGateway userGateway;


    public TransferDto transferValue(TransferDto transferDto) {
        makeTransfer(transferDto);
        return transferDto;
    }

    private void makeTransfer(TransferDto transferDto) {
        TransferDomain domain = new TransferDomain();
        domain.setPayer(userGateway.findById(transferDto.getPayer()).getAccount());
        domain.setPayee(userGateway.findById(transferDto.getPayee()).getAccount());
        domain.setValue(transferDto.getValue());
        initTranferGateway.initTrasnfer(domain);
    }
}
