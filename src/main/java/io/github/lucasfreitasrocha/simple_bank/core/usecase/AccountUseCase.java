package io.github.lucasfreitasrocha.simple_bank.core.usecase;

import io.github.lucasfreitasrocha.simple_bank.core.domain.AccountDomain;
import io.github.lucasfreitasrocha.simple_bank.core.domain.UserDomain;
import io.github.lucasfreitasrocha.simple_bank.core.exception.HandlerErrorService;
import io.github.lucasfreitasrocha.simple_bank.core.gateway.AccountDbGateway;
import io.github.lucasfreitasrocha.simple_bank.core.gateway.AccountGateway;
import io.github.lucasfreitasrocha.simple_bank.core.gateway.AuthGateway;
import io.github.lucasfreitasrocha.simple_bank.core.validator.BalanceValidator;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;

@Service
@AllArgsConstructor
public class AccountUseCase implements AccountGateway {


    private final AccountDbGateway dbGateway;
    private final BalanceValidator balanceValidator;
    private final HandlerErrorService handlerErrorService;
    private final AuthGateway authGateway;

    public AccountDomain create(UserDomain domain) {
        return this.dbGateway.save(new AccountDomain(domain));
    }

    @Transactional
    @Override
    public void deposit(AccountDomain domain, BigDecimal value) {
        validateValue(value);
        if (!authGateway.isAuthorized()) {
            balanceValidator.unauthorized(handlerErrorService);
        }
        domain.setBalance(domain.getBalance().add(value));
        dbGateway.save(domain);
    }

    @Transactional
    @Override
    public void withdraw(AccountDomain domain, BigDecimal value) {
        validateValue(value);
        if (!authGateway.isAuthorized()) {
            balanceValidator.unauthorized(handlerErrorService);
        }
        BigDecimal response = domain.getBalance().subtract(value);
        if (response.compareTo(BigDecimal.ZERO) == -1) {
            balanceValidator.insufficientBalance(handlerErrorService);
        }
        handlerErrorService.handle();
        domain.setBalance(response);
        dbGateway.save(domain);
    }

    private void validateValue(BigDecimal value) {
        handlerErrorService.init();
        if (value.compareTo(BigDecimal.ZERO) == -1) {
            handlerErrorService.addError("Não permitido operacao com esse valor");
        }

    }


    @Override
    public AccountDomain find(Long id) {
        handlerErrorService.init();
        AccountDomain domain = dbGateway.find(id);
        if (Objects.isNull(domain)) {
            handlerErrorService.addError("conta %s not found".formatted(id));
            handlerErrorService.addHttpStatus(HttpStatus.NOT_FOUND);
            handlerErrorService.handle();
        }
        return domain;
    }
}
