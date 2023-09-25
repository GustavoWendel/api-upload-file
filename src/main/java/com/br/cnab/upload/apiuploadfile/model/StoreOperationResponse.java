package com.br.cnab.upload.apiuploadfile.model;


import lombok.Data;



@Data
public class StoreOperationResponse {
    private  String storeName;
    private double totalBalance;

    public StoreOperationResponse(String storeName, double totalBalance) {
        this.storeName = storeName;
        this.totalBalance = totalBalance;
    }
}
