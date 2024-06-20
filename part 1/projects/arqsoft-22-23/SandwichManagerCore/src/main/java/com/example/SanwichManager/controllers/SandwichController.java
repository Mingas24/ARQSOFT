package com.example.SanwichManager.controllers;

import com.example.SanwichManager.dtos.result.ResponseDTO;
import com.example.SanwichManager.dtos.sandwich.InputSandwichDTO;
import com.example.SanwichManager.services.sandwich.ISandwichService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sandwich")
public class SandwichController {

    @Autowired
    private ISandwichService sandwichService;

    @PostMapping("/addSandwich")
    public ResponseDTO addSandwich(@RequestBody InputSandwichDTO inputSandwichDTO) {
        return sandwichService.addSandwich(inputSandwichDTO);
    }

    @GetMapping("/getAllSandwich")
    public ResponseDTO getAllSandwich() {
        return sandwichService.listAll();
    }

    @GetMapping("/getSandwichById/{sandwichId}")
    public ResponseDTO getSandwichById(@RequestParam Long sandwichId) {
        return sandwichService.listBySandwichId(sandwichId);
    }

    @PutMapping("/editSandwich/{sandwichId}")
    public ResponseDTO editSandwich(@PathVariable("sandwichId") Long sandwichId, @RequestBody InputSandwichDTO inputSandwichDTO) {
        return sandwichService.editSandwich(sandwichId, inputSandwichDTO);
    }

    @DeleteMapping("/deleteSandwich/{sandwichId}")
    public ResponseDTO deleteSandwich(@PathVariable("sandwichId") Long sandwichId) {
        return sandwichService.deleteSandwich(sandwichId);
    }

}
