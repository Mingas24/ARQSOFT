package com.example.promotionmanagement.dtos;

public class InputPromotionDTO {

    public Long sandwichId;
    public String type;
    public Long shopId;
    public Float percentage;
    public String startDate;
    public String endDate;

    public InputPromotionDTO(long sandwichId, String type, long shopId, float percentage, String startDate, String endDate) {
        this.sandwichId = sandwichId;
        this.type = type;
        this.shopId = shopId;
        this.percentage = percentage;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
