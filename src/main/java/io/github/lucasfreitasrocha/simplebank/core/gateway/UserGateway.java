package io.github.lucasfreitasrocha.simplebank.core.gateway;

import io.github.lucasfreitasrocha.simplebank.core.domain.UserDomain;

public interface UserGateway {
    UserDomain create(UserDomain domain);

    UserDomain findById(Long id);
}
