package com.example.ecommerce.exceptions;

public class UserExistException extends RuntimeException{
    public UserExistException(String message){
        super(message);
    }
}
