package com.contactManager.soap;

import com.contactManager.entity.ContactEntity;
import com.contactManager.repository.ContactRepository;
import com.contactManager.service.ContactManagerServiceImpl;
import com.contactManager.soap.gen.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;

@Endpoint
public class ContactSoapEndpoint {

    private static final String NAMESPACE_URI = "http://whereareyou/contacts";

    @Autowired
    private ContactManagerServiceImpl contactManagerService;

    @Autowired
    private ContactRepository repository;

    //@RequestPayload: Translates XML → Java (so I can work with the data)
    //@ResponsePayload: Translates Java → XML (so the client can read the result)

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "SaveNewContactRequest")
    @ResponsePayload // <--- This ensures the return object becomes XML
    public SaveNewContactResponse saveNewContact(@RequestPayload SaveNewContactRequest request) {
        SaveNewContactResponse response = new SaveNewContactResponse();

        String name = request.getName();
        String email = request.getEmail();
        String phoneNumber = request.getPhoneNumber();

        if (name == null || name.isEmpty()) {
            response.setStatus("FAILURE");
            response.setMessage("Name cannot be empty");
            return response;
        }

        if (email == null || email.isEmpty()) {
            response.setStatus("FAILURE");
            response.setMessage("Email cannot be empty");
            return response;
        }

        if (phoneNumber == null || phoneNumber.isEmpty()) {
            response.setStatus("FAILURE");
            response.setMessage("Phone number cannot be empty");
            return response;
        }

        if (!repository.existsByName(name)) {

            contactManagerService.saveNewContact(name, email, phoneNumber);
            response.setStatus("SUCCESS");
            response.setMessage("Contact saved successfully");

        } else {
            response.setStatus("FAILURE");
            response.setMessage(String.format("Contact %s already exists", name));
        }
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetContactsRequest")
    @ResponsePayload
    public GetContactsResponse getContacts(@RequestPayload GetContactsRequest request) {
        GetContactsResponse response = new GetContactsResponse();

        List<ContactEntity> contacts = contactManagerService.getContacts();

        for (ContactEntity dbEntity : contacts) {
            ContactDetails contact = new ContactDetails();
            contact.setName(dbEntity.getName());
            contact.setEmail(dbEntity.getEmail());
            contact.setPhoneNumber(dbEntity.getPhoneNumber());

            response.getContact().add(contact);
        }

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "FindByNameRequest")
    @ResponsePayload
    public FindByNameResponse findByName(@RequestPayload FindByNameRequest request) {
        FindByNameResponse response = new FindByNameResponse();

        ContactEntity contact = contactManagerService.findByName(request.getName());

        ContactDetails soapContact = new ContactDetails();

        soapContact.setName(contact.getName());
        soapContact.setEmail(contact.getEmail());
        soapContact.setPhoneNumber(contact.getPhoneNumber());

        response.setContact(soapContact);

        return response;
    }
}
