package com.hellFire.UserProfileService.exceptions;

public class UserAlreadyExists extends Exception{
    public UserAlreadyExists(String message){
        super(message);
    }
}
