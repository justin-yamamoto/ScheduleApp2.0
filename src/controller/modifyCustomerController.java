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
import model.Customers;
import model.FirstLevelDivisions;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**This allows for the modification of customer data. */
public class modifyCustomerController implements Initializable {

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

    /**Initialize.
     * Set country combo box data. */
    @Override
    public void initialize(URL url, ResourceBundle rb){
        //Get Countries observable list and set it to the Country combo box.
        countryCb.setItems(DAOCountries.getAllCountries());

    }

    /**Get data from previous form and display it. */
    public void sendCustomer(Customers customers)
    {
        customerIdTxt.setText(String.valueOf(customers.getCustomerId()));
        customerNameTxt.setText(String.valueOf(customers.getCustomerName()));
        addressTxt.setText(String.valueOf(customers.getAddress()));
        postalCodeTxt.setText(String.valueOf(customers.getPostalCode()));
        phoneTxt.setText(String.valueOf(customers.getPhone()));
        stateProvinceCb.setPromptText(String.valueOf(customers.getDivision()));
        countryCb.setPromptText(String.valueOf(customers.getCountry()));
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
    /**Save any changes made.
     * Display error messages if invalid data entry. */
    public void saveNewCustomerHandler(ActionEvent actionEvent) {

        try {
            //get text and combo box selections
            int customerId = Integer.parseInt(customerIdTxt.getText());
            String name = customerNameTxt.getText();
            String address = addressTxt.getText();
            String postalCode = postalCodeTxt.getText();
            String phone = phoneTxt.getText();
            FirstLevelDivisions firstLevelDivisions = (FirstLevelDivisions) stateProvinceCb.getValue();

            if (countryCb.getSelectionModel().getSelectedItem()==null){

                Alert alert = new Alert(Alert.AlertType.ERROR); //Error Dialogue box
                alert.setTitle("Error Dialogue");
                alert.setContentText("Pleased Re-Select Country");
                alert.showAndWait();

            }
            else if (stateProvinceCb.getSelectionModel().getSelectedItem()== null){
                Alert alert = new Alert(Alert.AlertType.ERROR); //Error Dialogue box
                alert.setTitle("Error Dialogue");
                alert.setContentText("Please Re-Select State or Province");
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
                DAOCustomers.updateCustomer(name,address,postalCode,phone,firstLevelDivisions.getDivisionId(),customerId);
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
        catch (NullPointerException | IOException e){
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
