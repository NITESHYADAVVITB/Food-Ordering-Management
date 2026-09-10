package utility;

import java.util.List;
import java.util.stream.Collectors;

import model.Order;

public class ReportGenerator {

    public void generateOrderReport(List<Order> orders) {

        System.out.println("\n========== ORDER REPORT ==========");

        if (orders.isEmpty()) {
            System.out.println("No orders available.");
            return;
        }

        long totalOrders = orders.size();

        long completedOrders = orders.stream()
                .filter(order ->
                        order.getStatus()
                                .equalsIgnoreCase("Completed"))
                .count();

        long pendingOrders = orders.stream()
                .filter(order ->
                        !order.getStatus()
                                .equalsIgnoreCase("Completed")
                        && !order.getStatus()
                                .equalsIgnoreCase("Cancelled"))
                .count();

        double totalSales = orders.stream()
                .mapToDouble(Order::calculateFinalAmount)
                .sum();

        double averageOrderValue =
                orders.stream()
                        .mapToDouble(Order::calculateFinalAmount)
                        .average()
                        .orElse(0.0);

        double highestOrderValue =
                orders.stream()
                        .mapToDouble(Order::calculateFinalAmount)
                        .max()
                        .orElse(0.0);

        double averageRating =
                orders.stream()
                        .filter(order -> order.getRating() > 0)
                        .mapToInt(Order::getRating)
                        .average()
                        .orElse(0.0);

        List<String> customers = orders.stream()
                .map(order ->
                        order.getCustomer().getName())
                .distinct()
                .collect(Collectors.toList());

        System.out.println(
                "Total Orders: " + totalOrders
        );

        System.out.println(
                "Completed Orders: " + completedOrders
        );

        System.out.println(
                "Pending Orders: " + pendingOrders
        );

        System.out.printf(
                "Total Sales: $%.2f%n",
                totalSales
        );

        System.out.printf(
                "Average Order Value: $%.2f%n",
                averageOrderValue
        );

        System.out.printf(
                "Highest Order Value: $%.2f%n",
                highestOrderValue
        );

        if (averageRating > 0) {
            System.out.printf(
                    "Average Restaurant Rating: %.2f/5%n",
                    averageRating
            );
        } else {
            System.out.println(
                    "Average Restaurant Rating: No ratings yet"
            );
        }

        System.out.println(
                "Customers Who Ordered: "
                + customers.size()
        );

        System.out.println(
                "=================================="
        );
    }
}