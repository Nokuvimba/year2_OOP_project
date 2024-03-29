package ie.atu.Manage;

import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class ManagementTest {

    private DatabaseManagement databaseManagement;
    private Connection mockConnection;//represents a database connection
    private PreparedStatement mockStatement;

    @BeforeEach
    public void setUp() throws SQLException {
        // Create mocks for connection and statement
        mockConnection = mock(Connection.class);
        mockStatement = mock(PreparedStatement.class);

        // Stub behavior for prepareStatement method of the mock connection
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);

        // Initialize DatabaseManagement with the mock connection
        databaseManagement = new DatabaseManagement(mockConnection);
    }

    @Test
    public void testAddDeviceData()throws SQLException{
        //method to be tested
        databaseManagement.addDeviceData("Samsung","Galaxy S21","999.99");
        // Verify that executeUpdate method is called on the mock statement
        verify(mockStatement).executeUpdate();
    }
    @Test
    public void testAddPhoneInfoData() throws SQLException {
        databaseManagement.addPhoneInfoData("256GB", "Android");
        verify(mockStatement).executeUpdate();
    }
    @Test
    public void testAddCustomerData() throws SQLException {
        databaseManagement.addCustomerData("John Doe", "john@example.com", "1234567890");
        verify(mockStatement).executeUpdate();
    }
    @Test
    public void testAddStoreData() throws SQLException {
        databaseManagement.addStoreData("Store Name", "Store Address");
        verify(mockStatement).executeUpdate();
    }

}