package com.example.promotionmanagement.controller;

import com.example.promotionmanagement.dtos.PromotionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.promotionmanagement.dtos.InputPromotionDTO;
import com.example.promotionmanagement.service.IPromotionService;

import java.util.List;

@RestController
public class PromotionController {

    @Autowired
    private IPromotionService promotionService;

    @MutationMapping
    public PromotionDTO addPromotion(@Argument InputPromotionDTO inputPromotionDTO){
        return promotionService.addPromotion(inputPromotionDTO);
    }

    @QueryMapping
    public List<PromotionDTO> listPromotions(){
        return promotionService.listPromotions();
    }

    @QueryMapping
    public PromotionDTO getPromotionById(@Argument Long promotionId){
        return promotionService.listByPromotionsId(promotionId);
    }

    @QueryMapping
    public PromotionDTO getActiveLocalPromotionBySandwich(@Argument Long sandwichId, @Argument Long shopId){
        return promotionService.getActiveLocalPromotionBySandwich(sandwichId,shopId);
    }

    @QueryMapping
    public PromotionDTO getActiveGlobalPromotionBySandwich(@Argument Long sandwichId){
        return promotionService.getActiveGlobalPromotionBySandwich(sandwichId);
    }

    @MutationMapping
    public Integer editPromotion(@Argument Long promotionId, @Argument InputPromotionDTO inputPromotionDTO){
        return promotionService.editPromotion(promotionId, inputPromotionDTO);
    }

    @MutationMapping
    public Integer deletePromotionById(@Argument Long promotionId){
        return promotionService.deletePromotionById(promotionId);
    }


}
