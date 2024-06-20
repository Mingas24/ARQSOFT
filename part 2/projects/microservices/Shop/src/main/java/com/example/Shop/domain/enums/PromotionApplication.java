package com.example.Shop.domain.enums;

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
