package com.example.SanwichManager.services.costumer;

import com.example.SanwichManager.dtos.costumer.InputCostumerDTO;
import com.example.SanwichManager.dtos.result.ResponseDTO;

public interface ICostumerService {

    ResponseDTO addCostumer(InputCostumerDTO inputCostumerDTO);

    ResponseDTO editCostumer(Long costumerId, InputCostumerDTO inputCostumerDTO);
}
