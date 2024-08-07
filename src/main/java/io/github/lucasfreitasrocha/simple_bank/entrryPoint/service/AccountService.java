package io.github.lucasfreitasrocha.simple_bank.entrryPoint.service;

import io.github.lucasfreitasrocha.simple_bank.core.gateway.AccountGateway;
import io.github.lucasfreitasrocha.simple_bank.entrryPoint.dto.in.BalanceDto;
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
