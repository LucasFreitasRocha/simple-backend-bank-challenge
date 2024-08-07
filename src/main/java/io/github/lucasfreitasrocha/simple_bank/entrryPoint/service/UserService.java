package io.github.lucasfreitasrocha.simple_bank.entrryPoint.service;

import io.github.lucasfreitasrocha.simple_bank.core.domain.UserDomain;
import io.github.lucasfreitasrocha.simple_bank.core.gateway.UserGateway;
import io.github.lucasfreitasrocha.simple_bank.entrryPoint.dto.in.CreateUserDto;
import io.github.lucasfreitasrocha.simple_bank.entrryPoint.dto.out.CreatedUserDto;
import io.github.lucasfreitasrocha.simple_bank.entrryPoint.dto.out.UserDto;
import io.github.lucasfreitasrocha.simple_bank.entrryPoint.mapper.UserMapperEP;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class UserService {
    private final UserGateway gateway;
    private final UserMapperEP mapper;


    public CreatedUserDto create(CreateUserDto dto) {
        return mapper.toDtoCreated(gateway.create(mapper.toDomain(dto)));
    }

    public UserDto getUser(Long id) {
        UserDomain domain = gateway.findById(id);
        UserDto response = new UserDto();
        response.setDocument(domain.getDocument());
        response.setId(domain.getId());
        response.setEmail(domain.getEmail());
        response.setName(domain.getName());
        response.setReceipts(new ArrayList<>());
        response.setPayments(new ArrayList<>());
        return response;
    }
}
