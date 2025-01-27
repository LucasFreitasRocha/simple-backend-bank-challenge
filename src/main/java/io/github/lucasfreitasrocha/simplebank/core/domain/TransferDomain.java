package io.github.lucasfreitasrocha.simplebank.core.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransferDomain {
    private Long id;
    @JsonManagedReference(value = "payer")
    private AccountDomain payer;
    @JsonManagedReference(value = "payee")
    private AccountDomain payee;
    private BigDecimal value;
    private StatusPayment status;


    @Override
    public String toString() {
        return "TransferDomain{" +
                "id=" + id +
                ", payer=" + payer +
                ", payee=" + payee +
                ", value=" + value +
                ", status=" + status.toString() +
                '}';
    }
}
