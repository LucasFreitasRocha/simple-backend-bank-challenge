package io.github.lucasfreitasrocha.simple_bank.entrryPoint.mapper;

import io.github.lucasfreitasrocha.simple_bank.core.domain.UserDomain;
import io.github.lucasfreitasrocha.simple_bank.entrryPoint.dto.in.CreateUserDto;
import io.github.lucasfreitasrocha.simple_bank.entrryPoint.dto.out.CreatedUserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapperEP {

    CreatedUserDto toDtoCreated(UserDomain domain);

    UserDomain toDomain(CreateUserDto dto);
}

