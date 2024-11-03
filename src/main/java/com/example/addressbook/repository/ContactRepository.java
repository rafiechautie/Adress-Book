package com.example.addressbook.repository;

import com.example.addressbook.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class ContactRepository {
    private List<Contact> contacts = new ArrayList<>();

    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    public Contact getContact(String id) {
        return  contacts.stream()
                .filter(contact -> contact.getId().equals(id))
                .findFirst()
                .orElse(null);

    }

    public List<Contact> getAllContacts() {
        return new ArrayList<>(contacts);
    }

    public void deletedContact(String id) {
        contacts.removeIf(contact -> contact.getId().equals(id));
    }
}
