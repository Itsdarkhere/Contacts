package Contacts;

import java.io.Serializable;
import java.util.Scanner;

//Root class for implementing polymorphism
class Parent implements Serializable {
    private static final long serialVersionUID = 7L;
    String phoneNumber;
    String organizationName;
    String address;
    String birthDate;
    String gender;
    String name;
    String lastName;




    String getFields() {
        return phoneNumber +  organizationName + address + birthDate + gender + name + lastName;

    }
    void setField(String field, String newValue) {}

    void modifyRecord(Scanner scanner, Main.PhoneBook phoneBook) {}

    <T> T getField(String field) {

        return (T) null;
    }

    String getFullName() {

        return name + " " +  lastName;
    }


}
