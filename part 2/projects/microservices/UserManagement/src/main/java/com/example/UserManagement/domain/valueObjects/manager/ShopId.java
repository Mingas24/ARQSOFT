package com.example.UserManagement.domain.valueObjects.manager;

import lombok.*;

import javax.persistence.Embeddable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Embeddable
public class ShopId {

    private long shopId;
}