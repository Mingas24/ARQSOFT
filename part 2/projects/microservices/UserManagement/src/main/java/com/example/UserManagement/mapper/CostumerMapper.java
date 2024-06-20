package com.example.UserManagement.mapper;

import com.example.UserManagement.domain.entities.Costumer;
import com.example.UserManagement.domain.entities.Manager;
import com.example.UserManagement.domain.valueObjects.costumer.Address;
import com.example.UserManagement.domain.valueObjects.costumer.CostumerNIF;
import com.example.UserManagement.domain.valueObjects.user.Email;
import com.example.UserManagement.domain.valueObjects.user.Name;
import com.example.UserManagement.domain.valueObjects.user.Username;
import com.example.UserManagement.dto.UserDTO;
import com.example.UserManagement.dto.costumer.CostumerDTO;
import com.example.UserManagement.dto.manager.ManagerDTO;
import org.springframework.stereotype.Component;

@Component
public class CostumerMapper {

    public Costumer toCostumer(CostumerDTO costumerDTO){
        return Costumer.builder()
                .name(new Name(costumerDTO.getName()))
                .email(new Email(costumerDTO.getEmail()))
                .username(new Username(costumerDTO.getUsername()))
                .costumerNIF(new CostumerNIF(costumerDTO.getCostumerNIF()))
                .costumerAddress(new Address(costumerDTO.getAddress()))
                .build();
    }

    public CostumerDTO toCostumerDTO(Costumer costumer){
        return CostumerDTO.builder()
                .name(costumer.getName().getName())
                .email(costumer.getEmail().getEmail())
                .username(costumer.getUsername().getUsername())
                .role(costumer.getRole().getDescription())
                .costumerNIF(costumer.getCostumerNIF().getCostumerNIF())
                .address(costumer.getCostumerAddress().getAddress())
                .build();
    }

    public UserDTO toUserDTO(Costumer manager){
        return UserDTO.builder()
                .name(manager.getName().getName())
                .email(manager.getEmail().getEmail())
                .username(manager.getUsername().getUsername())
                .role(manager.getRole().getDescription())
                .build();
    }
}
