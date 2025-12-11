package com.hellFire.UserProfileService.exceptions;

public class PasswordMisMatchException extends RuntimeException{

    public PasswordMisMatchException() {
        super("Invalid password.");
    }
}
