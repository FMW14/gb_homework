package com.vtb.javacourses.lesson12.exceptions;

public class DbTableNotFoundException extends RuntimeException{
    public DbTableNotFoundException(){
        super();
    }

    public DbTableNotFoundException(String message){
        super(message);
    }
}
