package ie.atu;

import java.sql.SQLException;

import java.sql.SQLOutput;
import java.util.Objects;
import java.util.Scanner;

public class MainApplication{
    public static void main(String[] args){
        String url = "jdbc:mysql://localhost:3306/smartphones";
        String username = "root";
        String pass = "password";
        String password = "smartphone";
        int option = 0;
        int adminOption = 0;
        Scanner scanner = new Scanner(System.in);
        while (option != 3) {
            try {
                DatabaseManagement databaseManagement = new DatabaseManagement(url, username, pass);

                databaseManagement.connectionTest();

                System.out.println("\nWelcome to Natalie's cell store!");
                System.out.println("1. Customer Log In\n2. Admin Log In\n3. Exit");
                option = scanner.nextInt();

                if (option == 1) {
                    String name, email, phoneNo;
                    System.out.println("Enter your name: ");
                    name = scanner.next();
                    System.out.println("Enter your Email: ");
                    email = scanner.next();
                    System.out.println("Enter your phone number: ");
                    phoneNo = scanner.next();
                    databaseManagement.addCustomerData(name, email, phoneNo);
                    System.out.println("Welcome: " + name + "\n" + email + "\n" + phoneNo);

                    System.out.println("1. Apple Menu \n2. Samsung");
                    int menuOption = scanner.nextInt();

                    if (menuOption == 1) {
                        String appleData = databaseManagement.getAppleData();
                        System.out.println("Apple Data from database: ");
                        System.out.println(appleData);
                    }
                    else if (menuOption == 2) {
                        String samsungData = databaseManagement.getSamsungData();
                        System.out.println("Samsung Data from database: ");
                        System.out.println(samsungData);
                    }
                    System.out.println("Select device id for the device you want to add to cart:");
                    String selection = scanner.next();
                    String customerSelection = databaseManagement.getCustomerSelection(selection);
                    System.out.println(customerSelection);
                }

                else if (option == 2) {

                        System.out.println("Please enter password: ");
                        String passInput = scanner.next();
                    while (adminOption != 7){
                        if (Objects.equals(passInput, password)) {
                            System.out.println("Welcome!");
                            System.out.println("1. View all smartphones in database");
                            System.out.println("2. View all customers in database");
                            System.out.println("3. Add new phone to database");
                            System.out.println("4. Add new customer to database");
                            System.out.println("5. Change admin password");
                            System.out.println("6. Remove customer from database");
                            System.out.println("7. Exit to Main Menu");
                            adminOption = scanner.nextInt();
                            switch (adminOption) {
                                case 1:
                                    String data = databaseManagement.getData();
                                    System.out.println("Data from database: ");
                                    System.out.println(data);
                                    break;
                                case 2:
                                    String customerData = databaseManagement.getCustomerData();
                                    System.out.println("Customers from database:");
                                    System.out.println(customerData);
                                    break;
                                case 3:
                                    System.out.println("Enter phone details.");
                                    System.out.println("Brand Name: ");
                                    String brandName = scanner.next();
                                    System.out.println("Model: ");
                                    String model = scanner.next();
                                    System.out.println("cost: ");
                                    String cost = scanner.next();
                                    databaseManagement.addDeviceData(brandName, model, cost);
                                    break;
                                case 4:
                                    System.out.println("Enter name: ");
                                    String name = scanner.next();
                                    System.out.println("Enter Email: ");
                                    String email = scanner.next();
                                    System.out.println("Enter phone number: ");
                                    String phoneNo = scanner.next();
                                    databaseManagement.addCustomerData(name, email, phoneNo);
                                    System.out.println("Customer Added Successfully");
                                    break;
                                case 5:
                                    System.out.println("Enter current password: ");
                                    passInput = scanner.next();

                                    if (Objects.equals(passInput, password)) {
                                        System.out.println("Enter new password: ");
                                        String newPassword = scanner.next();

                                        if (Objects.equals(newPassword, password)) {
                                            while (Objects.equals(newPassword, password)) {
                                                System.out.println("New password matches the old one");
                                                System.out.println("Please enter a new password");
                                                newPassword = scanner.next();
                                            }
                                        }
                                        password = newPassword;
                                        System.out.println("Password changed! ");
                                    }
                                    break;
                                case 6:
                                    break;
                                case 7:
                                    System.out.println("Logged out. ");
                                    break;
                                default:

                                    break;
                            }
                        }
                        else {
                            System.out.println("Wrong password...\nPlease enter correct password");
                            adminOption = 3;
                        }

                    }
                }
                else if (option == 3){
                    System.out.println("Thank you for shopping with us! ");
                }
                else
                {
                    System.out.println("Invalid input.");
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
