package com.example.UserManagement.service;

import com.example.UserManagement.dto.costumer.CostumerDTO;
import com.example.UserManagement.dto.manager.ManagerDTO;
import com.example.UserManagement.dto.result.ResponseDTO;

public interface IUserService {

    ManagerDTO createManager(ManagerDTO managerDTO);
    ResponseDTO getUserByUsername(String username);
    ManagerDTO changeManagerShop(Long managerId, Long shopId);
    CostumerDTO createCostumer(CostumerDTO costumerDTO);
    CostumerDTO editCostumer(Long costumerId, CostumerDTO costumerDTO);
    CostumerDTO getCostumerByEmail(String email);
}
