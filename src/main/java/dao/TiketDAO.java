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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Film;
import model.Penonton;
import model.Studio;
import model.Tiket;

/**
 *
 * @author ADVAN
 */
public class TiketDAO {

    public int tambah(Tiket tiket) throws SQLException {
        String sql = "INSERT INTO tiket"
                + "(id_penonton, id_film, id_studio, jumlah, "
                + "harga_tiket, total_harga) "
                + "VALUES(?,?,?,?,?,?)";

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(
                     sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, tiket.getPenonton().getIdPenonton());
            ps.setInt(2, tiket.getFilm().getIdFilm());
            ps.setInt(3, tiket.getStudio().getIdStudio());
            ps.setInt(4, tiket.getJumlah());
            ps.setDouble(5, tiket.getHargaTiket());
            ps.setDouble(6, tiket.getTotalHarga());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    int id = rs.getInt(1);
                    tiket.setIdTiket(id);
                    return id;
                }
            }
        }

        throw new SQLException("ID tiket baru tidak ditemukan.");
    }

    public List<Tiket> getSemua() throws SQLException {
        List<Tiket> daftar = new ArrayList<>();

        String sql = "SELECT "
                + "t.id_tiket, t.jumlah, t.harga_tiket, t.total_harga, "
                + "p.id_penonton, p.nama AS nama_penonton, "
                + "p.email, p.no_hp, p.password, "
                + "f.id_film, f.judul, f.genre, f.durasi, f.harga_tiket AS harga, "
                + "s.id_studio, s.nama AS nama_studio, "
                + "s.kapasitas, s.tipe "
                + "FROM tiket t "
                + "JOIN penonton p ON t.id_penonton=p.id_penonton "
                + "JOIN film f ON t.id_film=f.id_film "
                + "JOIN studio s ON t.id_studio=s.id_studio "
                + "ORDER BY t.id_tiket";

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

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
                        rs.getDouble("harga_tiket"),
                        rs.getDouble("total_harga")
                );

                daftar.add(t);
            }
        }

        return daftar;
    }

    public void update(int id, Tiket tiket) throws SQLException {
        String sql = "UPDATE tiket SET id_penonton=?, id_film=?, "
                + "id_studio=?, jumlah=?, harga_tiket=?, "
                + "total_harga=? WHERE id_tiket=?";

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, tiket.getPenonton().getIdPenonton());
            ps.setInt(2, tiket.getFilm().getIdFilm());
            ps.setInt(3, tiket.getStudio().getIdStudio());
            ps.setInt(4, tiket.getJumlah());
            ps.setDouble(5, tiket.getHargaTiket());
            ps.setDouble(6, tiket.getTotalHarga());
            ps.setInt(7, id);

            ps.executeUpdate();
        }
    }

    public void hapus(int id) throws SQLException {
        String sql = "DELETE FROM tiket WHERE id_tiket=?";

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
