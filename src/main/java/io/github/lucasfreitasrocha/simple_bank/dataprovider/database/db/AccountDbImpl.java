package io.github.lucasfreitasrocha.simple_bank.dataprovider.database.db;

import io.github.lucasfreitasrocha.simple_bank.core.domain.AccountDomain;
import io.github.lucasfreitasrocha.simple_bank.core.gateway.AccountDbGateway;
import io.github.lucasfreitasrocha.simple_bank.dataprovider.database.entity.AccountEntity;
import io.github.lucasfreitasrocha.simple_bank.dataprovider.database.mapper.AccountMapperDb;
import io.github.lucasfreitasrocha.simple_bank.dataprovider.database.mapper.CycleAvoidingMappingContext;
import io.github.lucasfreitasrocha.simple_bank.dataprovider.database.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class AccountDbImpl implements AccountDbGateway {
    private final AccountRepository repository;
    private final AccountMapperDb mapper;
    private final CycleAvoidingMappingContext context;

    @Override
    public AccountDomain save(AccountDomain accountDomain) {
        AccountEntity entity = repository.save(mapper.toEntity(accountDomain, context));
        return mapper.toDomain(entity, context);
    }

    @Override
    public AccountDomain find(Long id) {
        return mapper.toDomain(repository.getReferenceById(id), context);
    }


}
