package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.DriverManager;
import dbconnect.DBConnection;
import model.Product;

public class ProductDao {
    private Connection connection;

    public ProductDao() {
        try {
            
            this.connection = DBConnection.getConnection();
            setTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void setTable() {
        try (Statement statement = connection.createStatement()) {
            statement.execute("USE inventory_db");
            statement.execute("CREATE TABLE IF NOT EXISTS products (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "name VARCHAR(255) NOT NULL," +
                    "price VARCHAR(255) NOT NULL," +
                    "quantity VARCHAR(255) NOT NULL)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM products";

        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setName(resultSet.getString("name"));
                product.setPrice(resultSet.getDouble("price"));
                product.setQuantity(resultSet.getInt("quantity"));
                products.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    public void addProduct(Product product) {
        String query = "INSERT INTO products (name, price, quantity) VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.setInt(3, product.getQuantity());

            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0) {
                // Retrieve the auto-generated id
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int generatedId = generatedKeys.getInt(1);
                        product.setId(generatedId);
                    } else {
                        throw new SQLException("Failed to get the auto-generated id.");
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void updateProduct(Product product) {
        String query = "UPDATE products SET name=?, price=?, quantity=? WHERE id=?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.setInt(3, product.getQuantity());
            statement.setInt(4, product.getId());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                System.out.println("No product found with the given id.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteProduct(int productId) {
        String query = "DELETE FROM products WHERE id=?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, productId);

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                System.out.println("No product found with the given id.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public Product getProductById(int productId) {
        String query = "SELECT * FROM products WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, productId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Product product = new Product();
                    product.setId(resultSet.getInt("id"));
                    product.setName(resultSet.getString("name"));
                    product.setPrice(Double.parseDouble(resultSet.getString("price")));
                    product.setQuantity(Integer.parseInt(resultSet.getString("quantity")));
                    return product;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // Return null if no product found with the given ID
    }
}
