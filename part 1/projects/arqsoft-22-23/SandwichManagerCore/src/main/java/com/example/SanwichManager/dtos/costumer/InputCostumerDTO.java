package com.example.SanwichManager.dtos.costumer;

public class InputCostumerDTO {

    public String costumerName;
    public String costumerEmail;
    public String costumerNIF;
    public String costumerAddress;

    public InputCostumerDTO(String costumerName, String costumerEmail, String costumerNIF, String costumerAddress) {
        this.costumerName = costumerName;
        this.costumerEmail = costumerEmail;
        this.costumerNIF = costumerNIF;
        this.costumerAddress = costumerAddress;
    }
}
