package controller;

import DAO.DAOType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import main.firstInterface;
import model.Type;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**This shows the amount of appointments in chosen month by type. */
public class reportsController implements Initializable {

    public Label loginLbl;
    public Button cancelBtn;
    public Label appointmentCountLbl;
    public ComboBox typeCb;
    public ComboBox monthCb;
    public Button exitBtn;
    public Button submitBtn;
    public Button selectMBtn;
    Stage stage;

    /**Initialize.
     * Set combo boxes and create list of months. */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        monthCb.setPromptText("Choose a Month");
        final ObservableList<String> allMonths = FXCollections.observableArrayList("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");
        monthCb.setItems(allMonths);

    }
    /**Select month. */
    public void selectMonthHandler(ActionEvent actionEvent) {
        if (monthCb.getSelectionModel().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR); //Error Dialogue box
            alert.setTitle("Error Dialogue");
            alert.setContentText("Please select a Month");
            alert.showAndWait();
        }

        if (monthCb.getSelectionModel().getSelectedItem().equals("January")){
            if (DAOType.getJan().isEmpty()){
                typeCb.setPromptText("No Appointments");
                typeCb.setItems(null);
            }else {
                typeCb.setPromptText("Choose Type");
                typeCb.setItems(DAOType.getJan());
            }

        }

        if (monthCb.getSelectionModel().getSelectedItem().equals("February")){
            if (DAOType.getFeb().isEmpty()){
                typeCb.setPromptText("No Appointments");
                typeCb.setItems(null);
            }else {
                typeCb.setPromptText("Choose Type");
                typeCb.setItems(DAOType.getFeb());
            }

        }

        if (monthCb.getSelectionModel().getSelectedItem().equals("March") ){
            if (DAOType.getMarch().isEmpty()){
                typeCb.setPromptText("No Appointments");
                typeCb.setItems(null);
            }else {
                typeCb.setPromptText("Choose Type");
                typeCb.setItems(DAOType.getMarch());
            }

        }

        if (monthCb.getSelectionModel().getSelectedItem().equals("April") ) {
            if (DAOType.getApril().isEmpty()) {
                typeCb.setPromptText("No Appointments");
                typeCb.setItems(null);
            } else {
                typeCb.setPromptText("Choose Type");
                typeCb.setItems(DAOType.getApril());
            }
        }

        if (monthCb.getSelectionModel().getSelectedItem().equals("May") ) {
            if (DAOType.getMay().isEmpty()) {
                typeCb.setPromptText("No Appointments");
                typeCb.setItems(null);
            } else {
                typeCb.setPromptText("Choose Type");
                typeCb.setItems(DAOType.getMay());
            }
        }

        if (monthCb.getSelectionModel().getSelectedItem().equals("June") ) {
            if (DAOType.getJune().isEmpty()) {
                typeCb.setPromptText("No Appointments");
                typeCb.setItems(null);
            } else {
                typeCb.setPromptText("Choose Type");
                typeCb.setItems(DAOType.getJune());
            }
        }

        if (monthCb.getSelectionModel().getSelectedItem().equals("July") ) {
            if (DAOType.getJuly().isEmpty()) {
                typeCb.setPromptText("No Appointments");
                typeCb.setItems(null);
            } else {
                typeCb.setPromptText("Choose Type");
                typeCb.setItems(DAOType.getJuly());
            }
        }

        if (monthCb.getSelectionModel().getSelectedItem().equals("August") ) {
            if (DAOType.getAug().isEmpty()) {
                typeCb.setPromptText("No Appointments");
                typeCb.setItems(null);
            } else {
                typeCb.setPromptText("Choose Type");
                typeCb.setItems(DAOType.getAug());
            }
        }

        if (monthCb.getSelectionModel().getSelectedItem().equals("September") ) {
            if (DAOType.getSept().isEmpty()) {
                typeCb.setPromptText("No Appointments");
                typeCb.setItems(null);
            } else {
                typeCb.setPromptText("Choose Type");
                typeCb.setItems(DAOType.getSept());
            }
        }

        if (monthCb.getSelectionModel().getSelectedItem().equals("October") ) {
            if (DAOType.getOct().isEmpty()) {
                typeCb.setPromptText("No Appointments");
                typeCb.setItems(null);
            } else {
                typeCb.setPromptText("Choose Type");
                typeCb.setItems(DAOType.getOct());
            }
        }

        if (monthCb.getSelectionModel().getSelectedItem().equals("November") ) {
            if (DAOType.getNov().isEmpty()) {
                typeCb.setPromptText("No Appointments");
                typeCb.setItems(null);
            } else {
                typeCb.setPromptText("Choose Type");
                typeCb.setItems(DAOType.getNov());
            }
        }

        if (monthCb.getSelectionModel().getSelectedItem().equals("December")) {
            if (DAOType.getDec().isEmpty()) {
                typeCb.setPromptText("No Appointments");
                typeCb.setItems(null);
            } else {
                typeCb.setPromptText("Choose Type");
                typeCb.setItems(DAOType.getDec());
            }
        }
    }

    /**Return to main menu. */
    public void exitHandler(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/mainMenu.fxml"));
        loader.load();
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**Get data for selects month and type and display that data.
     * This contains a lambda expression.
     * Concats data into a String that is more readable for the user */
    public NullPointerException submitHandler(ActionEvent actionEvent) {
        try{

            Type type = (Type) typeCb.getSelectionModel().getSelectedItem();
            String sT = type.getType();
            int count = type.getCount();
            String month = (String) monthCb.getSelectionModel().getSelectedItem();

            if (month.isEmpty()){
                Alert alert = new Alert(Alert.AlertType.ERROR); //Error Dialogue box
                alert.setTitle("Error Dialogue");
                alert.setContentText("Please select a Month");
                alert.showAndWait();
            }
            else if (sT.isBlank()){
                Alert alert = new Alert(Alert.AlertType.ERROR); //Error Dialogue box
                alert.setTitle("Error Dialogue");
                alert.setContentText("Please select a Month");
                alert.showAndWait();
            }
            else {

                //Value returning lambda expression
                //Concats data into a String that is more readable for the user
                firstInterface message = (s,t) -> "Total: " + s + " Type: " + t;

                appointmentCountLbl.setText(message.getMessage(String.valueOf(count), sT ));
            }

        }catch (NullPointerException e){
            return e;

        }

        return null;
    }

    /**Refresh all selections and set to null. */
    public void refreshHandler(ActionEvent actionEvent) {

        monthCb.setValue(null);

        monthCb.setPromptText("Choose a Month");

        typeCb.setItems(null);
        typeCb.setPromptText(null);
        appointmentCountLbl.setText(null);

    }
}
