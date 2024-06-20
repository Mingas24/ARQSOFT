package com.example.Shop.services;

import com.example.Shop.domain.entities.Shop;
import com.example.Shop.domain.entities.ShopSchedule;
import com.example.Shop.domain.enums.PromotionApplication;
import com.example.Shop.domain.enums.StatusCode;
import com.example.Shop.domain.valueObjects.Address;
import com.example.Shop.domain.valueObjects.Designation;
import com.example.Shop.domain.valueObjects.Email;
import com.example.Shop.dtos.shop.InputShopDTO;
import com.example.Shop.dtos.shop.ShopDTO;
import com.example.Shop.mappers.ShopMapper;
import com.example.Shop.repositories.ScheduleRepository;
import com.example.Shop.repositories.ShopRepository;
import graphql.GraphQLException;
import graphql.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShopService implements IShopService {

    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    private ShopMapper shopMapper;
    @Autowired
    private ScheduleRepository scheduleRepository;

    @Override
    public ShopDTO newShop(InputShopDTO inputShopDTO) {
        try {

            Shop shop = shopMapper.toShop(inputShopDTO);
            Shop shopReturn = shopRepository.save(shop);
            if (shopReturn == null) {
                throw new NullPointerException("Error while saving");
            }
            ShopDTO shopDTO = shopMapper.toDTO(shopReturn);
            return shopDTO;
        } catch (Exception e) {
            throw new GraphQLException(e.getMessage());
        }
    }

    @Override
    public List<ShopDTO> listAllShops() {
        try {
            List<ShopDTO> shopsDto = new ArrayList<>();

            for(Shop shop : this.shopRepository.findAll()){
                shopsDto.add(shopMapper.toDTO(shop));
            }

            return shopsDto;
        } catch (Exception e) {
            throw new GraphQLException(e.getMessage());
        }
    }

    @Override
    public ShopDTO listShopById(Long shopId) {
        try {
            Optional<Shop> optionalShop = this.shopRepository.getShopByShopId(shopId);
            if (shopId != null && optionalShop.isPresent()) {
                return shopMapper.toDTO(optionalShop.get());
            }
            throw new GraphQLException(String.format("The shop with shop id %s does not exist.", shopId));
        } catch (Exception e) {
            throw new GraphQLException(e.getMessage());
        }
    }

    @Override
    public Integer removeShop(Long shopId) {
        try {
            Optional<Shop> optionalShop = this.shopRepository.getShopByShopId(shopId);
            if (shopId != null && optionalShop.isPresent()) {
                this.scheduleRepository.deleteScheduleByShopId(shopId);
                this.shopRepository.deleteByShopId(shopId);

                return StatusCode.DELETED.getCode();
            }
            throw new GraphQLException(String.format("The shop with shop id %s does not exist.", shopId));
        } catch (Exception e) {
            throw new GraphQLException(e.getMessage());
        }
    }

    @Override
    public Integer changePromotionApplication(Long shopId, String applicationType) {

        try {

            PromotionApplication promotionApplication = PromotionApplication.valueOf(applicationType);

            int updated = shopRepository.updatePromotionApplicatioType(shopId, promotionApplication.ordinal());

            if(updated == 0)
                throw new GraphQLException("Shop promotion application update failed");

            return StatusCode.UPDATED.getCode();

        } catch (Exception e) {
            throw new GraphQLException(e.getMessage());
        }
    }


    @Override
    public Integer editShop(Long shopId, InputShopDTO inputShopDTO){
        try {

            updateAddess(shopId, inputShopDTO.address);

            updateDesignation(shopId, inputShopDTO.designation);

            updateEmail(shopId, inputShopDTO.email);

            updateSchedule(shopId, inputShopDTO.schedule);

            return StatusCode.UPDATED.getCode();

        } catch(Exception e){
            throw new GraphQLException(e.getMessage());
        }
    }

    private void updateAddess(long shopId, String addressS){
        if(addressS != null){
            Address address = new Address(addressS);
            int updated = shopRepository.updateAddress(shopId, address.getAddress());

            if(updated == 0)
                throw new IllegalArgumentException("Shop address update failed");
        }
    }

    private void updateDesignation(long shopId, String designationS){
        if(designationS != null){
            Designation designation = new Designation(designationS);
            int updated = shopRepository.updateDesignation(shopId, designation.getDesignation());

            if(updated == 0)
                throw new IllegalArgumentException("Shop designation update failed");
        }
    }

    private void updateEmail(long shopId, String emailS){
        if(emailS != null){
            Email email = new Email(emailS);
            int updated = shopRepository.updateEmail(shopId, email.getEmail());

            if(updated == 0)
                throw new IllegalArgumentException("Shop address update failed");
        }
    }

    private void updateSchedule(long shopId, List<Pair<String,String>> scheduleM){
        if(scheduleM != null){

            for(Pair<String,String> entry: scheduleM){

                ShopSchedule schedule = new ShopSchedule(entry.first, entry.second);

                int updated = shopRepository.updateSchedule(shopId, schedule.getDay(), schedule.getDate());

                if(updated == 0)
                    throw new IllegalArgumentException("Shop schedule update failed");
            }
        }
    }
}