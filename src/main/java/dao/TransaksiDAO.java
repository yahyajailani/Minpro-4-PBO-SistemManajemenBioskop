/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import config.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Film;
import model.Penonton;
import model.Studio;
import model.Tiket;
import model.Transaksi;

/**
 *
 * @author ADVAN
 */
public class TransaksiDAO {

    public void tambah(Transaksi transaksi) throws SQLException {
        String sql = "INSERT INTO transaksi"
                + "(kode_transaksi, id_tiket, total_awal, "
                + "diskon, total_bayar) VALUES(?,?,?,?,?)";

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, transaksi.getKodeTransaksi());
            ps.setInt(2, transaksi.getTiket().getIdTiket());
            ps.setDouble(3, transaksi.getTotalAwal());
            ps.setDouble(4, transaksi.getDiskon());
            ps.setDouble(5, transaksi.getTotalBayar());

            ps.executeUpdate();
        }
    }

    public List<Transaksi> getSemua() throws SQLException {
        return queryTransaksi(null);
    }

    public List<Transaksi> getByPenontonId(int idPenonton)
            throws SQLException {
        return queryTransaksi(idPenonton);
    }

    private List<Transaksi> queryTransaksi(Integer idPenonton)
            throws SQLException {

        List<Transaksi> daftar = new ArrayList<>();

        String sql = "SELECT "
            + "tr.id_transaksi, tr.kode_transaksi, "
            + "tr.total_awal, tr.diskon, tr.total_bayar, "
            + "tr.tanggal_transaksi, "
            + "t.id_tiket, t.jumlah, t.harga_per_tiket, t.total_harga, "
            + "p.id_penonton, p.nama AS nama_penonton, "
            + "p.email, p.no_hp, p.password, "
            + "f.id_film AS id_film, f.Judul AS judul, f.genre, f.durasi, f.harga_tiket, "
            + "s.id_studio, s.nama AS nama_studio, "
            + "s.kapasitas, s.tipe "
            + "FROM transaksi tr "
            + "JOIN tiket t ON tr.id_tiket = t.id_tiket "
            + "JOIN penonton p ON t.id_penonton = p.id_penonton "
            + "JOIN film f ON t.id_film = f.id_film "
            + "JOIN studio s ON t.id_studio = s.id_studio "
            + (idPenonton == null ? "" : "WHERE p.id_penonton = ? ")
            + "ORDER BY tr.id_transaksi DESC";

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            if (idPenonton != null) {
                ps.setInt(1, idPenonton);
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Penonton p = new Penonton(
                            rs.getInt("id_penonton"),
                            rs.getString("nama_penonton"),
                            rs.getString("email"),
                            rs.getString("no_hp"),
                            rs.getString("password")
                    );

                    Film f = new Film(
                            rs.getInt("id_film"),
                            rs.getString("judul"),
                            rs.getString("genre"),
                            rs.getInt("durasi"),
                            rs.getDouble("harga_tiket")
                    );

                    Studio s = new Studio(
                            rs.getInt("id_studio"),
                            rs.getString("nama_studio"),
                            rs.getInt("kapasitas"),
                            rs.getString("tipe")
                    );

                    Tiket t = new Tiket(
                            rs.getInt("id_tiket"),
                            p, f, s,
                            rs.getInt("jumlah"),
                            rs.getDouble("harga_per_tiket"),
                            rs.getDouble("total_harga")
                    );

                    Transaksi transaksi = new Transaksi(
                            rs.getInt("id_transaksi"),
                            rs.getString("kode_transaksi"),
                            p, t,
                            rs.getDouble("total_awal"),
                            rs.getDouble("diskon"),
                            rs.getDouble("total_bayar"),
                            rs.getString("tanggal_transaksi")
                    );

                    daftar.add(transaksi);
                }
            }
        }

        return daftar;
    }
}