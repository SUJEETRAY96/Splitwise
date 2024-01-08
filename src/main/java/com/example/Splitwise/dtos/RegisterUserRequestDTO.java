package com.example.Splitwise.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterUserRequestDTO {
    private String name;
    private String phoneNumber;
    private String password;
}
