package com.example.Splitwise.controllers;

import com.example.Splitwise.dtos.RegisterUserRequestDTO;
import com.example.Splitwise.dtos.RegisterUserResponseDTO;
import com.example.Splitwise.exceptions.UserAlreadyExistWithThisPhone;
import com.example.Splitwise.models.User;
import com.example.Splitwise.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    private UserService userService;
    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }
    public RegisterUserResponseDTO registerUser(RegisterUserRequestDTO registerUserRequestDTO){
        User user;
        RegisterUserResponseDTO responseDTO = new RegisterUserResponseDTO();;
        try{
            user = userService.registerUser(registerUserRequestDTO.getName(),
                    registerUserRequestDTO.getPhoneNumber(),
                    registerUserRequestDTO.getPassword());
            responseDTO.setUserId(user.getId());
            responseDTO.setStatus("SUCCESS");
        }catch (UserAlreadyExistWithThisPhone ex){
            responseDTO.setStatus("FAILURE");
        }
        return responseDTO;
    }
}
