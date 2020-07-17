package com.vtb.javacourses.lesson12.exceptions;

public class DbIdNotFoundException extends RuntimeException{
    public DbIdNotFoundException(){
        super();
    }

    public DbIdNotFoundException(String message){
        super(message);
    }
}
