package com.example.OrderManagement.service;

import com.example.OrderManagement.domain.entities.Order;
import com.example.OrderManagement.domain.entities.SandwichOrder;
import com.example.OrderManagement.domain.enums.OrderStatus;
import com.example.OrderManagement.domain.enums.StatusCode;
import com.example.OrderManagement.dto.InputOrderDTO;
import com.example.OrderManagement.dto.OrderDTO;
import com.example.OrderManagement.dto.ResponseDTO;
import com.example.OrderManagement.mapper.OrderMapper;
import com.example.OrderManagement.repository.OrderRepository;
import com.example.OrderManagement.repository.SandwichOrderRepository;
import graphql.GraphQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements IOrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private SandwichOrderRepository sandwichOrderRepository;

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public OrderDTO addOrder(InputOrderDTO inputOrderDTO) {
        try {
            Order order = this.orderMapper.toOrder(inputOrderDTO, OrderStatus.PROCESSED);
            Order savedOrder = this.orderRepository.save(order);

            for (SandwichOrder sandwichOrder : savedOrder.getSandwichOrders()) {
                sandwichOrder.setOrder(savedOrder);
            }

            this.sandwichOrderRepository.saveAll(savedOrder.getSandwichOrders());
            return this.orderMapper.toOrderDTO(savedOrder);
        } catch (Exception e) {
            throw new GraphQLException(e.getMessage());
        }
    }

    @Override
    public ResponseDTO approveOrder(Long orderId) {
        try {
            Order order = findOrderById(orderId);
            if (order.getOrderStatus().getStatus().equals(OrderStatus.CONCLUDED.getStatus()) ||
                    order.getOrderStatus().getStatus().equals(OrderStatus.CANCELLED.getStatus())) {
                throw new GraphQLException( String.format("Order %s is already cancelled or concluded!", order.getOrder_id()));
            }

            int updatedOrder = this.orderRepository.updateOrderStatus(order.getOrder_id(), OrderStatus.CONCLUDED.ordinal());
            if (updatedOrder == 0) {

                throw new GraphQLException(String.format("Error during update of order: %s", orderId));
            }

            return new ResponseDTO(StatusCode.UPDATED.getCode(), String.format("Order %s approved!", order.getOrder_id()));
        } catch (Exception e) {
            throw new GraphQLException(e.getMessage());
        }
    }

    @Override
    public ResponseDTO cancelOrder(Long orderId) {
        try {
            Order order = findOrderById(orderId);
            if (order.getOrderStatus().getStatus().equals(OrderStatus.CONCLUDED.getStatus()) ||
                    order.getOrderStatus().getStatus().equals(OrderStatus.CANCELLED.getStatus())) {
                throw new GraphQLException(String.format("Order %s is already cancelled or concluded!", order.getOrder_id()));
            }

            int updatedOrder = this.orderRepository.updateOrderStatus(order.getOrder_id(), OrderStatus.CANCELLED.ordinal());
            if (updatedOrder == 0) {
                throw new GraphQLException(String.format("Error during update of order: %s", orderId));
            }

            return new ResponseDTO(StatusCode.UPDATED.getCode(), String.format("Order %s cancelled!", order.getOrder_id()));
        } catch (Exception e) {
            throw new GraphQLException(e.getMessage());
        }
    }

    @Override
    public List<OrderDTO> getOrderHistoryByCostumer(String costumerEmail) {
        try {
            Optional<List<Order>> orders = this.orderRepository.findOrdersByCostumer(costumerEmail);
            if (orders.get().isEmpty()) {
                return null;
            }
            return orders.get().stream().map(order -> orderMapper.toOrderDTO(order)).toList();
        } catch (Exception e) {
            throw new GraphQLException(e.getMessage());
        }
    }

    private Order findOrderById(Long orderId){
        Optional<Order> optionalOrder = this.orderRepository.findById(orderId);
        if (!optionalOrder.isPresent()) {
            throw new GraphQLException(String.format("There is no order with the id: %s", orderId));
        }
        return optionalOrder.get();
    }
}
