package controller;

import DAO.DAOUsers;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Locale;
import java.util.ResourceBundle;

/**This class allows user to login based on correct credentials. */
public class logInController implements Initializable {

    public Label loginLbl;
    public Label locationLbl;
    public Label zoneLbl;
    public Label userNameLbl;
    public TextField userNameTxt;
    public Label passwordLbl;
    public TextField passwordTxt;
    public Button submitBtn;
    public Button cancelBtn;
    Stage stage;

    /**Initialize.
     * Set retrived data. */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Sets the Zone Label to Zone ID of the system using it.
        zoneLbl.setText(String.valueOf(ZoneId.systemDefault()));

        //Retrieves resource bundle from NAT.
        //If OS is French then sets text and labels to corresponding French text.
        //If OS is English then sets text and labels to corresponding English text.
        ResourceBundle rb = ResourceBundle.getBundle("main/NAT", Locale.getDefault());

        if (Locale.getDefault().getLanguage().equals("fr")){
            loginLbl.setText(rb.getString("log-in"));
            userNameLbl.setText(rb.getString("username"));
            passwordLbl.setText(rb.getString("password"));
            submitBtn.setText(rb.getString("submit"));
            cancelBtn.setText(rb.getString("cancel"));
            locationLbl.setText(rb.getString("location"));
        }

        else if (Locale.getDefault().getLanguage().equals("en")){
            loginLbl.setText("Log-In");
            userNameLbl.setText("User-Name:");
            passwordLbl.setText("Password:");
            submitBtn.setText("Submit");
            cancelBtn.setText("Cancel");
            locationLbl.setText("Location:");
        }

    }


    /**Check to see if username and password are valid.
     * Change display language based on system setting,French or English.
     * Display error message. */
    public void submitHandler(ActionEvent actionEvent) throws SQLException, IOException {

        String userName = userNameTxt.getText();
        String password = passwordTxt.getText();

        if (DAOUsers.select(userName,password)){
            //log successful attempt to txt file
            String fileName = "src/files/login_activity.txt", item;

            FileWriter fwriter = new FileWriter(fileName, true);

            PrintWriter outputFile = new PrintWriter(fwriter);

            item =" UserName: " + userName + " Attempt: Success " +" Date/Time: " + LocalDateTime.now();
            outputFile.println(item);

            outputFile.close();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/mainMenu.fxml"));
            loader.load();
            stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        }
        else{
            if (Locale.getDefault().getLanguage().equals("fr"))
            {
                Alert alert = new Alert(Alert.AlertType.WARNING); //Error Dialogue box
                alert.setTitle("Avertissement");
                alert.setHeaderText("Invalide");
                alert.setContentText("Nom d'utilisateur ou mot de passe invalide");
                alert.showAndWait();
            }
            else if (Locale.getDefault().getLanguage().equals("en"))
            {
                Alert alert = new Alert(Alert.AlertType.WARNING); //Error Dialogue box
                alert.setTitle("Warning");
                alert.setHeaderText("Invalid");
                alert.setContentText("Invalid User-Name or Password");
                alert.showAndWait();
            }
            //log failed attempts to txt file
            String fileName = "src/files/login_activity.txt", item;

            FileWriter fwriter = new FileWriter(fileName, true);

            PrintWriter outputFile = new PrintWriter(fwriter);

            item = " UserName: " + userName + " Attempt: Failed " + " Date/Time: " + LocalDateTime.now();
            outputFile.println(item);

            outputFile.close();

        }

    }

    /**Exit application. */
    public void cancelHandler(ActionEvent actionEvent) {

        System.exit(0);
    }

}
