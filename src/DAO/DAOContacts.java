package DAO;

import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Contacts;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**This gets all Contact data. */
public class DAOContacts {
    /**Creates list of all contact data from database. */
    public static ObservableList<Contacts> getAllContacts() {
        ObservableList<Contacts>contactList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM CONTACTS ";

            PreparedStatement ps = JDBC.connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int contactId = rs.getInt("Contact_ID");
                String contactName = rs.getString("Contact_Name");
                String email = rs.getString("Email");

                Contacts newContact = new Contacts(contactId,contactName,email);
                contactList.add(newContact);

            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return contactList;
    }
}
