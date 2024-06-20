package com.example.OrderManagement.mapper;

import com.example.OrderManagement.domain.entities.*;
import com.example.OrderManagement.domain.enums.OrderStatus;
import com.example.OrderManagement.domain.enums.PromotionApplication;
import com.example.OrderManagement.domain.valueObjects.*;
import com.example.OrderManagement.dto.InputOrderDTO;
import com.example.OrderManagement.dto.OrderDTO;
import com.example.OrderManagement.service.GraphQLRequestService;
import graphql.util.Pair;
import net.minidev.json.parser.ParseException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

@Component
public class OrderMapper {

    @Autowired
    private SandwichOrderMapper sandwichOrderMapper;

    public Order toOrder(InputOrderDTO inputOrderDTO, OrderStatus orderStatus) {
        String shopPromotion = getShop(inputOrderDTO.shopId);
        ShopId shopId = new ShopId(inputOrderDTO.shopId);
        verifyCostumer(inputOrderDTO.costumerEmail);

        List<SandwichOrder> sandwichOrders = new ArrayList<>();
        for (Pair<String, String> entry : inputOrderDTO.sandwiches) {
            Long sandwichId = Long.parseLong(entry.first);
            getSandwichPriceBySandwichId(sandwichId);
            sandwichOrders.add(this.sandwichOrderMapper.toSandwichOrder(new SandwichId(sandwichId), Integer.valueOf(entry.second)));
        }
        String price = calculatePrice(shopId, shopPromotion, sandwichOrders);

        return Order.builder()
                .orderDate(new OrderDate(inputOrderDTO.orderDate))
                .orderPrice(new Price(price))
                .orderStatus(orderStatus)
                .costumerEmail(new Email(inputOrderDTO.costumerEmail))
                .shopId(shopId)
                .sandwichOrders(sandwichOrders)
                .build();
    }

    public OrderDTO toOrderDTO(Order order) {
        return OrderDTO.builder()
                .orderDate(order.getOrderDate().getOrderDate())
                .orderPrice(order.getOrderPrice().getPrice())
                .shopId(order.getShopId().getShopId())
                .costumerEmail(order.getCostumerEmail().getEmail())
                .status(order.getOrderStatus().getStatus())
                .build();
    }

    private float getSandwichPriceBySandwichId(Long sandwichId){
        try {
            JSONObject sandwichResponse = GraphQLRequestService.callGraphQLService("http://localhost:44388/graphql",
                    "query{getSandwichById(sandwichId:" + sandwichId + "){sandwichDTO}}");
            if (sandwichResponse.getJSONObject("sandwichDTO").isEmpty()){
                throw new IllegalArgumentException(String.format("Sandwich with the following ID does not exist: %s", sandwichId));
            }
            return sandwichResponse.getJSONObject("sandwichDTO").getFloat("price");
        } catch (URISyntaxException | IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private String getShop(Long shopId){
        try {
            JSONObject shopResponse = GraphQLRequestService.callGraphQLService("http://localhost:8084/graphql/",
                    "query{getShopById(shopId: " + shopId + "){ shop_id designation email address } }");
            if (shopResponse.getJSONObject("shopDTO").isEmpty()){
                throw new IllegalArgumentException(String.format("Shop with the following ID does not exist: %s", shopId));
            }
            return shopResponse.getJSONObject("shopDTO").getString("promotionApplication");
        } catch (URISyntaxException | IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private void verifyCostumer(String costumerEmail) {
        try {
            JSONObject costumerResponse = GraphQLRequestService.callGraphQLService("http://localhost:8082/graphql",
                    "query{getCostumerByEmail(costumerEmail:" + costumerEmail + "){CostumerDTO}}");
            if (costumerResponse.getJSONObject("CostumerDTO").isEmpty()){
                throw new IllegalArgumentException(String.format(String.format("Costumer with the following email does not exist: %s", costumerEmail)));
            }
        } catch (URISyntaxException | IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private String calculatePrice(ShopId shopId, String promotionApplication, List<SandwichOrder> sandwichOrders) {
        float totalPrice = 0;

        for (SandwichOrder sandwichOrder : sandwichOrders) {

            SandwichId sandwichId = sandwichOrder.getSandwichId();
            float localDiscount = getLocalDiscount(String.valueOf(sandwichId.getSandwichId()), shopId);
            float globalDiscount = getGlobalDiscount(String.valueOf(sandwichId.getSandwichId()));

            if (promotionApplication == PromotionApplication.Cumulative.getPromotionApplication()) {
                totalPrice += calculateCumulativeDiscount(localDiscount, globalDiscount,
                        String.valueOf(getSandwichPriceBySandwichId(sandwichId.getSandwichId())), sandwichOrder.getSandwichAmount());

            } else {
                totalPrice += calculateMostFavourableDiscount(localDiscount, globalDiscount,
                        String.valueOf(getSandwichPriceBySandwichId(sandwichId.getSandwichId())), sandwichOrder.getSandwichAmount());
            }
        }

        return String.format("%.2f", totalPrice).replace(',', '.');
    }

    public float getLocalDiscount(String sandwichId, ShopId shopId) {
        try {
            JSONObject localPromotionOP = GraphQLRequestService.callGraphQLService("http://localhost:8085/graphql",
                    "query{getActiveLocalPromotionBySandwich(sandwichId:" + sandwichId + "shopId:" + shopId + "){percentage}}");
            System.out.println(localPromotionOP);
            if (localPromotionOP.isEmpty()){
                throw new IllegalArgumentException("Something went wrong");
            }
            return localPromotionOP.getFloat("percentage");
        } catch (URISyntaxException | IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private float getGlobalDiscount(String sandwichId){
        try {
            JSONObject localPromotionOP = GraphQLRequestService.callGraphQLService("localhost:8085/graphql",
                    "query{getActiveGlobalPromotionBySandwich(sandwichId:"+sandwichId+"){ percentage } }");
            if (localPromotionOP.isEmpty()){
                throw new IllegalArgumentException("Something went wrong");

            }
            return localPromotionOP.getFloat("percentage");
        } catch (URISyntaxException | IOException | ParseException e) {
            throw new RuntimeException(e);
        }
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
