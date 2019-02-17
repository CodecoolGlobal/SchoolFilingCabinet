package com.codecool;

import java.util.NoSuchElementException;

public class NoPersonException extends Exception {
    public NoPersonException(String message) {
        super(message);
    }
}
