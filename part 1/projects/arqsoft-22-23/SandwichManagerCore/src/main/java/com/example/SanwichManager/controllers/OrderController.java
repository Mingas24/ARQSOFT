package com.example.SanwichManager.controllers;

import com.example.SanwichManager.dtos.order.InputOrderDTO;
import com.example.SanwichManager.dtos.result.ResponseDTO;
import com.example.SanwichManager.services.order.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @PostMapping("addOrder")
    public ResponseDTO addOrder(@RequestBody InputOrderDTO inputOrderDTO) {
        return this.orderService.addOrder(inputOrderDTO);
    }

    @PutMapping("approveOrder/{orderId}")
    public ResponseDTO approveOrder(@PathVariable(value = "orderId") Long orderId){
        return this.orderService.approveOrder(orderId);
    }

    @PutMapping("cancelOrder/{orderId}")
    public ResponseDTO cancelOrder(@PathVariable(value = "orderId") Long orderId){
        return this.orderService.cancelOrder(orderId);
    }

    @GetMapping("getOrderHistoryByCostumer/{costumerEmail}")
    public ResponseDTO getOrderHistoryByCostumer(@PathVariable(value = "costumerEmail") String costumerEmail){
        return this.orderService.getOrderHistoryByCostumer(costumerEmail);
    }
}