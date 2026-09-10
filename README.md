Food Ordering & Restaurant Management System

Project Description

The Food Ordering & Restaurant Management System is a console-based Java application designed to manage customers, restaurant menu items, food orders, order processing, ratings, and basic restaurant reports.

The project demonstrates important Java programming concepts through a practical restaurant management system.

Features
Add and view customers
Add and view restaurant menu items
Search menu items by category
Update menu item availability
Place food orders
Generate an order bill
Calculate subtotal, discount, and final amount
Update order status
Process orders using multithreading
Rate completed orders
Generate restaurant order reports
Save order information to a file
View previously saved orders
Handle invalid inputs using custom exceptions
Technologies Used
Java
Java Collections
Java Exception Handling
Java Streams
Java File Handling
Java NIO.2
Java Date and Time API
Java Multithreading
Java Concepts Demonstrated
Object-Oriented Programming

The project uses:

Classes and Objects
Encapsulation
Inheritance
Abstraction
Polymorphism
Exception Handling

Custom exceptions are used for validation:

InvalidCustomerException
InvalidMenuItemException
InvalidOrderException

The project also uses:

try-catch
throws
throw
InterruptedException
Collections

The application uses ArrayList and List to store:

Customers
Menu items
Orders
Order items
Streams

Java Streams are used in the report generator to calculate:

Total orders
Completed orders
Pending orders
Total sales
Average order value
Highest order value
Average rating
File Handling

The project uses Java NIO.2 classes such as:

Path
Files
StandardOpenOption

Order information is stored in:

data/orders.txt

Date and Time

LocalDateTime is used to record the date and time when an order is placed.

Multithreading

OrderProcessingThread extends the Thread class to simulate the order processing process.

The order status progresses through:

Placed → Preparing → Ready → Completed

Project Structure

FoodOrderingManagement
├── data
│ └── orders.txt
├── src
│ ├── exception
│ │ ├── InvalidCustomerException.java
│ │ ├── InvalidMenuItemException.java
│ │ └── InvalidOrderException.java
│ ├── manager
│ │ ├── CustomerManager.java
│ │ ├── MenuManager.java
│ │ └── OrderManager.java
│ ├── model
│ │ ├── Person.java
│ │ ├── Customer.java
│ │ ├── MenuItem.java
│ │ ├── Order.java
│ │ └── OrderItem.java
│ ├── utility
│ │ ├── FileManager.java
│ │ ├── OrderProcessingThread.java
│ │ └── ReportGenerator.java
│ └── Main.java
└── README.md

How to Run
Compile

Open the terminal in the FoodOrderingManagement folder and run:

javac -d out src\exception*.java src\model*.java src\manager*.java src\utility*.java src\Main.java

Run

java -cp out Main

Main Menu
Add Customer
View Customers
Add Menu Item
View Menu
Place Order
View Orders
Update Order Status
Rate Order
Generate Report
Update Menu Availability
View Saved Orders
Exit
Sample Menu Items

The application starts with default menu items:

Chicken Shawarma
Veg Burger
Cheese Pizza
French Fries
Chocolate Shake

Additional menu items can also be added through the application.

Billing

The system calculates:

Subtotal
Discount
Final Bill

A 10% discount is applied when the order subtotal is greater than $30.

File Storage

Completed order information is stored in:

data/orders.txt

Previously saved orders can be viewed through the application.

Conclusion

This project provides a practical implementation of Java programming concepts in a food ordering and restaurant management scenario. It combines object-oriented programming, exception handling, collections, streams, file handling, date and time APIs, and multithreading into one application.