package com.example.ecommerce.models;

import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User extends BaseModel {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String profileImage;
}
