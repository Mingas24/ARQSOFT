package com.example.SanwichManager.controllers;

import com.example.SanwichManager.dtos.costumer.InputCostumerDTO;
import com.example.SanwichManager.dtos.result.ResponseDTO;
import com.example.SanwichManager.services.costumer.ICostumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/costumer")
public class CostumerController {

    @Autowired
    private ICostumerService costumerService;

    @PostMapping("addCostumer")
    public ResponseDTO addCostumer(@RequestBody InputCostumerDTO inputCostumerDTO) {
        return this.costumerService.addCostumer(inputCostumerDTO);
    }

    @PutMapping("editCostumer/{costumerId}")
    public ResponseDTO editCostumer(@PathVariable(value = "costumerId") Long costumerId, @RequestBody InputCostumerDTO inputCostumerDTO) {
        return this.costumerService.editCostumer(costumerId, inputCostumerDTO);
    }
}
