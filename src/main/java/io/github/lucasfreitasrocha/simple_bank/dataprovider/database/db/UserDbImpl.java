package io.github.lucasfreitasrocha.simple_bank.dataprovider.database.db;

import io.github.lucasfreitasrocha.simple_bank.core.domain.UserDomain;
import io.github.lucasfreitasrocha.simple_bank.core.gateway.UserDbRepository;
import io.github.lucasfreitasrocha.simple_bank.dataprovider.database.mapper.UserMapperDb;
import io.github.lucasfreitasrocha.simple_bank.dataprovider.database.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserDbImpl implements UserDbRepository {
    private final UserRepository repository;
    private final UserMapperDb mapper;
    @Override
    public UserDomain save(UserDomain domain) {
        return mapper.toDomain(repository.save(mapper.toEntity(domain)));
    }

    @Override
    public UserDomain findById(Long id) {
        return mapper.toDomain(repository.getReferenceById(id));
    }

    @Override
    public UserDomain findByEmailOrDocument(String email, String document) {
        return mapper.toDomain(repository.findByEmailOrDocument(email,document));
    }
}
