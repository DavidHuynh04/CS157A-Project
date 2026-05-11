// David Huynh: Last Update 5/10
package com.example.controllers;
import java.io.IOException;
import java.sql.SQLException;

import com.example.App;
import com.example.MySqlLogic.ExtractFunction;
import com.example.MySqlLogic.SQLConnection;
import com.example.SessionInformation;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


// FXML Controller for the log in page
public class LoginController {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    // Returns to the main menu
    @FXML
    private void backButton() throws IOException {
        App.setRoot("MainMenuScene");
    }
    // Uses a Select query to find if the username and password matches a user in the database
    // Checks the user's role, if the user is a customer shows the user view, if the user is a admin shows the administroator view
    @FXML
    private void checkLogin() throws IOException, SQLException{
        String username = usernameField.getText();
        String password = passwordField.getText();
        SessionInformation check = ExtractFunction.getUserByLogin(SQLConnection.getConnection(), username, password);
        if (check == null){
            Alert error = new Alert(Alert.AlertType.ERROR); 
            error.setContentText("Incorrect Login"); 
            error.showAndWait();
        }
        else{
            SessionInformation.setSession(check);
            if (check.getRole().equals("Customer")){
                App.setRoot("UserView");
            }
            else if (check.getRole().equals("Admin")){
                App.setRoot("AdministratorView");
            }
        }
    }
}
