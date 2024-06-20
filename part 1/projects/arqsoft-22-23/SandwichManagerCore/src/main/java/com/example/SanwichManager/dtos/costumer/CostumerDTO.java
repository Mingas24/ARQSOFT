package com.example.SanwichManager.dtos.costumer;

public class CostumerDTO {

    public Long id;
    public String costumerName;
    public String costumerEmail;
    public String costumerNIF;
    public String costumerAddress;

    public CostumerDTO(Long id, String costumerName, String costumerEmail, String costumerNIF, String costumerAddress) {
        this.id = id;
        this.costumerName = costumerName;
        this.costumerEmail = costumerEmail;
        this.costumerNIF = costumerNIF;
        this.costumerAddress = costumerAddress;
    }
}
