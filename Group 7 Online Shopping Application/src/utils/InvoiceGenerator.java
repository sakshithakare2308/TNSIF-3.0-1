package utils;

import java.sql.*;

public class InvoiceGenerator {

    public static void printInvoice(int orderId) {
        String orderSql = "SELECT o.order_id, o.status, c.username " +
                          "FROM orders o JOIN customer c ON o.customer_id=c.customer_id " +
                          "WHERE o.order_id=?";
        String itemsSql = "SELECT p.name, op.quantity, p.price " +
                          "FROM order_products op JOIN product p ON op.product_id=p.product_id " +
                          "WHERE op.order_id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement psOrder = con.prepareStatement(orderSql)) {

            psOrder.setInt(1, orderId);
            try (ResultSet rs = psOrder.executeQuery()) {
                if (rs.next()) {
                    String customer = rs.getString("username");
                    String status = rs.getString("status");

                    System.out.println("========= Invoice =========");
                    System.out.println("Order ID: " + orderId);
                    System.out.println("Customer: " + customer);
                    System.out.println("Status: " + status);
                    System.out.println("---------------------------");
                    System.out.printf("%-15s %-5s %-8s %-8s%n", "Product", "Qty", "Price", "Total");

                    double grandTotal = 0.0;
                    try (PreparedStatement psItems = con.prepareStatement(itemsSql)) {
                        psItems.setInt(1, orderId);
                        try (ResultSet rsi = psItems.executeQuery()) {
                            while (rsi.next()) {
                                String pname = rsi.getString("name");
                                int qty = rsi.getInt("quantity");
                                double price = rsi.getDouble("price");
                                double total = price * qty;
                                grandTotal += total;
                                System.out.printf("%-15s %-5d %-8.2f %-8.2f%n", pname, qty, price, total);
                            }
                        }
                    }
                    System.out.println("---------------------------");
                    System.out.println("Grand Total: " + grandTotal);
                    System.out.println("===========================");
                } else {
                    System.out.println("Invoice not found for order ID " + orderId);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error printing invoice: " + e);
        }
    }
}
