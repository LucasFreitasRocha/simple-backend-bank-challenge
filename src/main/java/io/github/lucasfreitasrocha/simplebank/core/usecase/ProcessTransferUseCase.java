package io.github.lucasfreitasrocha.simplebank.core.usecase;

import io.github.lucasfreitasrocha.simplebank.core.domain.TransferDomain;
import io.github.lucasfreitasrocha.simplebank.core.exception.HandlerErrorService;
import io.github.lucasfreitasrocha.simplebank.core.gateway.AuthGateway;
import io.github.lucasfreitasrocha.simplebank.core.gateway.ProcessTransferGateway;
import io.github.lucasfreitasrocha.simplebank.core.gateway.TransferDbGateway;
import io.github.lucasfreitasrocha.simplebank.core.validator.CommonValidator;
import io.github.lucasfreitasrocha.simplebank.core.validator.TransferValidator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;


@Component
@RequiredArgsConstructor
public class ProcessTransferUseCase implements ProcessTransferGateway {

    private final TransferDbGateway repo;
    private final CommonValidator commonValidator;
    private final AuthGateway authGateway;
    private final HandlerErrorService handlerErrorService;
    private final TransferValidator validator;


    @Transactional
    @Override
    public void process(TransferDomain domain, String idTransaction) {
        if (!authGateway.isAuthorized()) {
            commonValidator.unauthorized();
        }
       domain = validator.validateIntegrationTransfer(domain, idTransaction);
        BigDecimal balance = domain.getPayer().getBalance();
        BigDecimal result = balance.subtract(domain.getValue());
        if (result.compareTo(BigDecimal.ZERO) == -1) {
            commonValidator.insufficientBalance(handlerErrorService);
        } else {
            domain.getPayer().setBalance(result);
            domain.getPayee().setBalance(domain.getPayee().getBalance().add(domain.getValue()));
        }

    }
}
