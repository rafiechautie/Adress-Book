package com.example.addressbook.service;

import com.example.addressbook.model.Contact;
import com.example.addressbook.repository.ContactRepository;

import java.util.List;
import java.util.UUID;

public class AdressBookService {
    private ContactRepository repository;

    public AdressBookService (ContactRepository repository) {
        this.repository = repository;
    }

    public void addContact(String name, String email, String number) {
        String id = UUID.randomUUID().toString().substring(0, 8);
        Contact newContact = new Contact(id, name, email, number);
        repository.addContact(newContact);
    }

    public List<Contact> getAllContact() {
        return repository.getAllContacts();
    }

    public void deleteContact(String id) {
        repository.deletedContact(id);
    }

    public void editContact(Contact contact) {
        repository.editContact(contact);
    }

    public Contact findContactById(String id) {
        return repository.getContact(id);
    }

    public void saveContacts(String filename) {
        repository.saveContactsToFile(filename);
    }

    public void loadContacts(String filename) {
        repository.loadContactsFromFile(filename);
    }

}
