package com.example.SanwichManager.services.sandwich;

import com.example.SanwichManager.domain.entities.Sandwich;
import com.example.SanwichManager.domain.enums.StatusCode;
import com.example.SanwichManager.domain.valueObjects.SandwichDescription;
import com.example.SanwichManager.domain.valueObjects.shared.Price;
import com.example.SanwichManager.dtos.result.ResponseDTO;
import com.example.SanwichManager.dtos.sandwich.InputSandwichDTO;
import com.example.SanwichManager.mappers.SandwichMapper;
import com.example.SanwichManager.repositories.SandwichRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SandwichService implements ISandwichService {

    @Autowired
    private SandwichRepository sandwichRepository;

    @Autowired
    private SandwichMapper sandwichMapper;

    @Override
    public ResponseDTO addSandwich(InputSandwichDTO inputSandwichDTO) {
        try {
            Sandwich sandwich = this.sandwichMapper.toSandwich(inputSandwichDTO);
            Sandwich savedSandwich = this.sandwichRepository.save(sandwich);
            return new ResponseDTO(StatusCode.CREATED.getCode(), this.sandwichMapper.tosandwichDTO(savedSandwich));
        } catch (Exception e) {
            return new ResponseDTO(StatusCode.BAD_REQUEST.getCode(), String.format("%s : %s", e.getClass(), e.getMessage()));
        }
    }

    @Override
    public ResponseDTO listAll() {
        try {
            return new ResponseDTO(StatusCode.OK.getCode(), this.sandwichRepository.findAll());
        } catch (Exception e) {
            return new ResponseDTO(StatusCode.BAD_REQUEST.getCode(), String.format("%s : %s", e.getClass(), e.getMessage()));
        }
    }

    @Override
    public ResponseDTO listBySandwichId(Long sandwichId) {
        try {
            Optional<Sandwich> optionalSandwich = this.sandwichRepository.getSandwichBySandwichId(sandwichId);
            if (sandwichId != null && optionalSandwich.isPresent()) {
                return new ResponseDTO(StatusCode.OK.getCode(), optionalSandwich.get());
            }
            return new ResponseDTO(StatusCode.BAD_REQUEST.getCode(), String.format("The sandwich with sandwich id %s does not exist.", sandwichId));
        } catch (Exception e) {
            return new ResponseDTO(StatusCode.BAD_REQUEST.getCode(), String.format("%s : %s", e.getClass(), e.getMessage()));
        }
    }

    @Override
    public ResponseDTO editSandwich(Long sandwichId, InputSandwichDTO inputSandwichDTO) {
        try {

            updatePrice(sandwichId, inputSandwichDTO.sandwichPrice);
            updateDescription(sandwichId, inputSandwichDTO.sandwichDescription);

            return new ResponseDTO(StatusCode.UPDATED.getCode(), "Sandwich updated successfully");

        } catch (Exception e) {
            return new ResponseDTO(StatusCode.BAD_REQUEST.getCode(), String.format("%s : %s", e.getClass(), e.getMessage()));
        }
    }

    private void updatePrice(long sandwichId, String priceS){

        if (priceS != null) {

            Price price = new Price(priceS);

            int updated = sandwichRepository.updateSandwichPrice(sandwichId, price.getPrice());

            if (updated == 0)
                throw new IllegalArgumentException("Sandwich price update failed");
        }
    }

    private void updateDescription(long sandwichId, String descriptionS){

        if (descriptionS != null) {

            SandwichDescription sandwichDescription = new SandwichDescription(descriptionS);

            int updated = sandwichRepository.updateSandwichDescription(sandwichId, sandwichDescription.getDescription());

            if (updated == 0)
                throw new IllegalArgumentException("Sandwich description update failed");
        }
    }

    @Override
    public ResponseDTO deleteSandwich(Long sandwichId) {
        try {
            Optional<Sandwich> optionalSandwich = this.sandwichRepository.findById(sandwichId);
            if (!optionalSandwich.isPresent()) {
                return new ResponseDTO(StatusCode.NOT_FOUND.getCode(), String.format("There is no sandwich with the id: %s", sandwichId));
            }
            this.sandwichRepository.deleteById(sandwichId);
            return new ResponseDTO(StatusCode.DELETED.getCode(), String.format("Sandwich successfully deleted!"));
        } catch (Exception e) {
            return new ResponseDTO(StatusCode.BAD_REQUEST.getCode(), String.format("%s : %s", e.getClass(), e.getMessage()));
        }
    }

}
