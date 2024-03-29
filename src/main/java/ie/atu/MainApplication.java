package ie.atu;

import java.sql.SQLException;

import java.util.Scanner;

public class MainApplication{
    public static void main(String[] args){
        String url = "jdbc:mysql://localhost:3306/smartphones";
        String username = "root";
        String pass = "password";
        Scanner scanner = new Scanner(System.in);
        try{
            DatabaseManagement databaseManagement = new DatabaseManagement(url, username, pass);

            databaseManagement.connectionTest();

            //databaseManagement.addDeviceData("Apple", "Iphone 12", "600");
            //databaseManagement.addPhoneInfoData("256GB", "IOS");
            //databaseManagement.addCustomerData();
            //databaseManagement.addStoreData();
            System.out.println("Welcome to Tremain's cell store\n");
            System.out.println("Press 1 to enter as guest customer or 2 as an admin.\n");
            int option = scanner.nextInt();

            if (option == 1)
            {
                String name, email, phoneNo;
                System.out.println("Enter your name: ");
                name = scanner.next();
                System.out.println("Enter your Email: ");
                email = scanner.next();
                System.out.println("Enter your phone number: ");
                phoneNo = scanner.next();
                databaseManagement.addCustomerData(name, email, phoneNo);
                System.out.println("Welcome: " + name + email + phoneNo);

                System.out.println("Press 1 to view Apple menu or 2 to view Samsung menu\n");
                int menuOption = scanner.nextInt();
                if (menuOption == 1)
                {
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
                System.out.println("Select device id for the device you want to add to cart:\n");
                String selection = scanner.next();
                String customerSelection = databaseManagement.getCustomerSelection(selection);
                System.out.println(customerSelection);
            }

            /*
            String data = databaseManagement.getData();
            System.out.println("Data from database: ");
            System.out.println(data);

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
