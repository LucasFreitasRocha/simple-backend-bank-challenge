package io.github.lucasfreitasrocha.simple_bank.dataprovider.database.mapper;

import io.github.lucasfreitasrocha.simple_bank.core.domain.UserDomain;
import io.github.lucasfreitasrocha.simple_bank.dataprovider.database.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapperDb {

    UserDomain toDomain(UserEntity entity);
    UserEntity toEntity(UserDomain domain);
}
