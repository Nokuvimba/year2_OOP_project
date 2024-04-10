package ie.atu.Customer;

import ie.atu.DatabaseManagement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManagementTest{
    private DatabaseManagement databaseManagement;

    @BeforeEach  //before each test method establish a real connection to the database for testing
    public void setUp() throws SQLException {
        databaseManagement =new DatabaseManagement( "jdbc:mysql://localhost:3306/smartphones","root","password");
        databaseManagement.connectionTest();
    }

    @Test //a test method to check if data was inserted correctly
    public void testAddDevice() throws SQLException {
        //test
        databaseManagement.addDeviceData("Apple","iPhone X","1000");

        try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/smartphones","root","password")) {
           Statement statement = connection.createStatement();
           String query =" SELECT * FROM device WHERE brand_name = 'Apple' AND model = 'iPhone X' AND cost ='1000'";
                   assertTrue(statement.executeQuery(query).next()); //check if query returns anything
        }
    }
   @Test
    public void testAddCustomerData() throws SQLException{
        databaseManagement.addCustomerData("John Doe","john@example.com","1234567890");

        try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/smartphones", "root", "password")){
            Statement statement =connection.createStatement();
            String query2 = "SELECT * FROM customer WHERE name = 'John Doe' AND email ='john@example.com' AND phone_no = '1234567890' ";
            assertTrue(statement.executeQuery(query2).next());
        }
   }
   @Test
    public void testAddStoreData() throws SQLException{
        databaseManagement.addStoreData("Store A", "123 Main Street");

        try(Connection connection =DriverManager.getConnection("jdbc:mysql://localhost:3306/smartphones", "root", "password")){
            Statement statement = connection.createStatement();
            String query3 =" SELECT * FROM store WHERE store_name = 'Store A' AND address = '123 Main Street' ";
            assertFalse(statement.executeQuery(query3).next());
        }
   }
}