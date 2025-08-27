package com.contactManager.service;

import com.contactManager.dto.ContactPatchDto;
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

    public ContactEntity patchContact(ContactPatchDto dto) {
        ContactEntity existingContact = findByName(dto.getName());

        if (dto.getNameToChange() != null) {
            existingContact.setName(dto.getNameToChange());
        }
        if (dto.getEmail() != null) {
            existingContact.setEmail(dto.getEmail());
        }
        if (dto.getPhoneNumber() != null) {
            existingContact.setPhoneNumber(dto.getPhoneNumber());
        }

        contactRepository.save(existingContact);
        return existingContact;
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
