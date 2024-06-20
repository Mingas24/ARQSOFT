package com.example.promotionmanagement.service;

import com.example.promotionmanagement.dtos.InputPromotionDTO;
import com.example.promotionmanagement.dtos.PromotionDTO;
import com.example.promotionmanagement.dtos.ResponseDTO;

import java.util.List;

public interface IPromotionService {

    PromotionDTO addPromotion(InputPromotionDTO inputPromotionDTO);

    List<PromotionDTO> listPromotions();

    PromotionDTO listByPromotionsId(Long promotionId);

    PromotionDTO getActiveLocalPromotionBySandwich(Long sandwichId,Long shopId);

    PromotionDTO getActiveGlobalPromotionBySandwich(Long sandwichId);

    Integer editPromotion(Long promotionId, InputPromotionDTO inputPromotionDTO);

    Integer deletePromotionById(Long promotionId);
}
