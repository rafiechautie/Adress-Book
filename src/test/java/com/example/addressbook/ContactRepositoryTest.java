package com.example.addressbook.repository;

import com.example.addressbook.model.Contact;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ContactRepositoryTest {
    private ContactRepository repository;

    @BeforeEach
    void setUp() {
        repository = new ContactRepository();
    }

    @Test
    void testAddAndGetContact() {
        Contact contact = new Contact("1", "Alice", "alice@example.com", "123456789");
        repository.addContact(contact);
        Contact retrieved = repository.getContact("1");
        assertNotNull(retrieved);
        assertEquals("Alice", retrieved.getName());
    }

    @Test
    void testGetAllContacts() {
        repository.addContact(new Contact("1", "Alice", "alice@example.com", "123456789"));
        repository.addContact(new Contact("2", "Bob", "bob@example.com", "987654321"));
        List<Contact> contacts = repository.getAllContacts();
        assertEquals(2, contacts.size());
    }

    @Test
    void testDeletedContact() {
        Contact contact = new Contact("1", "Alice", "alice@example.com", "123456789");
        repository.addContact(contact);
        repository.deletedContact("1");
        assertNull(repository.getContact("1"));
    }

    @Test
    void testEditContact() {
        Contact contact = new Contact("1", "Alice", "alice@example.com", "123456789");
        repository.addContact(contact);

        Contact updatedContact = new Contact("1", "Alice Updated", "aliceupdated@example.com", "987654321");
        repository.editContact(updatedContact);

        Contact retrieved = repository.getContact("1");
        assertEquals("Alice Updated", retrieved.getName());
        assertEquals("aliceupdated@example.com", retrieved.getEmail());
    }

    @Test
    void testSaveAndLoadContacts() {
        // Create temporary file
        String filename = "temp_contacts.dat";

        try {
            // Add some contacts and save them to file
            repository.addContact(new Contact("1", "Alice", "alice@example.com", "123456789"));
            repository.saveContactsToFile(filename);

            // Create a new repository and load from file
            ContactRepository newRepository = new ContactRepository();
            newRepository.loadContactsFromFile(filename);

            // Validate loaded data
            Contact loadedContact = newRepository.getContact("1");
            assertNotNull(loadedContact);
            assertEquals("Alice", loadedContact.getName());
        } finally {
            // Delete temporary file
            new File(filename).delete();
        }
    }

    @Test
    void testEditContactWithExistingId() {
        // Tambahkan kontak dengan ID yang sama
        Contact contact = new Contact("1", "Alice", "alice@example.com", "123456789");
        repository.addContact(contact);

        // Edit kontak dengan ID yang sama
        Contact updatedContact = new Contact("1", "Alice Updated", "aliceupdated@example.com", "987654321");
        repository.editContact(updatedContact);

        // Pastikan data terupdate
        Contact retrieved = repository.getContact("1");
        assertNotNull(retrieved);
        assertEquals("Alice Updated", retrieved.getName());
        assertEquals("aliceupdated@example.com", retrieved.getEmail());
        assertEquals("987654321", retrieved.getNumber());
    }

    @Test
    void testEditContactWithNonExistingId() {
        // Tambahkan kontak dengan ID yang berbeda
        Contact contact = new Contact("1", "Alice", "alice@example.com", "123456789");
        repository.addContact(contact);

        // Edit kontak dengan ID yang berbeda, yang tidak ada dalam repository
        Contact nonExistentContact = new Contact("2", "Bob", "bob@example.com", "987654321");
        repository.editContact(nonExistentContact);

        // Pastikan data awal tidak terpengaruh
        Contact retrieved = repository.getContact("1");
        assertNotNull(retrieved);
        assertEquals("Alice", retrieved.getName());
        assertEquals("alice@example.com", retrieved.getEmail());
        assertEquals("123456789", retrieved.getNumber());
    }

}
