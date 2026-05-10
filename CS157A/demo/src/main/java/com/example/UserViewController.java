package com.example;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import com.example.MySqlLogic.ExtractFunction;
import com.example.MySqlLogic.SQLConnection;

import javafx.fxml.FXML;
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
    private ArrayList<AccountClass> accounts;
    
    private static SessionInformation currentSession;

    public void initialize()throws SQLException{
        currentSession = SessionInformation.getSession();
        setInfo();
        setAccountInfo();
    }
    @FXML
    public void setInfo(){
        WelcomeName.setText("Welcome: " + currentSession.getName());
        Email.setText("Email: " + currentSession.getEmail());
        Phone.setText("Phone: " + currentSession.getPhone());
        Address.setText("Address: " + currentSession.getAddress());
    }
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
            else if (temp.getAccountType().equals("Credit")){
                AccountClass.setCheckingAccount(temp);
                CreditBalance.setText("Balance: " + temp.getBalance());
                CreditInterest.setText("Interest Rate: " + temp.getInterestRate() + "%");
            }
        }
    }
    @FXML
    private void switchToMainMenu() throws IOException {
        SessionInformation.setSession(null);
        AccountClass.setCheckingAccount(null);
        AccountClass.setSavingsAccount(null);
        AccountClass.setCreditAccount(null);
        LoanClass.setCurrentLoan(null);
        App.setRoot("MainMenuScene");
    }
    @FXML
    private void AccountLoad1() throws IOException{
        AccountClass temp = AccountClass.getCheckingAccount();
        if (temp != null){
            AccountClass.setCurrentAccount(temp);
            App.setRoot("AccountInformation");
        }
    }
    @FXML
    private void AccountLoad2() throws IOException{
        AccountClass temp = AccountClass.getSavingsAccount();
        if (temp != null){
            AccountClass.setCurrentAccount(temp);
            App.setRoot("AccountInformation");
        }
    }
    @FXML
    private void AccountLoad3() throws IOException{
        AccountClass temp = AccountClass.getCreditAccount();
        if (temp != null){
            AccountClass.setCurrentAccount(temp);
            App.setRoot("AccountInformation");
        }
    }   
    @FXML
    private void updateUser(){

    }
    
}
