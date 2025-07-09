package ConsoleApp;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ContactManager contactManager = new ContactManager(scanner);

        System.out.println("---Welcome in your Contact Manager---");

        Boolean exit = false;
        while (exit.equals(false)) {
            System.out.println("---- 1)-> Save new contact ----");
            System.out.println("---- 2)-> Find contact by name ----");
            System.out.println("---- 3)-> Get all contacts ----");
            System.out.println("---- 4)-> Remove contact ----");
            System.out.println("---- 5)-> Close Contact Manager ----");

            String userChoice = scanner.nextLine();

            switch (userChoice) {
                case "1" -> System.out.println(contactManager.saveNewContact());
                case "2" -> System.out.println(contactManager.findByName());
                case "3" -> System.out.println(contactManager.getContacts());
                case "4" -> {
                    try {
                        contactManager.deleteByName();
                    } catch (IllegalArgumentException e) {
                        System.out.println("ERROR: " + e.getMessage());
                    }
                }
                case "5" -> exit = true;
            }
        }
    }
}
