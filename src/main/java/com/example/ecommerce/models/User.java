package com.example.ecommerce.models;

import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User extends BaseModel {
    private String email;
    private String password;
    private String name;
    private String googleId;
}
