package com.example.promotionmanagement.domain.valueObjects;

import javax.persistence.Embeddable;

@Embeddable
public class SandwichId {

    private long sandwichId;

    public SandwichId() {
    }

    public SandwichId(long sandwichId) {
        this.sandwichId = sandwichId;
    }

    public long getSandwichId() {
        return sandwichId;
    }

    private void setSandwichId(long sandwichId) {
        this.sandwichId = sandwichId;
    }
    
}
