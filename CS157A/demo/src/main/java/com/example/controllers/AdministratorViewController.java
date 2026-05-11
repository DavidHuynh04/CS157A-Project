// David Huynh: Last Update 5/10
package com.example.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import com.example.AccountClass;
import com.example.App;
import com.example.LoanClass;
import com.example.MySqlLogic.DeleteFunction;
import com.example.MySqlLogic.ExtractFunction;
import com.example.MySqlLogic.SQLConnection;
import com.example.MySqlLogic.UpdateFunction;
import com.example.SessionInformation;
import com.example.TransactionClass;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

// FXML Controller for the Administrator view
public class AdministratorViewController {
    @FXML
    private ChoiceBox<String> SearchChoice;
    @FXML
    private TextField SearchBy;
    @FXML
    private TextField Equals;
    @FXML
    private TableView<Object> TableView;
    @FXML
    private ChoiceBox<String> DeleteChoice;
    @FXML
    private TextField DeleteID;

    private ArrayList<SessionInformation> userList;
    private ArrayList<AccountClass> accountList;
    private ArrayList<TransactionClass> transactionList;
    private ArrayList<LoanClass> loanList;


    // On start
    public void initialize() throws SQLException{
        setChoiceBoxes();
    }
    // Sets the options for the drop down boxes
    @FXML
    public void setChoiceBoxes(){
        SearchChoice.getItems().addAll("users", "accounts", "loans", "transactions");
        DeleteChoice.getItems().addAll("users", "accounts", "loans", "transactions");
    }
    // Based on the options selected, populates table with that search query
    // Temp1 is the table name, temp2, is the WHERE condition, where temp2 = temp3
    // First checks which table is selected, if the where condition is not filled, instead returns the full table 
    @FXML
    public void populateTable() throws SQLException{
        String temp1 = SearchChoice.getValue();
        String temp2 = SearchBy.getText();
        String temp3 = Equals.getText();
        if (temp1.equals("users")){
            if (temp2.equals("") || temp3.equals("")){
                userList = ExtractFunction.getFullUserDashboard(SQLConnection.getConnection());
            }
            else{
                userList = ExtractFunction.getUserDashboard(SQLConnection.getConnection(), temp2, temp3);
            }
            populateUserTable();
        }
        else if (temp1.equals("accounts")) {
            if (temp2.equals("") || temp3.equals("")){
                accountList = ExtractFunction.getFullAccountDashboard(SQLConnection.getConnection());
            }
            else{
                accountList = ExtractFunction.getAccountDashboard(SQLConnection.getConnection(), temp2, temp3);
            }
            populateAccountTable();
        }
        else if (temp1.equals("loans")) {
            if (temp2.equals("") || temp3.equals("")){
                loanList = ExtractFunction.getFullLoanDashboard(SQLConnection.getConnection());
            }
            else{
                loanList = ExtractFunction.getLoanDashboard(SQLConnection.getConnection(), temp2, temp3);
            }
            populateLoanTable();
        }
        else if (temp1.equals("transactions")) {
            if (temp2.equals("") || temp3.equals("")){
                transactionList = ExtractFunction.getFullTransactionDashboard(SQLConnection.getConnection());
            }
            else{
                transactionList = ExtractFunction.getTransactionDashboard(SQLConnection.getConnection(), temp2, temp3);
            }
            populateTransactionTable();
        }
    }
    // Populates the table with columns and values belonging to the user table
    @FXML
    public void populateUserTable(){
        TableView.getColumns().clear();
        TableColumn<Object, String> userIDColumn = new TableColumn<>("UserID");
        userIDColumn.setCellValueFactory(c-> new SimpleStringProperty(String.valueOf(((SessionInformation) c.getValue()).getUserID())));
        TableColumn<Object, String> userNameColumn = new TableColumn<>("UserName");
        userNameColumn.setCellValueFactory(c-> new SimpleStringProperty(((SessionInformation) c.getValue()).getUsername()));
        TableColumn<Object, String> passwordColumn = new TableColumn<>("Password");
        passwordColumn.setCellValueFactory(c-> new SimpleStringProperty(((SessionInformation) c.getValue()).getPassword()));
        TableColumn<Object, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(c-> new SimpleStringProperty(((SessionInformation) c.getValue()).getName()));
        TableColumn<Object, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(c-> new SimpleStringProperty(((SessionInformation) c.getValue()).getEmail()));
        TableColumn<Object, String> phoneColumn = new TableColumn<>("Phone");
        phoneColumn.setCellValueFactory(c-> new SimpleStringProperty(((SessionInformation) c.getValue()).getPhone()));
        TableColumn<Object, String> addressColumn = new TableColumn<>("Address");
        addressColumn.setCellValueFactory(c-> new SimpleStringProperty(((SessionInformation) c.getValue()).getAddress()));
        TableColumn<Object, String> roleColumn = new TableColumn<>("Role");
        roleColumn.setCellValueFactory(c-> new SimpleStringProperty(((SessionInformation) c.getValue()).getRole()));
        TableView.getColumns().addAll(userIDColumn,userNameColumn,passwordColumn,nameColumn,emailColumn,phoneColumn,addressColumn,roleColumn);
        TableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        TableView.setItems(FXCollections.observableArrayList(userList));
    }
    // Populates the table with columns and values belonging to the account table
    @FXML
    public void populateAccountTable(){
        TableView.getColumns().clear();
        TableColumn<Object, String> accountIDColumn = new TableColumn<>("AccountID");
        accountIDColumn.setCellValueFactory(c-> new SimpleStringProperty(String.valueOf(((AccountClass) c.getValue()).getAccountID())));
        TableColumn<Object, String> userIDColumn = new TableColumn<>("UserID");
        userIDColumn.setCellValueFactory(c-> new SimpleStringProperty(String.valueOf(((AccountClass) c.getValue()).getUserID())));
        TableColumn<Object, String> balanceColumn = new TableColumn<>("Balance");
        balanceColumn.setCellValueFactory(c-> new SimpleStringProperty(String.valueOf(((AccountClass) c.getValue()).getBalance())));
        TableColumn<Object, String> accountTypeColumn = new TableColumn<>("AccountType");
        accountTypeColumn.setCellValueFactory(c-> new SimpleStringProperty(((AccountClass) c.getValue()).getAccountType()));
        TableColumn<Object, String> interestColumn = new TableColumn<>("InterestRate");
        interestColumn.setCellValueFactory(c-> new SimpleStringProperty(String.valueOf(((AccountClass) c.getValue()).getInterestRate())));
        TableColumn<Object, String> dateColumn = new TableColumn<>("AccountCreationDate");
        dateColumn.setCellValueFactory(c-> new SimpleStringProperty(String.valueOf(((AccountClass) c.getValue()).getAccountCreationDate())));
        TableView.getColumns().addAll(accountIDColumn, userIDColumn, balanceColumn, accountTypeColumn, interestColumn, dateColumn);
        TableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        TableView.setItems(FXCollections.observableArrayList(accountList));     
    }
    // Populates the table with columns and values belonging to the transaction table
    @FXML
    public void populateTransactionTable(){
        TableView.getColumns().clear();
        TableColumn<Object, String> transactionIDColumn = new TableColumn<>("TransactionID");
        transactionIDColumn.setCellValueFactory(c-> new SimpleStringProperty(String.valueOf(((TransactionClass) c.getValue()).getTransactionID())));
        TableColumn<Object, String> senderColumn = new TableColumn<>("SenderAccountID");
        senderColumn.setCellValueFactory(c-> new SimpleStringProperty(String.valueOf(((TransactionClass) c.getValue()).getSenderID())));         
        TableColumn<Object, String> recipientColumn = new TableColumn<>("RecipientAccountID");
        recipientColumn.setCellValueFactory(c-> new SimpleStringProperty(String.valueOf(((TransactionClass) c.getValue()).getRecipientID())));
        TableColumn<Object, String> amountColumn = new TableColumn<>("TransactionAmount");
        amountColumn.setCellValueFactory(c-> new SimpleStringProperty(String.valueOf(((TransactionClass) c.getValue()).getTransactionAmount())));
        TableColumn<Object, String> dateColumn = new TableColumn<>("TransactionDate");
        dateColumn.setCellValueFactory(c-> new SimpleStringProperty(String.valueOf(((TransactionClass) c.getValue()).getTransactionDate())));
        TableView.getColumns().addAll(transactionIDColumn, senderColumn, recipientColumn,amountColumn, dateColumn);
        TableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        TableView.setItems(FXCollections.observableArrayList(transactionList));     
    }
    // Populates the table with columns and values belonging to the loan table
    @FXML
    public void populateLoanTable(){
        TableView.getColumns().clear();
        TableColumn<Object, String> loanIDColumn = new TableColumn<>("LoanID");
        loanIDColumn.setCellValueFactory(c-> new SimpleStringProperty(String.valueOf(((LoanClass) c.getValue()).getLoanID())));
        TableColumn<Object, String> accountColumn = new TableColumn<>("AccountID");
        accountColumn.setCellValueFactory(c-> new SimpleStringProperty(String.valueOf(((LoanClass) c.getValue()).getAccountID())));
        TableColumn<Object, String> originalColumn = new TableColumn<>("LoanOriginal");
        originalColumn.setCellValueFactory(c-> new SimpleStringProperty(String.valueOf(((LoanClass) c.getValue()).getLoanOriginal())));
        TableColumn<Object, String> interestColumn = new TableColumn<>("InterestRate");
        interestColumn.setCellValueFactory(c-> new SimpleStringProperty(String.valueOf(((LoanClass) c.getValue()).getInterestRate())));
        TableColumn<Object, String> loanRemainingColumn = new TableColumn<>("LoanRemaining");
        loanRemainingColumn.setCellValueFactory(c-> new SimpleStringProperty(String.valueOf(((LoanClass) c.getValue()).getLoanRemaining())));
        TableColumn<Object, String> loanMonthlyColumn = new TableColumn<>("LoanMonthly");
        loanMonthlyColumn.setCellValueFactory(c-> new SimpleStringProperty(String.valueOf(((LoanClass) c.getValue()).getLoanMonthly())));
        TableColumn<Object, String> dateColumn = new TableColumn<>("LoanStartDate");
        dateColumn.setCellValueFactory(c-> new SimpleStringProperty(String.valueOf(((LoanClass) c.getValue()).getLoanStartDate())));
        TableColumn<Object, String> periodColumn = new TableColumn<>("LoanPeriod");
        periodColumn.setCellValueFactory(c-> new SimpleStringProperty(String.valueOf(((LoanClass) c.getValue()).getLoanPeriod())));
        TableView.getColumns().addAll(loanIDColumn, accountColumn, originalColumn, interestColumn, loanRemainingColumn, loanMonthlyColumn, dateColumn, periodColumn);
        TableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        TableView.setItems(FXCollections.observableArrayList(loanList));     
    }
    // Gets the table and ID inputted, and uses a Delete Statement to remove the tuple
    @FXML
    public void deleteTuple() throws SQLException{
        String temp1 = DeleteChoice.getValue();
        if (!DeleteID.getText().equals("")){
            int temp2 = Integer.parseInt(DeleteID.getText());
            if (temp1.equals("users")){
                DeleteFunction.deleteUser(SQLConnection.getConnection(), temp2);
            }
            else if (temp1.equals("accounts")) {
                DeleteFunction.deleteAccount(SQLConnection.getConnection(), temp2);
            }
            else if (temp1.equals("loans")) {
                DeleteFunction.deleteLoan(SQLConnection.getConnection(), temp2);
            }
            else if (temp1.equals("transactions")) {
                DeleteFunction.deleteTransaction(SQLConnection.getConnection(), temp2);
            }
        }
    }
    // Applies monthly loan payments with an update function

    @FXML
    public void applyMonthly() throws SQLException{
        UpdateFunction.updateLoanMonthly(SQLConnection.getConnection());
    }
    // Returns to the main menu scene
    @FXML
    private void switchToMainMenu() throws IOException {
        App.setRoot("MainMenuScene");
    }
}
