package com.example.SanwichManager.mappers;

import com.example.SanwichManager.domain.entities.Costumer;
import com.example.SanwichManager.domain.valueObjects.CostumerNIF;
import com.example.SanwichManager.domain.valueObjects.shared.Address;
import com.example.SanwichManager.domain.valueObjects.shared.Email;
import com.example.SanwichManager.domain.valueObjects.shared.Name;
import com.example.SanwichManager.dtos.costumer.CostumerDTO;
import com.example.SanwichManager.dtos.costumer.InputCostumerDTO;
import org.springframework.stereotype.Component;

@Component
public class CostumerMapper {

    public Costumer toCostumer(InputCostumerDTO inputCostumerDTO){
        return new Costumer(new Name(inputCostumerDTO.costumerName), new Email(inputCostumerDTO.costumerEmail),
                new CostumerNIF(inputCostumerDTO.costumerNIF), new Address(inputCostumerDTO.costumerAddress));
    }

    public CostumerDTO toCostumerDTO(Costumer costumer) {
        return new CostumerDTO(costumer.getId(), costumer.getCostumerName().getName(), costumer.getCostumerEmail().getEmail(),
                costumer.getCostumerNIF().getCostumerNIF(), costumer.getCostumerAddress().getAddress());
    }
}
