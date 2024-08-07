package io.github.lucasfreitasrocha.simple_bank.core.gateway;

import io.github.lucasfreitasrocha.simple_bank.core.domain.UserDomain;

public interface UserGateway {
    UserDomain create(UserDomain domain);

    UserDomain findById(Long id);
}
