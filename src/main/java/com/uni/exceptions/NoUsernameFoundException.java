package com.uni.exceptions;

public class NoUsernameFoundException extends RuntimeException{

    public NoUsernameFoundException(){
        super("Username was not found");
    }
}
