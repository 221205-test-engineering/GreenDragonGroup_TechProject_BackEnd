package com.uni.exceptions;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String resourceIdentifier, Class c){
        super("No resource with ID " + resourceIdentifier + " found for " + c.toString());
    }
    public ResourceNotFoundException(int resourceIdentifier, Class c){
        super("No resource with ID " + resourceIdentifier + " found for " + c.toString());
    }


}
