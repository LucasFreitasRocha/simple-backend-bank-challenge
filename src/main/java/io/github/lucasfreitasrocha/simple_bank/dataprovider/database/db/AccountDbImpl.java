package io.github.lucasfreitasrocha.simple_bank.dataprovider.database.db;

import io.github.lucasfreitasrocha.simple_bank.core.domain.AccountDomain;
import io.github.lucasfreitasrocha.simple_bank.core.gateway.AccountDbGateway;
import io.github.lucasfreitasrocha.simple_bank.dataprovider.database.entity.AccountEntity;
import io.github.lucasfreitasrocha.simple_bank.dataprovider.database.entity.UserEntity;
import io.github.lucasfreitasrocha.simple_bank.dataprovider.database.entity.UserTypeEntity;
import io.github.lucasfreitasrocha.simple_bank.dataprovider.database.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class AccountDbImpl implements AccountDbGateway {
    private final AccountRepository repository;


    @Override
    public AccountDomain save(AccountDomain accountDomain) {
       AccountEntity entity = repository.save(toEntity(accountDomain));
       return toDomain(entity,accountDomain);
    }

    private AccountDomain toDomain(AccountEntity entity, AccountDomain domain) {
        domain.setId(entity.getId());
        domain.getOwner().setId(entity.getOwner().getId());
        domain.setBalance(entity.getBalance());
        return domain;
    }

    private AccountEntity toEntity(AccountDomain accountDomain) {
        UserEntity userEntity = new UserEntity();
        userEntity.setName(accountDomain.getOwner().getName());
        userEntity.setEmail(accountDomain.getOwner().getEmail());
        userEntity.setDocument(accountDomain.getOwner().getDocument());
        userEntity.setType(UserTypeEntity.getFromName(accountDomain.getOwner().getType().toString()));
        userEntity.setPassword(accountDomain.getOwner().getPassword());
       return new AccountEntity(userEntity);
    }
}
