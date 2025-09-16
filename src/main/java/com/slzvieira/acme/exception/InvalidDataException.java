package com.slzvieira.acme.exception;

import java.util.ArrayList;
import java.util.List;

public class InvalidDataException extends RuntimeException {

    private final List<String> errorMessages;

    public InvalidDataException() {
        this.errorMessages = new ArrayList<>();
    }

    public InvalidDataException(String message) {
        this.errorMessages = new ArrayList<>();
        this.errorMessages.add(message);
    }

    public List<String> getErrorMessages() {
        return this.errorMessages;
    }

    public void addMessage(String message) {
        errorMessages.add(message);
    }
}
