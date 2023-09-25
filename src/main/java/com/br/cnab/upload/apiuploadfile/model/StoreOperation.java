package com.br.cnab.upload.apiuploadfile.model;

import com.br.cnab.upload.apiuploadfile.entity.CNAB;
import com.br.cnab.upload.apiuploadfile.enums.TransactionTypeEnum;

import java.util.ArrayList;
import java.util.List;

public class StoreOperation {

    private final String storeName;
    private final List<CNAB> operations;
    private double totalBalance;

    public StoreOperation(String storeName) {
        this.storeName = storeName;
        this.operations = new ArrayList<>();
        this.totalBalance = 0.0;
    }

    public String getStoreName() {
        return storeName;
    }

    public List<CNAB> getOperations() {
        return operations;
    }

    public double getTotalBalance() {
        return totalBalance;
    }

    public void addOperation(CNAB cnab) {
        operations.add(cnab);

        String transactionType = cnab.getTransactionType().toString().trim();

        TransactionTypeEnum type = TransactionTypeEnum.fromCode(transactionType);

        if (type != null) {
            double normalizedValue = cnab.getTransactionValue();
            totalBalance += type.getSignal() * normalizedValue;
        }
    }

    public String getFormattedTotalBalance() {
        return String.format("R$ %.2f", this.totalBalance);
    }

    public StoreOperationResponse toResponse() {
        return new StoreOperationResponse(this.storeName, this.totalBalance);
    }
}
