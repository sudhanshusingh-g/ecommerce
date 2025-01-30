package com.example.ecommerce.service;

import com.example.ecommerce.exceptions.UserExistException;
import com.example.ecommerce.models.User;
import com.example.ecommerce.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    public void createUser(User user) {
        userRepository.findByEmail(user.getEmail())
                .ifPresent(existingUser->{
                    throw new UserExistException("User already exist.Try logging in!");
                });

        User newUser=User.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .password(user.getPassword())
                .profileImage(user.getProfileImage())
                .build();
        userRepository.save(newUser);
    }
}
