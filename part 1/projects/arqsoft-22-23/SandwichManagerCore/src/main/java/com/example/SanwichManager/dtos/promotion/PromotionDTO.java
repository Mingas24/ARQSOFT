package com.example.SanwichManager.dtos.promotion;

public class PromotionDTO {

    public long promotionId;
    public String type;
    public long sandwichId;
    public long shopId;
    public float percentage;
    public String startDate;
    public String endDate;

    public PromotionDTO(long promotionId, String type, long sandwichId, long shopId, float percentage, String startDate, String endDate) {
        this.promotionId = promotionId;
        this.type = type;
        this.sandwichId = sandwichId;
        this.shopId = shopId;
        this.percentage = percentage;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
