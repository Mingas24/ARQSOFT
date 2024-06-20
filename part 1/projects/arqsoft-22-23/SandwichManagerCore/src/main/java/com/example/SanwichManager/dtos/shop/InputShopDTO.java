package com.example.SanwichManager.dtos.shop;

import java.util.Map;

public class InputShopDTO {

    public String designation;
    public String email;
    public String address;
    public Map<String, String> schedule;

    public InputShopDTO(String designation, String email, String address, Map<String, String> schedule) {
        this.designation = designation;
        this.email = email;
        this.address = address;
        this.schedule = schedule;
    }
}