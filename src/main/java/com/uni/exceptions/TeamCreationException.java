package com.uni.exceptions;

public class TeamCreationException extends RuntimeException {

    public TeamCreationException(){
        super("Failed to create a team");
    }

}
