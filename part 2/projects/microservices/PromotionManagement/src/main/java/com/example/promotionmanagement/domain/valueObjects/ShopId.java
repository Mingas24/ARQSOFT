package com.example.promotionmanagement.domain.valueObjects;

import javax.persistence.Embeddable;

@Embeddable
public class ShopId {

    private long shopId;

    public ShopId() {
    }

    public ShopId(long shopId) {
        this.shopId = shopId;
    }

    public long getShopId() {
        return shopId;
    }

    private void setShopId(long shopId) {
        this.shopId = shopId;
    }
}
