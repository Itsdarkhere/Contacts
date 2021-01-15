package Contacts;

import java.time.LocalDateTime;
import java.util.Scanner;

// class for the person contacts
class Person extends Parent{
    private static final long serialVersionUID = 7L;
    String phoneNumber;
    String birthDate;
    String gender;
    String name;
    String lastName;
    LocalDateTime edited;
    LocalDateTime created;


    @Override
    String getFields() {
        return phoneNumber + birthDate + gender + name + lastName;
    }
    @Override
    void setField(String field, String newValue) {
        switch (field) {
            case "phoneNumber":
                this.phoneNumber = newValue;
                break;
            case "birthdate":
                this.birthDate = newValue;
                break;
            case "gender":
                this.gender = newValue;
                break;
            case "name":
                this.name = newValue;
                break;
            case "lastName":
                this.lastName = newValue;
                break;
            case "created":
                this.created = LocalDateTime.parse(newValue);
                break;
            case "edited":
                this.edited = LocalDateTime.parse(newValue);
                break;

            default:
                break;
        }
    }
    @Override
    <T> T getField(String field) {
        T type = null;
        switch (field) {
            case "phoneNumber":
                type = (T) phoneNumber;
                break;

            case "birthdate":
                type = (T) birthDate;
                break;

            case "gender":
                type = (T) gender;
                break;

            case "name":
                type = (T) name ;
                break;

            case "lastName":
                type = (T) lastName;
                break;

            case "edited":
                type = (T) edited;
                break;

            case "created":
                type = (T) created;
                break;

            default:
                break;
        }
        return type;
    }
    @Override
    String getFullName() {
        return name + " " + lastName;
    }

    @Override
    void modifyRecord (Scanner scanner, PhoneBook phoneBook) {
        boolean on = true;
        while (on) {
            System.out.println("\n[record] Enter action (edit, delete, menu): ");
            String action = scanner.nextLine();
            switch (action) {
                case "edit":
                    System.out.println("Select a field (name, surname, birth, gender," +
                            " number): ");
                    String field = scanner.nextLine();
                    switch (field) {
                        case "name":
                            System.out.println("Enter name: ");
                            String newName = scanner.nextLine();
                            setField("organizationName", newName);
                            System.out.println("saved");
                            System.out.println(toString());
                            break;
                        case "surname":
                            System.out.println("Enter surname: ");
                            String newSurName = scanner.nextLine();
                            setField("lastName", newSurName);
                            System.out.println("saved");
                            System.out.println(toString());
                            break;
                        case "birth":
                            System.out.println("Enter birthdate: ");
                            String newBirthDate = scanner.nextLine();
                            setField("birthdate", newBirthDate);
                            System.out.println("saved");
                            System.out.println(toString());
                            break;
                        case "gender":
                            System.out.println("Enter gender: ");
                            String newGender = scanner.nextLine();
                            setField("gender", newGender);
                            System.out.println("saved");
                            System.out.println(toString());
                            break;
                        case "number":
                            System.out.println("Enter number: ");
                            String newNumber = scanner.nextLine();
                            setField("phoneNumber", newNumber);
                            System.out.println("saved");
                            System.out.println(toString());
                            break;
                        default:
                            break;
                    }

                case "delete":
                    for (int i = 0; i < phoneBook.getListSize(); i++) {
                        if (phoneBook.getContact(i).getField("phoneNumber").equals(phoneNumber)) {
                            phoneBook.removeContact(phoneBook.getContact(i));
                            break;
                        }
                    }


                case "menu":
                    on = false;
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public String toString() {
        return "Name: " + name + "\nSurname: " + lastName + "\nBirth date: " + birthDate +
                "\nGender: " + gender + "\nNumber: " + phoneNumber + "\nTime created: " + created +
                "\nTime last edit: " + edited;
    }

}