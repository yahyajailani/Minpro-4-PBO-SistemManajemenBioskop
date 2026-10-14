/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADVAN
 */
public abstract class Pengguna {

    private int id;
    private String nama;
    private String email;
    private String noHP;

    public Pengguna(String nama, String email, String noHP) {
        this(0, nama, email, noHP);
    }

    public Pengguna(int id, String nama, String email, String noHP) {
        this.id = id;
        this.nama = nama;
        this.email = email;
        this.noHP = noHP;
    }

    public abstract String getRole();

    public abstract void tampilkanMenu();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNoHP() {
        return noHP;
    }

    public void setNoHP(String noHP) {
        this.noHP = noHP;
    }
}