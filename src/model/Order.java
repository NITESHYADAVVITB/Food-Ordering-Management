package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {

    private int orderId;
    private Customer customer;
    private List<OrderItem> items;
    private LocalDateTime orderDateTime;
    private String status;
    private int rating;

    public Order(int orderId, Customer customer) {
        this.orderId = orderId;
        this.customer = customer;
        this.items = new ArrayList<>();
        this.orderDateTime = LocalDateTime.now();
        this.status = "Placed";
        this.rating = 0;
    }

    public int getOrderId() {
        return orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public LocalDateTime getOrderDateTime() {
        return orderDateTime;
    }

    public String getStatus() {
        return status;
    }

    public int getRating() {
        return rating;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setRating(int rating) {
        if (rating >= 1 && rating <= 5) {
            this.rating = rating;
        }
    }

    public void addItem(OrderItem item) {
        items.add(item);
    }

    public double calculateTotal() {

        double total = 0;

        for (OrderItem item : items) {
            total += item.getTotalPrice();
        }

        return total;
    }

    public double calculateDiscount() {

        double total = calculateTotal();

        if (total > 30) {
            return total * 0.10;
        }

        return 0;
    }

    public double calculateFinalAmount() {

        return calculateTotal() - calculateDiscount();
    }

    public void displayOrder() {

        System.out.println("\nOrder ID: " + orderId);
        System.out.println("Customer: " + customer.getName());
        System.out.println("Order Time: " + orderDateTime);
        System.out.println("Status: " + status);

        if (rating > 0) {
            System.out.println("Rating: " + rating + "/5");
        }

        System.out.println("Items:");

        for (OrderItem item : items) {
            item.displayOrderItem();
        }

        System.out.printf(
                "Subtotal: $%.2f%n",
                calculateTotal()
        );

        System.out.printf(
                "Discount: $%.2f%n",
                calculateDiscount()
        );

        System.out.printf(
                "Final Bill: $%.2f%n",
                calculateFinalAmount()
        );
    }

    public void generateBill() {

        System.out.println("\n======================================");
        System.out.println("              ORDER BILL");
        System.out.println("======================================");

        System.out.println("Order ID   : " + orderId);
        System.out.println("Customer   : " + customer.getName());
        System.out.println("Phone      : " + customer.getPhone());
        System.out.println("Order Time : " + orderDateTime);
        System.out.println("Status     : " + status);

        System.out.println("--------------------------------------");
        System.out.println("Item                 Qty       Amount");
        System.out.println("--------------------------------------");

        for (OrderItem item : items) {

            System.out.printf(
                    "%-20s %-8d $%.2f%n",
                    item.getMenuItem().getItemName(),
                    item.getQuantity(),
                    item.getTotalPrice()
            );
        }

        System.out.println("--------------------------------------");

        System.out.printf(
                "SUBTOTAL:                    $%.2f%n",
                calculateTotal()
        );

        System.out.printf(
                "DISCOUNT:                    $%.2f%n",
                calculateDiscount()
        );

        System.out.printf(
                "FINAL BILL:                  $%.2f%n",
                calculateFinalAmount()
        );

        System.out.println("======================================");
        System.out.println("          Thank you for ordering!");
        System.out.println("======================================");
    }
}