package com.example.OrderManagement.domain.entities;

import com.example.OrderManagement.domain.enums.OrderStatus;
import com.example.OrderManagement.domain.valueObjects.Email;
import com.example.OrderManagement.domain.valueObjects.OrderDate;
import com.example.OrderManagement.domain.valueObjects.Price;
import com.example.OrderManagement.domain.valueObjects.ShopId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "orders", schema = "orderdb")
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

    @Column(name = "costumerEmail")
    private Email costumerEmail;

    @Column(name = "shopId")
    private ShopId shopId;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    private List<SandwichOrder> sandwichOrders;

}