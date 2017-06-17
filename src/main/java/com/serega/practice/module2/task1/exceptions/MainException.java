package com.serega.practice.module2.task1.exceptions;

public class MainException extends Exception {

    private String message;

    public MainException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
