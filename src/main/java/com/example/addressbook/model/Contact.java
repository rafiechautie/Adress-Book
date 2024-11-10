package com.example.addressbook.model;

import java.io.Serializable;

/*
* Ketika sebuah kelas di Java mengimplementasikan antarmuka Serializable, ini berarti objek dari kelas tersebut bisa diubah menjadi bentuk biner dan dikembalikan lagi ke bentuk objek asli (proses ini disebut deserialisasi).
*
* Mengapa Serializable Penting?
*
* Bayangkan kita punya objek yang menyimpan informasi kontak, dan kita ingin menyimpan informasi tersebut ke dalam file agar bisa digunakan nanti (misalnya setelah aplikasi ditutup dan dibuka kembali). Jika objek tersebut bisa diserialisasi, kita bisa langsung menyimpan seluruh objek ke dalam file dan membacanya kembali nanti, tanpa harus mengonversi setiap atribut satu per satu.
*
* Contoh Kasus:
* Misalkan ada kelas Contact yang menyimpan data seperti nama, email, dan nomor telepon. Dengan menjadikannya Serializable, kita bisa:
* Menyimpan objek Contact ke dalam file.
* Membaca data dari file dan mengembalikan objek Contact ke keadaan awalnya dengan semua data yang sama
*
* */
public class Contact implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String name;
    private String email;
    private String number;

    public Contact(String id, String name, String email, String number) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.number = number;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
