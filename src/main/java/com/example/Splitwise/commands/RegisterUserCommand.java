package com.example.Splitwise.commands;

import com.example.Splitwise.controllers.UserController;
import com.example.Splitwise.dtos.RegisterUserRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
@Component
public class RegisterUserCommand implements Command{
    //Register sujeet 12@34 88687
    private UserController userController;
    @Autowired
    public RegisterUserCommand(UserController userController){
        this.userController = userController;
    }
    @Override
    public boolean matches(String input) {
        List<String> inputs = Arrays.stream(input.split(" ")).toList();
        if(inputs.size()==4 && inputs.get(0).equals(CommandsKeywords.REGISTER)){
            return true;
        }
        return false;
    }

    @Override
    public void execute(String input) {
        List<String> inputs = Arrays.stream(input.split(" ")).toList();
        RegisterUserRequestDTO requestDTO = new RegisterUserRequestDTO();
        requestDTO.setName(inputs.get(1));
        requestDTO.setPassword(inputs.get(2));
        requestDTO.setPhoneNumber(inputs.get(3));
        userController.registerUser(requestDTO);
    }
}
