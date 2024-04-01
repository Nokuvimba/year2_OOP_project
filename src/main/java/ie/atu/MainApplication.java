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
        Scanner scanner = new Scanner(System.in);
        try{
            DatabaseManagement databaseManagement = new DatabaseManagement(url, username, pass);

            databaseManagement.connectionTest();

            //databaseManagement.addDeviceData("Apple", "Iphone 12", "600");
            //databaseManagement.addPhoneInfoData("256GB", "IOS");
            //databaseManagement.addCustomerData();
            //databaseManagement.addStoreData();
            System.out.println("\nWelcome to Natalie's cell store!");
            System.out.println("Press 1 to enter as guest customer or 2 as an admin.\n");
            int option = scanner.nextInt();

            if (option == 1) {
                String name, email, phoneNo;
                System.out.println("Enter your name: ");
                name = scanner.next();
                System.out.println("Enter your Email: ");
                email = scanner.next();
                System.out.println("Enter your phone number: ");
                phoneNo = scanner.next();
                databaseManagement.addCustomerData(name, email, phoneNo);
                System.out.println("Welcome: " + name + email + phoneNo);

                System.out.println("Press 1 to view Apple menu or 2 to view Samsung menu");
                int menuOption = scanner.nextInt();
                if (menuOption == 1) {
                    String appleData = databaseManagement.getAppleData();
                    System.out.println("Apple Data from database: ");
                    System.out.println(appleData);
                }
                else if (menuOption == 2)
                {
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

                if (Objects.equals(passInput, password)) {
                    System.out.println("Welcome!");
                    System.out.println("1. View all smartphones in database");
                    System.out.println("2. View all customers in database");
                    System.out.println("3. Add new phone to database");
                    System.out.println("4. Add new customer to database");
                    System.out.println("5. Change admin password");
                    System.out.println("6. ");
                    int adminOption = scanner.nextInt();
                    switch (adminOption){
                        case 1:
                            String data = databaseManagement.getData();
                            System.out.println("Data from database: ");
                            System.out.println(data);
                            break;
                        case 2:
                            break;
                        case 3:
                            break;
                        case 4:
                            break;
                        case 5:
                            break;
                        default:
                            break;
                    }
                }
            }
            /*


            String appleData = databaseManagement.getAppleData();
            System.out.println("Apple Data from database: ");
            System.out.println(appleData);

            String samsungData = databaseManagement.getSamsungData();
            System.out.println("Samsung Data from database: ");
            System.out.println(samsungData);
             */

        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
