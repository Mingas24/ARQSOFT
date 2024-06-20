package com.example.UserManagement.controller;

import com.example.UserManagement.dto.costumer.CostumerDTO;
import com.example.UserManagement.dto.manager.ManagerDTO;
import com.example.UserManagement.dto.result.ResponseDTO;
import com.example.UserManagement.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private IUserService userService;

    //MANAGER
    @MutationMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
    public ManagerDTO postManager(@Argument ManagerDTO managerDTO) {
        return this.userService.createManager(managerDTO);
    }

    //Test mutation purposes
    @QueryMapping
    public int getManager(@Argument String email){
        return 0;
    }

    @MutationMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
    public ManagerDTO changeManagerShop(@Argument Long managerId, @Argument Long shopId){
        return this.userService.changeManagerShop(managerId, shopId);
    }

    //COSTUMER
    @MutationMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER') or hasRole('ROLE_COSTUMER')")
    public CostumerDTO postCostumer(@Argument CostumerDTO costumerDTO) {
        return this.userService.createCostumer(costumerDTO);
    }

    @MutationMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER') or hasRole('ROLE_COSTUMER')")
    public CostumerDTO editCostumer(@Argument Long costumerId, @Argument CostumerDTO costumerDTO) {
        return this.userService.editCostumer(costumerId, costumerDTO);
    }

    @QueryMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER') or hasRole('ROLE_COSTUMER')")
    public CostumerDTO getCostumerByEmail(@Argument String costumerEmail) {
        return userService.getCostumerByEmail(costumerEmail);
    }

}
