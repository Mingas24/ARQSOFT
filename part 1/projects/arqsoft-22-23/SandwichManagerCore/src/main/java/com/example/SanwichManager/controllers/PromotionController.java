package com.example.SanwichManager.controllers;

import com.example.SanwichManager.dtos.promotion.InputPromotionDTO;
import com.example.SanwichManager.dtos.result.ResponseDTO;
import com.example.SanwichManager.services.promotion.IPromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/promotion")
public class PromotionController {

    @Autowired
    private IPromotionService promotionService;

    @PostMapping("/addPromotion")
    public ResponseDTO addPromotion(@RequestBody InputPromotionDTO inputPromotionDTO){
        return promotionService.addPromotion(inputPromotionDTO);
    }

    @GetMapping("/listPromotions")
    public ResponseDTO listPromotions(){
        return promotionService.listPromotions();
    }

    @GetMapping("listPromotionById/{promotionId}")
    public ResponseDTO getPromotionById(@RequestParam Long promotionId){
        return promotionService.listByPromotionsId(promotionId);
    }

    @PutMapping("editPromotion/{promotionId}")
    public ResponseDTO editPromotion(@PathVariable("promotionId") Long promotionId, @RequestBody InputPromotionDTO inputPromotionDTO){
        return promotionService.editPromotion(promotionId, inputPromotionDTO);
    }

    @DeleteMapping("deletePromotionById/{promotionId}")
    public ResponseDTO deletePromotionById(@RequestParam Long promotionId){
        return promotionService.deletePromotionById(promotionId);
    }


}
