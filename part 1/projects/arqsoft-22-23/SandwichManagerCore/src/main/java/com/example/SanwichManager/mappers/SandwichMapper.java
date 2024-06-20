package com.example.SanwichManager.mappers;


import com.example.SanwichManager.domain.entities.Sandwich;
import com.example.SanwichManager.domain.valueObjects.SandwichDescription;
import com.example.SanwichManager.domain.valueObjects.shared.Designation;
import com.example.SanwichManager.domain.valueObjects.shared.Price;
import com.example.SanwichManager.dtos.sandwich.InputSandwichDTO;
import com.example.SanwichManager.dtos.sandwich.SandwichDTO;
import org.springframework.stereotype.Component;

@Component
public class SandwichMapper {

    public SandwichDTO tosandwichDTO(Sandwich sandwich) {
        return new SandwichDTO(sandwich.getSandwichId(), sandwich.getSandwichPrice().getPrice(), sandwich.getSandwichDesignation().getDesignation(),
                sandwich.getSandwichDescription().getDescription());
    }

    public Sandwich toSandwich(InputSandwichDTO inputSandwichDTO) {
        return new Sandwich(new Price(inputSandwichDTO.sandwichPrice), new Designation(inputSandwichDTO.sandwichDesignation),
                new SandwichDescription(inputSandwichDTO.sandwichDescription));
    }

}
