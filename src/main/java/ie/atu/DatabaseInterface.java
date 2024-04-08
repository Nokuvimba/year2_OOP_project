package ie.atu;

import java.sql.*;
public interface DatabaseInterface {
    void addDeviceData(String brandName, String model, String cost) throws SQLException;
    void addPhoneInfoData(String storage, String os) throws SQLException;
    void addCustomerData(String custName, String email, String phoneNo) throws SQLException;
    void addStoreData(String storeName, String address) throws SQLException;
    void connectionTest() throws SQLException;
    void deleteCustomerData(int delete) throws SQLException;
    void updateCustomerData(int custId, String email) throws SQLException;
    String getData() throws SQLException;
    String getAppleData() throws SQLException;
    String getSamsungData() throws SQLException;
    String getCustomerData() throws SQLException;
    String getCustomerSelection(String selection) throws SQLException;
    double getCost(String selection) throws SQLException;
    String getModel(String selection) throws SQLException;
}

