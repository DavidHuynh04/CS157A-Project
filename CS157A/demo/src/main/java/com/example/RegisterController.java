package com.example;

import java.io.IOException;

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
    private void register(){
        String username = UsernameField.getText();
        String name = NameField.getText();
        String password = PasswordField.getText();
        String confirmPassword = ConfirmPasswordField.getText();
        String email = EmailField.getText();
        String phone = PhoneField.getText();
        String address = AddressField.getText();
        if (password != confirmPassword){
            Alert error = new Alert(Alert.AlertType.ERROR); 
            error.setContentText("Password Not Matching"); 
            error.showAndWait();
        }
    }
}
