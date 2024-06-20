package com.example.promotionmanagement.service;

import graphql.GraphQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.promotionmanagement.domain.entities.Promotion;
import com.example.promotionmanagement.domain.enums.StatusCode;
import com.example.promotionmanagement.domain.valueObjects.PromotionDate;
import com.example.promotionmanagement.domain.valueObjects.PromotionPercentage;
import com.example.promotionmanagement.dtos.InputPromotionDTO;
import com.example.promotionmanagement.dtos.PromotionDTO;
import com.example.promotionmanagement.dtos.ResponseDTO;
import com.example.promotionmanagement.mappers.PromotionMapper;
import com.example.promotionmanagement.repositories.PromotionRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PromotionService implements IPromotionService {

    @Autowired
    private PromotionRepository promotionRepository;

    @Autowired
    private PromotionMapper promotionMapper;

    @Override
    public PromotionDTO addPromotion(InputPromotionDTO inputPromotionDTO) {

        try {
            Promotion promotion = promotionMapper.toPromotion(inputPromotionDTO);
            Promotion promotionSaved = promotionRepository.save(promotion);
            return promotionMapper.toPromotionDTO(promotionSaved);

        } catch (Exception e) {
            throw new GraphQLException(e.getMessage());
        }
    }

    @Override
    public List<PromotionDTO> listPromotions(){
        try{
            List<PromotionDTO> promotionsDto = new ArrayList<>();
            for(Promotion promotion : this.promotionRepository.findAll()){
                promotionsDto.add(promotionMapper.toPromotionDTO(promotion));
            }
            return promotionsDto;
        } catch (Exception e) {
            throw new GraphQLException(e.getMessage());
        }
    }

    @Override
    public PromotionDTO listByPromotionsId(Long promotionId){
        try{
            Optional<Promotion> optionalPromotion = this.promotionRepository.getPromotionByPromotionId(promotionId);
            if(promotionId != null && optionalPromotion.isPresent()){
                return promotionMapper.toPromotionDTO(optionalPromotion.get());
            }
            throw new GraphQLException(String.format("The promotion with the id %s does not exist.", promotionId));
        } catch (Exception e){
            throw new GraphQLException(e.getMessage());
        }
    }

    @Override
    public PromotionDTO getActiveLocalPromotionBySandwich(Long sandwichId, Long shopId) {
        try{
            Optional<Promotion> optionalPromotion = this.promotionRepository.getActiveLocalPromotionBySandwich(sandwichId,shopId);
            if(optionalPromotion != null && optionalPromotion.isPresent()){
                return promotionMapper.toPromotionDTO(optionalPromotion.get());
            }
                throw new GraphQLException(String.format("The promotion with the id %s does not exist.", optionalPromotion.get().getPromotionId()));
        } catch (Exception e){
            throw new GraphQLException(e.getMessage());
        }
    }

    @Override
    public PromotionDTO getActiveGlobalPromotionBySandwich(Long sandwichId) {
        try{
            Optional<Promotion> optionalPromotion = this.promotionRepository.getActiveGlobalPromotionBySandwich(sandwichId);
            if(optionalPromotion != null && optionalPromotion.isPresent()){
                return promotionMapper.toPromotionDTO(optionalPromotion.get());
            }
            throw new GraphQLException(String.format("The promotion with the id %s does not exist.", optionalPromotion.get().getPromotionId()));
        } catch (Exception e){
            throw new GraphQLException(e.getMessage());
        }
    }

    @Override
    public Integer editPromotion(Long promotionId, InputPromotionDTO inputPromotionDTO) {
        try{

            updatePercentage(promotionId, inputPromotionDTO.percentage);
            updateStartDate(promotionId, inputPromotionDTO.startDate);
            updateEndDate(promotionId, inputPromotionDTO.endDate);

            return StatusCode.UPDATED.getCode();

        } catch(Exception e){
            throw new GraphQLException(e.getMessage());
        }
    }

    private void updatePercentage(long promotionId, Float percentageF){

        if(percentageF != null){

            PromotionPercentage percentage = new PromotionPercentage(percentageF);

            int updated = promotionRepository.updatePercentage(promotionId, percentage.getPercentage());

            if (updated == 0)
                throw new IllegalArgumentException("Promotion percentage update failed");
        }
    }

    private void updateStartDate(long promotionId, String startDate){

        if(startDate != null){

            PromotionDate date = new PromotionDate(startDate, startDate);
            int updated = promotionRepository.updateStartDate(promotionId, date.getStartDate());

            if (updated == 0)
                throw new IllegalArgumentException("Promotion start date update failed");
        }
    }

    private void updateEndDate(long promotionId, String endDate){

        if(endDate != null){

            PromotionDate date = new PromotionDate(endDate, endDate);
            int updated = promotionRepository.updateEndDate(promotionId, date.getEndDate());

            if (updated == 0)
                throw new IllegalArgumentException("Promotion start date update failed");
        }
    }

    @Override
    public Integer deletePromotionById(Long promotionId){
        try{
            Optional<Promotion> optionalPromotion = this.promotionRepository.findById(promotionId);
            if(optionalPromotion.isPresent()){
                this.promotionRepository.deleteById(promotionId);
                return StatusCode.DELETED.getCode();
            }
            throw new GraphQLException(String.format("The promotion with the id %s does not exist.", promotionId));
        } catch (Exception e) {
            throw new GraphQLException(e.getMessage());
        }
    }
}
