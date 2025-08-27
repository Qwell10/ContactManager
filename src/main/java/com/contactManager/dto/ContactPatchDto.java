package com.contactManager.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactPatchDto {

    private String name;
    private String nameToChange;
    private String email;
    private String phoneNumber;
}
