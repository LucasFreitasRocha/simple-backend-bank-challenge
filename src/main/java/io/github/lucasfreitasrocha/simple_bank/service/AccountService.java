package io.github.lucasfreitasrocha.simple_bank.service;

import io.github.lucasfreitasrocha.simple_bank.model.AccountModel;
import io.github.lucasfreitasrocha.simple_bank.model.UserModel;
import io.github.lucasfreitasrocha.simple_bank.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountService {

    private final AccountRepository repository;
    public AccountModel create(UserModel model){
        return this.repository.save(new AccountModel(model));
    }
}
