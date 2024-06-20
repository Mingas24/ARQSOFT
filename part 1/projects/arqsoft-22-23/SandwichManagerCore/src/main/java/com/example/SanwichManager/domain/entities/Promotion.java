package com.example.SanwichManager.domain.entities;

import com.example.SanwichManager.domain.valueObjects.PromotionPercentage;
import com.example.SanwichManager.domain.enums.PromotionType;
import com.example.SanwichManager.domain.valueObjects.PromotionDate;
import org.springframework.lang.NonNull;

import javax.persistence.*;

@Entity
public class Promotion {

    @Id
    @GeneratedValue
    private long promotionId;

    @Column(name = "promotionType", nullable = false)
    private PromotionType type;

    @OneToOne
    @NonNull
    private Sandwich sandwich;

    @OneToOne
    private Shop shop;

    @Column(name = "percentage", nullable = false)
    private PromotionPercentage percentage;

    @Column(name = "startDate", nullable = false)
    private PromotionDate promotionDate;

    public Promotion(PromotionType promotionType, Sandwich sandwich, Shop shop, PromotionPercentage percentage, PromotionDate promotionDate) {
        this.type = promotionType;
        this.sandwich = sandwich;
        this.shop = shop;
        this.percentage = percentage;
        this.promotionDate = promotionDate;
    }

    public Promotion(PromotionType promotionType, Sandwich sandwich, PromotionPercentage percentage, PromotionDate promotionDate) {

        this.type = promotionType;
        this.sandwich = sandwich;
        this.percentage = percentage;
        this.promotionDate = promotionDate;
    }

    protected Promotion() {

    }

    public long getPromotionId() {
        return promotionId;
    }

    public PromotionType getType() {
        return type;
    }

    public Sandwich getSandwich() {
        return sandwich;
    }

    public Shop getShop() {
        return shop;
    }

    public PromotionPercentage getPercentage() {
        return percentage;
    }

    public PromotionDate getPromotionDate() {
        return promotionDate;
    }

}
