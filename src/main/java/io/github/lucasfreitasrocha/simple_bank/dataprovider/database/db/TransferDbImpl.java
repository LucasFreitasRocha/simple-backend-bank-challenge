package io.github.lucasfreitasrocha.simple_bank.dataprovider.database.db;

import io.github.lucasfreitasrocha.simple_bank.core.domain.TransferDomain;
import io.github.lucasfreitasrocha.simple_bank.core.domain.UserDomain;
import io.github.lucasfreitasrocha.simple_bank.core.domain.UserTypeDomain;
import io.github.lucasfreitasrocha.simple_bank.core.gateway.TransferDbGateway;
import io.github.lucasfreitasrocha.simple_bank.dataprovider.database.entity.TransferEntity;
import io.github.lucasfreitasrocha.simple_bank.dataprovider.database.entity.UserEntity;
import io.github.lucasfreitasrocha.simple_bank.dataprovider.database.entity.UserTypeEntity;
import io.github.lucasfreitasrocha.simple_bank.dataprovider.database.mapper.AccountMapper;
import io.github.lucasfreitasrocha.simple_bank.dataprovider.database.mapper.TransferMapper;
import io.github.lucasfreitasrocha.simple_bank.dataprovider.database.repository.TransferRepository;
import jakarta.persistence.Entity;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class TransferDbImpl implements TransferDbGateway {

    private final TransferRepository repository;

    @Override
    @Transactional
    public TransferDomain save(TransferDomain domain) {
        return getDomain(repository.save(getEntity(domain)));
    }

    public  TransferDomain getDomain(TransferEntity entity) {
        if ( entity == null ) {
            return null;
        }


        TransferDomain transferDomain = new TransferDomain();
        transferDomain.setId(entity.getId());
        transferDomain.setPayer( getUser(entity.getPayer()));
        transferDomain.setPayee( getUser(entity.getPayee()) );
        transferDomain.setValue( entity.getValue() );

        return transferDomain;
    }

    private  UserDomain getUser(UserEntity user) {
        UserDomain userDomain = new UserDomain();
        userDomain.setId( user.getId() );
        userDomain.setName( user.getName() );
        userDomain.setType( UserTypeDomain.valueOf(user.getType().toString()) );
        userDomain.setDocument( user.getDocument() );
        userDomain.setEmail( user.getEmail() );
        userDomain.setPassword( user.getPassword() );
        userDomain.setAccount( AccountMapper.toDomain( user.getAccount() ) );
        return userDomain;
    }
    public  TransferEntity getEntity(TransferDomain domain) {
        if ( domain == null ) {
            return null;
        }

        TransferEntity transferEntity = new TransferEntity();

        UserEntity payer = userDomainToUserEntity( domain.getPayer() );
        UserEntity payee = userDomainToUserEntity( domain.getPayee() );
        payer.getAccount().setBalance(domain.getPayer().getAccount().getBalance());
        payee.getAccount().setBalance(domain.getPayee().getAccount().getBalance());
        transferEntity.setId(domain.getId());
        transferEntity.setPayer(payer);
        transferEntity.setPayee(payee);
        transferEntity.setValue( domain.getValue() );

        return transferEntity;
    }

    private UserEntity userDomainToUserEntity(UserDomain userDomain) {
        if ( userDomain == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setId( userDomain.getId() );
        userEntity.setName( userDomain.getName() );
        userEntity.setType( UserTypeEntity.valueOf( userDomain.getType().toString() ) );
        userEntity.setDocument( userDomain.getDocument() );
        userEntity.setEmail( userDomain.getEmail() );
        userEntity.setPassword( userDomain.getPassword() );

        userEntity.setAccount( AccountMapper.toEntity(userDomain.getAccount()) );


        return userEntity;
    }
}
