package com.example;

import java.io.IOException;

import javafx.fxml.FXML;

public class RegisterController {

    @FXML
    private void backButton() throws IOException {
        App.setRoot("MainMenuScene");
    }
}
