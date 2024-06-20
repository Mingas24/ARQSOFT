package com.example.SanwichManager.mappers;

import com.example.SanwichManager.domain.entities.Sandwich;
import com.example.SanwichManager.domain.entities.SandwichOrder;
import org.springframework.stereotype.Component;

@Component
public class SandwichOrderMapper {

    public SandwichOrder toSandwichOrder(Sandwich sandwich, Integer amount){
        return new SandwichOrder(sandwich,amount);
    }
}
