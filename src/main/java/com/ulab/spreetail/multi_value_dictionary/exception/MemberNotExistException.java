package com.ulab.spreetail.multi_value_dictionary.exception;

public class MemberNotExistException extends Exception {
    private static final String MESSAGE = "ERROR, member does not exist";

    public MemberNotExistException() {
        super(MESSAGE);
    }
}
