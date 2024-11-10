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
    private static final String FILENAME = "contacts.dat";

    public static void main( String[] args )
    {
        Scanner scanner = new Scanner(System.in);
        ContactRepository repository = new ContactRepository();
        AdressBookService service = new AdressBookService(repository);

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

            // switch new style java 12+
            switch (choice) {
                case 1 -> addContact(scanner, service);
                case 2 -> viewAllContacts(service);
                case 3 -> editContact(scanner, service);
                case 4 -> deleteContact(scanner, service);
                case 5 -> saveContacts(service);
                case 6 -> loadContacts(service);
                case 7 -> {
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

    private static void editContact(Scanner scanner, AdressBookService service) {
        System.out.print("ID contact yang ingin edit: ");
        String id = scanner.nextLine();

        Contact contact = service.findContactById(id);

        if (contact == null) {
            System.out.println("Kontak dengan ID tersebut tidak ditemukan");
            return;
        }

        System.out.println("Field yang ingin diubah (ketik angka): ");
        System.out.println("1. Name");
        System.out.println("2. Email");
        System.out.println("3. Phone Number");
        System.out.println("4. Batal");

        int choice = scanner.nextInt();
        scanner.nextLine();

        // switch case old style
        switch (choice) {
            case 1:
                System.out.print("Masukkan nama baru: ");
                String newName = scanner.nextLine();
                contact.setName(newName);
                service.editContact(contact);
                System.out.println("Nama telah diperbarui");
                break;
            case 2:
                System.out.print("Masukkan email baru: ");
                String newEmail = scanner.nextLine();
                contact.setEmail(newEmail);
                service.editContact(contact);
                System.out.println("Email telah diperbarui");
                break;
            case 3:
                System.out.print("Masukkan nomor HP kamu");
                String newPhoneNumber = scanner.nextLine();
                contact.setNumber(newPhoneNumber);
                System.out.println("Nomor HP telah diperbarui");
                break;
            case 4:
                System.out.println("Edit dibatalkan");
                break;
            default:
                System.out.println("Opsi tidak valid. Coba lagi.");
                break;
        }

    }

    private static void deleteContact(Scanner scanner, AdressBookService service) {
        System.out.print("Id: ");
        String id = scanner.nextLine();

        service.deleteContact(id);
        System.out.printf("Data contact dengan ID: %s berhasil dihapus%n", id);
    }

    private static void saveContacts(AdressBookService service) {
        service.saveContacts(FILENAME);
    }

    private static void loadContacts(AdressBookService service) {
        service.loadContacts(FILENAME);
    }
}
