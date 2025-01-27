package io.github.lucasfreitasrocha.simplebank.dataprovider.database.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

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

