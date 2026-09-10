package manager;

import java.util.ArrayList;
import java.util.List;

import exception.InvalidOrderException;
import model.Order;
import model.OrderItem;

public class OrderManager {

    private List<Order> orders;

    public OrderManager() {
        orders = new ArrayList<>();
    }

    public void addOrder(Order order)
            throws InvalidOrderException {

        if (order.getCustomer() == null) {
            throw new InvalidOrderException(
                    "Order must have a customer."
            );
        }

        if (order.getItems().isEmpty()) {
            throw new InvalidOrderException(
                    "Order must contain at least one item."
            );
        }

        if (findOrder(order.getOrderId()) != null) {
            throw new InvalidOrderException(
                    "Order ID already exists."
            );
        }

        for (OrderItem item : order.getItems()) {

            if (item.getQuantity() <= 0) {
                throw new InvalidOrderException(
                        "Order quantity must be greater than zero."
                );
            }

            if (!item.getMenuItem().isAvailable()) {
                throw new InvalidOrderException(
                        item.getMenuItem().getItemName()
                        + " is currently unavailable."
                );
            }
        }

        orders.add(order);

        System.out.println(
                "Order placed successfully."
        );
    }

    public Order findOrder(int orderId) {

        for (Order order : orders) {

            if (order.getOrderId() == orderId) {
                return order;
            }
        }

        return null;
    }

    public void updateOrderStatus(
            int orderId,
            String status)
            throws InvalidOrderException {

        String[] validStatuses = {
                "Placed",
                "Preparing",
                "Ready",
                "Completed",
                "Cancelled"
        };

        boolean valid = false;

        for (String validStatus : validStatuses) {

            if (validStatus.equalsIgnoreCase(status)) {
                valid = true;
                status = validStatus;
                break;
            }
        }

        if (!valid) {
            throw new InvalidOrderException(
                    "Invalid order status."
            );
        }

        Order order = findOrder(orderId);

        if (order == null) {
            System.out.println("Order not found.");
            return;
        }

        if (order.getStatus().equalsIgnoreCase("Completed")
                && status.equalsIgnoreCase("Cancelled")) {

            throw new InvalidOrderException(
                    "Completed orders cannot be cancelled."
            );
        }

        order.setStatus(status);

        System.out.println(
                "Order status updated successfully."
        );
    }

    public void addRating(
            int orderId,
            int rating)
            throws InvalidOrderException {

        Order order = findOrder(orderId);

        if (order == null) {
            throw new InvalidOrderException(
                    "Order not found."
            );
        }

        if (!order.getStatus().equalsIgnoreCase("Completed")) {
            throw new InvalidOrderException(
                    "Only completed orders can be rated."
            );
        }

        if (rating < 1 || rating > 5) {
            throw new InvalidOrderException(
                    "Rating must be between 1 and 5."
            );
        }

        if (order.getRating() > 0) {
            throw new InvalidOrderException(
                    "This order has already been rated."
            );
        }

        order.setRating(rating);

        System.out.println(
                "Thank you! Your rating has been recorded."
        );
    }

    public void displayAllOrders() {

        if (orders.isEmpty()) {
            System.out.println("No orders found.");
            return;
        }

        System.out.println("\n--- Order History ---");

        for (Order order : orders) {
            order.displayOrder();
            System.out.println("--------------------");
        }
    }

    public List<Order> getOrders() {
        return orders;
    }
}