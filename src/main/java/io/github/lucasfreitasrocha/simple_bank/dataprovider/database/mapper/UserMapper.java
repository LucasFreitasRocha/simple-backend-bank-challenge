package io.github.lucasfreitasrocha.simple_bank.dataprovider.database.mapper;

import io.github.lucasfreitasrocha.simple_bank.core.domain.UserDomain;
import io.github.lucasfreitasrocha.simple_bank.core.domain.UserTypeDomain;
import io.github.lucasfreitasrocha.simple_bank.dataprovider.database.entity.UserEntity;
import io.github.lucasfreitasrocha.simple_bank.dataprovider.database.entity.UserTypeEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class UserMapper {

    private final TransferMapper transferMapper;

    public  UserEntity userDomainToUserEntity(UserDomain userDomain) {
        if ( userDomain == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setId( userDomain.getId() );
        userEntity.setName( userDomain.getName() );
        userEntity.setType( userTypeDomainToUserTypeEntity( userDomain.getType() ) );
        userEntity.setDocument( userDomain.getDocument() );
        userEntity.setEmail( userDomain.getEmail() );
        userEntity.setPassword( userDomain.getPassword() );

        userEntity.setAccount( AccountMapper.toEntity(userDomain.getAccount()) );
        userEntity.setPayments( transferMapper.toEntityList( userDomain.getPayments() ) );
        userEntity.setReceipts( transferMapper.toEntityList( userDomain.getReceipts() ) );

        return userEntity;
    }

    public  UserDomain userEntityToUserDomain(UserEntity userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        UserDomain userDomain = new UserDomain();

        userDomain.setId( userEntity.getId() );
        userDomain.setName( userEntity.getName() );
        userDomain.setType( userTypeEntityToUserTypeDomain( userEntity.getType() ) );
        userDomain.setDocument( userEntity.getDocument() );
        userDomain.setEmail( userEntity.getEmail() );
        userDomain.setPassword( userEntity.getPassword() );
        userDomain.setAccount( AccountMapper.toDomain( userEntity.getAccount() ) );
        userDomain.setPayments(transferMapper.toDomainList(userEntity.getPayments()));
        userDomain.setReceipts(transferMapper.toDomainList(userEntity.getReceipts()));

        return userDomain;
    }
    public  UserTypeEntity userTypeDomainToUserTypeEntity(UserTypeDomain userTypeDomain) {
        if ( userTypeDomain == null ) {
            return null;
        }

        UserTypeEntity userTypeEntity;

        switch ( userTypeDomain ) {
            case PF: userTypeEntity = UserTypeEntity.PF;
                break;
            case PJ: userTypeEntity = UserTypeEntity.PJ;
                break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + userTypeDomain );
        }

        return userTypeEntity;
    }

    public   UserTypeDomain userTypeEntityToUserTypeDomain(UserTypeEntity userTypeEntity) {
        if ( userTypeEntity == null ) {
            return null;
        }

        UserTypeDomain userTypeDomain;

        switch ( userTypeEntity ) {
            case PF: userTypeDomain = UserTypeDomain.PF;
                break;
            case PJ: userTypeDomain = UserTypeDomain.PJ;
                break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + userTypeEntity );
        }

        return userTypeDomain;
    }
}
