package com.example.Splitwise.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
public class User extends BaseModel{
    private String name;
    private String phoneNumber;
    private String password;

    @Enumerated(EnumType.ORDINAL)
    private UserStatus userStatus;

    @ManyToMany(mappedBy = "members")
    private List<Group> groupList;
}
