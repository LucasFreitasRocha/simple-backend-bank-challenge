package io.github.lucasfreitasrocha.simplebank.core.gateway;

import io.github.lucasfreitasrocha.simplebank.core.domain.AccountDomain;

public interface AccountDbGateway {

    AccountDomain save(AccountDomain accountDomain);

    AccountDomain find(Long id);
}
