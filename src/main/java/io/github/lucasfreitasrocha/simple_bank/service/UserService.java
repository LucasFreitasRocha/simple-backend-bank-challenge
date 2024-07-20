package io.github.lucasfreitasrocha.simple_bank.service;

import io.github.lucasfreitasrocha.simple_bank.dto.in.CreateUserDto;
import io.github.lucasfreitasrocha.simple_bank.dto.out.CreatedUserDto;
import io.github.lucasfreitasrocha.simple_bank.exception.HandlerErrorService;
import io.github.lucasfreitasrocha.simple_bank.model.UserModel;
import io.github.lucasfreitasrocha.simple_bank.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final HandlerErrorService handlerErrorService;

    public CreatedUserDto create(CreateUserDto dto) {


        UserModel model = validationCreate(dto);
        model.setName(dto.getName());
        model.setDocument(dto.getDocument());
        model.setEmail(dto.getEmail());
        model.setType(dto.getType());
        model.setPassword(DigestUtils.sha256Hex(dto.getPassword()));
        model = this.repository.save(model);
        CreatedUserDto response = new CreatedUserDto();
        response.setId(model.getId());
        response.setDocument(model.getEmail());
        response.setName(model.getName());
        response.setEmail(model.getEmail());
        return response;
    }

    private UserModel validationCreate(CreateUserDto dto) {
        handlerErrorService.init();
        UserModel model = repository.findByEmailOrDocument(dto.getEmail(), dto.getDocument());
        if (Objects.nonNull(model)) {
            if (dto.getEmail().equals(model.getEmail())) {
                handlerErrorService.addFieldError("email", "esse email já existe");
            }
            if (dto.getDocument().equals(model.getDocument())) {
                handlerErrorService.addFieldError("document", "esse documento já existe");
            }
        }
        handlerErrorService.handle();
        return new UserModel();
    }
}
