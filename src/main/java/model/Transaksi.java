/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADVAN
 */
public class Transaksi implements Pembayaran {

    private int idTransaksi;
    private String kodeTransaksi;
    private Penonton penonton;
    private Tiket tiket;
    private double totalAwal;
    private double diskon;
    private double totalBayar;
    private String tanggalTransaksi;

    public Transaksi(String kodeTransaksi,
                     Penonton penonton, Tiket tiket) {
        this(0, kodeTransaksi, penonton, tiket,
                tiket.getTotalHarga(),
                penonton.hitungDiskon(
                        tiket.getTotalHarga(), tiket.getJumlah()),
                penonton.hitungHargaAkhir(
                        tiket.getTotalHarga(), tiket.getJumlah()),
                "");
    }

    public Transaksi(int idTransaksi, String kodeTransaksi,
                     Penonton penonton, Tiket tiket,
                     double totalAwal, double diskon,
                     double totalBayar, String tanggalTransaksi) {
        this.idTransaksi = idTransaksi;
        this.kodeTransaksi = kodeTransaksi;
        this.penonton = penonton;
        this.tiket = tiket;
        this.totalAwal = totalAwal;
        this.diskon = diskon;
        this.totalBayar = totalBayar;
        this.tanggalTransaksi = tanggalTransaksi;
    }

    @Override
    public double hitungTotal() {
        return totalAwal - diskon;
    }

    @Override
    public void tampilkanPembayaran() {
        System.out.println("\n========== DETAIL PEMBAYARAN ==========");
        System.out.println("Kode transaksi : " + kodeTransaksi);
        System.out.println("Penonton       : " + penonton.getNama());
        System.out.println("Film           : " + tiket.getFilm().getJudul());
        System.out.println("Studio         : " + tiket.getStudio().getNama()
                + " (" + tiket.getStudio().getTipe() + ")");
        System.out.println("Jumlah tiket   : " + tiket.getJumlah());
        System.out.printf("Harga awal     : Rp%.2f%n", totalAwal);
        System.out.printf("Diskon         : Rp%.2f%n", diskon);
        System.out.printf("Total bayar    : Rp%.2f%n", totalBayar);
    }

    public int getIdTransaksi() {
        return idTransaksi;
    }

    public void setIdTransaksi(int idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    public String getKodeTransaksi() {
        return kodeTransaksi;
    }

    public void setKodeTransaksi(String kodeTransaksi) {
        this.kodeTransaksi = kodeTransaksi;
    }

    public Penonton getPenonton() {
        return penonton;
    }

    public void setPenonton(Penonton penonton) {
        this.penonton = penonton;
    }

    public Tiket getTiket() {
        return tiket;
    }

    public void setTiket(Tiket tiket) {
        this.tiket = tiket;
    }

    public double getTotalAwal() {
        return totalAwal;
    }

    public void setTotalAwal(double totalAwal) {
        this.totalAwal = totalAwal;
    }

    public double getDiskon() {
        return diskon;
    }

    public void setDiskon(double diskon) {
        this.diskon = diskon;
    }

    public double getTotalBayar() {
        return totalBayar;
    }

    public void setTotalBayar(double totalBayar) {
        this.totalBayar = totalBayar;
    }

    public String getTanggalTransaksi() {
        return tanggalTransaksi;
    }

    public void setTanggalTransaksi(String tanggalTransaksi) {
        this.tanggalTransaksi = tanggalTransaksi;
    }
}