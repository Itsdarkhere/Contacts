package Contacts;


import java.time.LocalDateTime;
import java.util.Scanner;


//class for the business contacts
class Business extends Parent {

    private static final long serialVersionUID = 7L;
    String phoneNumber;
    String organizationName;
    String address;
    LocalDateTime edited;
    LocalDateTime created;


    @Override
    String getFields() {
        return phoneNumber + organizationName + address;
    }
    @Override
    void setField(String field, String newValue) {
        switch (field) {
            case "phoneNumber":
                this.phoneNumber = newValue;
                break;
            case "organizationName":
                this.organizationName = newValue;
                break;
            case "address":
                this.address = newValue;
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

            case "organizationName":
                type = (T) organizationName;
                break;

            case "address":
                type = (T) address;
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
        return organizationName;
    }

    @Override
    void modifyRecord(Scanner scanner, PhoneBook phoneBook) {
        boolean on = true;
        while (on) {
            System.out.println("\n[record] Enter action (edit, delete, menu): ");
            String action = scanner.nextLine();
            switch (action) {
                case "edit":
                    System.out.println("Select a field (name, address, number): ");
                    String field = scanner.nextLine();
                    switch (field) {
                        case "name":
                            System.out.println("Enter name: ");
                            String newName = scanner.nextLine();
                            setField("organizationName", newName);
                            System.out.println("saved");
                            System.out.println(toString());
                            break;
                        case "address":
                            System.out.println("Enter address: ");
                            String newAddress = scanner.nextLine();
                            setField("address", newAddress);
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
        return "Organization name: " + organizationName + "\nAddress: " + address +
                "\nNumber: " + phoneNumber + "\nTime created: " + created + "\nTime last edit: " + edited;
    }


}
