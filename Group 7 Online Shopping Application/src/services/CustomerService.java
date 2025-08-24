package services;

import entities.Customer;
import utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerService {

    public boolean addCustomer(Customer c) {
        String sql = "INSERT INTO customer(customer_id, username, email, address) VALUES (?,?,?,?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, c.getCustomerId());
            ps.setString(2, c.getUsername());
            ps.setString(3, c.getEmail());
            ps.setString(4, c.getAddress());
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public List<Customer> getAllCustomers() {
        String sql = "SELECT customer_id, username, email, address FROM customer ORDER BY customer_id";
        List<Customer> list = new ArrayList<>();
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Customer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
}
