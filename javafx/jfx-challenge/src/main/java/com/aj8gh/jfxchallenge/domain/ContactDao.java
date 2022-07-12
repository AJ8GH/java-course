package com.aj8gh.jfxchallenge.domain;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ContactDao {
    private static final ContactDao INSTANCE = new ContactDao();
    private static final String FILE_NAME = "./contacts.csv";
    private static final String DELIMITER = ",";

    private final ObservableList<Contact> contacts = FXCollections.observableArrayList();

    public static ContactDao instance() {
        return INSTANCE;
    }

    private ContactDao() {
    }

    public Contact add(Contact contact) {
        contacts.add(contact);
        return contact;
    }

    public void delete(Contact contact) {
        contacts.remove(contact);
    }

    public Contact update(Contact oldContact, Contact newContact) {
        contacts.remove(oldContact);
        contacts.add(newContact);
        return newContact;
    }

    public ObservableList<Contact> getContacts() {
        return contacts;
    }

    public void loadContacts() {
        Path path = Paths.get(FILE_NAME);

        String input;
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            while ((input = reader.readLine()) != null) {
                String[] contactArray = input.split(DELIMITER);
                String firstName = contactArray[0];
                String lastName = contactArray[1];
                String phoneNumber = contactArray[2];
                String notes = contactArray[3];
                Contact contact = new Contact(firstName, lastName, phoneNumber, notes);
                contacts.add(contact);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveContacts() {
        Path path = Paths.get(FILE_NAME);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (Contact contact : contacts) {
                writer.write(String.format("%s,%s,%s,%s",
                        contact.getFirstName(),
                        contact.getLastName(),
                        contact.getPhoneNumber(),
                        contact.getNotes()));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
