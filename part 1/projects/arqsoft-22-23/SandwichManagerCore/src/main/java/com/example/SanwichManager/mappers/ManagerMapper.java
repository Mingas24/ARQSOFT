package com.example.SanwichManager.mappers;

import com.example.SanwichManager.domain.entities.Manager;
import com.example.SanwichManager.domain.entities.Shop;
import com.example.SanwichManager.domain.valueObjects.shared.Email;
import com.example.SanwichManager.domain.valueObjects.shared.Name;
import com.example.SanwichManager.dtos.manager.InputManagerDTO;
import com.example.SanwichManager.dtos.manager.ManagerDTO;
import com.example.SanwichManager.repositories.ManagerRepository;
import com.example.SanwichManager.repositories.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ManagerMapper {

    @Autowired
    private ManagerRepository managerRepository;

    @Autowired
    private ShopRepository shopRepository;

    public ManagerDTO toDTO(Manager manager){
        return new ManagerDTO(manager.getManagerId(), manager.getName().getName(), manager.getEmail().getEmail(), manager.getShop().getShopId());
    }

    public Manager toManager(InputManagerDTO inputManagerDTO){
        Shop shop = getShop(inputManagerDTO.shopId);
        return new Manager(new Name(inputManagerDTO.name), new Email(inputManagerDTO.email), shop);
    }

    public Manager toManager(ManagerDTO managerDTO){
        return managerRepository.getReferenceById(managerDTO.id);
    }

    private Shop getShop(Long shopId){
        Optional<Shop> optionalShop = this.shopRepository.getShopByShopId(shopId);
        if (!optionalShop.isPresent())
            throw new IllegalArgumentException(String.format("Shop with the following ID does not exist: %s", shopId));

        return optionalShop.get();
    }
}