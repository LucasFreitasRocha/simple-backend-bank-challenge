package io.github.lucasfreitasrocha.simple_bank.dataprovider.database.mapper;

import io.github.lucasfreitasrocha.simple_bank.core.domain.TransferDomain;
import io.github.lucasfreitasrocha.simple_bank.dataprovider.database.entity.TransferEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransferMapperDb {

    TransferDomain toDomain(TransferEntity entity);
    TransferEntity toEntity(TransferDomain domain);
}
