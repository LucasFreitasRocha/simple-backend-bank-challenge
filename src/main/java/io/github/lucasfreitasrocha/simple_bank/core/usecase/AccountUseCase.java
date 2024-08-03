package io.github.lucasfreitasrocha.simple_bank.core.usecase;

import io.github.lucasfreitasrocha.simple_bank.core.domain.AccountDomain;
import io.github.lucasfreitasrocha.simple_bank.core.domain.UserDomain;
import io.github.lucasfreitasrocha.simple_bank.core.gateway.AccountDbGateway;
import io.github.lucasfreitasrocha.simple_bank.dataprovider.database.entity.AccountEntity;
import io.github.lucasfreitasrocha.simple_bank.dataprovider.database.entity.UserEntity;
import io.github.lucasfreitasrocha.simple_bank.dataprovider.database.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountUseCase {

    private final AccountDbGateway dbGateway;
    public AccountDomain create(UserDomain domain){
        return this.dbGateway.save(new AccountDomain(domain));
    }
}
