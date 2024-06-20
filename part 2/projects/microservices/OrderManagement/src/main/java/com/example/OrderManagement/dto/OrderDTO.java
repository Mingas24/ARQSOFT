package com.example.OrderManagement.dto;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDTO {

    public String orderDate;
    public String orderPrice;
    public Long shopId;
    public String costumerEmail;
    public String status;
}
