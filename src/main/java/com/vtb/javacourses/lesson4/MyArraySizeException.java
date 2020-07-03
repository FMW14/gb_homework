package com.vtb.javacourses.lesson4;

public class MyArraySizeException extends IllegalArgumentException{

    public MyArraySizeException(){

    }

    public MyArraySizeException(String message) {
        super(message);
    }
}
