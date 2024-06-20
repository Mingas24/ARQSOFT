package com.example.SanwichManager.dtos.sandwich;

public class InputSandwichDTO {

    public String sandwichPrice;
    public String sandwichDesignation;
    public String sandwichDescription;

    public InputSandwichDTO(String sandwichPrice, String sandwichDesignation, String sandwichDescription) {
        this.sandwichPrice = sandwichPrice;
        this.sandwichDesignation = sandwichDesignation;
        this.sandwichDescription = sandwichDescription;
    }
}
