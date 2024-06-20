package com.example.OrderManagement.dto;

import graphql.util.Pair;

import java.util.List;

public class InputOrderDTO {

    public String orderDate;
    public Long shopId;
    public String costumerEmail;
    public List<Pair<String, String>> sandwiches;

    public InputOrderDTO(String orderDate, Long shopId, String costumerEmail, List<Pair<String, String>> sandwiches) {
        this.orderDate = orderDate;
        this.shopId = shopId;
        this.costumerEmail = costumerEmail;
        this.sandwiches = sandwiches;
    }
}
