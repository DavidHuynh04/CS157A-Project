package com.example;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashSet;

import com.example.MySqlLogic.ExtractFunction;
import com.example.MySqlLogic.InsertFunction;
import com.example.MySqlLogic.SQLConnection;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegisterController {
    @FXML
    private TextField UsernameField;
    @FXML
    private TextField NameField;
    @FXML
    private PasswordField PasswordField;
    @FXML
    private PasswordField ConfirmPasswordField;
    @FXML
    private TextField EmailField;
    @FXML 
    private TextField PhoneField;
    @FXML 
    private TextField AddressField;
    @FXML
    private void backButton() throws IOException {
        App.setRoot("MainMenuScene");
    }
    @FXML
    private void register() throws SQLException, IOException{
        String username = UsernameField.getText();
        String name = NameField.getText();
        String password = PasswordField.getText();
        String confirmPassword = ConfirmPasswordField.getText();
        String email = EmailField.getText();
        String phone = PhoneField.getText();
        String address = AddressField.getText();
        HashSet<String> usernames = ExtractFunction.getUsernames(SQLConnection.getConnection());
        if (!password.equals(confirmPassword)){
            Alert error = new Alert(Alert.AlertType.ERROR); 
            error.setContentText("Password Not Matching"); 
            error.showAndWait();
        }
        else if (usernames.contains(username)) {
            Alert error = new Alert(Alert.AlertType.ERROR); 
            error.setContentText("Username Taken"); 
            error.showAndWait();
        }
        else{
            InsertFunction.insertUser(SQLConnection.getConnection(), username, password, name, email, phone, address, "Customer");
            App.setRoot("MainMenuScene");
        }
    }
}
