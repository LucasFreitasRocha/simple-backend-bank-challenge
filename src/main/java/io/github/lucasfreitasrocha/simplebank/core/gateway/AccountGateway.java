package io.github.lucasfreitasrocha.simplebank.core.gateway;

import io.github.lucasfreitasrocha.simplebank.core.domain.AccountDomain;

import java.math.BigDecimal;

public interface AccountGateway {

    void deposit(AccountDomain domain, BigDecimal value);

    void withdraw(AccountDomain domain, BigDecimal value);

    AccountDomain find(Long id);
}
