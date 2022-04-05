package DAO;

import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointments;
import java.sql.*;
import java.time.*;

/**This creates, updates,adds,deletes appointments from database. */
public abstract class DAOAppointments {

    /**Creates a list with all appointment information from the DB. */
    public static ObservableList<Appointments> getAllAppointments() {
        ObservableList<Appointments>aList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM APPOINTMENTS,CONTACTS,CUSTOMERS WHERE APPOINTMENTS.CONTACT_ID = CONTACTS.CONTACT_ID AND APPOINTMENTS.CUSTOMER_ID = CUSTOMERS.CUSTOMER_ID";

            PreparedStatement ps = JDBC.connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()){

                int appointmentId = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String type = rs.getString("Type");
                int customerId = rs.getInt("Customer_ID");
                int userId = rs.getInt("User_ID");
                int contactId = rs.getInt("Contact_ID");
                LocalDateTime appointmentStarts = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime appointmentEnds = rs.getTimestamp("End").toLocalDateTime();



                Appointments newAppointment = new Appointments(appointmentId,title,description,location,type,appointmentStarts,appointmentEnds,customerId,userId,contactId);
                aList.add(newAppointment);

            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return aList;
    }

    /** Add appointment to DB.*/
    public static void addAppointment(String title,String description, String location,String type,LocalDateTime start, LocalDateTime end, int customerId, int userId,int contactId){

        try {
            String sqlci = "INSERT INTO APPOINTMENTS VALUES(NULL,?,?,?,?,?,?,NULL,NULL,NULL,NULL,?,?,?)";

            PreparedStatement psti = JDBC.connection.prepareStatement(sqlci, Statement.RETURN_GENERATED_KEYS);



            psti.setString(1,title);
            psti.setString(2,description);
            psti.setString(3,location);
            psti.setString(4,type);
            psti.setTimestamp(5, Timestamp.valueOf(start));
            psti.setTimestamp(6,Timestamp.valueOf(end));
            psti.setInt(7,customerId);
            psti.setInt(8,userId);
            psti.setInt(9,contactId);


            psti.execute();

        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }


    /**Delete appointment from appointments table in DB. */
    public static void deleteAppointment(int appointmentId){

        try {
            String sqlt = "DELETE FROM APPOINTMENTS WHERE APPOINTMENT_ID =? ";

            PreparedStatement pst = JDBC.connection.prepareStatement(sqlt);

            pst.setInt(1,appointmentId);

            pst.execute();

        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }

    /**Update Appointment within the DB. */
    public static void updateAppointment(String title, String description, String location, String type, LocalDateTime start,LocalDateTime end, int customerId,int userId, int contactId,int appointmentId ){

        try {
            String sqlci = "UPDATE APPOINTMENTS SET TITLE=?,DESCRIPTION=?,LOCATION=?,TYPE=?,START=?,END=?,CUSTOMER_ID=?,USER_ID=?,CONTACT_ID=? WHERE APPOINTMENT_ID=?";

            PreparedStatement psti = JDBC.connection.prepareStatement(sqlci);

            psti.setString(1,title);
            psti.setString(2,description);
            psti.setString(3,location);
            psti.setString(4,type);
            psti.setTimestamp(5, Timestamp.valueOf(start));
            psti.setTimestamp(6, Timestamp.valueOf(end));
            psti.setInt(7,customerId);
            psti.setInt(8,userId);
            psti.setInt(9,contactId);
            psti.setInt(10,appointmentId);

            psti.execute();

        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }

    /**Creates a list with current week appointment information.*/
    public static ObservableList<Appointments> getWeeklyAppointments() {
        ObservableList<Appointments>weeklyList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM APPOINTMENTS WHERE YEARWEEK(START)=YEARWEEK(NOW())";

            PreparedStatement ps = JDBC.connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int appointmentId = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String type = rs.getString("Type");
                int customerId = rs.getInt("Customer_ID");
                int userId = rs.getInt("User_ID");
                int contactId = rs.getInt("Contact_ID");
                LocalDateTime appointmentStarts = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime appointmentEnds = rs.getTimestamp("End").toLocalDateTime();

                Appointments newAppointment = new Appointments(appointmentId,title,description,location,type,appointmentStarts,appointmentEnds,customerId,userId,contactId);
                weeklyList.add(newAppointment);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return weeklyList;
    }

    /**Creates a list with current month appointment information. */
    public static ObservableList<Appointments> getMonthlyAppointments() {
        ObservableList<Appointments>monthlyList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM APPOINTMENTS WHERE MONTH(START)=MONTH(NOW())";

            PreparedStatement ps = JDBC.connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int appointmentId = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String type = rs.getString("Type");
                int customerId = rs.getInt("Customer_ID");
                int userId = rs.getInt("User_ID");
                int contactId = rs.getInt("Contact_ID");
                LocalDateTime appointmentStarts = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime appointmentEnds = rs.getTimestamp("End").toLocalDateTime();

                ZoneId sd = ZoneId.systemDefault();
                ZonedDateTime ss = appointmentStarts.atZone(sd);
                ZonedDateTime ee = appointmentEnds.atZone(sd);

                LocalDateTime appointmentStart = ss.toLocalDateTime();
                LocalDateTime appointmentEnd = ee.toLocalDateTime();

                Appointments newAppointment = new Appointments(appointmentId,title,description,location,type,appointmentStart,appointmentEnd,customerId,userId,contactId);
                monthlyList.add(newAppointment);

            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return monthlyList;
    }

    /**Get list of appointment by customer ID. */
    public static ObservableList<Appointments> getAppointmentsByCustomerID(int customerId) throws SQLException {
        ObservableList<Appointments> appointments = FXCollections.observableArrayList();

        String sql = "SELECT * FROM appointments WHERE Customer_ID=?;";

        PreparedStatement ps = JDBC.connection.prepareStatement(sql);;

        ps.setInt(1, customerId);

        try {
            ps.execute();
            ResultSet rs = ps.getResultSet();

            // Forward scroll resultSet
            while (rs.next()) {
                int appointmentId = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String type = rs.getString("Type");
                int userId = rs.getInt("User_ID");
                int contactId = rs.getInt("Contact_Id");
                LocalDateTime appointmentStarts = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime appointmentEnds = rs.getTimestamp("End").toLocalDateTime();


                Appointments newAppointment = new Appointments(appointmentId,title,description,location,type,appointmentStarts,appointmentEnds,customerId,userId,contactId);
                appointments.add(newAppointment);
            }
            return appointments;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
    /**Get list of appointments by contact ID. */
    public static ObservableList<Appointments> getAppointmentsByContactID(int contactId) throws SQLException {
        ObservableList<Appointments> appointments = FXCollections.observableArrayList();

        String sql = "SELECT * FROM appointments WHERE Contact_ID=?;";

        PreparedStatement ps = JDBC.connection.prepareStatement(sql);;

        ps.setInt(1, contactId);

        try {
            ps.execute();
            ResultSet rs = ps.getResultSet();

            // Forward scroll resultSet
            while (rs.next()) {
                int appointmentId = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String type = rs.getString("Type");
                int customerId = rs.getInt("Customer_ID");
                int userId = rs.getInt("User_ID");
                LocalDateTime appointmentStarts = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime appointmentEnds = rs.getTimestamp("End").toLocalDateTime();

                Appointments newAppointment = new Appointments(appointmentId,title,description,location,type,appointmentStarts,appointmentEnds,customerId,userId,contactId);
                appointments.add(newAppointment);

            }
            return appointments;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

}
