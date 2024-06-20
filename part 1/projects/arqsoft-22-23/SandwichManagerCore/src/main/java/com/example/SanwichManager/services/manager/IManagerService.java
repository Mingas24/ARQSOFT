package com.example.SanwichManager.services.manager;

import com.example.SanwichManager.dtos.manager.InputManagerDTO;
import com.example.SanwichManager.dtos.result.ResponseDTO;

public interface IManagerService {

    ResponseDTO newManager(InputManagerDTO inputManagerDTO);

    ResponseDTO changeManagerShop(Long managerId, Long shopId);
}