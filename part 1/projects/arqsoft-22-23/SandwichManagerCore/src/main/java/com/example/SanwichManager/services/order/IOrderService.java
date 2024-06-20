package com.example.SanwichManager.services.order;

import com.example.SanwichManager.dtos.order.InputOrderDTO;
import com.example.SanwichManager.dtos.result.ResponseDTO;

public interface IOrderService {

    ResponseDTO addOrder(InputOrderDTO inputOrderDTO);

    ResponseDTO approveOrder(Long orderId);

    ResponseDTO cancelOrder(Long orderId);

    ResponseDTO getOrderHistoryByCostumer(String costumerEmail);
}
