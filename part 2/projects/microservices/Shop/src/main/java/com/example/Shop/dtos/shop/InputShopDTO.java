package com.example.Shop.dtos.shop;

import graphql.util.Pair;


import java.util.List;

public class InputShopDTO {

    public String designation;
    public String email;
    public String address;
    public List<Pair<String, String>> schedule;

    public InputShopDTO(String designation, String email, String address, List<Pair<String, String>> schedule) {
        this.designation = designation;
        this.email = email;
        this.address = address;
        this.schedule = schedule;
    }
}