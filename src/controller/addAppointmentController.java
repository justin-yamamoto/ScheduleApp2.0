package controller;

import DAO.DAOAppointments;
import DAO.DAOContacts;
import DAO.DAOCustomers;
import DAO.DAOUsers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Appointments;
import model.Contacts;
import model.Customers;
import model.Users;
import java.io.IOException;
import java.net.URL;
import java.time.*;
import java.time.chrono.ChronoLocalDateTime;
import java.util.ResourceBundle;

/** This class adds new appointments. */
public class addAppointmentController implements Initializable {

    public TextField appointmentIdTxt;
    public TextField titleTxt;
    public TextField DescriptionTxt;
    public TextField locationTxt;
    public TextField typeTxt;
    public ComboBox contactCb;
    public ComboBox startCb;
    public ComboBox endCb;
    public TextField customerIdTxt;
    public DatePicker datePicker;
    public Button saveBtn;
    public Button cancelBtn;
    public ComboBox customerIdCb;
    public ComboBox userIdCb;
    Stage stage;

    /**Initialize. */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //set contact combo box
        contactCb.setPromptText("Contacts");
        contactCb.setItems(DAOContacts.getAllContacts());

        //set customer ID combo box
        customerIdCb.setItems(DAOCustomers.getAllCustomers());

        //set user id combo box
        userIdCb.setItems(DAOUsers.getAllUsers());

    }

    /**
     * This is the date picker. It sets time slots for combo box.
     * Slots are 30 minutes from 8am to 10 pm EST.
     * @param actionEvent The date picker event
     */
    public void datePickerHandler(ActionEvent actionEvent) {

        //Set the time frame between 8am-10pm
        LocalTime open = LocalTime.of(8,0);
        LocalTime close = LocalTime.of(22,30);

        //Establish the time zone, EST
        ZoneId businessZone = ZoneId.of("America/New_York");

        //Create a list of Local time that is every 30 minutes
        ObservableList<LocalTime> businessHours = FXCollections.observableArrayList();
        LocalTime lt = open;
        while (lt.isBefore(close)) {
            businessHours.add(lt);
            lt = lt.plusMinutes(30);
        }

        //Local date is decided within the date picker
        LocalDate ld = datePicker.getValue();

        //List with chosen zone,date,and hours
        ObservableList<ZonedDateTime> businessHoursOnDate = FXCollections.observableArrayList();
        for (LocalTime localTime : businessHours)
        {
            ZonedDateTime zdt = ZonedDateTime.of(ld,localTime,businessZone);
            businessHoursOnDate.add(zdt);
        }

        //List with zone of user system default but still business hours
        ZoneId userZone = ZoneId.systemDefault();

        ObservableList<ZonedDateTime> userHoursOnDate= FXCollections.observableArrayList();
        for (ZonedDateTime zdtBusiness : businessHoursOnDate)
        {
            ZonedDateTime zdtUsers = zdtBusiness.withZoneSameInstant(userZone);
            userHoursOnDate.add(zdtUsers);
        }

        //get users choice and zone
        ZonedDateTime userPick = userHoursOnDate.get(1);
        //get users choice but with business zone
        ZonedDateTime userPBusiness = userPick.withZoneSameInstant(businessZone);


        //System.out.println("Business Hours: " + businessHours);
        //System.out.println("Local Date: " + ld);
        //System.out.println("Business Zone: " + businessZone);
        //System.out.println("Date,UHours,UZone" + userHoursOnDate);
        //Set Start Combo box with users date,hours,zone
        startCb.setItems(userHoursOnDate);
        //Set End combo box with user date,hours,zone
        endCb.setItems(userHoursOnDate);

        //System.out.println("User pick: "+ userPick);
        //System.out.println("User P Business: " + userPBusiness);
    }


    /**
     * This saves input data to the Database.
     * Alerts if input is missing.
     * @param actionEvent Save new appointment
     * @throws IOException
     */
    public void saveHandler(ActionEvent actionEvent) throws IOException {

        try {

            /**ZoneId utcZone = ZoneId.of("UTC");

            ZonedDateTime s = (ZonedDateTime) startCb.getSelectionModel().getSelectedItem();
            ZonedDateTime sUtc = s.withZoneSameInstant(utcZone);

            ZonedDateTime e = (ZonedDateTime) endCb.getSelectionModel().getSelectedItem();
            ZonedDateTime eUtc = e.withZoneSameInstant(utcZone);*/

            String title = titleTxt.getText();
            String description = DescriptionTxt.getText();
            String location = locationTxt.getText();
            String type = typeTxt.getText();

            Contacts contacts = (Contacts) contactCb.getValue();
            int contactId = contacts.getContactId();

            Customers cId = (Customers) customerIdCb.getValue();
            int customerId = cId.getCustomerId();

            Users uId = (Users) userIdCb.getValue();
            int userId = uId.getUserId();

            ZonedDateTime s = (ZonedDateTime) startCb.getSelectionModel().getSelectedItem();
            LocalDateTime appointmentStart =s.toLocalDateTime();

            ZonedDateTime e = (ZonedDateTime) endCb.getSelectionModel().getSelectedItem();
            LocalDateTime appointmentEnd = e.toLocalDateTime();


            if (title.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR); //Error Dialogue box
                alert.setTitle("Error Dialogue");
                alert.setContentText("Please enter a Title");
                alert.showAndWait();
            }
            if (description.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR); //Error Dialogue box
                alert.setTitle("Error Dialogue");
                alert.setContentText("Please enter a Description");
                alert.showAndWait();
            }
            if (location.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR); //Error Dialogue box
                alert.setTitle("Error Dialogue");
                alert.setContentText("Please enter a Location");
                alert.showAndWait();
            }
            if (type.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR); //Error Dialogue box
                alert.setTitle("Error Dialogue");
                alert.setContentText("Please enter a Type");
                alert.showAndWait();
            }
            if (datePicker.getValue().isBefore(LocalDate.now())) {
                Alert alert = new Alert(Alert.AlertType.ERROR); //Error Dialogue box
                alert.setTitle("Error Dialogue");
                alert.setContentText("Date must be current or future dated");
                alert.showAndWait();
            }
            if (appointmentStart.isAfter(appointmentEnd)) {
                Alert alert = new Alert(Alert.AlertType.ERROR); //Error Dialogue box
                alert.setTitle("Error Dialogue");
                alert.setContentText("Appointment Start Time must be before End Time.");
                alert.showAndWait();
            }
            if (appointmentStart.equals(appointmentEnd)) {
                Alert alert = new Alert(Alert.AlertType.ERROR); //Error Dialogue box
                alert.setTitle("Error Dialogue");
                alert.setContentText("Appointment Start Time cannot be the same as End Time");
                alert.showAndWait();
            }

                else {
                //ADD A NEW APPOINTMENT
                if (validationCheck()){
                    Alert alert = new Alert(Alert.AlertType.ERROR); //Error Dialogue box
                    alert.setTitle("Error Dialogue");
                    alert.setContentText("Appointments cannot overlap");
                    alert.showAndWait();
                }else {
                    DAOAppointments.addAppointment(title, description, location, type, appointmentStart, appointmentEnd, customerId, userId, contactId);
                    //load customer records form
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/view/mainMenu.fxml"));
                    loader.load();
                    stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
                    Parent scene = loader.getRoot();
                    stage.setScene(new Scene(scene));
                    stage.show();
                }

            }

        } catch (NullPointerException e) {
            return;
        }
    }

    /**This loads main menu form.
     * @param actionEvent Return to main menu. */
    public void cancelHandler(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/mainMenu.fxml"));
        loader.load();
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
    }


/**Set parameters for overlapping appointments.
 * @return True if valid
 * @return False if invalid */
    private boolean validationCheck() {
        ObservableList<Appointments> appointments = DAOAppointments.getAllAppointments();
        for (Appointments appointments1 : appointments) {

            LocalDateTime dbStart = appointments1.getStart();
            LocalDateTime dbEnd = appointments1.getEnd();


            ZonedDateTime zsCb= (ZonedDateTime) startCb.getSelectionModel().getSelectedItem();
            LocalDateTime appointmentStart = zsCb.toLocalDateTime();

            ZonedDateTime zeCb = (ZonedDateTime) endCb.getSelectionModel().getSelectedItem();

            LocalDateTime appointmentEnd = zeCb.toLocalDateTime();

            if (dbStart.isAfter(appointmentStart) && dbStart.isBefore(appointmentEnd) || dbEnd.isAfter(appointmentStart) && dbEnd.isBefore(appointmentEnd) || dbStart.equals(appointmentStart) || dbEnd.equals(appointmentEnd)) {
                return true;
            }
        }
        return false;
    }


}

