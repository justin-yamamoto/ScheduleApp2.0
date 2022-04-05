package DAO;

import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Countries;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**This gets all country data. */
public class DAOCountries {


        /**Get all Countries from the database.
         * Create a list of those countries.*/
        public static ObservableList<Countries> getAllCountries() {

        ObservableList<Countries> csList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM COUNTRIES ";

            PreparedStatement ps = JDBC.connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int countryId = rs.getInt("Country_ID");
                String country = rs.getString("Country");


                Countries newCountry = new Countries(countryId,country);
                csList.add(newCountry);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return csList;
    }


}
