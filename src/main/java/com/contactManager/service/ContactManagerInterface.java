package com.contactManager.service;

import com.contactManager.dto.ContactPatchDto;
import com.contactManager.entity.ContactEntity;

import java.util.List;

public interface ContactManagerInterface {

    void saveNewContact(String name, String email, String phoneNumber);

    ContactEntity patchContact(ContactPatchDto dto);

    ContactEntity findByName(String name);

    List<ContactEntity> getContacts();

    ContactEntity deleteByName(String name);
}
