package Contacts;



import java.io.*;
import java.lang.reflect.Field;
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

        //Reconstructing saved records from the file to the phonebook
        SerializationMethods methods = new SerializationMethods();
        //to empty the file
        methods.serialize(phoneBook, "C:\\HyperSkill\\text");
        try {
            phoneBook = (PhoneBook) methods.deserialize("C:\\HyperSkill\\text");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No phonebook found.");
        }

        Boolean on = true;


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
                        System.out.printf("Found %d results:", list.size());
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
                    //ends the program
                    on = false;
                    break;

                default:
                    break;
            }

        }



    }

    //Stores the list containing all contacts and has methods to help access them
    public static class PhoneBook implements Serializable{
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

//Root class for implementing polymorphism
class Parent implements Serializable{
    private static final long serialVersionUID = 7L;
    String phoneNumber;
    String organizationName;
    String address;
    String birthDate;
    String gender;
    String name;
    String lastName;




    String getFields() {
        String allField = phoneNumber+organizationName+address+birthDate+gender+name+lastName;
        return allField;

    }
    void setField(String field, String newValue) {


    }
    void modifyRecord(Scanner scanner, Main.PhoneBook phoneBook) {

    }
    <T> T getField(String field) {
        return (T) null;
    }
    String getFullName() {
        return name + " " +  lastName;
    }


}

//class for the business contacts
class Business extends Parent{
    private static final long serialVersionUID = 7L;
    String phoneNumber;
    String organizationName;
    String address;
    LocalDateTime edited;
    LocalDateTime created;
    Field[] fields = Business.class.getFields();

    @Override
    String getFields() {
        return phoneNumber+organizationName+address;
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
    void modifyRecord(Scanner scanner, Main.PhoneBook phoneBook) {
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
        return phoneNumber+birthDate+gender+name+lastName;
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
    void modifyRecord(Scanner scanner, Main.PhoneBook phoneBook) {
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
class SerializationMethods{

    //this writes the "object" into a file
    public void serialize(Object object, String fileName) throws IOException {
        FileOutputStream output = new FileOutputStream(fileName);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(output);
        ObjectOutputStream objectOutput = new ObjectOutputStream(bufferedOutputStream);
        objectOutput.writeObject(object);
        objectOutput.close();

    }
    //this reconstructs the "object" from a file
    public Object deserialize(String fileName) throws IOException, ClassNotFoundException{
        FileInputStream input = new FileInputStream(fileName);
        BufferedInputStream buffInput = new BufferedInputStream(input);
        ObjectInputStream objectInput = new ObjectInputStream(buffInput);
        Object object = objectInput.readObject();
        objectInput.close();

        //here we give back the object
        return object;

    }
}
