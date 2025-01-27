package io.github.lucasfreitasrocha.simplebank.core.gateway;

import io.github.lucasfreitasrocha.simplebank.core.domain.UserDomain;

public interface UserDbRepository {

    UserDomain save(UserDomain domain);

    UserDomain findById(Long id);

    UserDomain findByEmailOrDocument(String email, String document);
}
