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

/**
 *
 * @author ADVAN
 */
public class FilmDAO {

    public void tambah(Film film) throws SQLException {
        String sql = "INSERT INTO film(judul, genre, durasi, harga_tiket) "
                + "VALUES(?,?,?,?)";

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, film.getJudul());
            ps.setString(2, film.getGenre());
            ps.setInt(3, film.getDurasi());
            ps.setDouble(4, film.getHarga());

            ps.executeUpdate();
        }
    }

    public List<Film> getSemua() throws SQLException {
        List<Film> daftar = new ArrayList<>();

        String sql = "SELECT * FROM film ORDER BY id_film";

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                daftar.add(new Film(
                        rs.getInt("id_film"),
                        rs.getString("judul"),
                        rs.getString("genre"),
                        rs.getInt("durasi"),
                        rs.getDouble("harga_tiket")
                ));
            }
        }

        return daftar;
    }

    public void update(int id, Film film) throws SQLException {
        String sql = "UPDATE film SET judul=?, genre=?, durasi=?, "
                + "harga_tiket=? WHERE id_film=?";

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, film.getJudul());
            ps.setString(2, film.getGenre());
            ps.setInt(3, film.getDurasi());
            ps.setDouble(4, film.getHarga());
            ps.setInt(5, id);

            ps.executeUpdate();
        }
    }

    public void hapus(int id) throws SQLException {
        String sql = "DELETE FROM film WHERE id_film=?";

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}