package com.contactManager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ContactNotFoundException extends RuntimeException {

    public ContactNotFoundException(String name) {
        super("Contact with name '" + name + "' not found.");
    }
}
