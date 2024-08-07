package io.github.lucasfreitasrocha.simple_bank.core.gateway;

import io.github.lucasfreitasrocha.simple_bank.core.domain.AccountDomain;

import java.math.BigDecimal;

public interface AccountGateway {

    void deposit(AccountDomain domain, BigDecimal value);

    void withdraw(AccountDomain domain, BigDecimal value);

    AccountDomain find(Long id);
}
