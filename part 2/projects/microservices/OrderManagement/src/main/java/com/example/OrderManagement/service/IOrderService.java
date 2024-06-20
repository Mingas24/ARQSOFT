package com.example.OrderManagement.service;


import com.example.OrderManagement.dto.InputOrderDTO;
import com.example.OrderManagement.dto.OrderDTO;
import com.example.OrderManagement.dto.ResponseDTO;

import java.util.List;

public interface IOrderService {

    OrderDTO addOrder(InputOrderDTO inputOrderDTO);

    ResponseDTO approveOrder(Long orderId);

    ResponseDTO cancelOrder(Long orderId);

    List<OrderDTO> getOrderHistoryByCostumer(String costumerEmail);
}
