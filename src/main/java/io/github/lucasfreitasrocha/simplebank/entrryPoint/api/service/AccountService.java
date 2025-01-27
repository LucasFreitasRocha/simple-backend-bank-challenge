package io.github.lucasfreitasrocha.simplebank.entrryPoint.api.service;

import io.github.lucasfreitasrocha.simplebank.core.gateway.AccountGateway;
import io.github.lucasfreitasrocha.simplebank.entrryPoint.dto.in.BalanceDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountService {

    private final AccountGateway gateway;


    public void deposit(Long id, BalanceDto balanceDto) {
        gateway.deposit(gateway.find(id), balanceDto.value());
    }

    public void withdraw(Long id, BalanceDto balanceDto) {
        gateway.withdraw(gateway.find(id), balanceDto.value());
    }
}
