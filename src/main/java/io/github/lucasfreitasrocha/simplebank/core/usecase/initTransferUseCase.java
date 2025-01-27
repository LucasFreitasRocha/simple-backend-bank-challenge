package io.github.lucasfreitasrocha.simplebank.core.usecase;

import io.github.lucasfreitasrocha.simplebank.core.domain.TransferDomain;
import io.github.lucasfreitasrocha.simplebank.core.exception.HandlerErrorService;
import io.github.lucasfreitasrocha.simplebank.core.gateway.AuthGateway;
import io.github.lucasfreitasrocha.simplebank.core.gateway.EventGateway;
import io.github.lucasfreitasrocha.simplebank.core.gateway.InitTranferGateway;
import io.github.lucasfreitasrocha.simplebank.core.gateway.TransferDbGateway;
import io.github.lucasfreitasrocha.simplebank.core.validator.BalanceValidator;
import io.github.lucasfreitasrocha.simplebank.dataprovider.database.entity.UserTypeEntity;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class initTransferUseCase implements InitTranferGateway {


    private final TransferDbGateway repo;
    private final HandlerErrorService handlerErrorService;
    private final BalanceValidator balanceValidator;
    private final AuthGateway authGateway;
    private final EventGateway eventGateway;

    @Override
    @Transactional
    public TransferDomain initTrasnfer(TransferDomain domain) {
        handlerErrorService.init();
        validationTypePayer(domain);
//        if (!authGateway.isAuthorized()) {
//            balanceValidator.unauthorized(handlerErrorService);
//        }
        domain = this.repo.save(domain);
        eventGateway.processTransfer(domain);
        return domain;
    }


    private void validationTypePayer(TransferDomain domain) {
        if (domain.getPayer().getOwner().getType().equals(UserTypeEntity.PJ)) {
            handlerErrorService.addFieldError("OperationNotPermit", "você não pode fazer essa operação");
        }
//        BigDecimal balance = domain.getPayer().getBalance();
//        BigDecimal result = balance.subtract(domain.getValue());
//        if (result.compareTo(BigDecimal.ZERO) == -1) {
//            balanceValidator.insufficientBalance(handlerErrorService);
//        } else {
//            domain.getPayer().setBalance(result);
//            domain.getPayee().setBalance(domain.getPayee().getBalance().add(domain.getValue()));
//        }

        handlerErrorService.handle();
    }


}
