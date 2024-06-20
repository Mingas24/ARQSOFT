package com.example.SanwichManager.services.manager;

import com.example.SanwichManager.mappers.ManagerMapper;
import com.example.SanwichManager.repositories.ManagerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class ManagerServiceTest {

    @Autowired
    private ManagerService managerService;

    @Autowired
    @MockBean
    private ManagerMapper managerMapper;

    @MockBean
    private ManagerRepository managerRepository;


    @Test
    void newManagerSuccess() {

//        final String name = "Rui";
//        final String email = "rui@gmail.com";
//
//        InputManagerDTO inputManagerDTO = new InputManagerDTO(name, email);
//
//        Manager manager = managerMapper.toManager(inputManagerDTO);
//
//        when(managerRepository.save(manager)).thenReturn(manager);
//        when(managerMapper.toManager(inputManagerDTO)).thenReturn(manager);
//
//        ResponseDTO actualResponse = managerService.newManager(inputManagerDTO);
//
//        ManagerDTO managerDTO = (ManagerDTO) actualResponse.getObject();
//
//        assertEquals(StatusCode.CREATED.getCode(), actualResponse.getStatusCode());
//        assertEquals(name, managerDTO.name);
//        assertEquals(email, managerDTO.email);
    }

    @Test
    void newManagerFailName() {

//        final String name = "Rui7";
//        final String email = "rui@gmail.com";
//
//        InputManagerDTO inputManagerDTO = new InputManagerDTO(name, email);
//
//        Manager manager = managerMapper.toManager(inputManagerDTO);
//
//        when(managerRepository.save(manager)).thenReturn(manager);
//
//        ResponseDTO actualResponse = managerService.newManager(inputManagerDTO);
//
//        String exceptionExpected = String.format("%s : %s", "IllegalArgumentException", "Incorrect name");
//
//        assertEquals(StatusCode.BAD_REQUEST.getCode(), actualResponse.getStatusCode());
//        assertEquals(exceptionExpected, actualResponse.getObject().toString());
    }

    @Test
    void newManagerFailEmail() {

//        final String name = "Rui";
//        final String email = "rui";
//
//        InputManagerDTO inputManagerDTO = new InputManagerDTO(name, email);
//
//        Manager manager = managerMapper.toManager(inputManagerDTO);
//
//        when(managerRepository.save(manager)).thenReturn(manager);
//
//        ResponseDTO actualResponse = managerService.newManager(inputManagerDTO);
//
//        String exceptionExpected = String.format("%s : %s", "IllegalArgumentException", "Incorrect email");
//
//        assertEquals(StatusCode.BAD_REQUEST.getCode(), actualResponse.getStatusCode());
//        assertEquals(exceptionExpected, actualResponse.getObject().toString());
    }
}