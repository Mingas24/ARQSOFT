package com.example.SanwichManager.mappers;

import com.example.SanwichManager.domain.entities.Promotion;
import com.example.SanwichManager.domain.entities.Sandwich;
import com.example.SanwichManager.domain.entities.Shop;
import com.example.SanwichManager.domain.valueObjects.PromotionPercentage;
import com.example.SanwichManager.domain.enums.PromotionType;
import com.example.SanwichManager.domain.valueObjects.PromotionDate;
import com.example.SanwichManager.dtos.promotion.InputPromotionDTO;
import com.example.SanwichManager.dtos.promotion.PromotionDTO;
import com.example.SanwichManager.repositories.SandwichRepository;
import com.example.SanwichManager.repositories.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PromotionMapper {

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private SandwichRepository sandwichRepository;

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
                promotion.getSandwich().getSandwichId(),
                promotion.getShop().getShopId(),
                promotion.getPercentage().getPercentage(),
                promotion.getPromotionDate().getStartDate(),
                promotion.getPromotionDate().getEndDate());
    }

    private PromotionDTO globalToDTO(Promotion promotion) {

        return new PromotionDTO(
                promotion.getPromotionId(),
                promotion.getType().name(),
                promotion.getSandwich().getSandwichId(),
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

        Sandwich sandwich = getSandwich(inputPromotionDTO.sandwichId);

        return new Promotion(
                PromotionType.Global,
                sandwich,
                new PromotionPercentage(inputPromotionDTO.percentage),
                new PromotionDate(inputPromotionDTO.startDate, inputPromotionDTO.endDate)
        );
    }

    private Promotion localToPromotion(InputPromotionDTO inputPromotionDTO) {

        Sandwich sandwich = getSandwich(inputPromotionDTO.sandwichId);

        Shop shop = getShop(inputPromotionDTO.shopId);

        return new Promotion(
                PromotionType.Local,
                sandwich,
                shop,
                new PromotionPercentage(inputPromotionDTO.percentage),
                new PromotionDate(inputPromotionDTO.startDate, inputPromotionDTO.endDate)
        );
    }

    private Shop getShop(long shopId) {

        Optional<Shop> shopOP = shopRepository.findById(shopId);
        if (!shopOP.isPresent()) {
            throw new IllegalArgumentException("Shop " + shopId + " not found in the system");
        }

        return shopOP.get();
    }

    private Sandwich getSandwich(long sandwichId) {
        Optional<Sandwich> sandwichOP = sandwichRepository.findById(sandwichId);

        if (!sandwichOP.isPresent())
            throw new IllegalArgumentException("Sandwich " + sandwichId + " not found in the system");

        return sandwichOP.get();
    }
}
