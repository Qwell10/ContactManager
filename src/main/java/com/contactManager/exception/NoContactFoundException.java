package com.contactManager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoContactFoundException extends RuntimeException {

    public NoContactFoundException() {
        super("No contact found. No contact saved.");
    }
}
