package com.ulab.spreetail.multi_value_dictionary.exception;

public class MemberAlreadyExistException extends Exception {
    private static final String MESSAGE="ERROR, member already exists for key";

    public MemberAlreadyExistException() {
        super(MESSAGE);
    }
}
