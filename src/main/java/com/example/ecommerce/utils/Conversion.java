package com.example.ecommerce.utils;

import com.example.ecommerce.dtos.RegisterRequest;
import com.example.ecommerce.models.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public  class Conversion {
    public RegisterRequest userToDTO(User user){
        RegisterRequest dto=new RegisterRequest();
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setProfileImage(user.getProfileImage());
        dto.setPassword(user.getPassword());
        dto.setUsername(user.getUsername());
        return dto;
    }

    public User DTOToUser(RegisterRequest registerRequest){
        return User.builder()
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .email(registerRequest.getEmail())
                .profileImage(registerRequest.getProfileImage())
                .password(registerRequest.getPassword())
                .username(registerRequest.getUsername())
                .build();
    }
}
