package com.example.SanwichManager.dtos.manager;

public class InputManagerDTO {
    public String name;
    public String email;
    public Long shopId;

    public InputManagerDTO(String name, String email, Long shopId) {
        this.name = name;
        this.email = email;
        this.shopId = shopId;
    }
}