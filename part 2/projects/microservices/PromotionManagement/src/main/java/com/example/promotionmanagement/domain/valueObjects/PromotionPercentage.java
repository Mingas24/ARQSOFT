package com.example.promotionmanagement.domain.valueObjects;

import javax.persistence.Embeddable;

@Embeddable
public class PromotionPercentage {

    private float percentage;

    public PromotionPercentage(float percentage) {
        setPercentage(percentage);
    }

    protected PromotionPercentage() {

    }
    public float getPercentage() {
        return percentage;
    }

    private void setPercentage(float percentage) {

        if (percentage <= 0 || percentage > 100)
            throw new IllegalArgumentException("Percentage should be between 1% and 100%");

        this.percentage = percentage;
    }
}
