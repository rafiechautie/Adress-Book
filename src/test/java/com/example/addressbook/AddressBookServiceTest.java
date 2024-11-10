package com.example.addressbook;

import com.example.addressbook.model.Contact;
import com.example.addressbook.repository.ContactRepository;
import com.example.addressbook.service.AdressBookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AdressBookServiceTest {
    private AdressBookService service;
    private ContactRepository repository;

    @BeforeEach
    void setUp() {
        repository = new ContactRepository();
        service = new AdressBookService(repository);
    }

    @Test
    void testAddContact() {
        service.addContact("Alice", "alice@example.com", "123456789");
        List<Contact> contacts = service.getAllContact();
        assertEquals(1, contacts.size());
        assertEquals("Alice", contacts.get(0).getName());
    }

    @Test
    void testDeleteContact() {
        service.addContact("Alice", "alice@example.com", "123456789");
        String id = service.getAllContact().get(0).getId();
        service.deleteContact(id);
        assertNull(service.findContactById(id));
    }

    @Test
    void testEditContact() {
        service.addContact("Alice", "alice@example.com", "123456789");
        Contact contact = service.getAllContact().get(0);
        contact.setName("Alice Updated");
        service.editContact(contact);
        assertEquals("Alice Updated", service.findContactById(contact.getId()).getName());
    }

    @Test
    void testSaveAndLoadContacts() {
        String filename = "temp_contacts.dat";

        try {
            service.addContact("Alice", "alice@example.com", "123456789");
            service.saveContacts(filename);

            AdressBookService newService = new AdressBookService(new ContactRepository());
            newService.loadContacts(filename);

            List<Contact> loadedContacts = newService.getAllContact();
            assertEquals(1, loadedContacts.size());
            assertEquals("Alice", loadedContacts.get(0).getName());
        } finally {
            // Delete temporary file
            new File(filename).delete();
        }
    }
}
