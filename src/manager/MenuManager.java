package manager;

import java.util.ArrayList;
import java.util.List;

import exception.InvalidMenuItemException;
import model.MenuItem;

public class MenuManager {

    private List<MenuItem> menuItems;

    public MenuManager() {
        menuItems = new ArrayList<>();
    }

    public void addMenuItem(MenuItem item)
            throws InvalidMenuItemException {

        if (item.getItemName() == null ||
                item.getItemName().isBlank()) {

            throw new InvalidMenuItemException(
                    "Menu item name cannot be empty."
            );
        }

        if (item.getPrice() <= 0) {

            throw new InvalidMenuItemException(
                    "Menu item price must be greater than zero."
            );
        }

        if (findMenuItem(item.getItemId()) != null) {

            throw new InvalidMenuItemException(
                    "Menu item ID already exists."
            );
        }

        menuItems.add(item);

        System.out.println(
                "Menu item added successfully."
        );
    }

    public MenuItem findMenuItem(int itemId) {

        for (MenuItem item : menuItems) {

            if (item.getItemId() == itemId) {
                return item;
            }
        }

        return null;
    }

    public void updateAvailability(
            int itemId,
            boolean available) {

        MenuItem item = findMenuItem(itemId);

        if (item != null) {

            item.setAvailable(available);

            System.out.println(
                    "Menu item availability updated successfully."
            );

        } else {

            System.out.println(
                    "Menu item not found."
            );
        }
    }

    public void searchByCategory(String category) {

        boolean found = false;

        System.out.println(
                "\n--- " + category + " Items ---"
        );

        for (MenuItem item : menuItems) {

            if (item.getCategory()
                    .equalsIgnoreCase(category)) {

                item.displayItem();
                found = true;
            }
        }

        if (!found) {

            System.out.println(
                    "No items found in this category."
            );
        }
    }

    public void displayMenu() {

        if (menuItems.isEmpty()) {

            System.out.println(
                    "No menu items available."
            );

            return;
        }

        System.out.println(
                "\n--- Restaurant Menu ---"
        );

        for (MenuItem item : menuItems) {
            item.displayItem();
        }
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }
}