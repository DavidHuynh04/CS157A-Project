// David Huynh: Last Update 5/10
package com.example.controllers;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;

import com.example.AccountClass;
import com.example.App;
import com.example.LoanClass;
import com.example.MySqlLogic.DeleteFunction;
import com.example.MySqlLogic.ExtractFunction;
import com.example.MySqlLogic.InsertFunction;
import com.example.MySqlLogic.SQLConnection;
import com.example.TransactionClass;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

// FXML Controller for the account view
public class AccountController {
    @FXML
    private Label AccountField;
    @FXML
    private Text BalanceField;   
    @FXML
    private Text LoanAmountField;
    @FXML
    private Text InterestField;
    @FXML
    private Text PeriodField;
    @FXML
    private Text LoanStartField;
    @FXML
    private Text MonthlyField;
    @FXML
    private Text RemainingField;
    @FXML
    private TextField TransactionRecipient;
    @FXML
    private TextField TransactionAmount;    
    @FXML
    private TableView<TransactionClass> TransactionTable;
    @FXML
    private TableColumn<TransactionClass, Integer> SenderColumn;
    @FXML
    private TableColumn<TransactionClass, Integer> RecipientColumn;
    @FXML
    private TableColumn<TransactionClass, Double> AmountColumn;
    @FXML
    private TableColumn<TransactionClass, Timestamp> DateColumn;


    private static AccountClass currentAccount;
    private ArrayList<LoanClass> loans;
    private ArrayList<TransactionClass> transactions;

    // Gets the information of the current account and sets the information
    public void initialize() throws SQLException{
        currentAccount = AccountClass.getCurrentAccount();
        setInfo();
        setLoanInfo();
        populateTransactions();
    }
    // Sets the Account details
    @FXML
    public void setInfo(){
        AccountField.setText("Account Type: " + currentAccount.getAccountType());
        BalanceField.setText("Balance: " + currentAccount.getBalance());
    }
    // Sets the loan information if the account has a loan through a SELECT query
    @FXML
    public void setLoanInfo() throws SQLException{
        loans = ExtractFunction.getLoansByAccountID(SQLConnection.getConnection(), currentAccount.getAccountID());
        if (loans.size() >= 1){
            LoanClass temp = loans.get(0);
            LoanAmountField.setText("Loan Amount: " + temp.getLoanOriginal());
            InterestField.setText("Interest Rate: " + temp.getInterestRate());
            PeriodField.setText("Loan Perod: " + temp.getLoanPeriod());
            LoanStartField.setText("Loan Start Date " + temp.getLoanStartDate());
            MonthlyField.setText("Monthly Loan: " + temp.getLoanMonthly());
            RemainingField.setText("Remaining Loan: " + temp.getLoanRemaining());
        }
    }
    // Uses a SELECT query to find all transactions with the current account ID
    // Sets the table columns equal to values found in the transaction class
    @FXML
    public void populateTransactions() throws SQLException{
        transactions = ExtractFunction.getTransactionsByAccountID(SQLConnection.getConnection(), currentAccount.getAccountID());
        SenderColumn.setCellValueFactory(new PropertyValueFactory<>("senderID"));
        RecipientColumn.setCellValueFactory(new PropertyValueFactory<>("recipientID"));
        AmountColumn.setCellValueFactory(new PropertyValueFactory<>("transactionAmount"));
        DateColumn.setCellValueFactory(new PropertyValueFactory<>("transactionDate"));
        TransactionTable.getItems().setAll(transactions);
    }
    // If an account is not linked to a loan, inserts a default loan
    @FXML
    public void addLoan() throws SQLException{
        if (loans.isEmpty()){
            InsertFunction.insertLoan(SQLConnection.getConnection(), currentAccount.getAccountID(), 5000, 8.0, Date.valueOf(LocalDate.now()), 12);
            setLoanInfo();
            Alert success = new Alert(Alert.AlertType.CONFIRMATION); 
            success.setContentText("Default Loan Applied"); 
            success.showAndWait();
        }
        else{
            Alert error = new Alert(Alert.AlertType.ERROR); 
            error.setContentText("Account Already Has Loan"); 
            error.showAndWait();
        }
    }
    // Takes input of account to send to and amount to send, and uses an INSERT statement
    // The insertFunction also changes the balances of the account
    @FXML
    public void addTransaction() throws SQLException{
        int recipient = Integer.parseInt(TransactionRecipient.getText());
        double amount = Double.parseDouble(TransactionAmount.getText());
        InsertFunction.insertTransaction(SQLConnection.getConnection(), currentAccount.getAccountID(), recipient, amount);
        populateTransactions();
    }
    // Takes the ID of the selected transaction and uses a DELETE statement to delete it
    @FXML
    public void deleteTransaction() throws SQLException{
        if (!TransactionTable.getSelectionModel().isEmpty()){
            TransactionClass selected = TransactionTable.getSelectionModel().getSelectedItem();
            DeleteFunction.deleteTransaction(SQLConnection.getConnection(), selected.getTransactionID());
            populateTransactions();
        }
    }
    // Returns to the user view
    @FXML
    private void goBack() throws IOException{
        App.setRoot("UserView");
    }
}
