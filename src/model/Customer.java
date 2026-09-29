package model;

public class Customer extends Person {

    public Customer(int customerId, String name, String phone) {
        super(customerId, name, phone);
    }

    public int getCustomerId() {
        return getId();
    }

    public void displayDetails() {
        System.out.println("Customer ID: " + getId());
        System.out.println("Name: " + getName());
        System.out.println("Phone: " + getPhone());
    }

    public void displayCustomer() {
        displayDetails();
    }
}