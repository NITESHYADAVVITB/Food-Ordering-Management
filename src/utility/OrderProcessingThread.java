package utility;

import model.Order;

public class OrderProcessingThread extends Thread {

    private Order order;

    public OrderProcessingThread(Order order) {
        this.order = order;
    }

    @Override
    public void run() {

        System.out.println(
                "\nProcessing Order #" + order.getOrderId() + "..."
        );

        try {

            Thread.sleep(1000);

            order.setStatus("Preparing");

            System.out.println(
                    "Order #" + order.getOrderId()
                    + " is now being prepared."
            );

            Thread.sleep(1000);

            order.setStatus("Ready");

            System.out.println(
                    "Order #" + order.getOrderId()
                    + " is ready for delivery."
            );

            Thread.sleep(1000);

            order.setStatus("Completed");

            System.out.println(
                    "Order #" + order.getOrderId()
                    + " has been completed."
            );

        } catch (InterruptedException e) {

            System.out.println(
                    "Order processing was interrupted: "
                    + e.getMessage()
            );

            Thread.currentThread().interrupt();
        }
    }
}