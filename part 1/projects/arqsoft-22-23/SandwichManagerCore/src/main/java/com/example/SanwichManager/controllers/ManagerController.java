package com.example.SanwichManager.controllers;

import com.example.SanwichManager.dtos.manager.InputManagerDTO;
import com.example.SanwichManager.dtos.result.ResponseDTO;
import com.example.SanwichManager.services.manager.IManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    private IManagerService managerService;

    @PostMapping("/addManager")
    public ResponseDTO newManager(@RequestBody InputManagerDTO inputManagerDTO){
        return managerService.newManager(inputManagerDTO);
    }

    @PutMapping("/changeManagerShop/{managerId}")
    public ResponseDTO changeManagerShop(@PathVariable(value = "managerId") Long managerId, @RequestBody Long shopId){
        return this.managerService.changeManagerShop(managerId, shopId);
    }
}