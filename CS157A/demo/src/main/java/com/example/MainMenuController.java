package com.example;

import java.io.IOException;

import javafx.fxml.FXML;

public class MainMenuController {

    @FXML
    private void switchToLogin() throws IOException {
        App.setRoot("Login");
    }
    @FXML
    private void switchToRegister() throws IOException {
        App.setRoot("Register");
    }
}
