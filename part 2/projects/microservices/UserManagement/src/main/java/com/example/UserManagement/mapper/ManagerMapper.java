package com.example.UserManagement.mapper;

import com.example.UserManagement.domain.entities.Manager;
import com.example.UserManagement.domain.valueObjects.manager.ShopId;
import com.example.UserManagement.domain.valueObjects.user.Email;
import com.example.UserManagement.domain.valueObjects.user.Name;
import com.example.UserManagement.domain.valueObjects.user.Username;
import com.example.UserManagement.dto.UserDTO;
import com.example.UserManagement.dto.manager.ManagerDTO;
import com.example.UserManagement.service.GraphQLRequestService;
import net.minidev.json.parser.ParseException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URISyntaxException;

@Component
public class ManagerMapper {

    public Manager toManager(ManagerDTO managerDTO){
        //ShopId shopId = getShop(managerDTO.shopId);
        return Manager.builder()
                .name(new Name(managerDTO.getName()))
                .email(new Email(managerDTO.getEmail()))
                .username(new Username(managerDTO.getUsername()))
                .shopId(new ShopId(managerDTO.shopId))
                .build();
    }

    public ManagerDTO toManagerDTO(Manager manager, Long shopId){
        return ManagerDTO.builder()
                .name(manager.getName().getName())
                .email(manager.getEmail().getEmail())
                .username(manager.getUsername().getUsername())
                .role(manager.getRole().getDescription())
                .shopId(shopId)
                .build();
    }

    public UserDTO toUserDTO(Manager manager){
        return UserDTO.builder()
                .name(manager.getName().getName())
                .email(manager.getEmail().getEmail())
                .username(manager.getUsername().getUsername())
                .role(manager.getRole().getDescription())
                .build();
    }

    private ShopId getShop(Long shopId){
        try {
            JSONObject shopResponse = GraphQLRequestService.callGraphQLService("http://localhost:8084/graphql/",
                    "query{getShopById(shopId: " + shopId + "){ shop_id designation email address } }");
            if (shopResponse.getJSONObject("shopDTO").isEmpty()){
                throw new IllegalArgumentException(String.format("Shop with the following ID does not exist: %s", shopId));
            }
            return new ShopId(Long.parseLong(shopResponse.getJSONObject("shopDTO").getString("shop_id")));
        } catch (URISyntaxException | IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
