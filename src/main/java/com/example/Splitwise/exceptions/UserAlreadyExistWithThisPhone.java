package com.example.Splitwise.exceptions;

public class UserAlreadyExistWithThisPhone extends RuntimeException{
    public UserAlreadyExistWithThisPhone(String msj){
        super(msj);
    }
}
