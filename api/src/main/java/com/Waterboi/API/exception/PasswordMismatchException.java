package com.Waterboi.API.exception;

public class PasswordMismatchException extends Exception {
    public PasswordMismatchException() {
        super("Password mismatch");
    }
    public PasswordMismatchException(String message) {
        super(message);
    }
}
