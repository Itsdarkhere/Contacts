package Contacts;



import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.run();

    }

    public void run() throws IOException {

        ArrayList<Parent> contacts = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        PhoneBook phoneBook = new PhoneBook(contacts);
        Boolean on = true;

        SerializationMethods methods = new SerializationMethods();
        //Reconstructing saved records from the file to the phonebook
        try {
            phoneBook = (PhoneBook) methods.deserialize("C:\\HyperSkill\\text");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No phonebook found.");
        }


        while (on) {
            System.out.println("\n[menu] Enter action (add, list, search, count, " +
                    "exit):");
            String action = scanner.nextLine();
            switch (action) {

                case "add":
                    System.out.println("\nEnter the type (person, organization): ");
                    String type = scanner.nextLine();

                    if (type.equals("person")) {
                        System.out.println("Enter the name: ");
                        String name = scanner.nextLine();
                        System.out.println("Enter the surname: ");
                        String surName = scanner.nextLine();

                        System.out.println("Enter the birth date: ");
                        String birthDate = scanner.nextLine();
                        if (birthDate.isEmpty()) {
                            birthDate = "[no data]";
                        }

                        System.out.println("Enter the gender (M, F): ");
                        String gender = scanner.nextLine();
                        if (gender.isEmpty()) {
                            gender = "[no data]";
                        }

                        System.out.println("Enter the number: ");
                        String number = scanner.nextLine();

                        //creating the person object from user added fields.
                        Person person = new Person();
                        person.setField("name", name);
                        person.setField("lastName",surName);
                        person.setField("gender", gender);
                        person.setField("birthdate", birthDate);
                        person.setField("phoneNumber", number);
                        person.setField("created", LocalDateTime.now().toString());
                        person.setField("edited", LocalDateTime.now().toString());

                        phoneBook.addContact(person);
                        methods.serialize(phoneBook, "C:\\HyperSkill\\text");

                    } else {
                        System.out.println("Enter the organization name: ");
                        String organizationName = scanner.nextLine();
                        System.out.println("Enter the address: ");
                        String address = scanner.nextLine();
                        System.out.println("Enter the number: ");
                        String number = scanner.nextLine();

                        //creating business object from user added fields
                        Business biz = new Business();
                        biz.setField("organizationName", organizationName);
                        biz.setField("address", address);
                        biz.setField("phoneNumber", number);
                        biz.setField("created", LocalDateTime.now().toString());
                        biz.setField("edited", LocalDateTime.now().toString());

                        phoneBook.addContact(biz);
                        methods.serialize(phoneBook, "C:\\HyperSkill\\text");
                    }

                    break;

                case "list":
                    if (phoneBook.getListSize() == 0) {
                        break;
                    }
                    //printing all contacts for the user to see
                    for (int i = 0; i < phoneBook.getListSize(); i++) {
                        System.out.println(i+1+". " + phoneBook.getContact(i).getFullName());
                    }

                    System.out.println("\n[list] Enter action ([number], back): ");
                    String action1 = scanner.nextLine();
                    if (action1.equals("back")) {
                        break;

                    } else {
                        int chosenNumber = Integer.parseInt(action1);

                        try {
                            Parent parent = phoneBook.getContact(chosenNumber - 1);
                            System.out.println(parent);
                            parent.modifyRecord(scanner, phoneBook);
                            methods.serialize(phoneBook, "C:\\HyperSkill\\text");

                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println("Record does not exist");
                            break;
                        }

                    }
                    break;

                case "search":
                    boolean searching = true;
                    while (searching) {
                        System.out.println("Enter search query: ");
                        String query = scanner.nextLine().toLowerCase();
                        ArrayList<Parent> list = new ArrayList<>();
                        //extracting all contacts that contain the query
                        for (int i = 0; i < phoneBook.getListSize(); i++) {
                            if (phoneBook.getContact(i).getFields().toLowerCase().contains(query)) {
                                list.add(phoneBook.getContact(i));
                            }
                        }
                        //printing all matching contacts
                        System.out.printf("Found %d results: ", list.size());
                        int count = 1;
                        for (Parent p: list) {
                            System.out.println(count + ". " + p.getFullName());
                            count++;
                        }

                        System.out.println(" ");
                        System.out.println("[search] Enter action ([number], back, again): ");
                        String actionInSearch = scanner.nextLine();
                        if (actionInSearch.equals("back")) {
                            searching = false;
                            break;
                        } else if (actionInSearch.equals("again")) {
                            break;
                        } else {
                            int number = Integer.parseInt(actionInSearch);
                            Parent parent = phoneBook.getContact(number - 1);
                            System.out.println(parent);
                            parent.modifyRecord(scanner, phoneBook);
                            methods.serialize(phoneBook, "C:\\HyperSkill\\text");
                            searching = false;
                            break;


                        }

                    }

                    break;

                case "count":
                    System.out.format("The Phone Book has %d records. \n"
                            , phoneBook.getListSize());
                    break;


                case "exit":
                    on = false;
                    break;

                default:
                    break;
            }

        }



    }

    //Stores the list containing all contacts and has methods to help access them
    public static class PhoneBook implements Serializable {
        private static final long serialVersionUID = 7L;
        ArrayList<Parent> list;

        PhoneBook(ArrayList<Parent> list) {
            this.list = list;
        }

        void addContact(Parent parent) {
            list.add(parent);
            System.out.println("The record added.");
        }
        void removeContact(Parent parent) {
            list.remove(parent);
            System.out.println("The record removed!");
        }

        Parent getContact(int index) {

            return list.get(index);
        }

        Integer getListSize() {

            return list.size();
        }
    }

}


