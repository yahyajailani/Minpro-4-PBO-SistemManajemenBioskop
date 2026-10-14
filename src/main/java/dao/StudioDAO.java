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
import model.Studio;

/**
 *
 * @author ADVAN
 */
public class StudioDAO {

    public void tambah(Studio studio) throws SQLException {
        String sql = "INSERT INTO studio(nama, kapasitas, tipe) "
                + "VALUES(?,?,?)";

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, studio.getNama());
            ps.setInt(2, studio.getKapasitas());
            ps.setString(3, studio.getTipe());

            ps.executeUpdate();
        }
    }

    public List<Studio> getSemua() throws SQLException {
        List<Studio> daftar = new ArrayList<>();

        String sql = "SELECT * FROM studio ORDER BY id_studio";

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                daftar.add(new Studio(
                        rs.getInt("id_studio"),
                        rs.getString("nama"),
                        rs.getInt("kapasitas"),
                        rs.getString("tipe")
                ));
            }
        }

        return daftar;
    }

    public void update(int id, Studio studio) throws SQLException {
        String sql = "UPDATE studio SET nama=?, kapasitas=?, "
                + "tipe=? WHERE id_studio=?";

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, studio.getNama());
            ps.setInt(2, studio.getKapasitas());
            ps.setString(3, studio.getTipe());
            ps.setInt(4, id);

            ps.executeUpdate();
        }
    }

    public void hapus(int id) throws SQLException {
        String sql = "DELETE FROM studio WHERE id_studio=?";

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}