package io.github.lucasfreitasrocha.simple_bank.dataprovider.database.db;

import io.github.lucasfreitasrocha.simple_bank.core.domain.AccountDomain;
import io.github.lucasfreitasrocha.simple_bank.core.gateway.AccountDbGateway;
import io.github.lucasfreitasrocha.simple_bank.dataprovider.database.mapper.AccountMapperDb;
import io.github.lucasfreitasrocha.simple_bank.dataprovider.database.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class AccountDbImpl implements AccountDbGateway {
    private final AccountRepository repository;
    private final AccountMapperDb mapper;

    @Override
    public AccountDomain save(AccountDomain accountDomain) {
        return mapper.toDomain(repository.save(mapper.toEntity(accountDomain)));
    }
}
