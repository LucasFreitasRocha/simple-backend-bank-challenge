package io.github.lucasfreitasrocha.simple_bank.core.gateway;

import io.github.lucasfreitasrocha.simple_bank.core.domain.UserDomain;

public interface UserDbRepository {

    UserDomain save(UserDomain domain);

    UserDomain findById(Long id);
    UserDomain findByEmailOrDocument(String email , String document);
}
