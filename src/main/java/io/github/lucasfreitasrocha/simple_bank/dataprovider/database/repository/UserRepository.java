package io.github.lucasfreitasrocha.simple_bank.dataprovider.database.repository;

import io.github.lucasfreitasrocha.simple_bank.dataprovider.database.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByEmailOrDocument(String email, String document);
}
