package io.github.lucasfreitasrocha.simplebank.dataprovider.database.db;

import io.github.lucasfreitasrocha.simplebank.core.domain.UserDomain;
import io.github.lucasfreitasrocha.simplebank.core.gateway.UserDbRepository;
import io.github.lucasfreitasrocha.simplebank.dataprovider.database.entity.UserEntity;
import io.github.lucasfreitasrocha.simplebank.dataprovider.database.mapper.CycleAvoidingMappingContext;
import io.github.lucasfreitasrocha.simplebank.dataprovider.database.mapper.UserMapperDb;
import io.github.lucasfreitasrocha.simplebank.dataprovider.database.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserDbImpl implements UserDbRepository {
    private final UserRepository repository;
    private final UserMapperDb mapper;
    private final CycleAvoidingMappingContext context;

    @Override
    public UserDomain save(UserDomain domain) {
        return mapper.toDomain(repository.save(mapper.toEntity(domain, context)), context);
    }


    @Override
    public UserDomain findById(Long id) {
        UserEntity entity = repository.getReferenceById(id);
        return mapper.toDomain(entity, context);
    }

    @Override
    public UserDomain findByEmailOrDocument(String email, String document) {
        return mapper.toDomain(repository.findByEmailOrDocument(email, document), context);
    }


}
