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
import model.Penonton;

/**
 *
 * @author ADVAN
 */
public class PenontonDAO {

    private Penonton dariResultSet(ResultSet rs)
            throws SQLException {

        return new Penonton(
                rs.getInt("id_penonton"),
                rs.getString("nama"),
                rs.getString("email"),
                rs.getString("no_hp"),
                rs.getString("password")
        );
    }

    public void tambah(Penonton penonton) throws SQLException {
        String sql = "INSERT INTO penonton"
                + "(nama, email, no_hp, password) VALUES(?,?,?,?)";

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, penonton.getNama());
            ps.setString(2, penonton.getEmail());
            ps.setString(3, penonton.getNoHP());
            ps.setString(4, penonton.getPassword());

            ps.executeUpdate();
        }
    }

    public Penonton login(String email, String password)
            throws SQLException {

        String sql = "SELECT * FROM penonton "
                + "WHERE LOWER(email)=LOWER(?) AND password=?";

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return dariResultSet(rs);
                }
            }
        }

        return null;
    }

    public Penonton cariEmail(String email) throws SQLException {
        String sql = "SELECT * FROM penonton "
                + "WHERE LOWER(email)=LOWER(?)";

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, email);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return dariResultSet(rs);
                }
            }
        }

        return null;
    }

    public List<Penonton> getSemua() throws SQLException {
        List<Penonton> daftar = new ArrayList<>();
        String sql = "SELECT * FROM penonton ORDER BY id_penonton";

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                daftar.add(dariResultSet(rs));
            }
        }

        return daftar;
    }
}