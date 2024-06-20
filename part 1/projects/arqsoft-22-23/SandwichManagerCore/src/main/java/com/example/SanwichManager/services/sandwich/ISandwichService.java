package com.example.SanwichManager.services.sandwich;

import com.example.SanwichManager.dtos.result.ResponseDTO;
import com.example.SanwichManager.dtos.sandwich.InputSandwichDTO;

public interface ISandwichService {

    ResponseDTO addSandwich(InputSandwichDTO inputSandwichDTO);

    ResponseDTO listAll();

    ResponseDTO listBySandwichId(Long sandwichId);

    ResponseDTO editSandwich(Long sandwichId, InputSandwichDTO inputSandwichDTO);

    ResponseDTO deleteSandwich(Long sandwichId);


}
