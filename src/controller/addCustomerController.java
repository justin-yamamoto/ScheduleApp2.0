package controller;


import DAO.DAOCountries;
import DAO.DAOCustomers;
import DAO.DAOFirstLevelDivision;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Countries;
import model.FirstLevelDivisions;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**This class adds a new customer. */
public class addCustomerController implements Initializable {

    public Label customerIdLbl;
    public Label customerNameLbl;
    public Label addressLbl;
    public Label postalCodeLbl;
    public Label phoneLbl;
    public TextField customerIdTxt;
    public TextField customerNameTxt;
    public TextField addressTxt;
    public TextField postalCodeTxt;
    public TextField phoneTxt;
    public Label countryLbl;
    public Label stateProvinceLbl;
    public ComboBox stateProvinceCb;
    public ComboBox countryCb;
    public Button saveBtn;
    public Button cancelBtn;
    Stage stage;

    /**Initialize. */
    @Override
    public void initialize(URL url, ResourceBundle rb){

        //Get Countries observable list and set it to the Country combo box.
        countryCb.setItems(DAOCountries.getAllCountries());

        // Set prompt text for country combo box to Country.
        countryCb.setPromptText("Country");

        //Set prompt text for country combo box to State or Province.
        stateProvinceCb.setPromptText("State or Province");

    }

    public void stateProvinceCbHandler(ActionEvent actionEvent) {

    }

    /**Get Selection from Country combo box.
     * If UK produce UK Divisions.
     * If U.S. produce US Divisions.
     * If Canada produce Canada Divisions.
     * Set produced items into state/province combo box. */
    public void countryCbHandler(ActionEvent actionEvent) {

        StringBuilder sb = new StringBuilder("");

        Countries sc = (Countries) countryCb.getSelectionModel().getSelectedItem();

        if(sc== null){
            System.out.println("NULL");
        }
        else {
            sb.append(sc.getCountry());
        }
        switch (sc.getCountry()) {
            case "UK" -> stateProvinceCb.setItems(DAOFirstLevelDivision.getUKDivisions());
            case "U.S" -> stateProvinceCb.setItems(DAOFirstLevelDivision.getUSDivisions());
            case "Canada" -> stateProvinceCb.setItems(DAOFirstLevelDivision.getCANADADivisions());
        }

    }

    /** On save get information from text fields.
    * Create a new customer.
    * Return to customer records form.
     * @throws IOException*/
    public void saveNewCustomerHandler(ActionEvent actionEvent) throws IOException {
        try {
            //get text and combo box selections
            String name = customerNameTxt.getText();
            String address = addressTxt.getText();
            String postalCode = postalCodeTxt.getText();
            String phone = phoneTxt.getText();
            FirstLevelDivisions firstLevelDivisions = (FirstLevelDivisions) stateProvinceCb.getValue();

            if (countryCb.getSelectionModel().getSelectedItem()==null){

                Alert alert = new Alert(Alert.AlertType.ERROR); //Error Dialogue box
                alert.setTitle("Error Dialogue");
                alert.setContentText("Country must be Selected");
                alert.showAndWait();

            }
            else if (stateProvinceCb.getSelectionModel().getSelectedItem() == null){
                Alert alert = new Alert(Alert.AlertType.ERROR); //Error Dialogue box
                alert.setTitle("Error Dialogue");
                alert.setContentText("State or Province must be Selected");
                alert.showAndWait();
            }
            else if(customerNameTxt.getText().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.ERROR); //Error Dialogue box
                alert.setTitle("Error Dialogue");
                alert.setContentText("Please enter Customer Name");
                alert.showAndWait();
            }
            else if(addressTxt.getText().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.ERROR); //Error Dialogue box
                alert.setTitle("Error Dialogue");
                alert.setContentText("Please enter Address");
                alert.showAndWait();
            }
            else if(postalCodeTxt.getText().isBlank()){
                Alert alert = new Alert(Alert.AlertType.ERROR); //Error Dialogue box
                alert.setTitle("Error Dialogue");
                alert.setContentText("Please enter Postal Code");
                alert.showAndWait();
            }
            else if(phoneTxt.getText().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.ERROR); //Error Dialogue box
                alert.setTitle("Error Dialogue");
                alert.setContentText("Please enter Phone");
                alert.showAndWait();
            }
            else{
            //create a new customer
            DAOCustomers.addCustomer(name,address,postalCode,phone,firstLevelDivisions.getDivisionId());
            //load customer records form
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/mainMenu.fxml"));
            loader.load();
            stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
            }

        }
        catch (NullPointerException e){
            return;
        }

    }

    /**On cancel return to customer record form. */
    public void cancelHandler(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/mainMenu.fxml"));
        loader.load();
        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
    }
}
