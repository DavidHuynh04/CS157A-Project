// David Huynh: Last Update 5/10
package com.example.controllers;

import java.io.IOException;

import com.example.App;

import javafx.fxml.FXML;

// FXML Controller for the main menu page
public class MainMenuController {

    // Changes the page to the login page
    @FXML
    private void switchToLogin() throws IOException {
        App.setRoot("Login");
    }
    // Changes the page to the register page
    @FXML
    private void switchToRegister() throws IOException {
        App.setRoot("Register");
    }
}
