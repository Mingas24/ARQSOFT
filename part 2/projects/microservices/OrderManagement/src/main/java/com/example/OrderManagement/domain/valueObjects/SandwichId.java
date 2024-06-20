package com.example.OrderManagement.domain.valueObjects;

import lombok.*;

import javax.persistence.Embeddable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Embeddable
public class SandwichId {

    private long sandwichId;
}