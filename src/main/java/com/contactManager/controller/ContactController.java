package com.contactManager.controller;

import com.contactManager.dto.ContactDto;
import com.contactManager.dto.ContactPatchDto;
import com.contactManager.entity.ContactEntity;
import com.contactManager.exception.ContactNotFoundException;
import com.contactManager.exception.ErrorResponse;
import com.contactManager.exception.NoContactFoundException;
import com.contactManager.service.ContactManagerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:63342")
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

    @PatchMapping("/patchContact")
    public ResponseEntity<ContactEntity> patchContact(@RequestBody ContactPatchDto dto) {
        ContactEntity existingContact = contactManagerService.patchContact(dto);
        return ResponseEntity.status(HttpStatus.OK).body(existingContact);
    }

    @GetMapping("findContactByName/{name}")
    public ResponseEntity<ContactEntity> findByName(@PathVariable String name) {
        ContactEntity foundContact = contactManagerService.findByName(name);
        return ResponseEntity.status(HttpStatus.OK).body(foundContact);
    }

    @GetMapping("findContacts")
    public ResponseEntity<List<ContactEntity>> getContacts() {
        return ResponseEntity.status(HttpStatus.OK).body(contactManagerService.getContacts());
    }

    @DeleteMapping("/deleteContact/{name}")
    public ResponseEntity<String> deleteContact(@PathVariable String name) {
        ContactEntity foundContact = contactManagerService.deleteByName(name);
        return ResponseEntity.status(HttpStatus.OK).body(String.format("Contact named: %s was removed", name));
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    @ExceptionHandler(value = ContactNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleContactNotFoundException(ContactNotFoundException e) {
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage());
    }

    @ExceptionHandler(value = NoContactFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNoContactFoundException(NoContactFoundException e) {
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage());
    }
}
