/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.util.Scanner;
import model.Film;
import model.Studio;
import model.Tiket;
import model.Transaksi;

/**
 *
 * @author ADVAN
 */
public class BioskopView {

    private final Scanner input = new Scanner(System.in);

    public void header() {
        System.out.println("========================================");
        System.out.println("       SISTEM MANAJEMEN BIOSKOP");
        System.out.println("========================================");
    }

    public int bacaPilihan(String pesan, int min, int max) {
        while (true) {
            System.out.print(pesan);
            try {
                int angka = Integer.parseInt(input.nextLine().trim());

                if (angka >= min && angka <= max) {
                    return angka;
                }
            } catch (NumberFormatException e) {
                // Input bukan angka.
            }

            System.out.println("Masukkan angka antara "
                    + min + " sampai " + max + ".");
        }
    }

    public String bacaString(String pesan) {
        while (true) {
            System.out.print(pesan);
            String teks = input.nextLine().trim();

            if (!teks.isEmpty()) {
                return teks;
            }

            System.out.println("Input tidak boleh kosong.");
        }
    }

    public String bacaNama(String pesan) {
        while (true) {
            String nama = bacaString(pesan);

            if (nama.matches("[\\p{L} .'-]{2,100}")) {
                return nama;
            }

            System.out.println("Nama minimal 2 karakter "
                    + "dan hanya berisi huruf/spasi.");
        }
    }

    public String bacaEmail(String pesan) {
        while (true) {
            String email = bacaString(pesan);

            if (email.matches(
                    "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
                return email;
            }

            System.out.println("Format email tidak valid.");
        }
    }

    public String bacaNoHP(String pesan) {
        while (true) {
            String noHP = bacaString(pesan);

            if (noHP.matches("[0-9+]{8,20}")) {
                return noHP;
            }

            System.out.println("Nomor HP harus 8-20 karakter "
                    + "berupa angka atau tanda +.");
        }
    }

    public String bacaPassword(String pesan) {
        while (true) {
            String password = bacaString(pesan);

            if (password.length() >= 6) {
                return password;
            }

            System.out.println("Password minimal 6 karakter.");
        }
    }

    public int bacaPositif(String pesan) {
        while (true) {
            System.out.print(pesan);

            try {
                int angka = Integer.parseInt(input.nextLine().trim());

                if (angka > 0) {
                    return angka;
                }
            } catch (NumberFormatException e) {
                // Input bukan bilangan bulat.
            }

            System.out.println("Masukkan angka bulat lebih dari 0.");
        }
    }

    public double bacaHarga(String pesan) {
        while (true) {
            System.out.print(pesan);

            try {
                double harga = Double.parseDouble(
                        input.nextLine().trim().replace(",", "."));

                if (Double.isFinite(harga) && harga > 0) {
                    return harga;
                }
            } catch (NumberFormatException e) {
                // Input harga tidak valid.
            }

            System.out.println("Harga harus lebih dari 0.");
        }
    }

    public String bacaGenre() {
        String[] genre = {
            "Action", "Comedy", "Horror",
            "Romance", "Sci-Fi", "Animation"
        };

        System.out.println("\nPilihan Genre:");

        for (int i = 0; i < genre.length; i++) {
            System.out.println((i + 1) + ". " + genre[i]);
        }

        int pilihan = bacaPilihan("Pilih genre: ", 1, genre.length);
        return genre[pilihan - 1];
    }

    public String bacaTipeStudio() {
        System.out.println("1. Regular");
        System.out.println("2. VIP");

        int pilihan = bacaPilihan("Pilih tipe studio: ", 1, 2);

        return pilihan == 1 ? "Regular" : "VIP";
    }

    public boolean konfirmasi(String pesan) {
        while (true) {
            System.out.print(pesan + " (y/n): ");
            String jawaban = input.nextLine().trim();

            if (jawaban.equalsIgnoreCase("y")) {
                return true;
            }

            if (jawaban.equalsIgnoreCase("n")) {
                return false;
            }

            System.out.println("Masukkan y atau n.");
        }
    }

    public void pesan(String pesan) {
        System.out.println(pesan);
    }

    public void tampilFilm(Film film, int nomor) {
        System.out.printf(
                "%n[%d] ID: %d | %s | %s | %d menit | Rp%.2f%n",
                nomor, film.getIdFilm(), film.getJudul(),
                film.getGenre(), film.getDurasi(), film.getHarga()
        );
    }

    public void tampilStudio(Studio studio, int nomor) {
        System.out.printf(
                "%n[%d] ID: %d | %s | Kapasitas: %d | Tipe: %s%n",
                nomor, studio.getIdStudio(), studio.getNama(),
                studio.getKapasitas(), studio.getTipe()
        );
    }

    public void tampilTiket(Tiket tiket, int nomor) {
        System.out.printf(
                "%n[%d] ID tiket: %d | Penonton: %s | Film: %s "
                + "| Studio: %s | Jumlah: %d | Total: Rp%.2f%n",
                nomor, tiket.getIdTiket(),
                tiket.getPenonton().getNama(),
                tiket.getFilm().getJudul(),
                tiket.getStudio().getNama(),
                tiket.getJumlah(),
                tiket.getTotalHarga()
        );
    }

    public void tampilTransaksi(Transaksi transaksi, int nomor) {
        System.out.printf(
                "%n[%d] %s | Tanggal: %s | Penonton: %s "
                + "| Film: %s | Studio: %s "
                + "| Harga awal: Rp%.2f | Diskon: Rp%.2f "
                + "| Bayar: Rp%.2f%n",
                nomor,
                transaksi.getKodeTransaksi(),
                transaksi.getTanggalTransaksi(),
                transaksi.getPenonton().getNama(),
                transaksi.getTiket().getFilm().getJudul(),
                transaksi.getTiket().getStudio().getNama(),
                transaksi.getTotalAwal(),
                transaksi.getDiskon(),
                transaksi.getTotalBayar()
        );
    }
}