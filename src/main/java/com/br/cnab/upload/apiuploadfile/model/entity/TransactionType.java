package com.br.cnab.upload.apiuploadfile.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "tb_transaction_type")
public class TransactionType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type_transaction")
    private Integer type;

    @Column(name = "description")
    private String description;

    @Column(name = "nature")
    private String nature;

    @Column(name = "sign", length = 10)
    private Character sign;

    @OneToMany(mappedBy = "type", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Transaction> transactions;

    public TransactionType(Integer type, String description, String nature, Character sign) {
        this.type = type;
        this.description = description;
        this.nature = nature;
        this.sign = sign;
    }

    public TransactionType() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionType that = (TransactionType) o;
        return Objects.equals(type, that.type) && Objects.equals(description, that.description) && Objects.equals(nature, that.nature) && Objects.equals(sign, that.sign);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, description, nature, sign);
    }
}
