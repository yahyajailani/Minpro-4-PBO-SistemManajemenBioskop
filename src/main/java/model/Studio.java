/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADVAN
 */
public class Studio {

    private int idStudio;
    private String nama;
    private int kapasitas;
    private String tipe;

    public Studio(String nama, int kapasitas, String tipe) {
        this(0, nama, kapasitas, tipe);
    }

    public Studio(int idStudio, String nama,
                  int kapasitas, String tipe) {
        this.idStudio = idStudio;
        this.nama = nama;
        this.kapasitas = kapasitas;
        this.tipe = tipe;
    }

    public int getIdStudio() {
        return idStudio;
    }

    public void setIdStudio(int idStudio) {
        this.idStudio = idStudio;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getKapasitas() {
        return kapasitas;
    }

    public void setKapasitas(int kapasitas) {
        this.kapasitas = kapasitas;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }
}