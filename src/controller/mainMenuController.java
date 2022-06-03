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
import model.Appointments;
import model.Customers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.ResourceBundle;




/**This class presents data for customers and appointments.*/
public class mainMenuController implements Initializable {

    public Label aptStatusLbl;
    public TableView customerRecordsView;
    public TableColumn idCol;
    public TableColumn nameCol;
    public TableColumn addressCol;
    public TableColumn postalCodeCol;
    public TableColumn phoneCol;
    public TableColumn countryCol;
    public TableColumn stateProvinceCol;
    public Button addBtn;
    public Button updateBtn;
    public Button deleteBtn;
    public Label appointmentsLbl;
    public RadioButton allRBtn;
    public ToggleGroup appointmentsFilter;
    public RadioButton weeklyRBtn;
    public RadioButton monthlyRBtn;
    public TableView appointmentsView;
    public TableColumn appointmentIdCol;
    public TableColumn titleCol;
    public TableColumn descriptionCol;
    public TableColumn locationCol;
    public TableColumn contactCol;
    public TableColumn typeCol;
    public TableColumn startCol;
    public TableColumn endCol;
    public TableColumn customerIdCol;
    public TableColumn userIdCol;
    public Button addBtn1;
    public Button updateBtn1;
    public Button deleteBtn1;
    public Button contactSBtn;
    public TextField customerSearchTxt;
    Stage stage;
    public Button exitBtn;

    /**Initialize.
     * Set Table views and time alert.*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //load customer data upon loading form
        customerRecordsView.setItems(DAOCustomers.getAllCustomers());

        idCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        postalCodeCol.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        phoneCol.setCellValueFactory( new PropertyValueFactory<>("phone"));
        countryCol.setCellValueFactory(new PropertyValueFactory<>("country"));
        stateProvinceCol.setCellValueFactory(new PropertyValueFactory<>("division"));

        appointmentsView.setItems(DAOAppointments.getAllAppointments());

        appointmentIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        typeCol.setCellValueFactory( new PropertyValueFactory<>("type"));
        customerIdCol.setCellValueFactory( new PropertyValueFactory<>("customerId"));
        userIdCol.setCellValueFactory( new PropertyValueFactory<>("userId"));
        contactCol.setCellValueFactory(new PropertyValueFactory<>("contactId"));
        startCol.setCellValueFactory(new PropertyValueFactory<>("Start"));
        endCol.setCellValueFactory(new PropertyValueFactory<>("End"));

        //get object from appointments view
        for (Object o :appointmentsView.getItems()){

            //get LocalDateTime from object
            LocalDateTime sldt = (LocalDateTime) startCol.getCellData(o);
            LocalDateTime eldt = (LocalDateTime) endCol.getCellData(o);
            //System.out.println("Start: " + sldt + " End: " + eldt);

            int appointmentId = (int) appointmentIdCol.getCellData(o);

            LocalDate ldn = sldt.toLocalDate();

            //get local time from local date time
            LocalTime slt = LocalTime.from(sldt);
            LocalTime elt = LocalTime.from(eldt);
            LocalDate sld = LocalDate.from(sldt);
            LocalDate eld = LocalDate.from(eldt);
            //System.out.println("Start Time: " + slt + " Start Date: " + sld + " End Time: " + elt + " End Date: " + eld +
            //" Appointment Id: " + appointmentId);

            //establish current time
            LocalTime ltCurrent = LocalTime.now();
            LocalDate ldCurrent = LocalDate.now();

            //get difference between local time from table and local time now
            long timeD = ChronoUnit.MINUTES.between(slt,ltCurrent);
            long interval = (timeD + - 1)*-1;

            //System.out.println("This is local time current: " + ltCurrent);
            //System.out.println("This is local time starts: " + ldt);
            //System.out.println("This is difference: "  + interval);
            //System.out.println(" Upcoming Appointment in " + interval + " Minute(s) " + " Appointment ID: " + appointmentId + " DATE: " + ld + " Time: " + lt );

            //If schedule time is within 15 minutes, alert user
            if (interval >0 && interval<=15 && ldCurrent.isEqual(sld)){
                aptStatusLbl.setText(" Upcoming Appointment in " + interval + " Minute(s) " + " Appointment ID: " + appointmentId + " DATE: " + sld + " Time: " + slt );

                /** Alert alert = new Alert(Alert.AlertType.INFORMATION); //Error Dialogue box
                 alert.setTitle("Alert");
                 alert.setContentText(" Upcoming Appointment in " + interval + " Minute(s) \n" + " Appointment ID: " + appointmentId + "\n DATE: " + sld + "\n Time: " + slt );
                 alert.showAndWait();*/
            }

        }

    }



    /**Exit Application. */
    public void exitHandler(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Exiting, do you wish to continue?" );

        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
            System.exit(0);
        }
    }

    /**Load add customer form. */
    public void addCustomerHandler(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/addCustomer.fxml"));
        loader.load();
        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**Load modify customer form. */
    public void updateCustomerHandler(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/modifyCustomer.fxml"));
        loader.load();

        modifyCustomerController MCController = loader.getController();
        MCController.sendCustomer((Customers) customerRecordsView.getSelectionModel().getSelectedItem());

        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**Delete selected customer and associated appointments. */
    public void deleteCustomerHandler(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Deleting Selected Customer \n" + "Any associated appointments will also be deleted. " );

        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK){

            //get selected customer data from table view
            Customers oneCustomer;
            oneCustomer = (Customers) customerRecordsView.getSelectionModel().getSelectedItem();
            int customerId = oneCustomer.getCustomerId();

            DAOCustomers.deleteCustomer(customerId);

            customerRecordsView.setItems(DAOCustomers.getAllCustomers());
            appointmentsView.setItems(DAOAppointments.getAllAppointments());
        }
    }

    /**Set table view with all appointments. */
    public void allAppointmentsHandler(ActionEvent actionEvent) {
        appointmentsView.setItems(DAOAppointments.getAllAppointments());
        appointmentIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        typeCol.setCellValueFactory( new PropertyValueFactory<>("type"));
        customerIdCol.setCellValueFactory( new PropertyValueFactory<>("customerId"));
        userIdCol.setCellValueFactory( new PropertyValueFactory<>("userId"));
        contactCol.setCellValueFactory(new PropertyValueFactory<>("contactId"));
        startCol.setCellValueFactory(new PropertyValueFactory<>("Start"));
        endCol.setCellValueFactory(new PropertyValueFactory<>("End"));
    }
    /**Set table view with all appointments within current week.*/
    public void weeklyAppointmentsHandler(ActionEvent actionEvent) {
        appointmentsView.setItems(DAOAppointments.getWeeklyAppointments());
        appointmentIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        typeCol.setCellValueFactory( new PropertyValueFactory<>("type"));
        customerIdCol.setCellValueFactory( new PropertyValueFactory<>("customerId"));
        userIdCol.setCellValueFactory( new PropertyValueFactory<>("userId"));
        contactCol.setCellValueFactory(new PropertyValueFactory<>("contactId"));
        startCol.setCellValueFactory(new PropertyValueFactory<>("Start"));
        endCol.setCellValueFactory(new PropertyValueFactory<>("End"));
    }
    /**Set table view with all appointments within the current month. */
    public void monthlyAppointmentHandler(ActionEvent actionEvent) {
        appointmentsView.setItems(DAOAppointments.getMonthlyAppointments());
        appointmentIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        typeCol.setCellValueFactory( new PropertyValueFactory<>("type"));
        customerIdCol.setCellValueFactory( new PropertyValueFactory<>("customerId"));
        userIdCol.setCellValueFactory( new PropertyValueFactory<>("userId"));
        contactCol.setCellValueFactory(new PropertyValueFactory<>("contactId"));
        startCol.setCellValueFactory(new PropertyValueFactory<>("Start"));
        endCol.setCellValueFactory(new PropertyValueFactory<>("End"));
    }
    /**Load add appointment form. */
    public void addAppointmentHandler(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/addAppointment.fxml"));
        loader.load();
        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**Load update appointments form.
     * Get selected data and load that data into update form.
     * Alert if no appointment is selected. */
    public void updateAppointmentHandler(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/modifyAppointment.fxml"));
        loader.load();

        modifyAppointmentController MAController = loader.getController();
        Appointments appointments= (Appointments) appointmentsView.getSelectionModel().getSelectedItem();
        if (appointments!=null){

            MAController.sendAppointment((Appointments) appointmentsView.getSelectionModel().getSelectedItem());
            stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR); //Error Dialogue box
            alert.setTitle("Error Dialogue");
            alert.setContentText("Appointment must be selected");
            alert.showAndWait();

        }

    }
    /**Show alert to confirm appointment delete with appointment id and type of appointment.
     * Delete selected Appointment. */
    public void deleteAppointmentHandler(ActionEvent actionEvent) {
        Appointments selectedAppointment;
        selectedAppointment = (Appointments) appointmentsView.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Canceling Appointment ID: " + selectedAppointment.getAppointmentId() + "\nType of Appointment: " + selectedAppointment.getType() );

        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK){

            DAOAppointments.deleteAppointment(selectedAppointment.getAppointmentId());

            appointmentsView.setItems(DAOAppointments.getAllAppointments());

        }
    }

    /**Load reports form. */
    public void reportsHandler(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/reports.fxml"));
        loader.load();
        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**Load Contact schedule form. */
    public void contactSHandler(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/contactSchedule.fxml"));
        loader.load();
        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**Load Customer schedule Form. */
    public void customerScheduleHandler(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/customerSchedule.fxml"));
        loader.load();
        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**Search customer list by id or name. */
    public void getCustomerSearchResults(ActionEvent actionEvent) throws SQLException {
        if (customerSearchTxt.getText().isEmpty()){
            customerRecordsView.setItems(DAOCustomers.getAllCustomers());
        }
        else if (customerSearchTxt.getText().matches("[0-9]")){

            int r = Integer.parseInt(customerSearchTxt.getText());

            customerRecordsView.setItems(DAOCustomers.getCustomersByCustomerID(r));



        }else{
            String q = customerSearchTxt.getText();
            customerRecordsView.setItems(DAOCustomers.getCustomersByCustomerName(q));


        }

    }

}
