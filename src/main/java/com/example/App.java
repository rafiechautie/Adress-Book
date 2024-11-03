package com.example;

import com.example.addressbook.model.Contact;
import com.example.addressbook.repository.ContactRepository;
import com.example.addressbook.service.AdressBookService;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Scanner scanner = new Scanner(System.in);
        ContactRepository repository = new ContactRepository();
        AdressBookService service = new AdressBookService();

        while(true) {
            System.out.println("\n===== Buku Alamat =====");
            System.out.println("1. Tambah Kontak");
            System.out.println("2. Lihat Semua Kontak");
            System.out.println("3. Cari Kontak");
            System.out.println("4. Edit Kontak");
            System.out.println("5. Hapus Kontak");
            System.out.println("6. Simpan Kontak ke File");
            System.out.println("7. Muat Kontak dari File");
            System.out.println("8. Keluar");
            System.out.print("Pilih opsi: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addContact(scanner, service);
                case 2 -> viewAllContacts(scanner, service);
                case 3 -> searchContact(scanner, service);
                case 4 -> editContact(scanner, service);
                case 5 -> deleteContact(scanner, service);
                case 6 -> saveContacts(service);
                case 7 -> loadContacts(service);
                case 8 -> {
                    System.out.println("Keluar dari aplikasi. Terima kasih!");
                    scanner.close();
                    System.exit(0);
                }
                default -> System.out.println("Opsi tidak valid. Coba lagi.");
            }
        }
    }

    private static void addContact(Scanner scanner, AdressBookService service) {
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Phone Number: ");
        String phoneNumber = scanner.nextLine();

        service.addContact(name, email, phoneNumber);
        System.out.println("Kontak berhasil ditambahkan");
    }

    private static void viewAllContacts(AdressBookService service) {
        System.out.println("=== List Contacts ===");
        for (Contact contact: service.getAllContact()) {
            System.out.println("ID: " + contact.getId());
            System.out.println("Name: " + contact.getName());
            System.out.println("Email: " + contact.getEmail());
            System.out.println("Phone Number: " + contact.getNumber());
            System.out.println("-------------------");
        }
    }

    private static void  searchContact(Scanner scanner, AdressBookService service) {

    }

    private static void editContact(Scanner scanner, AdressBookService service) {

    }

    private static void deleteContact(Scanner scanner, AdressBookService service) {

    }

    private static void saveContacts(AdressBookService service) {

    }

    private static void loadContacts(AdressBookService service) {

    }
}
