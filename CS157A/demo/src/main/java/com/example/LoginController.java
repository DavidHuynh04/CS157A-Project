package com.example;
import java.io.IOException;
import java.sql.SQLException;

import com.example.MySqlLogic.ExtractFunction;
import com.example.MySqlLogic.SQLConnection;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;



public class LoginController {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private void backButton() throws IOException {
        App.setRoot("MainMenuScene");
    }
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
            App.setRoot("UserView");
        }
    }
}
