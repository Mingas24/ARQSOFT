package com.example.SanwichManager.services.sandwich;

import com.example.SanwichManager.mappers.SandwichMapper;
import com.example.SanwichManager.repositories.SandwichRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class SandwichServiceTest {

    @Autowired
    SandwichService sandwichService;

    @Autowired
    @MockBean
    SandwichRepository sandwichRepository;

    @Autowired
    SandwichMapper sandwichMapper;

   /* @Test
    void createSandwichSuccess(){

        final String sandwichPrice = "2.30";
        final String sandwichDesignation = "Long Chicken Barbeque";
        final String sandwichDescription = "Frango, molho BBQ, alface";

        InputSandwichDTO inputSandwichDTO = new InputSandwichDTO(sandwichPrice, sandwichDesignation, sandwichDescription);
        Sandwich sandwich = this.sandwichMapper.toSandwich(inputSandwichDTO);
        when(this.sandwichRepository.save(Mockito.any(Sandwich.class))).thenReturn(sandwich);

        ResponseDTO expectedResponse = new ResponseDTO(StatusCode.CREATED.getCode(), this.sandwichMapper.tosandwichDTO(sandwich));

        ResponseDTO actualResponse = this.sandwichService.addSandwich(inputSandwichDTO);

        assertEquals(actualResponse.statusCode, expectedResponse.statusCode);

        SandwichDTO actualSandwichDTO = (SandwichDTO) actualResponse.object;
        assertEquals(sandwichPrice, actualSandwichDTO.sandwichPrice);
        assertEquals(sandwichDescription, actualSandwichDTO.sandwichDescription);
        assertEquals(sandwichDesignation, actualSandwichDTO.sandwichDesignation);
    }*/

/*    @Test
    void createSandwichFailSandwichId(){
        final Long sandwichId = null;
        final String sandwichPrice = "2.30€";
        final String sandwichDesignation = "Long Chicken Barbeque";
        final String sandwichDescription = "Frango, molho BBQ, alface";

        SandwichDTO sandwichDTO = new SandwichDTO(sandwichId, sandwichPrice, sandwichDesignation, sandwichDescription);

        Sandwich sandwich =  sandwichMapper.sandwichDTOtoModel(sandwichDTO);

        when(sandwichRepository.save(sandwich)).thenReturn(sandwich);

        ResponseDTO actualResponse = sandwichService.addSandwich(sandwichDTO);

        String exceptionExpected = String.format("%s : %s", "IllegalArgumentException", "SandwichId cannot be null");

        assertEquals(StatusCode.BAD_REQUEST.getCode(), actualResponse.getStatusCode());
        assertEquals(exceptionExpected, actualResponse.getObject().toString());
    }*/
}
