package io.github.lucasfreitasrocha.simple_bank.dataprovider.database.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.util.List;

@Entity
@Table(name = "bank_user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private UserTypeEntity type;
    @Column(unique = true)
    private String document;
    @Column(unique = true)
    private String email;
    private String password;
    @OneToOne(mappedBy = "owner")
    private AccountEntity account;

}

