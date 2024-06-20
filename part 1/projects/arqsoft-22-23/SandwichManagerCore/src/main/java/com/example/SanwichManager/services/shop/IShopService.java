package com.example.SanwichManager.services.shop;

import com.example.SanwichManager.dtos.result.ResponseDTO;
import com.example.SanwichManager.dtos.shop.InputShopDTO;
import org.springframework.stereotype.Service;
import java.util.List;
import com.example.SanwichManager.domain.entities.Shop;

@Service
public interface IShopService {

    ResponseDTO newShop(InputShopDTO inputShopDTO);

    ResponseDTO listAllShops();

    ResponseDTO listShopById(Long shopId);

    ResponseDTO removeShop(Long shopId);

    ResponseDTO changePromotionApplication(Long shopId, String applicationType);

    ResponseDTO editShop(Long shopId, InputShopDTO inputShopDTO);

}