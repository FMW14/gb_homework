package com.vtb.javacourses.lesson14;

import java.io.IOException;

public class InvalidPathException extends IOException {
    public InvalidPathException(String message) {
        super(message);
    }
}
