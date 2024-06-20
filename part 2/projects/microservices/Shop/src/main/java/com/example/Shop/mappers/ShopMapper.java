package com.example.Shop.mappers;

import com.example.Shop.domain.entities.Shop;
import com.example.Shop.domain.entities.ShopSchedule;
import com.example.Shop.domain.valueObjects.Address;
import com.example.Shop.domain.valueObjects.Designation;
import com.example.Shop.domain.valueObjects.Email;
import com.example.Shop.dtos.shop.InputShopDTO;
import com.example.Shop.dtos.shop.ShopDTO;
import com.example.Shop.dtos.shop.ShopScheduleDTO;
import org.springframework.stereotype.Component;

import graphql.util.Pair;

import java.util.ArrayList;
import java.util.List;

@Component
public class ShopMapper {

    public ShopDTO toDTO(Shop shop) {
        List<ShopScheduleDTO> schedules = new ArrayList<>();

        for(ShopSchedule schedule: shop.getShopSchedule()){
            schedules.add(new ShopScheduleDTO(schedule.getId(), schedule.getDay(), schedule.getDate()));
        }

        return new ShopDTO(shop.getShopId(), shop.getAddress().getAddress(), shop.getEmail().getEmail(),
                shop.getDesignation().getDesignation(),schedules, shop.getPromotionApplication().getPromotionApplication());
    }

    public Shop toShop(InputShopDTO inputShopDTO) {

        List<ShopSchedule> schedule = new ArrayList<>();
        for (Pair<String, String> entry : inputShopDTO.schedule) {
            schedule.add(new ShopSchedule(entry.first, entry.second));
        }

        return new Shop(new Designation(inputShopDTO.designation), new Email(inputShopDTO.email),
                new Address(inputShopDTO.address), schedule);
    }
}
