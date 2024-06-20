package com.example.SanwichManager.domain.enums;

public enum PromotionApplication {
    Cumulative("Cumulative"),
    MostFavourable("MostFavourable");

    private final String promotionApplication;

    PromotionApplication(String promotionApplication){
        this.promotionApplication = promotionApplication;
    }

    public String getPromotionApplication(){
        return  promotionApplication;
    }
}
