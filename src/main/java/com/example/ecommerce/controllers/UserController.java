package com.example.ecommerce.controllers;

import com.example.ecommerce.dtos.RegisterRequest;
import com.example.ecommerce.models.User;
import com.example.ecommerce.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.security.Principal;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private UserService userService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserController(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping("/register")
    public String register(@RequestParam String email, @RequestParam String password, @RequestParam String name) {
        userService.registerUser(email, password, name);
        return "User registered successfully";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password) {
        User user = userService.findByEmail(email);
        if (user != null && bCryptPasswordEncoder.matches(password, user.getPassword())) {
            return "Login successful";
        }
        return "Invalid credentials";
    }

    @GetMapping("/login/oauth2/code/google")
    public String googleLogin(Principal principal) {
        if (principal instanceof OAuth2AuthenticationToken oAuthToken) {
            Map<String, Object> attributes = oAuthToken.getPrincipal().getAttributes();
            String googleId = (String) attributes.get("sub");
            User user = userService.findByGoogleId(googleId);
            if (user == null) {
                user = new User();
                user.setGoogleId(googleId);
                user.setEmail((String) attributes.get("email"));
                user.setName((String) attributes.get("name"));
                userService.registerUser(user.getEmail(), null, user.getName());
            }
            return "Google login successful";
        }
        return "Invalid OAuth2 login attempt";
    }
}
