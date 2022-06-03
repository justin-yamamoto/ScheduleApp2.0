package DAO;

import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customers;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**This creates, updates,adds,deletes customers from database. */
public class DAOCustomers {


    /**Gets list of all customers from the DB. */
    public static ObservableList<Customers> getAllCustomers() {

        ObservableList<Customers>cList =FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM CUSTOMERS, FIRST_LEVEL_DIVISIONS, COUNTRIES WHERE CUSTOMERS.DIVISION_ID = FIRST_LEVEL_DIVISIONS.DIVISION_ID AND FIRST_LEVEL_DIVISIONS.COUNTRY_ID = COUNTRIES.COUNTRY_ID ";

            PreparedStatement ps = JDBC.connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int customerId = rs.getInt("Customer_ID");
                String customerName = rs.getString("Customer_Name");
                String address = rs.getString("Address");
                String postalCode = rs.getString("Postal_Code");
                String phone = rs.getString("Phone");
                String country = rs.getString("Country");
                String division = rs.getString("Division");

                Customers newCustomer = new Customers(customerId,customerName,address,postalCode,phone,country,division);
                cList.add(newCustomer);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return cList;
    }

    /**Add new customer method. */
    public static void addCustomer(String customerName, String address, String postalCode,String phone, int divisionId){

        try {
            String sqlci = "INSERT INTO CUSTOMERS VALUES(NULL,?,?,?,?,current_timestamp,NULL,current_timestamp,NULL,?)";

            PreparedStatement psti = JDBC.connection.prepareStatement(sqlci, Statement.RETURN_GENERATED_KEYS);

            psti.setString(1,customerName);
            psti.setString(2,address);
            psti.setString(3,postalCode);
            psti.setString(4,phone);
            psti.setInt(5,divisionId);

            psti.executeUpdate();

        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }

    /**Update customer data method. */
    public static void updateCustomer(String customerName,String address, String postalCode,String phone, int divisionId,int customerId){

        try {
            String sqlci = "UPDATE CUSTOMERS SET CUSTOMER_NAME=?,ADDRESS=?,POSTAL_CODE=?,PHONE=?,DIVISION_ID=? WHERE CUSTOMER_ID=?";

            PreparedStatement psti = JDBC.connection.prepareStatement(sqlci);

            psti.setString(1,customerName);
            psti.setString(2,address);
            psti.setString(3,postalCode);
            psti.setString(4,phone);
            psti.setInt(5,divisionId);
            psti.setInt(6,customerId);
            
            psti.executeUpdate();

        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }
    /**Delete customer method. */
    public static void deleteCustomer(int customerId){

        try {
            String sqlt = "DELETE APPOINTMENTS FROM APPOINTMENTS WHERE CUSTOMER_ID=? ";

            PreparedStatement pst = JDBC.connection.prepareStatement(sqlt);

            pst.setInt(1,customerId);

            pst.executeUpdate();

            String sqlti = "DELETE CUSTOMERS FROM CUSTOMERS WHERE CUSTOMER_ID=? ";

            PreparedStatement psti = JDBC.connection.prepareStatement(sqlti);

            psti.setInt(1,customerId);
            
            psti.executeUpdate();

        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }
    /**Get all customers by customer ID. */
    public static ObservableList<Customers> getCustomersByCustomerID(int customerId) throws SQLException {
        ObservableList<Customers> customers = FXCollections.observableArrayList();

        String sql = "SELECT * FROM CUSTOMERS, FIRST_LEVEL_DIVISIONS, COUNTRIES WHERE CUSTOMERS.DIVISION_ID = FIRST_LEVEL_DIVISIONS.DIVISION_ID AND FIRST_LEVEL_DIVISIONS.COUNTRY_ID = COUNTRIES.COUNTRY_ID AND CUSTOMER_ID=?;";

        PreparedStatement ps = JDBC.connection.prepareStatement(sql);;

        ps.setInt(1, customerId);

        try {
            ps.execute();
            ResultSet rs = ps.getResultSet();

            // Forward scroll resultSet
            while (rs.next()) {
                Customers newCustomer = new Customers(
                        rs.getInt("Customer_ID"),
                        rs.getString("Customer_Name"),
                        rs.getString("Address"),
                        rs.getString("Postal_Code"),
                        rs.getString("Phone"),
                        rs.getString("Country"),
                        rs.getString("Division")

                );

                customers.add(newCustomer);
            }
            return customers;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
    /**Get all customers by customer Name. */
    public static ObservableList<Customers> getCustomersByCustomerName(String customerName) throws SQLException {
        ObservableList<Customers> customers = FXCollections.observableArrayList();

        String sql = "SELECT * FROM CUSTOMERS, FIRST_LEVEL_DIVISIONS, COUNTRIES WHERE CUSTOMERS.DIVISION_ID = FIRST_LEVEL_DIVISIONS.DIVISION_ID AND FIRST_LEVEL_DIVISIONS.COUNTRY_ID = COUNTRIES.COUNTRY_ID AND CUSTOMER_NAME LIKE '" + customerName +"%'";

        PreparedStatement ps = JDBC.connection.prepareStatement(sql);;



        try {
            ps.execute();
            ResultSet rs = ps.getResultSet();

            // Forward scroll resultSet
            while (rs.next()) {
                Customers newCustomer = new Customers(
                        rs.getInt("Customer_ID"),
                        rs.getString("Customer_Name"),
                        rs.getString("Address"),
                        rs.getString("Postal_Code"),
                        rs.getString("Phone"),
                        rs.getString("Country"),
                        rs.getString("Division")

                );

                customers.add(newCustomer);
            }
            return customers;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

}
