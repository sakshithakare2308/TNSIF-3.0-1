package services;

import entities.Product;
import utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductService {

    public boolean addProduct(Product p) {
        String sql = "INSERT INTO product(product_id,name,price,stock_quantity) VALUES(?,?,?,?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, p.getProductId());
            ps.setString(2, p.getName());
            ps.setDouble(3, p.getPrice());
            ps.setInt(4, p.getStockQuantity());
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean removeProduct(int productId) {
        String sql = "DELETE FROM product WHERE product_id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, productId);
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public List<Product> getAllProducts() {
        String sql = "SELECT product_id,name,price,stock_quantity FROM product ORDER BY product_id";
        List<Product> list = new ArrayList<>();
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Product(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getInt(4)));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public boolean updateProductStock(int productId, int stock) {
        String sql = "UPDATE product SET stock_quantity=? WHERE product_id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, stock);
            ps.setInt(2, productId);
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean updateProductPrice(int productId, double price) {
        String sql = "UPDATE product SET price=? WHERE product_id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDouble(1, price);
            ps.setInt(2, productId);
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }
}
