package com.example.OrderManagement.controller;

import com.example.OrderManagement.dto.InputOrderDTO;
import com.example.OrderManagement.dto.OrderDTO;
import com.example.OrderManagement.dto.ResponseDTO;
import com.example.OrderManagement.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @MutationMapping
    public OrderDTO addOrder(@Argument InputOrderDTO inputOrderDTO) {
        return this.orderService.addOrder(inputOrderDTO);
    }

    @MutationMapping
    public ResponseDTO approveOrder(@Argument Long orderId){
        return this.orderService.approveOrder(orderId);
    }

    @MutationMapping
    public ResponseDTO cancelOrder(@Argument Long orderId){
        return this.orderService.cancelOrder(orderId);
    }

    @QueryMapping
    public List<OrderDTO> getOrderHistoryByCostumer(@Argument String costumerEmail){
        return this.orderService.getOrderHistoryByCostumer(costumerEmail);
    }
}