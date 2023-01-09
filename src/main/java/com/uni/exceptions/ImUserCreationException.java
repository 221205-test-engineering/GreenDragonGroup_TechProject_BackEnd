package com.uni.exceptions;

public class ImUserCreationException extends RuntimeException {

    public ImUserCreationException(){
        super("Failed to create a user");
    }

}
