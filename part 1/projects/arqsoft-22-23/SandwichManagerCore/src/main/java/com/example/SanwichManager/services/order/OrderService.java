package com.example.SanwichManager.services.order;

import com.example.SanwichManager.domain.entities.Costumer;
import com.example.SanwichManager.domain.entities.Order;
import com.example.SanwichManager.domain.entities.SandwichOrder;
import com.example.SanwichManager.domain.enums.OrderStatus;
import com.example.SanwichManager.domain.enums.StatusCode;
import com.example.SanwichManager.domain.valueObjects.shared.Email;
import com.example.SanwichManager.dtos.order.InputOrderDTO;
import com.example.SanwichManager.dtos.result.ResponseDTO;
import com.example.SanwichManager.mappers.OrderMapper;
import com.example.SanwichManager.repositories.CostumerRepository;
import com.example.SanwichManager.repositories.OrderRepository;
import com.example.SanwichManager.repositories.SandwichOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements IOrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private SandwichOrderRepository sandwichOrderRepository;

    @Autowired
    private CostumerRepository costumerRepository;

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public ResponseDTO addOrder(InputOrderDTO inputOrderDTO) {
        try {
            Order order = this.orderMapper.toOrder(inputOrderDTO, OrderStatus.PROCESSED);
            Order savedOrder = this.orderRepository.save(order);

            for (SandwichOrder sandwichOrder : savedOrder.getSandwichOrders()) {
                sandwichOrder.setOrder(savedOrder);
            }

            this.sandwichOrderRepository.saveAll(savedOrder.getSandwichOrders());
            return new ResponseDTO(StatusCode.CREATED.getCode(), this.orderMapper.toOrderDTO(savedOrder));
        } catch (Exception e) {
            return new ResponseDTO(StatusCode.BAD_REQUEST.getCode(), String.format("%s : %s", e.getClass(), e.getMessage()));
        }
    }

    @Override
    public ResponseDTO approveOrder(Long orderId) {
        try {
            Order order = findOrderById(orderId);
            if (order.getOrderStatus().getStatus().equals(OrderStatus.CONCLUDED.getStatus()) ||
                    order.getOrderStatus().getStatus().equals(OrderStatus.CANCELLED.getStatus())) {
                return new ResponseDTO(StatusCode.NOT_FOUND.getCode(), String.format("Order %s is already cancelled or concluded!", order.getId()));
            }

            int updatedOrder = this.orderRepository.updateOrderStatus(order.getId(), OrderStatus.CONCLUDED.ordinal());
            if (updatedOrder == 0) {
                return new ResponseDTO(StatusCode.BAD_REQUEST.getCode(), String.format("Error during update of order: %s", orderId));
            }

            return new ResponseDTO(StatusCode.UPDATED.getCode(), String.format("Order %s approved!", order.getId()));
        } catch (Exception e) {
            return new ResponseDTO(StatusCode.BAD_REQUEST.getCode(), String.format("%s : %s", e.getClass(), e.getMessage()));
        }
    }

    @Override
    public ResponseDTO cancelOrder(Long orderId) {
        try {
            Order order = findOrderById(orderId);
            if (order.getOrderStatus().getStatus().equals(OrderStatus.CONCLUDED.getStatus()) ||
                    order.getOrderStatus().getStatus().equals(OrderStatus.CANCELLED.getStatus())) {
                return new ResponseDTO(StatusCode.NOT_FOUND.getCode(), String.format("Order %s is already cancelled or concluded!", order.getId()));
            }

            int updatedOrder = this.orderRepository.updateOrderStatus(order.getId(), OrderStatus.CANCELLED.ordinal());
            if (updatedOrder == 0) {
                return new ResponseDTO(StatusCode.BAD_REQUEST.getCode(), String.format("Error during update of order: %s", orderId));
            }

            return new ResponseDTO(StatusCode.UPDATED.getCode(), String.format("Order %s cancelled!", order.getId()));
        } catch (Exception e) {
            return new ResponseDTO(StatusCode.BAD_REQUEST.getCode(), String.format("%s : %s", e.getClass(), e.getMessage()));
        }
    }

    @Override
    public ResponseDTO getOrderHistoryByCostumer(String costumerEmail) {
        try {
            Optional<Costumer> optionalCostumer = this.costumerRepository.findCostumerByCostumerEmail(new Email(costumerEmail));
            if (!optionalCostumer.isPresent()) {
                return new ResponseDTO(StatusCode.NOT_FOUND.getCode(), String.format("There is no costumer with the email: %s", costumerEmail));
            }
            Optional<List<Order>> orders = this.orderRepository.findOrdersByCostumer(optionalCostumer.get().getId());
            if (orders.get().isEmpty()) {
                return new ResponseDTO(StatusCode.NOT_FOUND.getCode(), String.format("Currently the costumer %s has no orders.", costumerEmail));
            }
            return new ResponseDTO(StatusCode.OK.getCode(), orders);
        } catch (Exception e) {
            return new ResponseDTO(StatusCode.BAD_REQUEST.getCode(), String.format("%s : %s", e.getClass(), e.getMessage()));
        }
    }

    private Order findOrderById(Long orderId){
        Optional<Order> optionalOrder = this.orderRepository.findById(orderId);
        if (!optionalOrder.isPresent()) {
            throw new IllegalArgumentException(String.format("There is no order with the id: %s", orderId));
        }
        return optionalOrder.get();
    }
}
