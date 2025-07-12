package com.contactManager.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContactEntity {

    @Id
    @SequenceGenerator(
            name = "contact_sequence",
            sequenceName = "contact_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "contact_sequence"
    )
    private long id;

    @NotBlank(message = "Name cannot be null or blank")
    private String name;

    @NotBlank(message = "Phone number cannot be null or blank")
    private String phoneNumber;

    @NotBlank(message = "Email cannot be null or blank")
    @Email(message = "Invalid email format")
    private String email;

    public ContactEntity(String name, String email, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
}
