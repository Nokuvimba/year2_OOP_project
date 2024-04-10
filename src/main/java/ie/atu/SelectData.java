package ie.atu;

import java.sql.*;

public class SelectData {
    public static void main(String[] args) {
        // MySQL database connection details
        String url = "jdbc:mysql://localhost:3306/smartphones";
        String username = "root";
        String password = "password";

        // SQL statement
        String selectSQL = "SELECT d.brand_name, d.model, p.storage, p.os_name " +
                "FROM device d " +
                "JOIN phone_info p ON d.device_id = p.device_id";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectSQL)) {

            while (resultSet.next()) {
                String brandName = resultSet.getString("brand_name");
                String model = resultSet.getString("model");
                String storage = resultSet.getString("storage");
                String osName = resultSet.getString("os_name");

                System.out.println("Name: " + brandName + ", model: " + model + ", storage: " + storage + ", OS: " + osName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
