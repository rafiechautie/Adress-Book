package com.example.addressbook.repository;

import com.example.addressbook.model.Contact;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ContactRepository {
    private List<Contact> contacts = new ArrayList<>();

    /***
     *
     * File contacts.dat yang digunakan untuk menyimpan data kontak sebenarnya tersimpan di lokasi direktori proyek atau working directory dari aplikasi Java yang sedang dijalankan. Lokasi tepatnya tergantung dari mana aplikasi tersebut dijalankan. Jika aplikasi dijalankan di lingkungan pengembangan seperti IDE (misalnya, IntelliJ IDEA atau Eclipse), maka file tersebut biasanya tersimpan di root folder proyek. Jika aplikasi dijalankan dari terminal, file ini akan tersimpan di folder tempat perintah untuk menjalankan aplikasi dieksekusi.
     *
     * Kenapa Menggunakan .dat?
     * Ekstensi .dat umumnya digunakan untuk menunjukkan bahwa file tersebut berisi data biner atau data mentah. File ini tidak disimpan dalam format teks biasa yang mudah dibaca manusia, melainkan dalam bentuk biner yang hanya dapat dibaca oleh aplikasi yang tahu cara menginterpretasikan datanya (dalam hal ini, aplikasi Java yang melakukan proses serialisasi dan deserialisasi).
     *
     * Jadi, file .dat adalah cara untuk menyimpan data dalam bentuk biner yang lebih ringkas dan efisien untuk penyimpanan objek, terutama saat menggunakan mekanisme serialisasi Java.
     *
     * Apakah Wajib Menggunakan .dat?
     * Tidak. Ekstensi file sebenarnya bisa bebas, misalnya .bin, .ser, atau bahkan tanpa ekstensi. Namun, menggunakan .dat atau .bin adalah konvensi umum untuk menunjukkan bahwa file tersebut berisi data biner.
     */

    public void saveContactsToFile(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(contacts);
            System.out.println("Kontak berhasil disimpan ke file" + filename);
        } catch (IOException error) {
            System.out.println("Gagal menyimpan kontak: " + error.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public void loadContactsFromFile(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            contacts = (List<Contact>) ois.readObject();
            System.out.println("Kontak berhasil dimuat dari file " + filename);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Gagal memuat kontak: " + e.getMessage());
        }
    }

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

    public void editContact(Contact contact) {
        for (Contact itemContact: contacts) {
            if (itemContact.getId().equals(contact.getId())) {
                itemContact.setName(contact.getName());
                itemContact.setEmail(contact.getEmail());
                itemContact.setNumber(contact.getNumber());
            }
        }
    }
}
