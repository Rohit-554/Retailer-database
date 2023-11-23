import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

import controller.*;;

public class App {
	public static void main(String[] args) {
	
		Scanner scanner = new Scanner(System.in);

        boolean exit = false;

        while (!exit) {
            System.out.println("Choose management type:");
            System.out.println("1. Product Management");
            System.out.println("2. Employee Management");
            System.out.println("3. Exit");

            int managementChoice = scanner.nextInt();

            switch (managementChoice) {
                case 1:{
                		productManagement();
                		scanner.nextLine();
                	  }
                    
                  
                    break;
                case 2:
                    employeeManagement();
                    break;
                case 3:
                    exit = true;
                    System.out.println("Exiting the program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 3.");
            }
        }

        scanner.close();
    }

    private static void productManagement() {
        InventoryController inventoryController = new InventoryController();
        Scanner scanner = new Scanner(System.in);

        boolean exit = false;

        while (!exit) {
            System.out.println("Choose an operation:");
            System.out.println("1. Add Product");
            System.out.println("2. Display All Products");
            System.out.println("3. Update Product");
            System.out.println("4. Delete Product");
            System.out.println("5. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    inventoryController.addProduct();
                    scanner.nextLine();
                    break;
                case 2:
                    inventoryController.displayAllProducts();
                    break;
                case 3:
                    inventoryController.updateProduct();
                    break;
                case 4:
                    inventoryController.deleteProduct();
                    break;
                case 5:
                    exit = true;
                    System.out.println("Exiting the product management. Returning to main menu.");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }

            // Consume the newline character
            scanner.nextLine();
        }

        scanner.close();
    }

    private static void employeeManagement() {
        InventoryController inventoryController = new InventoryController();
        Scanner scanner = new Scanner(System.in);

        boolean exit = false;

        while (!exit) {
            System.out.println("Choose an operation:");
            System.out.println("1. Add Employee");
            System.out.println("2. Display All Employees");
            System.out.println("3. Update Employee");
            System.out.println("4. Delete Employee");
            System.out.println("5. Exit");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();  // Consume the newline character

                switch (choice) {
                    case 1:
                        inventoryController.addEmployee();
                        break;
                    case 2:
                        inventoryController.displayAllEmployees();
                        break;
                    case 3:
                        // Add update employee functionality
                        break;
                    case 4:
                        // Add delete employee functionality
                        break;
                    case 5:
                        exit = true;
                        System.out.println("Exiting the employee management. Returning to main menu.");
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 5.");
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();  // Consume the invalid input
            }
        }

        scanner.close();
    }

		
}
    
