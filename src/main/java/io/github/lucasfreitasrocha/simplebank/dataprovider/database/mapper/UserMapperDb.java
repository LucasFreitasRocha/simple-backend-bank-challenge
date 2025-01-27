package io.github.lucasfreitasrocha.simplebank.dataprovider.database.mapper;

import io.github.lucasfreitasrocha.simplebank.core.domain.UserDomain;
import io.github.lucasfreitasrocha.simplebank.dataprovider.database.entity.UserEntity;
import org.mapstruct.Context;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapperDb {

    UserDomain toDomain(UserEntity entity, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    @InheritInverseConfiguration
    UserEntity toEntity(UserDomain domain, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);
}
