package com.br.cnab.upload.apiuploadfile.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Data
@Entity
public class CNAB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer transactionType;
    private String transactionDate;
    private double transactionValue;
    private String cpf;
    private String card;
    private String occurrenceTime;
    private String storeOwner;
    private String storeName;

    public CNAB() {
    }

    public CNAB(Integer transactionType, String transactionDate, double transactionValue, String cpf, String card, String occurrenceTime, String storeOwner, String storeName) {
        this.transactionType = transactionType;
        this.transactionDate = transactionDate;
        this.transactionValue = transactionValue;
        this.cpf = cpf;
        this.card = card;
        this.occurrenceTime = occurrenceTime;
        this.storeOwner = storeOwner;
        this.storeName = storeName;
    }
}
