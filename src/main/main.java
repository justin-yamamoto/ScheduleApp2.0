package main;

import helper.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class main extends Application {
    @Override

    /** Loads log-in form. */
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/view/logIn.fxml"));
        primaryStage.setTitle("Log-In");
        primaryStage.setScene(new Scene(root, 400, 450));
        primaryStage.show();
    }

    public static void main(String[] args){

        //Opens database connection.
        JDBC.openConnection();

        launch(args);

        //Close database connection
        JDBC.closeConnection();

    }
}
