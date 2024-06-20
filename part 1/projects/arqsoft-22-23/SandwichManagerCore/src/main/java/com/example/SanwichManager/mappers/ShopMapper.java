package com.example.SanwichManager.mappers;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.SanwichManager.domain.entities.Shop;
import com.example.SanwichManager.domain.valueObjects.shared.Designation;
import com.example.SanwichManager.domain.valueObjects.shared.Email;
import com.example.SanwichManager.domain.entities.ShopSchedule;
import com.example.SanwichManager.domain.valueObjects.shared.Address;
import com.example.SanwichManager.dtos.shop.InputShopDTO;
import com.example.SanwichManager.dtos.shop.ShopDTO;
import com.example.SanwichManager.repositories.ShopRepository;

@Component
public class ShopMapper {

    @Autowired
    private ShopRepository shopRepository;

    public ShopDTO toDTO(Shop shop) {
        Map<String, String> schedule = new HashMap<>();

        for (int i = 0; i < shop.getShopSchedule().size(); i++) {
            schedule.put(shop.getShopSchedule().get(i).getDay(), shop.getShopSchedule().get(i).getDate());
        }

        return new ShopDTO(shop.getShopId(), shop.getAddress().getAddress(), shop.getEmail().getEmail(),
                shop.getDesignation().getDesignation(),schedule, shop.getPromotionApplication().getPromotionApplication());
    }

    public Shop toShop(InputShopDTO inputShopDTO) {

        List<ShopSchedule> schedule = new ArrayList<>();
        for (Map.Entry<String, String> entry : inputShopDTO.schedule.entrySet()) {
            schedule.add(new ShopSchedule(entry.getKey(), entry.getValue()));
        }

        return new Shop(new Designation(inputShopDTO.designation), new Email(inputShopDTO.email),
                new Address(inputShopDTO.address), schedule);
    }

    public Shop toShop(ShopDTO shopDTO) {
        return shopRepository.getReferenceById(shopDTO.shop_id);
    }

}
