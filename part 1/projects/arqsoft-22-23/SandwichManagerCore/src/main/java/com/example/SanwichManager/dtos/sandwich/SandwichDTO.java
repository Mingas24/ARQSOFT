package com.example.SanwichManager.dtos.sandwich;


public class SandwichDTO{

    public long sandwichId;
    public String sandwichPrice;
    public String sandwichDesignation;
    public String sandwichDescription;

    public SandwichDTO(long sandwichId, String sandwichPrice, String sandwichDesignation, String sandwichDescription) {
        this.sandwichId = sandwichId;
        this.sandwichPrice = sandwichPrice;
        this.sandwichDesignation = sandwichDesignation;
        this.sandwichDescription = sandwichDescription;
    }
}
