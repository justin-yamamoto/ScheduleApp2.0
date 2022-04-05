package DAO;

import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.FirstLevelDivisions;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**Get all First level division data. */
public class DAOFirstLevelDivision {

    /**Create an Observable list that contains all First-Level Division data. */
    public static ObservableList<FirstLevelDivisions> getAllDivisions() {

        ObservableList<FirstLevelDivisions> fldList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM FIRST-LEVEL DIVISIONS ";

            PreparedStatement ps = JDBC.connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int divisionId = rs.getInt("Division_ID");
                String division = rs.getString("Division");
                int countryId = rs.getInt("Country_ID");


                FirstLevelDivisions newDivision = new FirstLevelDivisions(divisionId,division,countryId);
                fldList.add(newDivision);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return fldList;
    }

    /**Create a list for UK Divisions. */
    public static ObservableList<FirstLevelDivisions> getUKDivisions() {

        ObservableList<FirstLevelDivisions> UKList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM FIRST_LEVEL_DIVISIONS WHERE COUNTRY_ID = 2";

            PreparedStatement ps = JDBC.connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int divisionId = rs.getInt("Division_ID");
                String division = rs.getString("Division");
                int countryId = rs.getInt("Country_ID");

                FirstLevelDivisions newUKDivision = new FirstLevelDivisions(divisionId,division,countryId);
                UKList.add(newUKDivision);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return UKList;
    }

    /**Create a list for US Divisions. */
    public static ObservableList<FirstLevelDivisions> getUSDivisions() {

        ObservableList<FirstLevelDivisions> USList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM FIRST_LEVEL_DIVISIONS WHERE COUNTRY_ID = 1";

            PreparedStatement ps = JDBC.connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int divisionId = rs.getInt("Division_ID");
                String division = rs.getString("Division");
                int countryId = rs.getInt("Country_ID");


                FirstLevelDivisions newUSDivision = new FirstLevelDivisions(divisionId,division,countryId);
                USList.add(newUSDivision);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return USList;
    }

    /**Create a list for Canada Divisions. */
    public static ObservableList<FirstLevelDivisions> getCANADADivisions() {

        ObservableList<FirstLevelDivisions> CANADAList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM FIRST_LEVEL_DIVISIONS WHERE COUNTRY_ID = 3";

            PreparedStatement ps = JDBC.connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int divisionId = rs.getInt("Division_ID");
                String division = rs.getString("Division");
                int countryId = rs.getInt("Country_ID");


                FirstLevelDivisions newCANADADivision = new FirstLevelDivisions(divisionId,division,countryId);
                CANADAList.add(newCANADADivision);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return CANADAList;
    }

}
