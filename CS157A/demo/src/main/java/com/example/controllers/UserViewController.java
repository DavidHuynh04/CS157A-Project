// David Huynh: Last Update 5/10
package com.example.controllers;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import com.example.AccountClass;
import com.example.App;
import com.example.LoanClass;
import com.example.SessionInformation;
import com.example.MySqlLogic.ExtractFunction;
import com.example.MySqlLogic.InsertFunction;
import com.example.MySqlLogic.SQLConnection;
import com.example.MySqlLogic.UpdateFunction;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
// FXML controller for the customer user view
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
    private TextField Email;
    @FXML 
    private TextField Phone;
    @FXML 
    private TextField Address;
    private ArrayList<AccountClass> accounts;
    
    private static SessionInformation currentSession;

    // On start get and set user information
    public void initialize()throws SQLException{
        currentSession = SessionInformation.getSession();
        setInfo();
        setAccountInfo();
    }
    // Sets user information
    @FXML
    public void setInfo(){
        WelcomeName.setText("Welcome: " + currentSession.getName());
        Email.setText(currentSession.getEmail());
        Phone.setText(currentSession.getPhone());
        Address.setText(currentSession.getAddress());
    }
    // Sets user account information by using SQL select account from userID
    public void setAccountInfo()throws SQLException{
        accounts = ExtractFunction.getAccountsByUserID(SQLConnection.getConnection(), currentSession.getUserID());
        AccountClass temp;
        for (int i = 0; i < accounts.size(); i++){
            temp = accounts.get(i);
            if (temp.getAccountType().equals("Checking")){
                AccountClass.setCheckingAccount(temp);
                CheckingBalance.setText("Balance: " + temp.getBalance());
                CheckingInterest.setText("Interest Rate: " + temp.getInterestRate() + "%");
            }
            else if (temp.getAccountType().equals("Savings")){
                AccountClass.setSavingsAccount(temp);
                SavingsBalance.setText("Balance: " + temp.getBalance());
                SavingsInterest.setText("Interest Rate: " + temp.getInterestRate() + "%");
            }
            else if (temp.getAccountType().equals("Credit Card")){
                AccountClass.setCreditAccount(temp);
                CreditBalance.setText("Balance: " + temp.getBalance());
                CreditInterest.setText("Interest Rate: " + temp.getInterestRate() + "%");
            }
        }
    }
    // Sets information related to user to null and returns to main menu
    @FXML
    private void switchToMainMenu() throws IOException {
        SessionInformation.setSession(null);
        AccountClass.setCheckingAccount(null);
        AccountClass.setSavingsAccount(null);
        AccountClass.setCreditAccount(null);
        LoanClass.setCurrentLoan(null);
        App.setRoot("MainMenuScene");
    }
    // Loads the Checking Account information of the user
    // If account is not found, inserts a new account of this type
    @FXML
    private void AccountLoad1() throws SQLException, IOException{
        AccountClass temp = AccountClass.getCheckingAccount();
        if (temp != null){
            AccountClass.setCurrentAccount(temp);
            App.setRoot("AccountInformation");
        }
        else{
            InsertFunction.insertAccount(SQLConnection.getConnection(), currentSession.getUserID(), 0, "Checking", 0.0, Date.valueOf(LocalDate.now()));
            setAccountInfo();
        }
    }
    // Loads the Savings Account information of the user;
    // If account is not found, inserts a new account of this type
    @FXML
    private void AccountLoad2() throws SQLException, IOException{
        AccountClass temp = AccountClass.getSavingsAccount();
        if (temp != null){
            AccountClass.setCurrentAccount(temp);
            App.setRoot("AccountInformation");
        }
        else{
            InsertFunction.insertAccount(SQLConnection.getConnection(), currentSession.getUserID(), 0, "Savings", 2.5, Date.valueOf(LocalDate.now()));
            setAccountInfo();
        }
    }
    // Loads the Credit Card Account information of the user;
    // If account is not found, inserts a new account of this type
    @FXML
    private void AccountLoad3() throws SQLException, IOException{
        AccountClass temp = AccountClass.getCreditAccount();
        if (temp != null){
            AccountClass.setCurrentAccount(temp);
            App.setRoot("AccountInformation");
        }
        else{
            InsertFunction.insertAccount(SQLConnection.getConnection(), currentSession.getUserID(), 0, "Credit Card", 18.0, Date.valueOf(LocalDate.now()));
            setAccountInfo();
        }
    }
    // Gets updated information from text fields and uses SQL update function
    @FXML
    private void updateUser() throws SQLException{
        UpdateFunction.updateUser(SQLConnection.getConnection(), currentSession.getUserID(), currentSession.getUsername(), 
        currentSession.getPassword(), currentSession.getName(), Email.getText(), Phone.getText(), Address.getText(), currentSession.getRole());
    }
    
}
