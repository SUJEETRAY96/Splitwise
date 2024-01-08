package com.example.Splitwise.services;

import com.example.Splitwise.exceptions.UserAlreadyExistWithThisPhone;
import com.example.Splitwise.models.User;
import com.example.Splitwise.models.UserStatus;
import com.example.Splitwise.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    public User registerUser(String userName, String phoneNumber, String password) throws UserAlreadyExistWithThisPhone {
        Optional<User> userOptional= userRepository.findByPhoneNumber(phoneNumber);
        if(userOptional.isPresent()){
            if(userOptional.get().getUserStatus().equals(UserStatus.ACTIVE)){
                throw new UserAlreadyExistWithThisPhone("User Already registered");
            }else{
                User user = userOptional.get();
                user.setName(userName);
                user.setPhoneNumber(phoneNumber);
                user.setPassword(password);
                user.setUserStatus(UserStatus.ACTIVE);
                return userRepository.save(user);
            }
        }
        User user = new User();
        user.setName(userName);
        user.setPhoneNumber(phoneNumber);
        user.setPassword(password);
        user.setUserStatus(UserStatus.ACTIVE);
        return userRepository.save(user);
    }
}
