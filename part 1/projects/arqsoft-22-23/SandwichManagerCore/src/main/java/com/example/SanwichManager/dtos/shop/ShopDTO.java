package com.example.SanwichManager.dtos.shop;

import java.util.Map;

public class ShopDTO {

    public long shop_id;
    public Map<String,String> schedule;
    public String email;
    public String address;
    public String designation;
    public String promotionApplication;

    public ShopDTO(long id, String address, String email, String designation, 
    Map<String,String> schedule, String promotionApplication) {
        
        this.shop_id = id;
        this.address = address;
        this.email = email;
        this.designation = designation;
        this.schedule = schedule;
        this.promotionApplication = promotionApplication;
    }

}
