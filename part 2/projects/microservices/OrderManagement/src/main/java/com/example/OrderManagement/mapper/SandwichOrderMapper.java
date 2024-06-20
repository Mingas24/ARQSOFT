package com.example.OrderManagement.mapper;

import com.example.OrderManagement.domain.entities.SandwichOrder;
import com.example.OrderManagement.domain.valueObjects.SandwichId;
import org.springframework.stereotype.Component;

@Component
public class SandwichOrderMapper {

    public SandwichOrder toSandwichOrder(SandwichId sandwichId, Integer amount){
        return SandwichOrder.builder()
                .sandwichId(sandwichId)
                .sandwichAmount(amount)
                .build();
    }
}
