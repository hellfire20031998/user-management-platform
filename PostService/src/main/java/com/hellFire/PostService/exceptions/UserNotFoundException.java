package com.hellFire.PostService.exceptions;

public class UserNotFoundException extends Exception{
    public UserNotFoundException(String message){
        super(message);
    }
}
