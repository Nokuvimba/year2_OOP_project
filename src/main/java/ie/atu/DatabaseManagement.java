package ie.atu;

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

            stmt = connection.prepareStatement("INSERT INTO phone_info (device_id, info_id, storage, os_name,processor) VALUES (?, ?, ?, ?,?)");
            stmt.setInt(1, getLastInsertId(connection));
            stmt.setInt(2, getLastInsertId(connection));
            stmt.setString(3, storage);
            stmt.setString(4, os);
            stmt.setString(5, processor);
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
    public void connectionTest() throws SQLException {
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
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT LAST_INSERT_ID()");
        rs.next();
        int id = rs.getInt(1);
        rs.close();
        stmt.close();
        return id;
    }
}
