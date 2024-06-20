package com.example.SanwichManager.services.shop;

import com.example.SanwichManager.domain.entities.Shop;
import com.example.SanwichManager.domain.entities.ShopSchedule;
import com.example.SanwichManager.domain.enums.PromotionApplication;
import com.example.SanwichManager.domain.enums.StatusCode;
import com.example.SanwichManager.domain.valueObjects.shared.Address;
import com.example.SanwichManager.domain.valueObjects.shared.Designation;
import com.example.SanwichManager.domain.valueObjects.shared.Email;
import com.example.SanwichManager.dtos.result.ResponseDTO;
import com.example.SanwichManager.dtos.shop.InputShopDTO;
import com.example.SanwichManager.mappers.ShopMapper;
import com.example.SanwichManager.repositories.ScheduleRepository;
import com.example.SanwichManager.repositories.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
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
    public ResponseDTO newShop(InputShopDTO inputShopDTO) {
        try {

            Shop shop = shopMapper.toShop(inputShopDTO);
            Shop shopReturn = shopRepository.save(shop);
            if (shopReturn == null) {
                throw new NullPointerException("Error while saving");
            }
            return new ResponseDTO(StatusCode.OK.getCode(), shopMapper.toDTO(shopReturn));
        } catch (Exception e) {
            return new ResponseDTO(StatusCode.BAD_REQUEST.getCode(),
                    String.format("%s : %s", e.getClass(), e.getMessage()));
        }
    }

    @Override
    public ResponseDTO listAllShops() {
        try {
            return new ResponseDTO(StatusCode.OK.getCode(), this.shopRepository.findAll());
        } catch (Exception e) {
            return new ResponseDTO(StatusCode.BAD_REQUEST.getCode(),
                    String.format("%s : %s", e.getClass(), e.getMessage()));
        }
    }

    @Override
    public ResponseDTO listShopById(Long shopId) {
        try {
            Optional<Shop> optionalShop = this.shopRepository.getShopByShopId(shopId);
            if (shopId != null && optionalShop.isPresent()) {
                return new ResponseDTO(StatusCode.OK.getCode(), optionalShop.get());
            }
            return new ResponseDTO(StatusCode.BAD_REQUEST.getCode(),
                    String.format("The shop with shop id %s does not exist.", shopId));
        } catch (Exception e) {
            return new ResponseDTO(StatusCode.BAD_REQUEST.getCode(),
                    String.format("%s : %s", e.getClass(), e.getMessage()));
        }
    }

    @Override
    public ResponseDTO removeShop(Long shopId) {
        try {
            Optional<Shop> optionalShop = this.shopRepository.getShopByShopId(shopId);
            if (shopId != null && optionalShop.isPresent()) {
                this.scheduleRepository.deleteScheduleByShopId(shopId);
                this.shopRepository.deleteByShopId(shopId);
                return new ResponseDTO(StatusCode.DELETED.getCode(), String.format("Shop successfully deleted"));
            }
            return new ResponseDTO(StatusCode.BAD_REQUEST.getCode(),
                    String.format("The shop with shop id %s does not exist.", shopId));
        } catch (Exception e) {
            return new ResponseDTO(StatusCode.BAD_REQUEST.getCode(),
                    String.format("%s : %s", e.getClass(), e.getMessage()));
        }
    }

    @Override
    public ResponseDTO changePromotionApplication(Long shopId, String applicationType) {

        try {

            PromotionApplication promotionApplication = PromotionApplication.valueOf(applicationType);

            int updated = shopRepository.updatePromotionApplicatioType(shopId, promotionApplication.ordinal());

            if(updated == 0)
                throw new IllegalArgumentException("Shop promotion application update failed");

            return new ResponseDTO(StatusCode.UPDATED.getCode(), "Shop updated successfully");

        } catch (Exception e) {
            return new ResponseDTO(StatusCode.BAD_REQUEST.getCode(), String.format("%s : %s", e.getClass(), e.getMessage()));
        }
    }


    @Override
    public ResponseDTO editShop(Long shopId, InputShopDTO inputShopDTO){
        try {

            updateAddess(shopId, inputShopDTO.address);

            updateDesignation(shopId, inputShopDTO.designation);

            updateEmail(shopId, inputShopDTO.email);

            updateSchedule(shopId, inputShopDTO.schedule);

            return new ResponseDTO(StatusCode.UPDATED.getCode(), "Shop updated successfully");

        } catch(Exception e){
            return new ResponseDTO(StatusCode.BAD_REQUEST.getCode(), String.format("%s : %s", e.getClass(), e.getMessage()));
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

    private void updateSchedule(long shopId, Map<String,String> scheduleM){
        if(scheduleM != null){

            for(Map.Entry<String,String> entry: scheduleM.entrySet()){

                ShopSchedule schedule = new ShopSchedule(entry.getKey(), entry.getValue());

                int updated = shopRepository.updateSchedule(shopId, schedule.getDay(), schedule.getDate());

                if(updated == 0)
                    throw new IllegalArgumentException("Shop schedule update failed");
            }
        }
    }
}