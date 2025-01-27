package io.github.lucasfreitasrocha.simplebank.entrryPoint.api.mapper;

import io.github.lucasfreitasrocha.simplebank.core.domain.UserDomain;
import io.github.lucasfreitasrocha.simplebank.entrryPoint.dto.in.CreateUserDto;
import io.github.lucasfreitasrocha.simplebank.entrryPoint.dto.out.CreatedUserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapperEP {

    CreatedUserDto toDtoCreated(UserDomain domain);

    UserDomain toDomain(CreateUserDto dto);
}

