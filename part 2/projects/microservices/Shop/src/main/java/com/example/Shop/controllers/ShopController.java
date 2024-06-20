package com.example.Shop.controllers;

import com.example.Shop.dtos.shop.InputShopDTO;
import com.example.Shop.dtos.shop.ShopDTO;
import com.example.Shop.services.IShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ShopController {

    @Autowired
    private IShopService shopService;

    @MutationMapping
    public ShopDTO addShop(@Argument InputShopDTO inputShopDTO) {
        return shopService.newShop(inputShopDTO);
    }

    @QueryMapping
    public List<ShopDTO> getAllShops() {
        return shopService.listAllShops();
    }

    @QueryMapping
    public ShopDTO getShopById(@Argument Long shopId) {
        return shopService.listShopById(shopId);
    }

    @MutationMapping
    public Integer deleteShop(@Argument Long shopId) {
        return shopService.removeShop(shopId);
    }

    @MutationMapping
    public Integer changePromotionApplication(@Argument Long shopId,
                                                  @Argument String applicationType){
        return shopService.changePromotionApplication(shopId, applicationType);
    }
    @MutationMapping
    public Integer editShop(@Argument Long shopId, @Argument InputShopDTO inputShopDTO){
        return shopService.editShop(shopId, inputShopDTO);
    }

}