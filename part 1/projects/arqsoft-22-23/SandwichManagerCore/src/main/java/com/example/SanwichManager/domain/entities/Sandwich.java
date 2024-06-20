package com.example.SanwichManager.domain.entities;


import com.example.SanwichManager.domain.valueObjects.SandwichDescription;
import com.example.SanwichManager.domain.valueObjects.shared.Designation;
import com.example.SanwichManager.domain.valueObjects.shared.Price;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "sandwich", schema = "arqsoftdb")
public class Sandwich {

    @Column(name = "sandwichPrice", nullable = false)
    public Price sandwichPrice;
    @Column(name = "sandwichDesignation", nullable = false, unique = true)
    @NotNull(message = "Sandwich Designation may not be null")
    public Designation sandwichDesignation;
    @Column(name = "sandwichDescription", nullable = false)
    public SandwichDescription sandwichDescription;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long sandwichId;


    public Sandwich(Price sandwichPrice, Designation sandwichDesignation, SandwichDescription sandwichDescription) {
        this.sandwichPrice = sandwichPrice;
        this.sandwichDesignation = sandwichDesignation;
        this.sandwichDescription = sandwichDescription;
    }

    protected Sandwich() {
    }

    public Long getSandwichId() {
        return sandwichId;
    }

    private void setSandwichId(Long sandwichId) {
        this.sandwichId = sandwichId;
    }

    public Price getSandwichPrice() {
        return sandwichPrice;
    }

    private void setSandwichPrice(Price sandwichPrice) {
        this.sandwichPrice = sandwichPrice;
    }

    public Designation getSandwichDesignation() {
        return sandwichDesignation;
    }

    private void setSandwichDesignation(Designation sandwichDesignation) {
        this.sandwichDesignation = sandwichDesignation;
    }

    public SandwichDescription getSandwichDescription() {
        return sandwichDescription;
    }

    private void setSandwichDescription(SandwichDescription sandwichDescription) {
        this.sandwichDescription = sandwichDescription;
    }
}