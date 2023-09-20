package com.br.cnab.upload.apiuploadfile.model.entity;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_store")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String storeName;

    private String cpf;

    private BigDecimal totalAmount;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Transaction> transactions;


    public Store(Long id, String storeName, String cpf, BigDecimal totalAmount) {
        this.id = id;
        this.storeName = storeName;
        this.cpf = cpf;
        this.totalAmount = totalAmount;
    }

    public Store() {
    }

    public Store(String storeName) {
        this.storeName = storeName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal accountBalance) {
        this.totalAmount = accountBalance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Store store = (Store) o;
        return Objects.equals(id, store.id) && Objects.equals(storeName, store.storeName) && Objects.equals(cpf, store.cpf) && Objects.equals(totalAmount, store.totalAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, storeName, cpf, totalAmount);
    }
}
