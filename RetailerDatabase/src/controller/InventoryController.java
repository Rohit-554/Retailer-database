package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import dao.EmployeeDAO;
import dao.ProductDao;
import model.Employee;
import model.Product;

public class InventoryController {
    private ProductDao productDAO;
    private EmployeeDAO employeeDAO;

    public InventoryController() {
        this.productDAO = new ProductDao();
        this.employeeDAO = new EmployeeDAO();
    }

    public void displayAllProducts() {
        List<Product> products = productDAO.getAllProducts();

        for (Product product : products) {
            System.out.println(product);
        }
    }

    public void addProduct() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter product name:");
        String name = scanner.nextLine();

        System.out.println("Enter product price:");
        double price = scanner.nextDouble();

        System.out.println("Enter product quantity:");
        int quantity = scanner.nextInt();

        Product newProduct = new Product();
        newProduct.setName(name);
        newProduct.setPrice(price);
        newProduct.setQuantity(quantity);

        productDAO.addProduct(newProduct);
        System.out.println("Product added successfully!");

       
        scanner.close();
    }
    
    public void updateProduct() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the ID of the product you want to update:");
        int productId = scanner.nextInt();

        Product existingProduct = productDAO.getProductById(productId);

        if (existingProduct == null) {
            System.out.println("No product found with the given ID.");
        } else {
            System.out.println("Enter updated product name:");
            String updatedName = scanner.nextLine(); // Consume the newline character
            updatedName = scanner.nextLine();

            System.out.println("Enter updated product price:");
            double updatedPrice = scanner.nextDouble();

            System.out.println("Enter updated product quantity:");
            int updatedQuantity = scanner.nextInt();

            existingProduct.setName(updatedName);
            existingProduct.setPrice(updatedPrice);
            existingProduct.setQuantity(updatedQuantity);

            productDAO.updateProduct(existingProduct);
            System.out.println("Product updated successfully!");
        }
    }

    public void deleteProduct() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the ID of the product you want to delete:");
        int productId = scanner.nextInt();

        Product existingProduct = productDAO.getProductById(productId);

        if (existingProduct == null) {
            System.out.println("No product found with the given ID.");
        } else {
            productDAO.deleteProduct(productId);
            System.out.println("Product deleted successfully!");
        }
        
        scanner.close();
    }
    
    
    //employee
    public void displayAllEmployees() {
        List<Employee> employees = employeeDAO.getAllEmployees();

        if (employees.isEmpty()) {
            System.out.println("No employees available.");
        } else {
            System.out.println("List of Employees:");
            for (Employee employee : employees) {
                System.out.println(employee);
            }
        }
    }

    public void addEmployee() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter employee name:");
        String name = scanner.nextLine();

        System.out.println("Enter employee position:");
        String position = scanner.nextLine();

        Employee newEmployee = new Employee();
        newEmployee.setName(name);
        newEmployee.setPosition(position);

        employeeDAO.addEmployee(newEmployee);
        System.out.println("Employee added successfully!");

        scanner.close();
    }

    
    
    
    
    
    
}
