package io.github.lucasfreitasrocha.simple_bank.dataprovider.database.mapper;

import io.github.lucasfreitasrocha.simple_bank.core.domain.AccountDomain;
import io.github.lucasfreitasrocha.simple_bank.dataprovider.database.entity.AccountEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapperDb {

    AccountDomain toDomain(AccountEntity entity);
    AccountEntity toEntity(AccountDomain domain);
}
