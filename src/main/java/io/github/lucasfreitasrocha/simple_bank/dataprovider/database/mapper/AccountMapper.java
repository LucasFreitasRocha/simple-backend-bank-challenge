package io.github.lucasfreitasrocha.simple_bank.dataprovider.database.mapper;

import io.github.lucasfreitasrocha.simple_bank.core.domain.AccountDomain;
import io.github.lucasfreitasrocha.simple_bank.core.domain.UserDomain;
import io.github.lucasfreitasrocha.simple_bank.dataprovider.database.entity.AccountEntity;
import io.github.lucasfreitasrocha.simple_bank.dataprovider.database.entity.UserEntity;

public class AccountMapper {

    public static AccountDomain toDomain(AccountEntity entity) {
        AccountDomain accountDomain = new AccountDomain();
        accountDomain.setId(entity.getId());
        accountDomain.setBalance(entity.getBalance());

        if (entity.getOwner() != null) {
            UserDomain userDomain = new UserDomain();
            userDomain.setId(entity.getOwner().getId());
            userDomain.setAccount(accountDomain);
            accountDomain.setOwner(userDomain);
        }
        return accountDomain;
    }

    public static AccountEntity toEntity(AccountDomain domain) {
        if (domain == null) {
            return null;
        }
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setId(domain.getId());
        accountEntity.setBalance(domain.getBalance());
        UserEntity userEntity = new UserEntity();
        userEntity.setAccount(accountEntity);
        userEntity.setId(domain.getOwner().getId());
        accountEntity.setOwner(userEntity);
        return accountEntity;
    }


}
