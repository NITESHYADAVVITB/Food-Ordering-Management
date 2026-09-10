import java.util.List;
import java.util.Scanner;

import exception.InvalidCustomerException;
import exception.InvalidMenuItemException;
import exception.InvalidOrderException;

import manager.CustomerManager;
import manager.MenuManager;
import manager.OrderManager;

import model.Customer;
import model.MenuItem;
import model.Order;
import model.OrderItem;

import utility.FileManager;
import utility.OrderProcessingThread;
import utility.ReportGenerator;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        CustomerManager customerManager = new CustomerManager();
        MenuManager menuManager = new MenuManager();
        OrderManager orderManager = new OrderManager();
        FileManager fileManager = new FileManager();
        ReportGenerator reportGenerator = new ReportGenerator();

        // Default customer
        try {
            customerManager.addCustomer(
                    new Customer(
                            101,
                            "NITESH",
                            "9876543210"
                    )
            );
        } catch (InvalidCustomerException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Default menu items
        try {
            menuManager.addMenuItem(
                    new MenuItem(
                            101,
                            "Chicken Shawarma",
                            "Main Course",
                            13.00
                    )
            );

            menuManager.addMenuItem(
                    new MenuItem(
                            102,
                            "Veg Burger",
                            "Fast Food",
                            9.00
                    )
            );

            menuManager.addMenuItem(
                    new MenuItem(
                            103,
                            "Cheese Pizza",
                            "Pizza",
                            15.00
                    )
            );

            menuManager.addMenuItem(
                    new MenuItem(
                            104,
                            "French Fries",
                            "Sides",
                            6.00
                    )
            );

            menuManager.addMenuItem(
                    new MenuItem(
                            105,
                            "Chocolate Shake",
                            "Beverage",
                            7.00
                    )
            );

        } catch (InvalidMenuItemException e) {
            System.out.println("Error: " + e.getMessage());
        }

        boolean running = true;

        System.out.println("======================================");
        System.out.println("   FOOD ORDERING & RESTAURANT SYSTEM");
        System.out.println("======================================");

        while (running) {

            displayMainMenu();

            int choice = readInt("Enter your choice: ");

            try {

                switch (choice) {

                    case 1:
                        addCustomer(customerManager);
                        break;

                    case 2:
                        customerManager.displayAllCustomers();
                        break;

                    case 3:
                        addMenuItem(menuManager);
                        break;

                    case 4:
                        menuManager.displayMenu();
                        break;

                    case 5:
                        placeOrder(
                                customerManager,
                                menuManager,
                                orderManager,
                                fileManager
                        );
                        break;

                    case 6:
                        orderManager.displayAllOrders();
                        break;

                    case 7:
                        updateOrderStatus(orderManager);
                        break;

                    case 8:
                        rateOrder(orderManager);
                        break;

                    case 9:
                        reportGenerator.generateOrderReport(
                                orderManager.getOrders()
                        );
                        break;

                    case 10:
                        updateMenuAvailability(menuManager);
                        break;

                    case 11:
                        viewSavedOrders(fileManager);
                        break;

                    case 12:
                        running = false;

                        System.out.println(
                                "\nThank you for using the system!"
                        );
                        break;

                    default:
                        System.out.println(
                                "Invalid choice. Please try again."
                        );
                }

            } catch (
                    InvalidCustomerException |
                    InvalidMenuItemException |
                    InvalidOrderException e) {

                System.out.println(
                        "\nError: " + e.getMessage()
                );

            } catch (InterruptedException e) {

                System.out.println(
                        "\nOrder processing was interrupted."
                );

                Thread.currentThread().interrupt();
            }
        }

        scanner.close();

        System.out.println(
                "======================================"
        );
    }

    // ================= MAIN MENU =================

    public static void displayMainMenu() {

        System.out.println("\n========== MAIN MENU ==========");
        System.out.println("1. Add Customer");
        System.out.println("2. View Customers");
        System.out.println("3. Add Menu Item");
        System.out.println("4. View Menu");
        System.out.println("5. Place Order");
        System.out.println("6. View Orders");
        System.out.println("7. Update Order Status");
        System.out.println("8. Rate Order");
        System.out.println("9. Generate Report");
        System.out.println("10. Update Menu Availability");
        System.out.println("11. View Saved Orders");
        System.out.println("12. Exit");
        System.out.println("===============================");
    }

    // ================= CUSTOMER =================

    public static void addCustomer(
            CustomerManager customerManager)
            throws InvalidCustomerException {

        System.out.println("\n--- Add Customer ---");

        int id = readInt(
                "Enter customer ID: "
        );

        String name = readText(
                "Enter customer name: "
        );

        String phone = readText(
                "Enter phone number: "
        );

        Customer customer =
                new Customer(
                        id,
                        name,
                        phone
                );

        customerManager.addCustomer(customer);
    }

    // ================= MENU =================

    public static void addMenuItem(
            MenuManager menuManager)
            throws InvalidMenuItemException {

        System.out.println("\n--- Add Menu Item ---");

        int id = readInt(
                "Enter menu item ID: "
        );

        String name = readText(
                "Enter item name: "
        );

        String category = readText(
                "Enter category: "
        );

        double price = readDouble(
                "Enter price: $"
        );

        MenuItem item =
                new MenuItem(
                        id,
                        name,
                        category,
                        price
                );

        menuManager.addMenuItem(item);
    }

    // ================= ORDER =================

    public static void placeOrder(
            CustomerManager customerManager,
            MenuManager menuManager,
            OrderManager orderManager,
            FileManager fileManager)
            throws InvalidOrderException,
                   InterruptedException {

        System.out.println("\n--- Place Order ---");

        if (customerManager.getCustomers().isEmpty()) {

            System.out.println(
                    "No customers available."
            );

            System.out.println(
                    "Please add a customer first."
            );

            return;
        }

        if (menuManager.getMenuItems().isEmpty()) {

            System.out.println(
                    "No menu items available."
            );

            System.out.println(
                    "Please add a menu item first."
            );

            return;
        }

        customerManager.displayAllCustomers();

        int customerId = readInt(
                "\nEnter customer ID: "
        );

        Customer customer =
                customerManager.findCustomer(
                        customerId
                );

        if (customer == null) {

            throw new InvalidOrderException(
                    "Customer not found."
            );
        }

        menuManager.displayMenu();

        int orderId = readInt(
                "\nEnter order ID: "
        );

        Order order =
                new Order(
                        orderId,
                        customer
                );

        System.out.println(
                "\nEnter items for the order."
        );

        System.out.println(
                "Enter 0 as item ID when finished."
        );

        while (true) {

            int itemId = readInt(
                    "\nEnter item ID: "
            );

            if (itemId == 0) {
                break;
            }

            MenuItem menuItem =
                    menuManager.findMenuItem(
                            itemId
                    );

            if (menuItem == null) {

                System.out.println(
                        "Menu item not found."
                );

                continue;
            }

            if (!menuItem.isAvailable()) {

                System.out.println(
                        "This item is currently unavailable."
                );

                continue;
            }

            int quantity = readInt(
                    "Enter quantity: "
            );

            if (quantity <= 0) {

                System.out.println(
                        "Quantity must be greater than zero."
                );

                continue;
            }

            OrderItem orderItem =
                    new OrderItem(
                            menuItem,
                            quantity
                    );

            order.addItem(orderItem);

            System.out.println(
                    "Item added to order."
            );
        }

        if (order.getItems().isEmpty()) {

            throw new InvalidOrderException(
                    "Order must contain at least one item."
            );
        }

        orderManager.addOrder(order);

        order.generateBill();

        System.out.println(
                "\nStarting order processing..."
        );

        OrderProcessingThread processingThread =
                new OrderProcessingThread(order);

        processingThread.start();

        processingThread.join();

        System.out.println(
                "\nFinal Order Status: "
                + order.getStatus()
        );

        String orderData =
                "Order ID: " + order.getOrderId()
                + ", Customer: "
                + customer.getName()
                + ", Total: $"
                + String.format(
                        "%.2f",
                        order.calculateFinalAmount()
                )
                + ", Status: "
                + order.getStatus();

        fileManager.saveData(
                "orders.txt",
                orderData
        );
    }

    // ================= STATUS =================

    public static void updateOrderStatus(
            OrderManager orderManager)
            throws InvalidOrderException {

        if (orderManager.getOrders().isEmpty()) {

            System.out.println(
                    "No orders available."
            );

            return;
        }

        orderManager.displayAllOrders();

        int orderId = readInt(
                "\nEnter order ID: "
        );

        System.out.println(
                "\nAvailable statuses:"
        );

        System.out.println("1. Placed");
        System.out.println("2. Preparing");
        System.out.println("3. Ready");
        System.out.println("4. Completed");
        System.out.println("5. Cancelled");

        int choice = readInt(
                "Choose status: "
        );

        String status;

        switch (choice) {

            case 1:
                status = "Placed";
                break;

            case 2:
                status = "Preparing";
                break;

            case 3:
                status = "Ready";
                break;

            case 4:
                status = "Completed";
                break;

            case 5:
                status = "Cancelled";
                break;

            default:
                throw new InvalidOrderException(
                        "Invalid status choice."
                );
        }

        orderManager.updateOrderStatus(
                orderId,
                status
        );
    }

    // ================= RATING =================

    public static void rateOrder(
            OrderManager orderManager)
            throws InvalidOrderException {

        if (orderManager.getOrders().isEmpty()) {

            System.out.println(
                    "No orders available."
            );

            return;
        }

        orderManager.displayAllOrders();

        int orderId = readInt(
                "\nEnter order ID to rate: "
        );

        int rating = readInt(
                "Enter rating (1-5): "
        );

        orderManager.addRating(
                orderId,
                rating
        );
    }

    // ================= AVAILABILITY =================

    public static void updateMenuAvailability(
            MenuManager menuManager) {

        if (menuManager.getMenuItems().isEmpty()) {

            System.out.println(
                    "No menu items available."
            );

            return;
        }

        menuManager.displayMenu();

        int itemId = readInt(
                "\nEnter menu item ID: "
        );

        System.out.println(
                "1. Available"
        );

        System.out.println(
                "2. Not Available"
        );

        int choice = readInt(
                "Choose option: "
        );

        if (choice == 1) {

            menuManager.updateAvailability(
                    itemId,
                    true
            );

        } else if (choice == 2) {

            menuManager.updateAvailability(
                    itemId,
                    false
            );

        } else {

            System.out.println(
                    "Invalid choice."
            );
        }
    }

    // ================= SAVED DATA =================

    public static void viewSavedOrders(
            FileManager fileManager) {

        System.out.println(
                "\n========== SAVED ORDERS =========="
        );

        if (!fileManager.fileExists("orders.txt")) {

            System.out.println(
                    "No saved orders found."
            );

            System.out.println(
                    "=================================="
            );

            return;
        }

        List<String> savedOrders =
                fileManager.readData(
                        "orders.txt"
                );

        if (savedOrders.isEmpty()) {

            System.out.println(
                    "No saved orders found."
            );

        } else {

            for (String order : savedOrders) {
                System.out.println(order);
            }
        }

        System.out.println(
                "=================================="
        );
    }

    // ================= INPUT METHODS =================

    public static int readInt(String message) {

        while (true) {

            try {

                System.out.print(message);

                return Integer.parseInt(
                        scanner.nextLine().trim()
                );

            } catch (NumberFormatException e) {

                System.out.println(
                        "Please enter a valid integer."
                );
            }
        }
    }

    public static double readDouble(String message) {

        while (true) {

            try {

                System.out.print(message);

                return Double.parseDouble(
                        scanner.nextLine().trim()
                );

            } catch (NumberFormatException e) {

                System.out.println(
                        "Please enter a valid number."
                );
            }
        }
    }

    public static String readText(String message) {

        while (true) {

            System.out.print(message);

            String input =
                    scanner.nextLine().trim();

            if (!input.isEmpty()) {
                return input;
            }

            System.out.println(
                    "This field cannot be empty."
            );
        }
    }
}