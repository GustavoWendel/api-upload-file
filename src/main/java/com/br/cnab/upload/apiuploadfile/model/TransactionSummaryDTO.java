package com.br.cnab.upload.apiuploadfile.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Data
@Getter
@Setter
@Builder
public class TransactionSummaryDTO {
    private Long storeId;
    private String storeName;
    private BigDecimal accountBalance;

    public TransactionSummaryDTO(Long storeId, String storeName, BigDecimal accountBalance) {
        this.storeId = storeId;
        this.storeName = storeName;
        this.accountBalance = accountBalance;
    }

    public TransactionSummaryDTO() {
    }
}
