package com.example.SanwichManager.services.promotion;

import com.example.SanwichManager.dtos.promotion.InputPromotionDTO;
import com.example.SanwichManager.dtos.result.ResponseDTO;

public interface IPromotionService {

    ResponseDTO addPromotion(InputPromotionDTO inputPromotionDTO);

    ResponseDTO listPromotions();

    ResponseDTO listByPromotionsId(Long promotionId);

    ResponseDTO editPromotion(Long promotionId, InputPromotionDTO inputPromotionDTO);

    ResponseDTO deletePromotionById(Long promotionId);
}
