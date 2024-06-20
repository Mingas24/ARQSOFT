package com.example.SanwichManager.controllers;

import com.example.SanwichManager.dtos.result.ResponseDTO;
import com.example.SanwichManager.dtos.shop.InputShopDTO;
import com.example.SanwichManager.services.shop.IShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    private IShopService shopService;

    @PostMapping("/addShop")
    public ResponseDTO newShop(@RequestBody InputShopDTO inputShopDTO) {
        return shopService.newShop(inputShopDTO);
    }

    @GetMapping("/getAllShops")
    public ResponseDTO getAllShops() {
        return shopService.listAllShops();
    }

    @GetMapping("/getShopById/{shopId}")
    public ResponseDTO getShopById(@PathVariable("shopId") Long shopId) {
        return shopService.listShopById(shopId);
    }

    @DeleteMapping("/deleteShop/{shopId}")
    public ResponseDTO deleteShop(@PathVariable("shopId") Long shopId) {
        return shopService.removeShop(shopId);
    }

    @PutMapping("/{shopId}/changePromotionApplication/{applicationType}")
    public ResponseDTO changePromotionApplication(@PathVariable("shopId") Long shopId,
                                                  @PathVariable("applicationType") String applicationType){
        return shopService.changePromotionApplication(shopId, applicationType);
    }
    @PutMapping("/editShop/{shopId}")
    public ResponseDTO editShop(@PathVariable("shopId") Long shopId, @RequestBody InputShopDTO inputShopDTO){
        return shopService.editShop(shopId, inputShopDTO);
    }

}