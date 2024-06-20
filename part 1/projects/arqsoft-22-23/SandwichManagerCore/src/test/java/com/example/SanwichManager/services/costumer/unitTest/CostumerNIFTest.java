package com.example.SanwichManager.services.costumer.unitTest;

import com.example.SanwichManager.domain.valueObjects.CostumerNIF;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CostumerNIFTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    void createCostumerNIFSucess() {
        final String costumerNIFValue = "999999999";
        CostumerNIF costumerNIF = new CostumerNIF(costumerNIFValue);
        assertEquals(costumerNIFValue, costumerNIF.getCostumerNIF());
    }

    @Test
    void createCostumerNIFNullFail() {
        assertThrows(NullPointerException.class, () -> {
            final String costumerNIFValue = null;
            thrown.expect(Exception.class);
            new CostumerNIF(costumerNIFValue);
        });
    }

    @Test
    void createCostumerNIFEmptyRegexFail() {
        assertThrows(IllegalArgumentException.class, () -> {
            final String costumerNIFValue = "";
            thrown.expect(Exception.class);
            new CostumerNIF(costumerNIFValue);
        });
    }

    @Test
    void createCostumerNIFRegexLessNumbersFail() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            final String costumerNIFValue = "123";
            thrown.expect(Exception.class);
            new CostumerNIF(costumerNIFValue);
        });
        assertEquals("NIF should only have 9 numbers.", exception.getMessage());
    }

    @Test
    void createCostumerNIFRegexMoreNumbersFail() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            final String costumerNIFValue = "1239999999";
            thrown.expect(Exception.class);
            new CostumerNIF(costumerNIFValue);
        });
        assertEquals("NIF should only have 9 numbers.", exception.getMessage());
    }
}
