package io.github.lucasfreitasrocha.simple_bank.usecase;

import io.github.lucasfreitasrocha.simple_bank.core.exception.HandlerErrorService;
import io.github.lucasfreitasrocha.simple_bank.core.usecase.TransferUseCase;
import io.github.lucasfreitasrocha.simple_bank.core.usecase.UserUseCase;
import io.github.lucasfreitasrocha.simple_bank.dataprovider.client.AuthClient;
import io.github.lucasfreitasrocha.simple_bank.dataprovider.client.dto.out.DataResponseAuthDto;
import io.github.lucasfreitasrocha.simple_bank.dataprovider.client.dto.out.ResponseAuthDto;
import io.github.lucasfreitasrocha.simple_bank.dataprovider.database.entity.AccountEntity;
import io.github.lucasfreitasrocha.simple_bank.dataprovider.database.entity.UserEntity;
import io.github.lucasfreitasrocha.simple_bank.dataprovider.database.entity.UserTypeEntity;
import io.github.lucasfreitasrocha.simple_bank.dataprovider.database.repository.TransferRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

@ExtendWith(MockitoExtension.class)
class TransferUseCaseTest {

    @InjectMocks
    private TransferUseCase service;

    @Mock
    private UserUseCase userUseCase;
    @Mock
    private TransferRepository transferRepository;
    @Mock
    private HandlerErrorService handlerErrorService;
    @Mock
    private AuthClient authClient;

//    @Test
//    void transferValueSucess() {
//
//        TransferDomain domain = new TransferDomain();
//        domain.setValue(BigDecimal.TEN);
//        UserDomain payer = new UserDomain();
//        payer.setId(Long.valueOf("1"));
//        domain.setPayer(payer);
//        UserDomain payee = new UserDomain();
//        payer.setId(Long.valueOf("2"));
//        domain.setPayee(payee);
//        when(userUseCase.findById(Long.valueOf("1"))).thenReturn(getModel(Long.valueOf("1"), UserTypeEntity.PF,
//                BigDecimal.valueOf(100.00)));
//        when(userUseCase.findById(Long.valueOf("2"))).thenReturn(getModel(Long.valueOf("2"), UserTypeEntity.PF,
//                BigDecimal.valueOf(100.00)));
//        when(authClient.getAuth()).thenReturn(getAuthResponse(true));
//        service.transferValue(domain);
//        verify(handlerErrorService, never()).addFieldError("OperationNotPermit", "Saldo insuficiente");
//        verify(handlerErrorService, never()).addFieldError("OperationNotPermit", "você não pode fazer essa operação");
//        verify(transferRepository).save(any());
//    }

    private ResponseAuthDto getAuthResponse(boolean isAuthorization) {
        ResponseAuthDto responseAuthDto = new ResponseAuthDto();
        responseAuthDto.setData(new DataResponseAuthDto(isAuthorization));
        return responseAuthDto;
    }

    public UserEntity getModel(Long id, UserTypeEntity userTypeEntity, BigDecimal value) {
        UserEntity model = new UserEntity();
        model.setType(userTypeEntity);
        AccountEntity accountModel = new AccountEntity(model);
        accountModel.setBalance(value);
        model.setAccount(accountModel);
        model.setId(Long.valueOf("1"));
        return model;
    }
}