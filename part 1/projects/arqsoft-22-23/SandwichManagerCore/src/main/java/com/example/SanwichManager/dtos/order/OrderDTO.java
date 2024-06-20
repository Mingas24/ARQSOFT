package com.example.SanwichManager.dtos.order;

import java.util.Map;

public class OrderDTO {

    public Long id;
    public String orderDate;
    public String orderPrice;
    public String shopId;
    public String costumerEmail;
    public Map<String,Integer> sandwiches;
    public String status;

    public OrderDTO(Long id, String orderDate, String orderPrice, String status, String shopId, String costumerEmail, Map<String,Integer> sandwiches) {
        this.id = id;
        this.orderDate = orderDate;
        this.orderPrice = orderPrice;
        this.shopId = shopId;
        this.costumerEmail = costumerEmail;
        this.sandwiches = sandwiches;
        this.status = status;
    }
}
