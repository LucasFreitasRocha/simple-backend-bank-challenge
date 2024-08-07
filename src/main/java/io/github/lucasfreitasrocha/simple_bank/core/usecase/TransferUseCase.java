package io.github.lucasfreitasrocha.simple_bank.core.usecase;

import io.github.lucasfreitasrocha.simple_bank.core.domain.TransferDomain;
import io.github.lucasfreitasrocha.simple_bank.core.exception.HandlerErrorService;
import io.github.lucasfreitasrocha.simple_bank.core.gateway.AuthGateway;
import io.github.lucasfreitasrocha.simple_bank.core.gateway.TranferGateway;
import io.github.lucasfreitasrocha.simple_bank.core.gateway.TransferDbGateway;
import io.github.lucasfreitasrocha.simple_bank.dataprovider.database.entity.UserTypeEntity;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
@Slf4j
public class TransferUseCase implements TranferGateway {


    private final TransferDbGateway repo;
    private final HandlerErrorService handlerErrorService;
    private final BalanceValidator balanceValidator;
    private final AuthGateway authGateway;

    @Override
    @Transactional
    public TransferDomain transferValue(TransferDomain domain) {
        handlerErrorService.init();
        valaditionPayer(domain);
        if (!authGateway.isAuthorized()) {
            balanceValidator.unauthorized(handlerErrorService);
        }
        domain = this.repo.save(domain);
        return domain;
    }


    private void valaditionPayer(TransferDomain domain) {
        if (domain.getPayer().getOwner().getType().equals(UserTypeEntity.PJ)) {
            handlerErrorService.addFieldError("OperationNotPermit", "você não pode fazer essa operação");
        }
        BigDecimal balance = domain.getPayer().getBalance();
        BigDecimal result = balance.subtract(domain.getValue());
        if (result.compareTo(BigDecimal.ZERO) == -1) {
            balanceValidator.insufficientBalance(handlerErrorService);
        } else {
            domain.getPayer().setBalance(result);
            domain.getPayee().setBalance(domain.getPayee().getBalance().add(domain.getValue()));
        }

        handlerErrorService.handle();
    }


}
