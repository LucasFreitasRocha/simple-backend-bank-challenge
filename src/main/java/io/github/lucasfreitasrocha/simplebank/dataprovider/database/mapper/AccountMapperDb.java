package io.github.lucasfreitasrocha.simplebank.dataprovider.database.mapper;

import io.github.lucasfreitasrocha.simplebank.core.domain.AccountDomain;
import io.github.lucasfreitasrocha.simplebank.dataprovider.database.entity.AccountEntity;
import org.mapstruct.Context;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapperDb {
    AccountDomain toDomain(AccountEntity entity, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    @InheritInverseConfiguration
    AccountEntity toEntity(AccountDomain domain, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);
}
