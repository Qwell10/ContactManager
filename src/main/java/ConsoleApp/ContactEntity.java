package ConsoleApp;

public record ContactEntity(String name, String phoneNumber, String email) {

    @Override
    public String toString() {
        return String.format("Name: %s, Phone number: %s, Email: %s",
                name(), phoneNumber(), email());
    }
}

