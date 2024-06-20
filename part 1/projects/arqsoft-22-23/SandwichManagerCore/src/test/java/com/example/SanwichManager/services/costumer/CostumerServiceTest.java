package com.example.SanwichManager.services.costumer;

import com.example.SanwichManager.domain.entities.Costumer;
import com.example.SanwichManager.domain.enums.StatusCode;
import com.example.SanwichManager.dtos.costumer.CostumerDTO;
import com.example.SanwichManager.dtos.costumer.InputCostumerDTO;
import com.example.SanwichManager.dtos.result.ResponseDTO;
import com.example.SanwichManager.mappers.CostumerMapper;
import com.example.SanwichManager.repositories.CostumerRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CostumerServiceTest {

    @Autowired
    CostumerService costumerService;

    @Autowired
    @MockBean
    CostumerRepository costumerRepository;

    @Autowired
    CostumerMapper costumerMapper;

    @Test
    void should_create_one_costumer() {
        final String costumerName = "Daniela";
        final String costumerEmail = "maria@isep.pt";
        final String costumerNIF = "123456789";
        final String costumerAddress = "Rua Sao Tome N99";

        InputCostumerDTO inputCostumerDTO = new InputCostumerDTO(costumerName, costumerEmail, costumerNIF, costumerAddress);
        Costumer costumer = this.costumerMapper.toCostumer(inputCostumerDTO);
        when(this.costumerRepository.save(Mockito.any(Costumer.class))).thenReturn(costumer);
        ResponseDTO expectedResponse = new ResponseDTO(StatusCode.CREATED.getCode(), this.costumerMapper.toCostumerDTO(costumer));

        ResponseDTO actualResponse = this.costumerService.addCostumer(inputCostumerDTO);
        assertEquals(actualResponse.statusCode, expectedResponse.statusCode);

        CostumerDTO actualCostumerDTO = (CostumerDTO) actualResponse.object;
        assertEquals(actualCostumerDTO.costumerName, costumerName);
        assertEquals(actualCostumerDTO.costumerEmail, costumerEmail);
        assertEquals(actualCostumerDTO.costumerNIF, costumerNIF);
        assertEquals(actualCostumerDTO.costumerAddress, costumerAddress);
    }

    /*@Test
    void should_return_exception_for_unique_constraint_violation(){
        final String costumerName = "Daniela";
        final String costumerEmail = "maria@isep.pt";
        final String costumerNIF = "987654321";
        final String costumerAddress = "Rua Sao Tome N99";

        InputCostumerDTO inputCostumerDTO = new InputCostumerDTO(costumerName,costumerEmail,costumerNIF,costumerAddress);
        Costumer costumer = this.costumerMapper.toCostumer(inputCostumerDTO);
        when(this.costumerRepository.save(costumer)).thenReturn(null);
        ResponseDTO expectedResponse = new ResponseDTO(StatusCode.BAD_REQUEST.getCode(), new Exception("class org.springframework.dao.DataIntegrityViolationException : could not execute statement; SQL [n/a]; constraint [costumer.email]; nested exception is org.hibernate.exception.ConstraintViolationException: could not execute statement"));
        ResponseDTO actualResponse = this.costumerService.addCostumer(inputCostumerDTO);
        assertEquals(actualResponse.statusCode,expectedResponse.statusCode);

        Exception actualException = (Exception) actualResponse.object;
        Exception expectedException = (Exception) expectedResponse.object;
        assertEquals(actualException.getMessage(), expectedException.getMessage());
    }*/

    @Test
    void should_edit_one_costumer() {

        final Long id = Long.valueOf(30);
        final String costumerName = "Daniela";
        final String costumerEmail = "maria@isep.pt";
        final String costumerNIF = "123456789";
        final String costumerAddress = "Rua Sao Tome N99";

        InputCostumerDTO inputCostumerDTO = new InputCostumerDTO(costumerName, costumerEmail, costumerNIF, costumerAddress);
        Costumer costumer = this.costumerMapper.toCostumer(inputCostumerDTO);
        when(this.costumerRepository.findById(id)).thenReturn(Optional.ofNullable(costumer));

        final String newCostumerNif = "122456789";
        final String newCostumerAddress = "Rua Sao Tome N98";

        Costumer costumerToBeSaved = this.costumerMapper.toCostumer(new InputCostumerDTO(costumer.getCostumerName().getName(),
                costumer.getCostumerEmail().getEmail(), newCostumerNif, newCostumerAddress));
        when(this.costumerRepository.save(Mockito.any(Costumer.class))).thenReturn(costumerToBeSaved);
        ResponseDTO expectedResponse = new ResponseDTO(StatusCode.UPDATED.getCode(), this.costumerMapper.toCostumerDTO(costumerToBeSaved));

        ResponseDTO actualResponse = this.costumerService.editCostumer(id, new InputCostumerDTO(null, null, newCostumerNif, newCostumerAddress));
        assertEquals(actualResponse.statusCode, expectedResponse.statusCode);

        CostumerDTO actualCostumerDTO = (CostumerDTO) actualResponse.object;
        assertEquals(actualCostumerDTO.costumerName, costumerName);
        assertEquals(actualCostumerDTO.costumerEmail, costumerEmail);
        assertEquals(actualCostumerDTO.costumerNIF, newCostumerNif);
        assertEquals(actualCostumerDTO.costumerAddress, newCostumerAddress);
    }

  /*  @Test
    void should_return_exception_for_email_duplication(){

    }*/
}
