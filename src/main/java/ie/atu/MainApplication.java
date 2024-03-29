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

                /*System.out.println("Enter your name");
                databaseManagement.addCustomerData(scanner.next(), scanner.nextLine(), scanner.nextLine());*/
            }

            String data = databaseManagement.getData();
            System.out.println("Data from database: ");
            System.out.println(data);

        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
