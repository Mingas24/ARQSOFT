package com.example.SanwichManager.services.manager;

import com.example.SanwichManager.domain.entities.Manager;
import com.example.SanwichManager.domain.enums.StatusCode;
import com.example.SanwichManager.dtos.manager.InputManagerDTO;
import com.example.SanwichManager.dtos.result.ResponseDTO;
import com.example.SanwichManager.mappers.ManagerMapper;
import com.example.SanwichManager.repositories.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ManagerService implements IManagerService {

    @Autowired
    private ManagerRepository managerRepository;

    @Autowired
    private ManagerMapper managerMapper;

    @Override
    public ResponseDTO newManager(InputManagerDTO inputManagerDTO) {
        try {
            Manager manager = managerMapper.toManager(inputManagerDTO);
            Manager savedManager = managerRepository.save(manager);
            return new ResponseDTO(StatusCode.CREATED.getCode(), managerMapper.toDTO(savedManager));
        } catch (Exception e) {
            return new ResponseDTO(StatusCode.BAD_REQUEST.getCode(), String.format("%s : %s", e.getClass(), e.getMessage()));
        }
    }

    @Override
    public ResponseDTO changeManagerShop(Long managerId, Long shopId) {
        try {
            Optional<Manager> optionalManager = this.managerRepository.findById(managerId);
            if (!optionalManager.isPresent()) {
                return new ResponseDTO(StatusCode.NOT_FOUND.getCode(), String.format("There is no manager with the id: %s", managerId));
            }

            Manager manager = optionalManager.get();
            if (manager.getShop().getShopId() == shopId) {
                return new ResponseDTO(StatusCode.BAD_REQUEST.getCode(), String.format("The shop %s is already associated to the manager %s", shopId, manager.getName().getName()));
            }
            InputManagerDTO inputManagerDTO = new InputManagerDTO(manager.getName().getName(), manager.getEmail().getEmail(), shopId);
            Manager managerToBeSaved = managerMapper.toManager(inputManagerDTO);

            this.managerRepository.delete(manager);
            Manager savedManager = this.managerRepository.save(managerToBeSaved);
            return new ResponseDTO(StatusCode.CREATED.getCode(), managerMapper.toDTO(savedManager));
        } catch (Exception e) {
            return new ResponseDTO(StatusCode.BAD_REQUEST.getCode(), String.format("%s : %s", e.getClass(), e.getMessage()));
        }
    }
}