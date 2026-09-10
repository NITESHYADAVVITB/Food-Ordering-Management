package model;

public class OrderItem {
    private MenuItem menuItem;
    private int quantity;

    public OrderItem(MenuItem menuItem, int quantity) {
        this.menuItem = menuItem;
        this.quantity = quantity;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalPrice() {
        return menuItem.getPrice() * quantity;
    }

    public void displayOrderItem() {
        System.out.println(
            menuItem.getItemName() + " | Quantity: " + quantity +
            " | Total: $" + getTotalPrice()
        );
    }
}