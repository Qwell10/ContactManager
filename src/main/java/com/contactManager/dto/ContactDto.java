package com.contactManager.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ContactDto {
    private String name;
    private String email;
    private String phoneNumber;
}
