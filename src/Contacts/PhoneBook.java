package Contacts;

import java.io.Serializable;
import java.util.ArrayList;

//Stores the list containing all contacts and has methods to help access them
public class PhoneBook implements Serializable {
    private static final long serialVersionUID = 7L;
    ArrayList<Parent> list;

    PhoneBook (ArrayList<Parent> list) {
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