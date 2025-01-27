package io.github.lucasfreitasrocha.simplebank.core.usecase;

import io.github.lucasfreitasrocha.simplebank.core.domain.UserDomain;
import io.github.lucasfreitasrocha.simplebank.core.domain.UserTypeDomain;
import io.github.lucasfreitasrocha.simplebank.core.exception.HandlerErrorService;
import io.github.lucasfreitasrocha.simplebank.core.gateway.UserDbRepository;
import io.github.lucasfreitasrocha.simplebank.core.gateway.UserGateway;
import io.github.lucasfreitasrocha.simplebank.core.validator.DocumentValidator;
import io.github.lucasfreitasrocha.simplebank.core.validator.EmailValidator;
import lombok.AllArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class UserUseCase implements UserGateway {

    private final UserDbRepository repository;
    private final HandlerErrorService handlerErrorService;
    private final AccountUseCase accountUseCase;

    @Override
    public UserDomain create(UserDomain domain) {
        validationCreate(domain);
        domain.setPassword(DigestUtils.sha256Hex(domain.getPassword()));
        domain.setAccount(accountUseCase.create(domain));
        domain.setId(domain.getAccount().getOwner().getId());
        return domain;
    }

    @Override
    public UserDomain findById(Long userId) {
        handlerErrorService.init();
        UserDomain domain = this.repository.findById(userId);
        if (Objects.isNull(domain)) {
            handlerErrorService.addError("user %s not found".formatted(userId));
            handlerErrorService.addHttpStatus(HttpStatus.NOT_FOUND);
        }
        handlerErrorService.handle();
        return domain;
    }


    private void validationCreate(UserDomain domain) {
        handlerErrorService.init();
        domain.setDocument(domain.getDocument().replaceAll("[^a-z0-9]", ""));
        if (!domain.getType().equals(UserTypeDomain.PF) && !domain.getType().equals(UserTypeDomain.PJ)) {
            handlerErrorService.addFieldError("type", "informe um tipo valido, PF ou PJ");
        }
        if (!DocumentValidator.isCpf(domain.getDocument()) && !DocumentValidator.isCnpj(domain.getDocument())) {
            handlerErrorService.addFieldError("document", "informe um documento valido, cpf ou cnpj");
        }
        if (DocumentValidator.isCpf(domain.getDocument()) && !domain.getType().equals(UserTypeDomain.PF)) {
            handlerErrorService.addFieldError("type/document", "informe o documento cpf de acordo com o tipo PF");
        }
        if (DocumentValidator.isCnpj(domain.getDocument()) && !domain.getType().equals(UserTypeDomain.PJ)) {
            handlerErrorService.addFieldError("type/document", "informe o documento cnpj de acordo com o tipo PJ");
        }
        if (!EmailValidator.isValidEmail(domain.getEmail())) {
            handlerErrorService.addFieldError("email", "informe um email valido");
        }
        UserDomain model = repository.findByEmailOrDocument(domain.getEmail(), domain.getDocument());
        if (Objects.nonNull(model)) {
            if (domain.getEmail().equals(model.getEmail())) {
                handlerErrorService.addFieldError("email", "esse email já existe");
            }
            if (domain.getDocument().equals(model.getDocument())) {
                handlerErrorService.addFieldError("document", "esse documento já existe");
            }
        }
        handlerErrorService.handle();
    }

    public UserDomain getUser(Long id) {
        return this.findById(id);
    }


}
