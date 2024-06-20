package com.example.Shop.dtos.shop;


import java.util.List;

public class ShopDTO {

    public long shop_id;
    public List<ShopScheduleDTO> schedule;
    public String email;
    public String address;
    public String designation;
    public String promotionApplication;

    public ShopDTO(long id, String address, String email, String designation,
                   List<ShopScheduleDTO> schedule, String promotionApplication) {
        
        this.shop_id = id;
        this.address = address;
        this.email = email;
        this.designation = designation;
        this.schedule = schedule;
        this.promotionApplication = promotionApplication;
    }

}
