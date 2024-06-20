package com.example.SanwichManager.services.promotion;

import com.example.SanwichManager.domain.entities.Promotion;
import com.example.SanwichManager.domain.enums.StatusCode;
import com.example.SanwichManager.domain.valueObjects.PromotionDate;
import com.example.SanwichManager.domain.valueObjects.PromotionPercentage;
import com.example.SanwichManager.dtos.promotion.InputPromotionDTO;
import com.example.SanwichManager.dtos.promotion.PromotionDTO;
import com.example.SanwichManager.dtos.result.ResponseDTO;
import com.example.SanwichManager.mappers.PromotionMapper;
import com.example.SanwichManager.repositories.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PromotionService implements IPromotionService {

    @Autowired
    private PromotionRepository promotionRepository;

    @Autowired
    private PromotionMapper promotionMapper;

    @Override
    public ResponseDTO addPromotion(InputPromotionDTO inputPromotionDTO) {

        try {
            final Promotion promotion = promotionMapper.toPromotion(inputPromotionDTO);

            final Promotion promotionSaved = promotionRepository.save(promotion);

            final PromotionDTO promotionDTO = promotionMapper.toPromotionDTO(promotionSaved);

            return new ResponseDTO(StatusCode.CREATED.getCode(), promotionDTO);

        } catch (Exception e) {
            return new ResponseDTO(StatusCode.BAD_REQUEST.getCode(), String.format("%s : %s", e.getClass(), e.getMessage()));
        }
    }

    @Override
    public ResponseDTO listPromotions(){
        try{
            return new ResponseDTO(StatusCode.OK.getCode(), this.promotionRepository.findAll());
        } catch (Exception e) {
            return new ResponseDTO(StatusCode.BAD_REQUEST.getCode(), String.format("%s : %s", e.getClass(), e.getMessage()));
        }
    }

    @Override
    public ResponseDTO listByPromotionsId(Long promotionId){
        try{
            Optional<Promotion> optionalPromotion = this.promotionRepository.getPromotionByPromotionId(promotionId);
            if(promotionId != null && optionalPromotion.isPresent()){
                return new ResponseDTO(StatusCode.OK.getCode(), optionalPromotion.get());
            }
            return new ResponseDTO(StatusCode.BAD_REQUEST.getCode(), String.format("Thje promotion with promotion id %s does not exist.", promotionId));
        } catch (Exception e){
            return new ResponseDTO(StatusCode.BAD_REQUEST.getCode(), String.format("%s : %s", e.getClass() , e.getMessage()));
        }
    }

    @Override
    public ResponseDTO editPromotion(Long promotionId, InputPromotionDTO inputPromotionDTO) {
        try{

            updatePercentage(promotionId, inputPromotionDTO.percentage);
            updateStartDate(promotionId, inputPromotionDTO.startDate);
            updateEndDate(promotionId, inputPromotionDTO.endDate);

            return new ResponseDTO(StatusCode.UPDATED.getCode(), "Promotion updated successfully");

        } catch(Exception e){
            return new ResponseDTO(StatusCode.BAD_REQUEST.getCode(), String.format("%s : %s", e.getClass(), e.getMessage()));
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
    public ResponseDTO deletePromotionById(Long promotionId){
        try{
            Optional<Promotion> optionalPromotion = this.promotionRepository.findById(promotionId);
            if(!optionalPromotion.isPresent()){
                return new ResponseDTO(StatusCode.NOT_FOUND.getCode(), String.format("There is no promotion with promotion id %s", promotionId));
            }
            this.promotionRepository.deleteById(promotionId);
            return new ResponseDTO(StatusCode.DELETED.getCode(), String.format("String successfully deleted!"));
        }catch (Exception e){
            return new ResponseDTO(StatusCode.BAD_REQUEST.getCode(), String.format("%s : %s", e.getClass(), e.getMessage()));
        }
    }
}
