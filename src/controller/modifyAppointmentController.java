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
import java.util.ResourceBundle;

/**This class modifies selected appointments. */
public class modifyAppointmentController implements Initializable {

    public TextField appointmentIdTxt;
    public TextField titleTxt;
    public TextField DescriptionTxt;
    public TextField locationTxt;
    public TextField typeTxt;
    public TextField customerIdTxt;
    public ComboBox contactCb;
    public ComboBox startCb;
    public ComboBox endCb;
    public DatePicker datePicker;
    public Button saveBtn;
    public Button cancelBtn;
    public ComboBox customerIdCb;
    public ComboBox userIdCb;
    Stage stage;

    /**Initialize.
     * Set combo box with data. */
    @Override
    public void initialize(URL url, ResourceBundle rb){

        contactCb.setItems(DAOContacts.getAllContacts());

        customerIdCb.setItems(DAOCustomers.getAllCustomers());

        userIdCb.setItems(DAOUsers.getAllUsers());


    }

    /**Get data from previous form and display it.
     * @param appointments An appointment object. */
    public void sendAppointment(Appointments appointments)
    {
        //Establish Localdatetime since that is how it is stored
        LocalDateTime start = appointments.getStart();
        LocalDateTime end = appointments.getEnd();

        //Establish the Zone those localdatetimes represent
        ZoneId utcZone =ZoneId.of("UTC");

        //Establish ldt start as a UTC zone
        ZonedDateTime dbStart = start.atZone(utcZone);
        ZonedDateTime dbEnd = end.atZone(utcZone);

        //Establish the systems zone
        ZoneId sdZone = ZoneId.systemDefault();
        //Convert the stored utc time to system time
        ZonedDateTime ss = dbStart.withZoneSameInstant(sdZone);
        ZonedDateTime ee = dbEnd.withZoneSameInstant(sdZone);


        appointmentIdTxt.setText(String.valueOf(appointments.getAppointmentId()));
        titleTxt.setText(String.valueOf(appointments.getTitle()));
        DescriptionTxt.setText(String.valueOf(appointments.getDescription()));
        locationTxt.setText(String.valueOf(appointments.getLocation()));
        contactCb.setPromptText(String.valueOf(appointments.getContactId()));
        typeTxt.setText(String.valueOf(appointments.getType()));
        startCb.setValue(appointments.getStart());
        endCb.setValue(appointments.getEnd());
        datePicker.setValue(appointments.getStart().toLocalDate());
        customerIdCb.setPromptText(String.valueOf(appointments.getCustomerId()));
        userIdCb.setPromptText(String.valueOf(appointments.getUserId()));

    }

    /**Date picker.
     * Set time frame within combo boxed when date is chosen. */
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
        ObservableList<ZonedDateTime>userHoursOnDate= FXCollections.observableArrayList();
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


    /**Save update appointment information.
     * If all check pass then appointment data is updated. */
    public void saveHandler(ActionEvent actionEvent) throws IOException{

        try {
           /** ZoneId utcZone = ZoneId.of("UTC");

            ZonedDateTime s = (ZonedDateTime) startCb.getSelectionModel().getSelectedItem();
            ZonedDateTime sUtc = s.withZoneSameInstant(utcZone);
            ZonedDateTime e = (ZonedDateTime) endCb.getSelectionModel().getSelectedItem();
            ZonedDateTime eUtc =e.withZoneSameInstant(utcZone);*/

            int appointmentId = Integer.parseInt(appointmentIdTxt.getText());
            String title = titleTxt.getText();
            String description = DescriptionTxt.getText();
            String location = locationTxt.getText();
            String type = typeTxt.getText();
            Contacts contacts = (Contacts) contactCb.getValue();
            Customers customerId = (Customers) customerIdCb.getValue();
            Users userId = (Users) userIdCb.getValue();
            ZonedDateTime s = (ZonedDateTime) startCb.getSelectionModel().getSelectedItem();
            LocalDateTime appointmentStart = s.toLocalDateTime();
            ZonedDateTime e = (ZonedDateTime) endCb.getSelectionModel().getSelectedItem();
            LocalDateTime appointmentEnd = e.toLocalDateTime();

            if (title.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR); //Error Dialogue box
                alert.setTitle("Error Dialogue");
                alert.setContentText("Please enter a Title");
                alert.showAndWait();
            } else if (description.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR); //Error Dialogue box
                alert.setTitle("Error Dialogue");
                alert.setContentText("Please enter a Description");
                alert.showAndWait();
            } else if (location.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR); //Error Dialogue box
                alert.setTitle("Error Dialogue");
                alert.setContentText("Please enter a Location");
                alert.showAndWait();
            } else if (type.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR); //Error Dialogue box
                alert.setTitle("Error Dialogue");
                alert.setContentText("Please enter a Type");
                alert.showAndWait();
            } else if (contacts == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR); //Error Dialogue box
                alert.setTitle("Error Dialogue");
                alert.setContentText("Please Re-Select Contact");
                alert.showAndWait();
            } else if (customerId == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR); //Error Dialogue box
                alert.setTitle("Error Dialogue");
                alert.setContentText("Please Re-Select Customer Id");
                alert.showAndWait();
            } else if (userId == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR); //Error Dialogue box
                alert.setTitle("Error Dialogue");
                alert.setContentText("Please Re-Select User Id");
                alert.showAndWait();
            } else if (datePicker.getValue().isBefore(LocalDate.now())) {
                Alert alert = new Alert(Alert.AlertType.ERROR); //Error Dialogue box
                alert.setTitle("Error Dialogue");
                alert.setContentText("Date must be current or future dated");
                alert.showAndWait();
            } else if (appointmentStart.isAfter(appointmentEnd)) {
                Alert alert = new Alert(Alert.AlertType.ERROR); //Error Dialogue box
                alert.setTitle("Error Dialogue");
                alert.setContentText("Appointment Start Time must be before End Time.");
                alert.showAndWait();
            } else if (appointmentStart.equals(appointmentEnd)) {
                Alert alert = new Alert(Alert.AlertType.ERROR); //Error Dialogue box
                alert.setTitle("Error Dialogue");
                alert.setContentText("Appointment Start Time cannot be the same as End Time");
                alert.showAndWait();
            } else {
                if (validationCheck()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR); //Error Dialogue box
                    alert.setTitle("Error Dialogue");
                    alert.setContentText("Appointments cannot overlap");
                    alert.showAndWait();
                }
                else
                {
                    DAOAppointments.updateAppointment(title, description, location, type, appointmentStart, appointmentEnd, customerId.getCustomerId(), userId.getUserId(), contacts.getContactId(), appointmentId);

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

        }
         catch (NullPointerException e) {
            return;
        }

    }

    /**Return to Appointment Forms. */
    public void cancelHandler(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/mainMenu.fxml"));
        loader.load();
        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
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
