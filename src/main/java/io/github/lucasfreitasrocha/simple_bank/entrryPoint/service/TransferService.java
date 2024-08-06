package io.github.lucasfreitasrocha.simple_bank.entrryPoint.service;

import io.github.lucasfreitasrocha.simple_bank.core.domain.TransferDomain;
import io.github.lucasfreitasrocha.simple_bank.core.gateway.TranferGateway;
import io.github.lucasfreitasrocha.simple_bank.core.gateway.UserGateway;
import io.github.lucasfreitasrocha.simple_bank.entrryPoint.dto.TransferDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TransferService {

    private final TranferGateway tranferGateway;
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
        tranferGateway.transferValue(domain);
    }
}
