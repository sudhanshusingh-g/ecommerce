package com.example.ecommerce.controllers;

import com.example.ecommerce.dtos.RegisterRequest;
import com.example.ecommerce.service.UserService;
import com.example.ecommerce.utils.Conversion;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private UserService userService;

    public UserController(RestTemplate restTemplate,
                          UserService userService){
        this.userService=userService;
    }


    @PostMapping
    public ResponseEntity<String> registerUser(@RequestBody RegisterRequest registerRequest) {
        userService.createUser(Conversion.DTOToUser(registerRequest));
        return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
    }
}
