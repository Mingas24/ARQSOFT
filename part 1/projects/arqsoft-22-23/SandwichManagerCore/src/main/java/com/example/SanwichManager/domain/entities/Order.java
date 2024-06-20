package com.example.SanwichManager.domain.entities;

import com.example.SanwichManager.domain.enums.OrderStatus;
import com.example.SanwichManager.domain.valueObjects.OrderDate;
import com.example.SanwichManager.domain.valueObjects.shared.Price;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "orders", schema = "arqsoftdb")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long order_id;

    @Column(name = "orderDate")
    private OrderDate orderDate;

    @Column(name = "orderPrice")
    private Price orderPrice;

    @Column(name = "orderStatus")
    private OrderStatus orderStatus;

    @OneToOne
    private Costumer costumer;

    @OneToOne
    private Shop shop;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    private List<SandwichOrder> sandwichOrders;

    protected Order() {
    }

    public Order(OrderDate orderDate, Price orderPrice, OrderStatus orderStatus, Costumer costumer, Shop shop, List<SandwichOrder> sandwichOrders) {
        this.orderDate = orderDate;
        this.orderPrice = orderPrice;
        this.orderStatus = orderStatus;
        this.costumer = costumer;
        this.shop = shop;
        this.sandwichOrders = sandwichOrders;
    }

    public Long getId() {
        return order_id;
    }

    private void setId(Long id) {
        this.order_id = id;
    }

    public OrderDate getOrderDate() {
        return orderDate;
    }

    private void setOrderDate(OrderDate orderDate) {
        this.orderDate = orderDate;
    }

    public Price getOrderPrice() {
        return orderPrice;
    }

    private void setOrderPrice(Price orderPrice) {
        this.orderPrice = orderPrice;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    private void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Costumer getCostumer() {
        return costumer;
    }

    private void setCostumer(Costumer costumer) {
        this.costumer = costumer;
    }

    public Shop getShop() {
        return shop;
    }

    private void setShop(Shop shop) {
        this.shop = shop;
    }

    public List<SandwichOrder> getSandwichOrders() {
        return sandwichOrders;
    }

    private void setSandwichOrders(List<SandwichOrder> sandwichOrder) {
        this.sandwichOrders = sandwichOrders;
    }
}
