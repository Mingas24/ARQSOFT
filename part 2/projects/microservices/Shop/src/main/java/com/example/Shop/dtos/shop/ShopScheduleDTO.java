package com.example.Shop.dtos.shop;

public class ShopScheduleDTO {

    public Long schedule_id;
    public String day;
    public String date;

    public ShopScheduleDTO(Long schedule_id, String day, String date) {
        this.schedule_id = schedule_id;
        this.day = day;
        this.date = date;
    }

    public ShopScheduleDTO() {
    }
}
