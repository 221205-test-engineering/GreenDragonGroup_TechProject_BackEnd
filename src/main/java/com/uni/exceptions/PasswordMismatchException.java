package com.uni.exceptions;

public class PasswordMismatchException extends RuntimeException{

    public PasswordMismatchException(){
        super("Incorrect password for user");
    }
}
