package com.example.SanwichManager.services.costumer;

import com.example.SanwichManager.domain.entities.Costumer;
import com.example.SanwichManager.domain.valueObjects.CostumerNIF;
import com.example.SanwichManager.domain.valueObjects.shared.Email;
import com.example.SanwichManager.dtos.costumer.InputCostumerDTO;
import com.example.SanwichManager.dtos.result.ResponseDTO;
import com.example.SanwichManager.domain.enums.StatusCode;
import com.example.SanwichManager.mappers.CostumerMapper;
import com.example.SanwichManager.repositories.CostumerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CostumerService implements ICostumerService {

    @Autowired
    private CostumerRepository costumerRepository;

    @Autowired
    private CostumerMapper costumerMapper;

    @Override
    public ResponseDTO addCostumer(InputCostumerDTO inputCostumerDTO) {
        try {
            if (this.costumerRepository.findCostumerByCostumerEmail(new Email(inputCostumerDTO.costumerEmail)).isPresent()) {
                return new ResponseDTO(StatusCode.BAD_REQUEST.getCode(),
                        String.format("Costumer with the following email already exists: %s", inputCostumerDTO.costumerEmail));
            } else if (this.costumerRepository.findCostumerByCostumerNIF(new CostumerNIF(inputCostumerDTO.costumerNIF)).isPresent()) {
                return new ResponseDTO(StatusCode.BAD_REQUEST.getCode(),
                        String.format("Costumer with the following NIF already exists: %s", inputCostumerDTO.costumerNIF));
            }
            Costumer costumer = this.costumerMapper.toCostumer(inputCostumerDTO);
            Costumer savedCostumer = this.costumerRepository.save(costumer);
            return new ResponseDTO(StatusCode.CREATED.getCode(), this.costumerMapper.toCostumerDTO(savedCostumer));
        } catch (Exception e) {
            return new ResponseDTO(StatusCode.BAD_REQUEST.getCode(), String.format("%s : %s", e.getClass(), e.getMessage()));
        }
    }

    @Override
    public ResponseDTO editCostumer(Long costumerId, InputCostumerDTO inputCostumerDTO) {
        try {
            Optional<Costumer> optionalCostumer = this.costumerRepository.findById(costumerId);
            if (!optionalCostumer.isPresent()) {
                return new ResponseDTO(StatusCode.NOT_FOUND.getCode(), String.format("There is no costumer with the id: %s", costumerId));
            }

            String costumerEmail = inputCostumerDTO.costumerEmail;
            if (costumerEmail != null) {
                Optional<Costumer> optionalEmailCostumer = this.costumerRepository.findCostumerByCostumerEmail(new Email(costumerEmail));
                if (optionalEmailCostumer.isPresent()) {
                    return new ResponseDTO(StatusCode.BAD_REQUEST.getCode(), String.format("There is already a costumer with the email: %s", costumerEmail));
                }
            }

            String costumerNIF = inputCostumerDTO.costumerNIF;
            if (costumerNIF != null) {
                Optional<Costumer> optionalNIFCostumer = this.costumerRepository.findCostumerByCostumerNIF(new CostumerNIF(costumerNIF));
                if (optionalNIFCostumer.isPresent()) {
                    return new ResponseDTO(StatusCode.BAD_REQUEST.getCode(), String.format("There is already a costumer with the NIF: %s", costumerNIF));
                }
            }

            Costumer costumer = optionalCostumer.get();
            Costumer toBeSavedCostumer = this.costumerMapper.toCostumer(new InputCostumerDTO(inputCostumerDTO.costumerName != null ? inputCostumerDTO.costumerName : costumer.getCostumerName().getName(),
                    costumerEmail != null ? costumerEmail : costumer.getCostumerEmail().getEmail(), costumerNIF != null ? costumerNIF : costumer.getCostumerNIF().getCostumerNIF(),
                    inputCostumerDTO.costumerAddress != null ? inputCostumerDTO.costumerAddress : costumer.getCostumerAddress().getAddress()));

            this.costumerRepository.delete(costumer);
            Costumer savedCostumer = this.costumerRepository.save(toBeSavedCostumer);

            return new ResponseDTO(StatusCode.UPDATED.getCode(), this.costumerMapper.toCostumerDTO(savedCostumer));
        } catch (Exception e) {
            return new ResponseDTO(StatusCode.BAD_REQUEST.getCode(), String.format("%s : %s", e.getClass(), e.getMessage()));
        }
    }
}
