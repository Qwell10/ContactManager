package com.contactManager.soap;

import com.contactManager.dto.ContactDto;
import com.contactManager.service.ContactManagerServiceImpl;
import com.contactManager.soap.gen.SaveNewContactRequest;
import com.contactManager.soap.gen.SaveNewContactResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class ContactSoapEndpoint {

    private static final String NAMESPACE_URI = "http://whereareyou/contacts";

    @Autowired
    private ContactManagerServiceImpl contactManagerService;

    //@RequestPayload: Translates XML → Java (so I can work with the data)
    //@ResponsePayload: Translates Java → XML (so the client can read the result)

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "SaveNewContactRequest")
    @ResponsePayload // <--- This ensures the return object becomes XML
    public SaveNewContactResponse saveNewContact(@RequestPayload SaveNewContactRequest request) {

        // 1. Extract data from the Request
        ContactDto contactDto = new ContactDto(request.getName(), request.getEmail(), request.getPhoneNumber());

        // 2. Call the service to save to Database
        contactManagerService.saveNewContact(contactDto.getName(), contactDto.getEmail(), contactDto.getPhoneNumber());

        // 3. Construct the Response object
        SaveNewContactResponse saveNewContactResponse = new SaveNewContactResponse();
        saveNewContactResponse.setStatus("SUCCESS");
        saveNewContactResponse.setMessage("Contact saved successfully");

        return saveNewContactResponse;
    }
}
