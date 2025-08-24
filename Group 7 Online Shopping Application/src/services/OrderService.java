package services;

import utils.DBConnection;

import java.sql.*;

public class OrderService {

    public int createOrder(int customerId) {
        String sql = "INSERT INTO orders(customer_id, status) VALUES(?, 'CREATED')";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, customerId);
            int rows = ps.executeUpdate();
            if (rows == 1) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return -1;
    }

    public boolean addItemToOrder(int orderId, int productId, int qty) {
        String check = "SELECT stock_quantity FROM product WHERE product_id=?";
        String dec = "UPDATE product SET stock_quantity=stock_quantity-? WHERE product_id=?";
        String ins = "INSERT INTO order_products(order_id, product_id, quantity) VALUES(?,?,?)";
        try (Connection con = DBConnection.getConnection()) {
            con.setAutoCommit(false);
            try (PreparedStatement ps1 = con.prepareStatement(check)) {
                ps1.setInt(1, productId);
                try (ResultSet rs = ps1.executeQuery()) {
                    if (!rs.next() || rs.getInt(1) < qty) {
                        con.rollback();
                        return false;
                    }
                }
            }
            try (PreparedStatement ps2 = con.prepareStatement(dec)) {
                ps2.setInt(1, qty);
                ps2.setInt(2, productId);
                ps2.executeUpdate();
            }
            try (PreparedStatement ps3 = con.prepareStatement(ins)) {
                ps3.setInt(1, orderId);
                ps3.setInt(2, productId);
                ps3.setInt(3, qty);
                ps3.executeUpdate();
            }
            con.commit();
            con.setAutoCommit(true);
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public void printOrdersByCustomer(int customerId) {
        String head = "SELECT order_id, status FROM orders WHERE customer_id=? ORDER BY order_id";
        String items = "SELECT p.name, op.quantity FROM order_products op JOIN product p ON op.product_id=p.product_id WHERE op.order_id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(head)) {
            ps.setInt(1, customerId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int oid = rs.getInt(1);
                    String st = rs.getString(2);
                    System.out.println("Order ID: " + oid + " | Status: " + st);
                    try (PreparedStatement psi = con.prepareStatement(items)) {
                        psi.setInt(1, oid);
                        try (ResultSet rsi = psi.executeQuery()) {
                            while (rsi.next()) {
                                System.out.println("  Product: " + rsi.getString(1) + " | Quantity: " + rsi.getInt(2));
                            }
                        }
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void printAllOrders() {
        String head = "SELECT o.order_id, o.status, c.username FROM orders o JOIN customer c ON o.customer_id=c.customer_id ORDER BY o.order_id";
        String items = "SELECT p.name, op.quantity FROM order_products op JOIN product p ON op.product_id=p.product_id WHERE op.order_id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(head);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int oid = rs.getInt(1);
                String st = rs.getString(2);
                String user = rs.getString(3);
                System.out.println("Order ID: " + oid + " | Customer: " + user + " | Status: " + st);
                try (PreparedStatement psi = con.prepareStatement(items)) {
                    psi.setInt(1, oid);
                    try (ResultSet rsi = psi.executeQuery()) {
                        while (rsi.next()) {
                            System.out.println("  Product: " + rsi.getString(1) + " | Quantity: " + rsi.getInt(2));
                        }
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
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
