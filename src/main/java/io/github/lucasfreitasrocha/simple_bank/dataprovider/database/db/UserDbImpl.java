package io.github.lucasfreitasrocha.simple_bank.dataprovider.database.db;

import io.github.lucasfreitasrocha.simple_bank.core.domain.UserDomain;
import io.github.lucasfreitasrocha.simple_bank.core.domain.UserTypeDomain;
import io.github.lucasfreitasrocha.simple_bank.core.gateway.UserDbRepository;
import io.github.lucasfreitasrocha.simple_bank.dataprovider.database.entity.UserEntity;
import io.github.lucasfreitasrocha.simple_bank.dataprovider.database.entity.UserTypeEntity;
import io.github.lucasfreitasrocha.simple_bank.dataprovider.database.mapper.AccountMapper;
import io.github.lucasfreitasrocha.simple_bank.dataprovider.database.mapper.TransferMapper;
import io.github.lucasfreitasrocha.simple_bank.dataprovider.database.mapper.UserMapper;
import io.github.lucasfreitasrocha.simple_bank.dataprovider.database.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@AllArgsConstructor
public class UserDbImpl implements UserDbRepository {
    private final UserRepository repository;
    private final UserMapper userMapper;

    @Override
    public UserDomain save(UserDomain domain) {
        return userMapper.userEntityToUserDomain(repository.save(userMapper.userDomainToUserEntity(domain)));
    }


    @Override
    public UserDomain findById(Long id) {
        UserEntity entity = repository.getReferenceById(id);
        return userMapper.userEntityToUserDomain(entity);
    }

    @Override
    public UserDomain findByEmailOrDocument(String email, String document) {
        return userMapper.userEntityToUserDomain(repository.findByEmailOrDocument(email, document));
    }





}
