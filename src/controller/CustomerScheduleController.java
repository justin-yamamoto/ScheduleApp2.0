package controller;

import DAO.DAOAppointments;
import DAO.DAOCustomers;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import main.secondInterface;
import model.Customers;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**This class shows the schedule of customers. */
public class CustomerScheduleController implements Initializable {

    public ComboBox contactCb;
    public TableColumn appointmentIdCol;
    public TableColumn titleCol;
    public TableColumn typeCol;
    public TableColumn descriptionCol;
    public TableColumn startCol;
    public TableColumn endCol;
    public Button returnBtn;
    public ComboBox customerCb;
    public Label customerLbl;
    public TableView customerTv;
    Stage stage;

    /**Initialize.
     * Set customer combo box with customer data. */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        customerCb.setItems(DAOCustomers.getAllCustomers());

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

    /**Customer Combo box.
     * Get customer object.
     * Then fill table view with appointment that have matching customer ID.
     * Displays combined string for user friendly readability. */
    public void customerCbHandler(ActionEvent actionEvent) throws SQLException {
        Customers customers = (Customers) customerCb.getSelectionModel().getSelectedItem();
        String name = customers.getCustomerName();
        int customerId = customers.getCustomerId();
        //Value returning lambda expression
        secondInterface message = s-> "Selected: " + s;
        customerLbl.setText(message.displayMessage(name));

        customerTv.setItems(DAOAppointments.getAppointmentsByCustomerID(customerId));

        appointmentIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        typeCol.setCellValueFactory( new PropertyValueFactory<>("type"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        startCol.setCellValueFactory(new PropertyValueFactory<>("Start"));
        endCol.setCellValueFactory(new PropertyValueFactory<>("End"));

    }
}

