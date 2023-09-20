package com.br.cnab.upload.apiuploadfile.model.entity;

import lombok.*;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;


@Getter
@Setter
@Entity
@Builder
@Table(name = "tb_file")
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type", length = 1)
    private Integer type;

    @Column(name = "date", length = 8)
    private LocalDate date;

    @Column(name = "value", length = 10)
    @NumberFormat(pattern = "0.00")
    private BigDecimal value;

    @Column(name = "cpf", length = 11)
    private String cpf;

    @Column(name = "card", length = 12)
    private String card;

    @Column(name = "time", length = 12)
    private LocalDateTime time;

    @Column(name = "storeOwner", length = 14)
    private String storeOwner;

    @Column(name = "storeName", length = 50)
    private String storeName;


    public File(Long id, Integer type, LocalDate date, BigDecimal value, String cpf, String card, LocalDateTime time, String storeOwner, String storeName) {
        this.id = id;
        this.type = type;
        this.date = date;
        this.value = value;
        this.cpf = cpf;
        this.card = card;
        this.time = time;
        this.storeOwner = storeOwner;
        this.storeName = storeName;
    }

    public File() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        File file = (File) o;
        return Objects.equals(id, file.id) && Objects.equals(type, file.type) && Objects.equals(date, file.date) && Objects.equals(value, file.value) && Objects.equals(cpf, file.cpf) && Objects.equals(card, file.card) && Objects.equals(time, file.time) && Objects.equals(storeOwner, file.storeOwner) && Objects.equals(storeName, file.storeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, date, value, cpf, card, time, storeOwner, storeName);
    }
}
