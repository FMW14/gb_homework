package com.vtb.javacourses.lesson12.exceptions;

public class DbColumnNotFoundException extends RuntimeException{
    public DbColumnNotFoundException(){
        super();
    }

    public DbColumnNotFoundException(String message){
        super(message);
    }
}
