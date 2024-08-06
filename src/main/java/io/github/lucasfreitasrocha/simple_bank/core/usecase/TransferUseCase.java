package io.github.lucasfreitasrocha.simple_bank.core.usecase;

import feign.FeignException;
import io.github.lucasfreitasrocha.simple_bank.core.domain.TransferDomain;
import io.github.lucasfreitasrocha.simple_bank.core.exception.HandlerErrorService;
import io.github.lucasfreitasrocha.simple_bank.core.gateway.TranferGateway;
import io.github.lucasfreitasrocha.simple_bank.core.gateway.TransferDbGateway;
import io.github.lucasfreitasrocha.simple_bank.dataprovider.client.AuthClient;
import io.github.lucasfreitasrocha.simple_bank.dataprovider.database.entity.UserTypeEntity;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
@Slf4j
public class TransferUseCase implements TranferGateway {

    private final UserUseCase userUseCase;
    private final TransferDbGateway repo;
    private final HandlerErrorService handlerErrorService;
    private final AuthClient authClient;

    @Override
    @Transactional
    public TransferDomain transferValue(TransferDomain domain) {
        handlerErrorService.init();
        valaditionPayer(domain);
        if (!getAuth()) {
            userUnauthorized();
        }
        domain = this.repo.save(domain);
        return domain;
    }

    private boolean getAuth() {
        boolean response = false;
        try {
            response = authClient.getAuth().getData().isAuthorization();
        } catch (FeignException e) {
            if (e.status() == 403) {
                userUnauthorized();
            } else {
                log.error("ocorreu um erro ao chamar o autorizado" + e.getMessage());
                e.printStackTrace();
                throw e;
            }

        }
        return response;
    }

    private void userUnauthorized() {
        log.error("usuario não autorizado");
        this.handlerErrorService.addFieldError("unauthorized", "usuario não autorizado");
        this.handlerErrorService.addHttpStatus(HttpStatus.FORBIDDEN);
        this.handlerErrorService.handle();
    }

    private void valaditionPayer(TransferDomain domain) {
        if (domain.getPayer().getOwner().getType().equals(UserTypeEntity.PJ)) {
            handlerErrorService.addFieldError("OperationNotPermit", "você não pode fazer essa operação");
        }
        BigDecimal balance = domain.getPayer().getBalance();
        BigDecimal result = balance.subtract(domain.getValue());
        if (result.compareTo(BigDecimal.ZERO) == -1) {
            handlerErrorService.addFieldError("OperationNotPermit", "Saldo insuficiente");
        } else {
            domain.getPayer().setBalance(result);
            domain.getPayee().setBalance(domain.getPayee().getBalance().add(domain.getValue()));
        }

        handlerErrorService.handle();
    }


}
