package io.github.lucasfreitasrocha.simplebank.dataprovider.database.repository;

import io.github.lucasfreitasrocha.simplebank.dataprovider.database.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByEmailOrDocument(String email, String document);
}
