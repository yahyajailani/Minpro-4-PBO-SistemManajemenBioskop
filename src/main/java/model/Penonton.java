/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADVAN
 */
public class Penonton extends Pengguna {

    private String password;

    public Penonton(String nama, String email, String noHP,
                    String password) {
        this(0, nama, email, noHP, password);
    }

    public Penonton(int id, String nama, String email,
                    String noHP, String password) {
        super(id, nama, email, noHP);
        this.password = password;
    }

    public boolean login(String email, String password) {
        return getEmail().equalsIgnoreCase(email)
                && this.password.equals(password);
    }

    public double hitungDiskon(double total) {
        if (total >= 100000) {
            return total * 0.10;
        } else if (total >= 50000) {
            return total * 0.05;
        }
        return 0;
    }

    public double hitungDiskon(double total, int jumlahTiket) {
        if (jumlahTiket >= 4) {
            return total * 0.15;
        }
        return hitungDiskon(total);
    }

    public double hitungHargaAkhir(double total, int jumlahTiket) {
        return total - hitungDiskon(total, jumlahTiket);
    }

    @Override
    public String getRole() {
        return "Penonton";
    }

    @Override
    public void tampilkanMenu() {
        System.out.println("\n========== MENU PENONTON ==========");
        System.out.println("1. Lihat Profil");
        System.out.println("2. Lihat Film");
        System.out.println("3. Lihat Studio");
        System.out.println("4. Beli Tiket");
        System.out.println("5. Riwayat Pembelian");
        System.out.println("0. Logout");
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIdPenonton() {
        return getId();
    }

    public void setIdPenonton(int id) {
        setId(id);
    }
}
