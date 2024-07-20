package io.github.lucasfreitasrocha.simple_bank.repository;

import io.github.lucasfreitasrocha.simple_bank.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

    UserModel findByEmailOrDocument(String email,String document);
}
