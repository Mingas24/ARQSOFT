package com.example.SanwichManager.services.costumer.unitTest;

import com.example.SanwichManager.domain.entities.Costumer;
import com.example.SanwichManager.dtos.costumer.InputCostumerDTO;
import com.example.SanwichManager.mappers.CostumerMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CostumerTest {

    @Autowired
    CostumerMapper costumerMapper;

   @Test
    void should_create_costumer_object(){
       final String costumerName = "Daniela";
       final String costumerEmail = "maria@isep.pt";
       final String costumerNIF = "123456789";
       final String costumerAddress = "Rua Sao Tome N99";

       InputCostumerDTO inputCostumerDTO = new InputCostumerDTO(costumerName,costumerEmail,costumerNIF,costumerAddress);
       Costumer costumer = this.costumerMapper.toCostumer(inputCostumerDTO);
       assertEquals(costumer.getCostumerName().getName(),costumerName);
       assertEquals(costumer.getCostumerEmail().getEmail(),costumerEmail);
       assertEquals(costumer.getCostumerNIF().getCostumerNIF(),costumerNIF);
       assertEquals(costumer.getCostumerAddress().getAddress(),costumerAddress);
   }
}
