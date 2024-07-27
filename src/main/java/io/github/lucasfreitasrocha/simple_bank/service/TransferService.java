package io.github.lucasfreitasrocha.simple_bank.service;

import feign.FeignException;
import io.github.lucasfreitasrocha.simple_bank.client.AuthClient;
import io.github.lucasfreitasrocha.simple_bank.dto.TransferDto;
import io.github.lucasfreitasrocha.simple_bank.exception.HandlerErrorService;
import io.github.lucasfreitasrocha.simple_bank.model.TransferModel;
import io.github.lucasfreitasrocha.simple_bank.model.UserModel;
import io.github.lucasfreitasrocha.simple_bank.model.UserType;
import io.github.lucasfreitasrocha.simple_bank.repository.TransferRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
@Slf4j
public class TransferService {

    private final UserService userService;
    private final TransferRepository transferRepository;
    private final HandlerErrorService handlerErrorService;
    private final AuthClient authClient;

    @Transactional
    public TransferDto transferValue(TransferDto transferDto) {
        handlerErrorService.init();
        UserModel payer = userService.findById(transferDto.getPayer());
        UserModel payee = userService.findById(transferDto.getPayee());
        valaditionPayer(payer, transferDto.getValue());
        payee.getAccount().setBalance(payee.getAccount().getBalance().add(transferDto.getValue()));
        TransferModel model = new TransferModel();
        model.setPayer(payer);
        model.setPayee(payee);
        model.setValue(transferDto.getValue());
        if (!getAuth()) {
            userUnauthorized();
        }
        this.transferRepository.save(model);
        return transferDto;
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

    private void valaditionPayer(UserModel payer, BigDecimal value) {
        if (payer.getType().equals(UserType.PJ)) {
            handlerErrorService.addFieldError("OperationNotPermit", "você não pode fazer essa operação");
        }
        BigDecimal balance = payer.getAccount().getBalance();
        BigDecimal result = balance.subtract(value);
        if (result.compareTo(BigDecimal.ZERO) == -1) {
            handlerErrorService.addFieldError("OperationNotPermit", "Saldo insuficiente");
        } else {
            payer.getAccount().setBalance(result);
        }

        handlerErrorService.handle();
    }
}
