package com.example.Shop.services;

import com.example.Shop.dtos.shop.InputShopDTO;
import com.example.Shop.dtos.shop.ShopDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IShopService {

    ShopDTO newShop(InputShopDTO inputShopDTO);

    List<ShopDTO> listAllShops();

    ShopDTO listShopById(Long shopId);

    Integer removeShop(Long shopId);

    Integer changePromotionApplication(Long shopId, String applicationType);

    Integer editShop(Long shopId, InputShopDTO inputShopDTO);

}