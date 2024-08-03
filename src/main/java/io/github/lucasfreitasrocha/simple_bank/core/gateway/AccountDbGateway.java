package io.github.lucasfreitasrocha.simple_bank.core.gateway;

import io.github.lucasfreitasrocha.simple_bank.core.domain.AccountDomain;

public interface AccountDbGateway {

    AccountDomain save(AccountDomain accountDomain);
}
