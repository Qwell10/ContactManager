package com.contactManager.service;

import com.contactManager.entity.ContactEntity;
import com.contactManager.exception.ContactNotFoundException;
import com.contactManager.exception.NoContactFoundException;
import com.contactManager.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactManagerService {

    @Autowired
    ContactRepository contactRepository;

    public void saveNewContact(String name, String email, String phoneNumber) {
        ContactEntity newContact = new ContactEntity(name, email, phoneNumber);
        contactRepository.save(newContact);
    }

    public ContactEntity findByName(String name) {
        return contactRepository.findByNameIgnoreCase(name).orElseThrow(() -> new ContactNotFoundException(name));
    }

    public List<ContactEntity> getContacts() {
        List<ContactEntity> contacts = contactRepository.findAll();
        if (contacts.isEmpty()) {
            throw new NoContactFoundException();
        }
        return contacts;
    }

    public ContactEntity deleteByName(String name) {
        ContactEntity foundContact = findByName(name);
        contactRepository.delete(foundContact);
        return foundContact;
    }
}
