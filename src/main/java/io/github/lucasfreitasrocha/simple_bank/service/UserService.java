package io.github.lucasfreitasrocha.simple_bank.service;

import io.github.lucasfreitasrocha.simple_bank.dto.in.CreateUserDto;
import io.github.lucasfreitasrocha.simple_bank.dto.out.CreatedUserDto;
import io.github.lucasfreitasrocha.simple_bank.exception.HandlerErrorService;
import io.github.lucasfreitasrocha.simple_bank.model.UserModel;
import io.github.lucasfreitasrocha.simple_bank.model.UserType;
import io.github.lucasfreitasrocha.simple_bank.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final HandlerErrorService handlerErrorService;
    private final AccountService accountService;

    public CreatedUserDto create(CreateUserDto dto) {
        UserModel model = validationCreate(dto);
        model.setName(dto.getName());
        model.setDocument(dto.getDocument());
        model.setEmail(dto.getEmail());
        model.setType(UserType.valueOf(dto.getType()));
        model.setPassword(dto.getPassword());
        model.setAccount(accountService.create(model));
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
        dto.setDocument(dto.getDocument().replaceAll("[^a-z0-9]", ""));
        if(!dto.getType().equals(UserType.PF.toString()) && !dto.getType().equals(UserType.PJ.toString()) ){
            handlerErrorService.addFieldError("type", "informe um tipo valido, PF ou PJ");
        }
        if(!DocumentValidator.isCpf(dto.getDocument()) && !DocumentValidator.isCnpj(dto.getDocument())){
            handlerErrorService.addFieldError("document", "informe um documento valido, cpf ou cnpj");
        }
        if(DocumentValidator.isCpf(dto.getDocument()) && !dto.getType().equals(UserType.PF.toString())){
            handlerErrorService.addFieldError("type/document", "informe o documento cpf de acordo com o tipo PF ");
        }
        if(DocumentValidator.isCnpj(dto.getDocument()) && !dto.getType().equals(UserType.PJ.toString())){
            handlerErrorService.addFieldError("type/document", "informe o documento cnpj de acordo com o tipo PJ");
        }
        if(!EmailValidator.isValidEmail(dto.getEmail())){
            handlerErrorService.addFieldError("email", "informe um email valido");
        }
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
