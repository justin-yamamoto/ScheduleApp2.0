package controller;

import DAO.DAOAppointments;
import DAO.DAOContacts;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Contacts;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**This class allows for obtaining the schedule of contacts. */
public class ContactScheduleController implements Initializable {

    public ComboBox contactCb;
    public Label contactLbl;
    public TableColumn appointmentIdCol;
    public TableColumn titleCol;
    public TableColumn typeCol;
    public TableColumn descriptionCol;
    public TableColumn startCol;
    public TableColumn endCol;
    public TableColumn customerIdCol;
    public Button returnBtn;
    public TableView contactTv;
    Stage stage;

    /**Initialize.
     * Set contact combo box with contact data. */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        contactCb.setItems(DAOContacts.getAllContacts());

    }

    /**Get contact data from combo box selection.
     * Set table view with appointments that have selected contact id. */
    public void contactCbHandler(ActionEvent actionEvent) throws SQLException {

        Contacts contacts = (Contacts) contactCb.getSelectionModel().getSelectedItem();
        int contactId = contacts.getContactId();
        String contactName = contacts.getContactName();

            contactLbl.setText(contactName);
            contactTv.setItems(DAOAppointments.getAppointmentsByContactID(contactId));

            appointmentIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
            titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
            typeCol.setCellValueFactory( new PropertyValueFactory<>("type"));
            descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
            startCol.setCellValueFactory(new PropertyValueFactory<>("Start"));
            endCol.setCellValueFactory(new PropertyValueFactory<>("End"));
            customerIdCol.setCellValueFactory( new PropertyValueFactory<>("customerId"));


    }
    /**Return to main menu. */
    public void returnHandler(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/mainMenu.fxml"));
        loader.load();
        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
    }
}

