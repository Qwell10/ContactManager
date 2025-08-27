package com.contactManager.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ContactDto {

    @NotBlank(message = "Name cannot be null or blank")
    private String name;

    @NotBlank(message = "Email cannot be null or blank")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Phone number cannot be null or blank")
    private String phoneNumber;
}
