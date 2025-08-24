package services;

import entities.Admin;
import utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminService {

    public boolean validateAdminLogin(String username, String password) {
        String sql = "SELECT COUNT(*) FROM admin WHERE username=? AND password=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean createAdmin(Admin a) {
        String sql = "INSERT INTO admin(admin_id,username,password,email) VALUES(?,?,?,?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, a.getAdminId());
            ps.setString(2, a.getUsername());
            ps.setString(3, a.getPassword());
            ps.setString(4, a.getEmail());
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public List<String> getAllOrdersSummary() {
        String sql = "SELECT o.order_id, o.status, c.username FROM orders o JOIN customer c ON o.customer_id=c.customer_id ORDER BY o.order_id";
        List<String> list = new ArrayList<>();
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add("Order ID: " + rs.getInt(1) + " | Customer: " + rs.getString(3) + " | Status: " + rs.getString(2));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public boolean updateOrderStatus(int orderId, String status) {
        String sql = "UPDATE orders SET status=? WHERE order_id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setInt(2, orderId);
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }
}
