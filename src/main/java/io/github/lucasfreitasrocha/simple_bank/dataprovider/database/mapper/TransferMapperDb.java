package io.github.lucasfreitasrocha.simple_bank.dataprovider.database.mapper;

import io.github.lucasfreitasrocha.simple_bank.core.domain.TransferDomain;
import io.github.lucasfreitasrocha.simple_bank.dataprovider.database.entity.TransferEntity;
import org.mapstruct.Context;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransferMapperDb {

    TransferDomain toDomain(TransferEntity entity, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    @InheritInverseConfiguration
    TransferEntity toEntity(TransferDomain domain, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);
}
