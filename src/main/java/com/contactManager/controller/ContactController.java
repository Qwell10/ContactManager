package com.contactManager.controller;

import com.contactManager.dto.ContactDto;
import com.contactManager.exception.ErrorResponse;
import com.contactManager.service.ContactManagerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contact")
public class ContactController {

    @Autowired
    private ContactManagerService contactManagerService;

    @PostMapping("/saveNewContact")
    public ResponseEntity<String> saveNewContact(@Valid @RequestBody ContactDto contactDto) {
        String contactName = contactDto.getName();

        contactManagerService.saveNewContact(contactName, contactDto.getEmail(), contactDto.getPhoneNumber());

        return ResponseEntity.status(HttpStatus.CREATED).body(String.format("Contact %s was saved.", contactName));
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }
}
