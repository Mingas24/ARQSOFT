package com.example.promotionmanagement.mappers;

import com.example.promotionmanagement.domain.entities.Promotion;
import com.example.promotionmanagement.domain.enums.PromotionType;
import com.example.promotionmanagement.domain.valueObjects.PromotionDate;
import com.example.promotionmanagement.domain.valueObjects.PromotionPercentage;
import com.example.promotionmanagement.domain.valueObjects.ShopId;
import com.example.promotionmanagement.dtos.InputPromotionDTO;
import com.example.promotionmanagement.dtos.PromotionDTO;

import org.springframework.stereotype.Component;


@Component
public class PromotionMapper {

    // @Autowired
    // private ShopRepository shopRepository;

    public PromotionDTO toPromotionDTO(Promotion promotion) {

        if (promotion.getType().equals(PromotionType.Global)) {
            return globalToDTO(promotion);
        }
        return localToDTO(promotion);
    }

    private PromotionDTO localToDTO(Promotion promotion) {

        return new PromotionDTO(
                promotion.getPromotionId(),
                promotion.getType().name(),
                Long.parseLong(promotion.getSandwich()),
                promotion.getShop().getShopId(),
                promotion.getPercentage().getPercentage(),
                promotion.getPromotionDate().getStartDate(),
                promotion.getPromotionDate().getEndDate());
    }

    private PromotionDTO globalToDTO(Promotion promotion) {

        return new PromotionDTO(
        promotion.getPromotionId(),
        promotion.getType().name(),
        Long.parseLong(promotion.getSandwich()),
        promotion.getShop().getShopId(),
        promotion.getPercentage().getPercentage(),
        promotion.getPromotionDate().getStartDate(),
        promotion.getPromotionDate().getEndDate());
    }

    public Promotion toPromotion(InputPromotionDTO inputPromotionDTO) {

        if (PromotionType.valueOf(inputPromotionDTO.type).equals(PromotionType.Global)) {
            return globalToPromotion(inputPromotionDTO);
        }

        return localToPromotion(inputPromotionDTO);
    }

    private Promotion globalToPromotion(InputPromotionDTO inputPromotionDTO) {


        return new Promotion(
                PromotionType.Global,
                inputPromotionDTO.sandwichId.toString(),
                new ShopId(-1L),
                new PromotionPercentage(inputPromotionDTO.percentage),
                new PromotionDate(inputPromotionDTO.startDate, inputPromotionDTO.endDate)
        );
    }

    private Promotion localToPromotion(InputPromotionDTO inputPromotionDTO) {

        return new Promotion(
                PromotionType.Local,
                inputPromotionDTO.sandwichId.toString(),
                new ShopId(inputPromotionDTO.shopId),
                new PromotionPercentage(inputPromotionDTO.percentage),
                new PromotionDate(inputPromotionDTO.startDate, inputPromotionDTO.endDate)
        );
    }
}
