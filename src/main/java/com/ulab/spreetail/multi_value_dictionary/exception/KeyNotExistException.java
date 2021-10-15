package com.ulab.spreetail.multi_value_dictionary.exception;

public class KeyNotExistException extends Exception {
    private static final String MESSAGE="ERROR, key does not exist";

    public KeyNotExistException() {
        super(MESSAGE);
    }
}
