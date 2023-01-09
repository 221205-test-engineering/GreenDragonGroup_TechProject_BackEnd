package com.uni.exceptions;

public class SeasonCreationException extends RuntimeException {

    public SeasonCreationException(){
        super("Failed to create a season");
    }

}
