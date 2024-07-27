package io.github.lucasfreitasrocha.simple_bank.service;

import io.github.lucasfreitasrocha.simple_bank.client.AuthClient;
import io.github.lucasfreitasrocha.simple_bank.client.dto.out.DataResponseAuthDto;
import io.github.lucasfreitasrocha.simple_bank.client.dto.out.ResponseAuthDto;
import io.github.lucasfreitasrocha.simple_bank.dto.TransferDto;
import io.github.lucasfreitasrocha.simple_bank.exception.HandlerErrorService;
import io.github.lucasfreitasrocha.simple_bank.model.AccountModel;
import io.github.lucasfreitasrocha.simple_bank.model.UserModel;
import io.github.lucasfreitasrocha.simple_bank.model.UserType;
import io.github.lucasfreitasrocha.simple_bank.repository.TransferRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransferServiceTest {

    @InjectMocks
    private TransferService service;

    @Mock
    private  UserService userService;
    @Mock
    private  TransferRepository transferRepository;
    @Mock
    private  HandlerErrorService handlerErrorService;
    @Mock
    private  AuthClient authClient;

    @Test
    void transferValueSucess() {

        TransferDto transferDto = new TransferDto();
        transferDto.setValue(BigDecimal.TEN);
        transferDto.setPayer(Long.valueOf("1"));
        transferDto.setPayee(Long.valueOf("2"));
        when(userService.findById(Long.valueOf("1"))).thenReturn(getModel(Long.valueOf("1"), UserType.PF,
                BigDecimal.valueOf(100.00)));
        when(userService.findById(Long.valueOf("2"))).thenReturn(getModel(Long.valueOf("2"), UserType.PF,
                BigDecimal.valueOf(100.00)));
        when(authClient.getAuth()).thenReturn(getAuthResponse(true));
        service.transferValue(transferDto);
        verify(handlerErrorService, never()).addFieldError("OperationNotPermit", "Saldo insuficiente");
        verify(handlerErrorService, never()).addFieldError("OperationNotPermit", "você não pode fazer essa operação");
        verify(transferRepository).save(any());
    }

    private ResponseAuthDto getAuthResponse(boolean isAuthorization) {
        ResponseAuthDto responseAuthDto = new ResponseAuthDto();
        responseAuthDto.setData(new DataResponseAuthDto(isAuthorization));
        return responseAuthDto;
    }

    public UserModel getModel(Long id, UserType userType, BigDecimal value){
        UserModel model = new UserModel();
        model.setType(userType);
        AccountModel accountModel =  new AccountModel(model);
        accountModel.setBalance(value);
        model.setAccount(accountModel);
        model.setId(Long.valueOf("1"));
        return model;
    }
}