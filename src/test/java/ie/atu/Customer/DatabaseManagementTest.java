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

        try(Connection connection = DriverManager.getConnection("jbdc:mysql://localhost:3306/smartphones","root","password")) {
           Statement statement = connection.createStatement();
           String query =" SELECT * FROM device WHERE brand_name = 'Apple' AND model = 'iPhone X' AND cost ='1000'";
                   assertTrue(statement.executeQuery(query).next()); //check if query returns anything
        }
    }
    @Test //Testing the addPhoneInfo Data
    public void testAddPhoneInfoData() throws SQLException {
        //test
        databaseManagement.addPhoneInfoData("128GB","iOS");

        try(Connection connection = DriverManager.getConnection("jbdc:mysql://localhost:3306/smartphones","root","password")) {
            Statement statement = connection.createStatement();
            String query1 = "SELECT * FROM phone_info WHERE storage ='128GB' AND os_name = 'iOS' AND processor ='A11'";
            assertTrue(statement.executeQuery(query1).next()); //check if query returns anything
        }
    }
}