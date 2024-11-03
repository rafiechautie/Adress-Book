package com.example.addressbook.service;

import com.example.addressbook.model.Contact;
import com.example.addressbook.repository.ContactRepository;

import java.util.List;
import java.util.UUID;

public class AdressBookService {
    private ContactRepository repository;

    public void ContactService(ContactRepository repository) {
        this.repository = repository;
    }

    public void addContact(String name, String email, String number) {
        // Menghasilkan angka acak antara 0 (inklusif) dan 100 (eksklusif)
        String id = UUID.randomUUID().toString().substring(0, 8);
        Contact newContact = new Contact(id, name, email, number);
        repository.addContact(newContact);
    }

    public List<Contact> getAllContact() {
        return repository.getAllContacts();
    }

    public Contact getContact(String id) {
        return repository.getContact(id);
    }

    public void deleteContact(String id) {
        repository.deletedContact(id);
    }

}
