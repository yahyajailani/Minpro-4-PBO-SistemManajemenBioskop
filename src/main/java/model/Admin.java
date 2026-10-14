/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADVAN
 */
public class Admin extends Pengguna {

    private final String password;

    public Admin(String nama, String email, String noHP,
                 String password) {
        super(nama, email, noHP);
        this.password = password;
    }

    public boolean login(String email, String password) {
        return getEmail().equalsIgnoreCase(email)
                && this.password.equals(password);
    }

    @Override
    public String getRole() {
        return "Cinematter";
    }

    @Override
    public void tampilkanMenu() {
        System.out.println("\n========== MENU ADMIN ==========");
        System.out.println("1. Kelola Film");
        System.out.println("2. Kelola Studio");
        System.out.println("3. Kelola Tiket");
        System.out.println("4. Lihat Penonton");
        System.out.println("5. Lihat Transaksi");
        System.out.println("0. Logout");
    }
}