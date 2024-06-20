package com.example.SanwichManager.domain.entities;

import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "sandwichOrder", schema = "arqsoftdb")
public class SandwichOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne
    private Sandwich sandwich;

    @Column(name = "sandwichAmount")
    private Integer sandwichAmount;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;


    protected SandwichOrder() {
    }

    public SandwichOrder(Sandwich sandwich, Integer sandwichAmount) {
        this.sandwich = sandwich;
        this.sandwichAmount = sandwichAmount;
    }

    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public Sandwich getSandwich() {
        return sandwich;
    }

    private void setSandwich(Sandwich sandwich) {
        this.sandwich = sandwich;
    }

    public Integer getSandwichAmount() {
        return sandwichAmount;
    }

    private void setSandwichAmount(Integer sandwichAmount) {
        this.sandwichAmount = sandwichAmount;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

}
