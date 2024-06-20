package com.example.SanwichManager.dtos.manager;

public class ManagerDTO {

    public long id;
    public String name;
    public String email;
    public Long shopId;

    public ManagerDTO(Long id, String name, String email, Long shopId) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.shopId = shopId;
    }
}