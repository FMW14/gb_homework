package com.vtb.javacourses.lesson14;

import java.io.IOException;

public class EmptyDirectoryException extends IOException {
    public EmptyDirectoryException(String message) {
        super(message);
    }
}
