package com.vtb.javacourses.lesson18.exceptions;

public class WrongInputException extends RuntimeException{
    public WrongInputException(String message) {
        super(message);
    }

    public WrongInputException() {
    }
}
