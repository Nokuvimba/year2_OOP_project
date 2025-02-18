package ie.atu;

import com.mysql.cj.x.protobuf.MysqlxCrud;

import java.sql.*;
public class DatabaseManagement implements DatabaseInterface{
    private Connection connection;
    private PreparedStatement stmt;

    public DatabaseManagement(String url, String username, String pass) throws SQLException
    {
        connection = DriverManager.getConnection(url, username, pass);
    }

    @Override
    public void addDeviceData(String brandName, String model, String cost) throws SQLException {
        try {

            // Insert a new record into the "device" table
            stmt = connection.prepareStatement("INSERT INTO device (device_id, brand_name, model, cost) VALUES (?, ?, ?, ?)");
            stmt.setInt(1, getLastInsertId(connection));
            stmt.setString(2, brandName);
            stmt.setString(3, model);
            stmt.setString(4, cost);
            stmt.executeUpdate();

            System.out.println("Insert completed successfully.");
        } catch (SQLException ex) {

            System.out.println("Record insert failed.");
            ex.printStackTrace();
        }
    }

    @Override
    public void addPhoneInfoData(String storage, String os) throws SQLException {
        try {
            // Insert a new record into the "phone_info" table
            stmt = connection.prepareStatement("INSERT INTO phone_info (device_id, info_id, storage, os_name) VALUES (?, ?, ?, ?)");
            stmt.setInt(1, getLastInsertId(connection));
            stmt.setInt(2, getLastInsertId(connection));
            stmt.setString(3, storage);
            stmt.setString(4, os);

            stmt.executeUpdate();

            System.out.println("Insert completed successfully.");
        } catch (SQLException ex) {

            System.out.println("Record insert failed.");
            ex.printStackTrace();
        }
    }

    @Override
    public void addCustomerData(String custName, String email, String phoneNo) throws SQLException {
        try {
            // Insert a new record into the "customer" table
            stmt = connection.prepareStatement("INSERT INTO customer (customer_id, name, email, phone_no) VALUES (?, ?, ?, ?)");
            stmt.setInt(1, getLastInsertId(connection));
            stmt.setString(2, custName);
            stmt.setString(3, email);
            stmt.setString(4, phoneNo);
            stmt.executeUpdate();

            System.out.println("Insert completed successfully.");
        } catch (SQLException ex) {

            System.out.println("Record insert failed.");
            ex.printStackTrace();
        }
    }

    @Override
    public void addStoreData(String storeName, String address) throws SQLException {
        try {
            // Insert a new record into the "store" table
            stmt = connection.prepareStatement("INSERT INTO store (store_id, device_id, customer_id, store_name, address) VALUES (?, ?, ?, ?, ?)");
            stmt.setInt(1, getLastInsertId(connection));
            stmt.setInt(2, getLastInsertId(connection));
            stmt.setInt(3, getLastInsertId(connection));
            stmt.setString(4, storeName);
            stmt.setString(5, address);
            stmt.executeUpdate();

            System.out.println("Insert completed successfully.");
        } catch (SQLException ex) {

            System.out.println("Record insert failed.");
            ex.printStackTrace();
        }
    }

    @Override
    public String getData() throws SQLException {
        //returns everything from the device & phone_info table
        StringBuilder resultBuilder = new StringBuilder();

        String selectSQL = "SELECT * FROM device d " +
                "JOIN phone_info p ON d.device_id = p.device_id";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectSQL)) {

            while (resultSet.next()) {
                String deviceID = resultSet.getString("device_id");
                String brandName = resultSet.getString("brand_name");
                String model = resultSet.getString("model");
                String storage = resultSet.getString("storage");
                String osName = resultSet.getString("os_name");

                //resultBuilder ensures all data from the SQL statement is displayed and not just the most recent
                resultBuilder.append(deviceID).append(" ").append(brandName).append(" ").append(model).append(" ").append(storage).append(" ").append(osName).append("\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultBuilder.toString();
    }

    @Override
    public String getAppleData() throws SQLException {
        //returns everything from the device & phone_info table where the brand is Apple
        StringBuilder resultBuilder = new StringBuilder();

        String selectSQL = "SELECT * FROM device d " +
                "JOIN phone_info p ON d.device_id = p.device_id " +
                "WHERE d.brand_name = 'Apple'";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectSQL)) {

            while (resultSet.next()) {
                String deviceID = resultSet.getString("device_id");
                String brandName = resultSet.getString("brand_name");
                String model = resultSet.getString("model");
                String storage = resultSet.getString("storage");
                String osName = resultSet.getString("os_name");


                //resultBuilder ensures all data from the SQL statement is displayed and not just the most recent
                resultBuilder.append(deviceID).append(" ").append(brandName).append(" ").append(model).append(" ").append(storage).append(" ").append(osName).append("\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultBuilder.toString();
    }

    @Override
    public String getSamsungData() throws SQLException {
        //returns everything from the device & phone_info table where the brand is Samsung
        StringBuilder resultBuilder = new StringBuilder();

        String selectSQL = "SELECT * FROM device d " +
                "JOIN phone_info p ON d.device_id = p.device_id " +
                "WHERE d.brand_name = 'Samsung'";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectSQL)) {

            while (resultSet.next()) {
                String deviceID = resultSet.getString("device_id");
                String brandName = resultSet.getString("brand_name");
                String model = resultSet.getString("model");
                String storage = resultSet.getString("storage");
                String osName = resultSet.getString("os_name");

                //resultBuilder ensures all data from the SQL statement is displayed and not just the most recent
                resultBuilder.append(deviceID).append(" ").append(brandName).append(" ").append(model).append(" ").append(storage).append(" ").append(osName).append("\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultBuilder.toString();
    }
    @Override
    public String getCustomerData() throws SQLException{
        //returns everything from customer
        StringBuilder resultBuilder = new StringBuilder();

        String selectSQL = "SELECT * FROM customer d";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectSQL)) {

            while (resultSet.next()) {
                String customer_id = resultSet.getString("customer_id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String phone_no = resultSet.getString("phone_no");

                //resultBuilder ensures all data from the SQL statement is displayed and not just the most recent
                resultBuilder.append(customer_id).append(" ").append(name).append(" ").append(email).append(" ").append(phone_no).append("\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultBuilder.toString();
    }

    @Override
    public void connectionTest() throws SQLException {
        //tests connection to the database
        try
        {
            // Load the driver class
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Create a connection to the database, hardcoding values for now.
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/smartphones", "root", "password");
            System.out.println("Connection made to connection pool");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private static int getLastInsertId(Connection conn) throws SQLException {
        //This method retrieves the last inserted ID from a database connection
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT LAST_INSERT_ID()");
        rs.next();
        int id = rs.getInt(1);
        rs.close();
        stmt.close();
        return id;
    }

    @Override
    public void deleteCustomerData(int delete) throws SQLException {
        //using transactions to wrap DELETE statement to ensure all or nothing
        String deleteStoreSQL = "DELETE FROM store WHERE customer_id = ?" ;
        String deleteCustomerSQL = "DELETE FROM customer WHERE customer_id = ?";

        try {
            connection.setAutoCommit(false); // Start transaction
            try (PreparedStatement statement1 = connection.prepareStatement(deleteStoreSQL);
                 PreparedStatement statement2 = connection.prepareStatement(deleteCustomerSQL)) {
                statement1.setInt(1, delete);
                statement2.setInt(1, delete);

                int affectedData1 = statement1.executeUpdate();
                int affectedData2 = statement2.executeUpdate();

                if (affectedData1 == 0 && affectedData2 == 1)
                {
                    System.out.println("Customer with ID " + delete + " deleted");
                }else
                {
                    System.out.println("No customer found with ID: " + delete);
                }

                connection.commit(); // Commit transaction
            } catch (SQLException e) {
                connection.rollback(); // Rollback transaction on error
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.setAutoCommit(true); // Reset auto-commit mode
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void updateCustomerData(int custId, String newEmail) throws SQLException {
        //updates customer email of certain customer when corresponding customer_id is entered
        String updateSQL = "UPDATE customer SET email = ? WHERE customer_id = ?";

        try(PreparedStatement statement = connection.prepareStatement(updateSQL)) {
            statement.setString(1, newEmail);
            statement.setInt(2, custId);

            int affectedInfo = statement.executeUpdate();
            if(affectedInfo > 0)
            {
                System.out.println("Customer email updated successfully.");
            }else{
                System.out.println("No customer found with ID: " + custId);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public String getCustomerSelection(String selection) throws SQLException {
        StringBuilder resultBuilder = new StringBuilder();

        // SQL query with a placeholder (?)
        String selectSQL = "SELECT * FROM device d " +
                "JOIN phone_info p ON d.device_id = p.device_id " +
                "WHERE p.device_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(selectSQL)) {
            // Set the value of the placeholder (?)
            statement.setString(1, selection);

            // Execute the query
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String deviceID = resultSet.getString("device_id");
                    String brandName = resultSet.getString("brand_name");
                    String model = resultSet.getString("model");
                    String storage = resultSet.getString("storage");
                    String osName = resultSet.getString("os_name");
                    String cost = resultSet.getString("cost");

                    //resultBuilder ensures all data from the SQL statement is displayed and not just the most recent
                    resultBuilder.append(deviceID).append(" ").append(brandName).append(" ").append(model).append(" ").append(storage).append(" ").append(osName).append(" ").append(cost).append("\n");                }
            }
        }

        return resultBuilder.toString(); //returns all database data and not just most recent
    }

    @Override
    public double getCost(String selection) throws SQLException {
        StringBuilder resultBuilder = new StringBuilder();

        double price = 0.0; // Initialize price variable

        // SQL query with a placeholder (?)
        String selectSQL = "SELECT * FROM device d " +
                "JOIN phone_info p ON d.device_id = p.device_id " +
                "WHERE p.device_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(selectSQL)) {
            // Set the value of the placeholder (?)
            statement.setString(1, selection);

            // Execute the query
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    price = resultSet.getDouble("cost");

                    //resultBuilder ensures all data from the SQL statement is displayed and not just the most recent
                    resultBuilder.append(price).append("\n");                }
            }
        }

        return price; //returns all database data and not just most recent
    }
    @Override
    public String getModel(String selection) throws SQLException {
        StringBuilder resultBuilder = new StringBuilder();

        // SQL query with a placeholder (?)
        String selectSQL = "SELECT * FROM device d " +
                "JOIN phone_info p ON d.device_id = p.device_id " +
                "WHERE p.device_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(selectSQL)) {
            // Set the value of the placeholder (?)
            statement.setString(1, selection);

            // Execute the query
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String dID = resultSet.getString("model");

                    //resultBuilder ensures all data from the SQL statement is displayed and not just the most recent
                    resultBuilder.append(dID).append("\n");                }
            }
        }

        return resultBuilder.toString(); //returns all database data and not just most recent
    }

}
