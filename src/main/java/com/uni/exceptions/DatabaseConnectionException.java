package com.uni.exceptions;

public class DatabaseConnectionException extends RuntimeException {

    public DatabaseConnectionException(){
        super("Failed to connect to the database");
    }
}
