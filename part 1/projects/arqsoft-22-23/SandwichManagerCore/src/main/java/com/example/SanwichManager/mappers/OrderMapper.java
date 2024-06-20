package com.example.SanwichManager.mappers;

import com.example.SanwichManager.domain.entities.*;
import com.example.SanwichManager.domain.enums.OrderStatus;
import com.example.SanwichManager.domain.enums.PromotionApplication;
import com.example.SanwichManager.domain.valueObjects.OrderDate;
import com.example.SanwichManager.domain.valueObjects.shared.Email;
import com.example.SanwichManager.domain.valueObjects.shared.Price;
import com.example.SanwichManager.dtos.order.InputOrderDTO;
import com.example.SanwichManager.dtos.order.OrderDTO;
import com.example.SanwichManager.repositories.CostumerRepository;
import com.example.SanwichManager.repositories.PromotionRepository;
import com.example.SanwichManager.repositories.SandwichRepository;
import com.example.SanwichManager.repositories.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class OrderMapper {

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private SandwichRepository sandwichRepository;

    @Autowired
    private CostumerRepository costumerRepository;

    @Autowired
    private PromotionRepository promotionRepository;

    @Autowired
    private SandwichOrderMapper sandwichOrderMapper;

    public Order toOrder(InputOrderDTO inputOrderDTO, OrderStatus orderStatus) {
        Shop shop = getShop(inputOrderDTO.shopId);
        Costumer costumer = getCostumer(inputOrderDTO.costumerEmail);
        List<SandwichOrder> sandwichOrders = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : inputOrderDTO.sandwiches.entrySet()) {
            Optional optionalSandwich = this.sandwichRepository.getSandwichBySandwichId(Long.parseLong(entry.getKey()));

            if (!optionalSandwich.isPresent()) {
                throw new IllegalArgumentException(String.format("Sandwich with the following id does not exist: %s", entry.getKey()));
            }
            sandwichOrders.add(this.sandwichOrderMapper.toSandwichOrder((Sandwich) optionalSandwich.get(), entry.getValue()));
        }

        String price = calculatePrice(shop, sandwichOrders);
        return new Order(new OrderDate(inputOrderDTO.orderDate), new Price(price), orderStatus, costumer, shop, sandwichOrders);
    }

    public OrderDTO toOrderDTO(Order order) {
        Map<String, Integer> sandwichOrders = new HashMap<>();

        for (SandwichOrder sandwichOrder : order.getSandwichOrders()) {
            sandwichOrders.put(String.valueOf(sandwichOrder.getSandwich().getSandwichId()), sandwichOrder.getSandwichAmount());
        }
        return new OrderDTO(order.getId(), order.getOrderDate().getOrderDate(), order.getOrderPrice().getPrice()
                , order.getOrderStatus().getStatus(), String.valueOf(order.getShop().getShopId()), order.getCostumer().getCostumerEmail().getEmail(),
                sandwichOrders);
    }

    private Shop getShop(Long shopId) {
        Optional<Shop> optionalShop = this.shopRepository.getShopByShopId(shopId);
        if (!optionalShop.isPresent())
            throw new IllegalArgumentException(String.format("Shop with the following ID does not exist: %s", shopId));

        return optionalShop.get();
    }

    private Costumer getCostumer(String costumerEmail) {
        Optional<Costumer> optionalCostumer = this.costumerRepository.findCostumerByCostumerEmail(new Email(costumerEmail));
        if (!optionalCostumer.isPresent())
            throw new IllegalArgumentException(String.format(String.format("Costumer with the following email does not exist: %s", costumerEmail)));

        return optionalCostumer.get();
    }

    private String calculatePrice(Shop shop, List<SandwichOrder> sandwichOrders) {
        float totalPrice = 0;

        for (SandwichOrder sandwichOrder : sandwichOrders) {

            Sandwich sandwich = sandwichOrder.getSandwich();

            float localDiscount = getLocalDiscount(sandwich, shop);
            float globalDiscount = getGlobalDiscount(sandwich);

            if (shop.getPromotionApplication() == PromotionApplication.Cumulative) {

                totalPrice = calculateCumulativeDiscount(localDiscount, globalDiscount,
                        sandwich.getSandwichPrice().getPrice(), sandwichOrder.getSandwichAmount());

            } else {

                totalPrice = calculateMostFavourableDiscount(localDiscount, globalDiscount,
                        sandwich.getSandwichPrice().getPrice(), sandwichOrder.getSandwichAmount());
            }
        }

        return String.format("%.2f", totalPrice).replace(',', '.');
    }
    private float getLocalDiscount(Sandwich sandwich, Shop shop){

        Optional<Promotion> localPromotionOP = promotionRepository.getActiveLocalPromotionBySandwich(sandwich.getSandwichId(), shop.getShopId());

        if (localPromotionOP.isPresent()){
            return localPromotionOP.get().getPercentage().getPercentage();
        }

        return 0;
    }

    private float getGlobalDiscount(Sandwich sandwich){

        Optional<Promotion> globalPromotionOP = promotionRepository.getActiveGlobalPromotionBySandwich(sandwich.getSandwichId());

        if (globalPromotionOP.isPresent())
            return globalPromotionOP.get().getPercentage().getPercentage();

        return 0;
    }
    private float calculateCumulativeDiscount(float localDiscount, float globalDiscount, String priceS, int amount){

        float desconto = (globalDiscount + localDiscount) / 100;

        if (desconto > 1)
            desconto = 1;

        float price = Float.parseFloat(priceS);

        return amount * (price * (1 - desconto));
    }

    private float calculateMostFavourableDiscount(float localDiscount, float globalDiscount, String priceS, int amount){

        float desconto;

        if (localDiscount >= globalDiscount)
            desconto = localDiscount / 100;
        else
            desconto = globalDiscount / 100;

        float price = Float.parseFloat(priceS);

        return amount * (price * (1 - desconto));
    }
}
