package com.example.SanwichManager.dtos.order;

import java.util.Map;

public class InputOrderDTO {

    public String orderDate;
    public Long shopId;
    public String costumerEmail;
    public Map<String,Integer> sandwiches;

    public InputOrderDTO(String orderDate, Long shopId, String costumerEmail, Map<String,Integer> sandwiches) {
        this.orderDate = orderDate;
        this.shopId = shopId;
        this.costumerEmail = costumerEmail;
        this.sandwiches = sandwiches;
    }
}
