package com.example.promotionmanagement.domain.entities;

import com.example.promotionmanagement.domain.enums.PromotionType;
import com.example.promotionmanagement.domain.valueObjects.PromotionDate;
import com.example.promotionmanagement.domain.valueObjects.PromotionPercentage;
import com.example.promotionmanagement.domain.valueObjects.ShopId;

import javax.persistence.*;

@Entity
public class Promotion {

    @Id
    @GeneratedValue
    private long promotionId;

    @Column(name = "promotionType", nullable = false)
    private PromotionType type;

    @Column(name = "sandwichId", nullable = false)
    private String sandwichId;

    @Column(name = "shopId", nullable = true)
    private ShopId shop;

    @Column(name = "percentage", nullable = false)
    private PromotionPercentage percentage;

    @Column(name = "startDate", nullable = false)
    private PromotionDate promotionDate;

    public Long getId() {
        return this.promotionId;
    }

    public void setId(Long id) {
        this.promotionId = id;
    }

    public Promotion(PromotionType promotionType, String sandwichId, ShopId shop, PromotionPercentage percentage, PromotionDate promotionDate) {
        this.type = promotionType;
        this.sandwichId = sandwichId;
        this.shop = shop;
        this.percentage = percentage;
        this.promotionDate = promotionDate;
    }

    public Promotion(PromotionType promotionType, String sandwichId, PromotionPercentage percentage, PromotionDate promotionDate) {

        this.type = promotionType;
        this.sandwichId = sandwichId;
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

    public String getSandwich() {
        return sandwichId;
    }

    public ShopId getShop() {
        return shop;
    }

    public PromotionPercentage getPercentage() {
        return percentage;
    }

    public PromotionDate getPromotionDate() {
        return promotionDate;
    }

}
