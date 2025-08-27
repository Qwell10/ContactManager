package ConsoleApp;

import java.util.*;

public class ContactManager {
    final Scanner scanner;

    public ContactManager(Scanner scanner) {
        this.scanner = scanner;
    }

    List<ContactEntity> contacts = new ArrayList<>();

    public String saveNewContact() {
        System.out.println("Name:");
        String name = scanner.nextLine();

        if (name == null || name.isBlank()) {
            return "invalid name: " + name;
        }

        System.out.println("Phone number:");
        String phoneNumber = scanner.nextLine();

        if (phoneNumber == null || phoneNumber.isBlank()) {
            return "invalid phone number: " + phoneNumber;
        }

        System.out.println("email:");
        String email = scanner.nextLine();

        if (email == null || email.isBlank()) {
            return "invalid email: " + email;
        }

        ContactEntity contactEntity = new ContactEntity(name, phoneNumber, email);
        contacts.add(contactEntity);

        return "Contact was added.";
    }

    public List<ContactEntity> getContacts() {
        return Collections.unmodifiableList(contacts);
    }

    public Optional<ContactEntity> findByName() {
        System.out.println("Enter a name to search:");
        String name = scanner.nextLine();

        if (name == null || name.isBlank()) {
            System.out.println("Name cannot be empty or blank.");
            return Optional.empty();
        }

        Optional<ContactEntity> foundContact = contacts.stream()
                .filter(contact -> contact.name().equals(name))
                .findFirst();

        if (foundContact.isEmpty()) {
            System.out.printf("Contact with name: %s was not found.%n", name);
        }
        return foundContact;
    }

    public void deleteByName() {
        System.out.println("Enter a name to search:");
        String name = scanner.nextLine();

        Optional<ContactEntity> foundContact = contacts.stream()
                .filter(contact -> contact.name().equals(name))
                .findFirst();

        ContactEntity contactToRemove = foundContact.orElseThrow(() -> new IllegalArgumentException(String.format("Contact with name: %s was not found.", name)));

        contacts.remove(contactToRemove);
        System.out.printf("Contact with name: %s was removed", contactToRemove.name());
    }
}
