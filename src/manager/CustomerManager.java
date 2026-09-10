package manager;

import java.util.ArrayList;
import java.util.List;
import exception.InvalidCustomerException;
import model.Customer;

public class CustomerManager {

    private List<Customer> customers;

    public CustomerManager() {
        customers = new ArrayList<>();
    }

    public void addCustomer(Customer customer)
            throws InvalidCustomerException {

        if (customer.getName() == null ||
                customer.getName().isBlank()) {

            throw new InvalidCustomerException(
                    "Customer name cannot be empty."
            );
        }

        if (customer.getPhone() == null ||
                !customer.getPhone().matches("\\d{10}")) {

            throw new InvalidCustomerException(
                    "Customer phone number must contain exactly 10 digits."
            );
        }

        if (findCustomer(customer.getCustomerId()) != null) {
            throw new InvalidCustomerException(
                    "Customer ID already exists."
            );
        }

        customers.add(customer);
        System.out.println("Customer added successfully.");
    }

    public Customer findCustomer(int customerId) {

        for (Customer customer : customers) {

            if (customer.getCustomerId() == customerId) {
                return customer;
            }
        }

        return null;
    }

    public void displayAllCustomers() {

        if (customers.isEmpty()) {
            System.out.println("No customers found.");
            return;
        }

        System.out.println("\n--- Customer List ---");

        for (Customer customer : customers) {
            customer.displayCustomer();
            System.out.println("--------------------");
        }
    }

    public List<Customer> getCustomers() {
        return customers;
    }
}