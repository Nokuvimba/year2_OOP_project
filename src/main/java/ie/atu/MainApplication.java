package ie.atu;

import java.sql.SQLException;

import java.sql.SQLOutput;
import java.util.Objects;
import java.util.Scanner;

public class MainApplication{
    public static void main(String[] args){
        //databases connection parameters
        String url = "jdbc:mysql://localhost:3306/smartphones";
        String username = "root";
        String pass = "password";
        String password = "smartphone";

        //option variable for menu selection
        int option = 0;
        int adminOption = 0;
        int passUpdate = 0;
        int buyMoreFlag = 0;
        int selectionFlag = 0;

        Scanner scanner = new Scanner(System.in);
        while (option != 3) {
            try {
                DatabaseManagement databaseManagement = new DatabaseManagement(url, username, pass); //database instance
                ShoppingCart shoppingCart = new ShoppingCart();//shopping cart instance

                //tests connection to database at the beginning of the application
                databaseManagement.connectionTest();

                System.out.println("\nWelcome to Natalie's cell store!");
                System.out.println("1. Customer Log In\n2. Admin Log In\n3. Exit");
                option = scanner.nextInt();
                //variable for managing user interactions
                int buyMore = 1;
                int invalid = 0;

                //Customer LogIn and Menu
                if (option == 1) {
                    String name, email, phoneNo;
                    System.out.println("Enter your name: ");
                    name = scanner.next();
                    System.out.println("Enter your Email: ");
                    email = scanner.next();
                    System.out.println("Enter your phone number: ");
                    phoneNo = scanner.next();
                    databaseManagement.addCustomerData(name, email, phoneNo); //loads customer data to database
                    System.out.println("Welcome: " + name + "!\n");
                    //Menu loop
                    while (buyMore==1) {
                        buyMoreFlag = 0;
                        invalid = 0;
                        System.out.println("Select a menu to continue...");
                        System.out.println("1. Apple Menu \n2. Samsung Menu");
                        int menuOption = scanner.nextInt();

                        //Display device data based on menu selection
                        if (menuOption == 1) {
                            String appleData = databaseManagement.getAppleData();
                            System.out.println("Apple Data from database: ");
                            System.out.println(appleData);
                            selectionFlag = 1;
                            buyMore = 2;
                        } else if (menuOption == 2) {
                            String samsungData = databaseManagement.getSamsungData();
                            System.out.println("Samsung Data from database: ");
                            System.out.println(samsungData);
                            selectionFlag = 2;
                            buyMore = 2;
                        }
                        else {
                            System.out.println("Invalid Entry!\n");
                            buyMore = 1;
                        }

                        //handle device selection and shopping cart management
                        if (buyMore == 2) {
                            System.out.println("Select device id for the device you want to add to cart:");
                            String selection = scanner.next();
                            int num = Integer.parseInt(selection);

                            //Conditions to allow customer to select items from the given menu
                            if (selectionFlag == 1 && num > 18 ) {
                                System.out.println("Invalid device ID entered!\n");
                                buyMore = 1;
                                invalid = 1;
                            }
                            if (selectionFlag == 2 && (num < 19 || num > 31)){
                                System.out.println("Invalid device ID entered!\n");
                                buyMore = 1;
                                invalid = 1;
                            }
                            //Condition to continue Customer menu if above conditions not violated
                            if (invalid == 0) {
                                String customerSelection = databaseManagement.getCustomerSelection(selection);
                                System.out.println(customerSelection);

                                double cost = databaseManagement.getCost(selection);
                                String model = databaseManagement.getModel(selection);
                                shoppingCart.addDevice(model, cost); // Assuming you have the price of the selected device
                                while (buyMoreFlag == 0) { //loop to keep looping if user enters invalid input
                                    System.out.println("If you wish to select more devices press 1, If you wish to check out press 0");
                                    buyMore = scanner.nextInt();
                                    //conditions for customer input
                                    if (buyMore == 0) {
                                        shoppingCart.displayCart();
                                        buyMoreFlag = 1;
                                    } else if (buyMore == 1) {
                                        buyMoreFlag = 1;
                                        buyMore = 1;
                                    } else {
                                        System.out.println("Invalid Entry!\n");
                                        buyMoreFlag = 0;
                                    }
                                }
                            }
                        }
                    }

                }
                //Admin LogIn
                else if (option == 2) {
                    while (option == 2) { //Loop to keep running to allow user multiple attempts for the password
                        System.out.println("Please enter password to Enter Admin Menu: ");
                        String passInput = scanner.next();
                        adminOption = 0;
                        while (adminOption != 8) { //Loop for Admin Menu
                            if (Objects.equals(passInput, password)) { //Will only enter menu if password is correct
                                System.out.println("Welcome!");
                                System.out.println("1. View all smartphones in database");
                                System.out.println("2. View all customers in database");
                                System.out.println("3. Add new customer to database");
                                System.out.println("4. Change admin password");
                                System.out.println("5. Remove customer from database");
                                System.out.println("6. Update customer info");
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
                                        System.out.println("Enter name: ");
                                        String name = scanner.next();
                                        System.out.println("Enter Email: ");
                                        String email = scanner.next();
                                        System.out.println("Enter phone number: ");
                                        String phoneNo = scanner.next();
                                        databaseManagement.addCustomerData(name, email, phoneNo); //Loads new customer details to database
                                        System.out.println("Customer Added Successfully");
                                        break;
                                    case 4:
                                        int passCount = 0;
                                        while (passCount == 0) { //Loop will continue running unless current password is correct
                                            System.out.println("Enter current password: ");
                                            passInput = scanner.next();

                                            if (Objects.equals(passInput, password)) { //Will only allow user to enter new password if current password is correct
                                                System.out.println("Enter new password: ");
                                                String newPassword = scanner.next();

                                                if (Objects.equals(newPassword, password)) { //New password cannot match old password
                                                    while (Objects.equals(newPassword, password)) {
                                                        System.out.println("New password matches the old one");
                                                        System.out.println("Please enter a new password");
                                                        newPassword = scanner.next();
                                                    }
                                                }
                                                password = newPassword;
                                                System.out.println("Password changed! ");
                                                passCount = 1;
                                                passUpdate = 1;
                                                adminOption = 8;
                                            }
                                            else {
                                                System.out.println("Wrong current password!");
                                            }
                                        }
                                        break;
                                    case 5:
                                        System.out.println("Enter customer ID of customer to be removed: ");
                                        int delete = scanner.nextInt();
                                        databaseManagement.deleteCustomerData(delete); //Deletes customer with entered id from database
                                        break;
                                    case 6:
                                        System.out.println("Enter customer ID of customer info to be updated: ");
                                        int custId = scanner.nextInt();
                                        System.out.println("Enter new email: ");
                                        String newEmail = scanner.next();
                                        databaseManagement.updateCustomerData(custId, newEmail); //Updates customer email in the database
                                        break;
                                    case 7:
                                        System.out.println("Logged out. ");
                                        option = 0; //option = 0 will break Admin Menu loop
                                        break;
                                    default:
                                        System.out.println("Invalid Entry!\nEnter only numbers in the Menu");
                                        break;
                                }
                            } else {
                                if(passUpdate == 0) { //Condition to recognise when the password has changed, whether to print error statement or not
                                    System.out.println("\nIncorrect password!!!\n");
                                    adminOption = 8;

                                }
                            }
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
