package com.example;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
public class UserViewController {
    @FXML 
    private Text WelcomeName;
    @FXML 
    private Text CheckingBalance;
    @FXML 
    private Text CheckingInterest;
    @FXML 
    private Text SavingsBalance;
    @FXML 
    private Text SavingsInterest;
    @FXML 
    private Text CreditBalance;
    @FXML 
    private Text CreditInterest;
    @FXML 
    private Label Email;
    @FXML 
    private Label Phone;
    @FXML 
    private Label Address;
    @FXML
    private Button LogOutButton;
    @FXML
    private Button UpdateButton;
    

    private static SessionInformation currentSession;

    public void initialize(){
        currentSession = SessionInformation.getSession();
        setInfo();
    }
    @FXML
    public void setInfo(){
        WelcomeName.setText("Welcome: " + currentSession.getName());
        Email.setText("Email: " + currentSession.getEmail());
        Phone.setText("Phone: " + currentSession.getPhone());
        Address.setText("Address: " + currentSession.getAddress());
    }
    public void setAccountInfo(){

    }
    @FXML
    private void switchToMainMenu() throws IOException {
        App.setRoot("MainMenuScene");
    }
    
}
