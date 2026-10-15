/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADVAN
 */
public class Tiket {

    private int idTiket;
    private Penonton penonton;
    private Film film;
    private Studio studio;
    private int jumlah;
    private double hargaTiket;
    private double totalHarga;

    public Tiket(Penonton penonton, Film film,
                 Studio studio, int jumlah) {
        this(0, penonton, film, studio, jumlah,
                hitungHarga(film, studio),
                hitungHarga(film, studio) * jumlah);
    }

    public Tiket(int idTiket, Penonton penonton, Film film,
                 Studio studio, int jumlah,
                 double hargaTiket, double totalHarga) {
        this.idTiket = idTiket;
        this.penonton = penonton;
        this.film = film;
        this.studio = studio;
        this.jumlah = jumlah;
        this.hargaTiket = hargaTiket;
        this.totalHarga = totalHarga;
    }

    private static double hitungHarga(Film film, Studio studio) {
        double harga = film.getHarga();

        if ("VIP".equalsIgnoreCase(studio.getTipe())) {
            harga += 10000;
        }

        return harga;
    }

    public void hitungUlang() {
        hargaTiket = hitungHarga(film, studio);
        totalHarga = hargaTiket * jumlah;
    }

    public int getIdTiket() {
        return idTiket;
    }

    public void setIdTiket(int idTiket) {
        this.idTiket = idTiket;
    }

    public Penonton getPenonton() {
        return penonton;
    }

    public void setPenonton(Penonton penonton) {
        this.penonton = penonton;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
        hitungUlang();
    }

    public Studio getStudio() {
        return studio;
    }

    public void setStudio(Studio studio) {
        this.studio = studio;
        hitungUlang();
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
        hitungUlang();
    }

    public double getHargaTiket() {
        return hargaTiket;
    }

    public double getTotalHarga() {
        return totalHarga;
    }
}