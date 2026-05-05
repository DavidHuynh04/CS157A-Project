package com.example.MySqlLogic;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// InsertFunction is used to insert new data/rows into the 4 main tables (Users, Accounts, Transactions, Loans)
public class InsertFunction {
    public static void insertUser(Connection conn, String username, String password, String name, String email, String phone, String address, String role) throws SQLException {
        String sql = "INSERT INTO Users (UserName, Password, Name, Email, Phone, Address, Role) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement p_stmt = conn.prepareStatement(sql)) {
            p_stmt.setString(1, username);
            p_stmt.setString(2, password);
            p_stmt.setString(3, name);
            p_stmt.setString(4, email);
            p_stmt.setString(5, phone);
            p_stmt.setString(6, address);
            p_stmt.setString(7, role);
            p_stmt.executeUpdate();
            System.out.println("User inserted successfully.");
        }
    }

    public static void insertAccount(Connection conn, int userID, double balance, String accountType, double interestRate, Date creationDate) throws SQLException{
        String sql = "INSERT INTO Accounts (UserID, Balance, AccountType, InterestRate, AccountCreationDate) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement p_stmt = conn.prepareStatement(sql)) {
            p_stmt.setInt(1, userID);
            p_stmt.setDouble(2, balance);
            p_stmt.setString(3, accountType);
            p_stmt.setDouble(4, interestRate);
            p_stmt.setDate(5, creationDate);
            p_stmt.executeUpdate();
            System.out.println("Account inserted successfully.");
        }
    }

    public static void insertTransaction(Connection conn, int senderAccountID, int recipientAccountID, double amount) throws SQLException{
        String sql = "INSERT INTO Transactions (SenderAccountID, RecipientAccountID, TransactionAmount) VALUES (?, ?, ?)";
        try (PreparedStatement p_stmt = conn.prepareStatement(sql)) {
            p_stmt.setInt(1, senderAccountID);
            p_stmt.setInt(2, recipientAccountID);
            p_stmt.setDouble(3, amount);
            p_stmt.executeUpdate();
            System.out.println("Transaction inserted successfully.");
        }
    }

    public static void insertLoan(Connection conn, int accountId, double loanAmount, double interestRate, Date startDate, int loanPeriod) throws SQLException{
        String sql = "INSERT INTO Loans (AccountID, LoanAmount, InterestRate, LoanStartDate, LoanPeriod) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement p_stmt = conn.prepareStatement(sql)) {
            p_stmt.setInt(1, accountId);
            p_stmt.setDouble(2, loanAmount);
            p_stmt.setDouble(3, interestRate);
            p_stmt.setDate(4, startDate);
            p_stmt.setInt(5, loanPeriod);
            p_stmt.executeUpdate();
            System.out.println("Loan inserted successfully.");
        }
    }

    public static void updateUser(Connection conn, int userID, String username, String password, String name, String email, String phone, String address, String role) throws SQLException {
        String sql = "UPDATE Users SET UserName = ?, Password = ?, Name = ?, Email = ?, Phone = ?, Address = ?, Role = ? WHERE UserID = ?";
        try (PreparedStatement p_stmt = conn.prepareStatement(sql)) {
            p_stmt.setString(1, username);
            p_stmt.setString(2, password);
            p_stmt.setString(3, name);
            p_stmt.setString(4, email);
            p_stmt.setString(5, phone);
            p_stmt.setString(6, address);
            p_stmt.setString(7, role);
            p_stmt.setInt(8,userID);
            int rows = p_stmt.executeUpdate();
            System.out.println((rows > 0)? "User updated successfully." : "No User found with this userID");
        }
    }

    public static void updateAccount(Connection conn, int userID, int accountID, double balance, String accountType, double interestRate) throws SQLException{
        String sql = "UPDATE Accounts SET Balance =?, AccountType =?, InterestRate =? WHERE UserID = ? AND AccountID = ?";
        try (PreparedStatement p_stmt = conn.prepareStatement(sql)) {
            p_stmt.setDouble(1, balance);
            p_stmt.setString(2, accountType);
            p_stmt.setDouble(3, interestRate);
            p_stmt.setInt(4, userID);
            p_stmt.setInt(5, accountID);
            int rows = p_stmt.executeUpdate();
            System.out.println((rows>0)? "Account updated successfully." : "No Account found with those IDs");
        }
    }

    public static void updateTransaction(Connection conn, int transactionID, int senderAccountID, int recipientAccountID, double amount) throws SQLException{
        String sql = "UPDATE Transactions SET SenderAccountID =?, RecipientAccountID =?, TransactionAmount =? WHERE TransactionID = ?";
        try (PreparedStatement p_stmt = conn.prepareStatement(sql)) {
            p_stmt.setInt(1, senderAccountID);
            p_stmt.setInt(2, recipientAccountID);
            p_stmt.setDouble(3, amount);
            p_stmt.setInt(4, transactionID);
            int rows = p_stmt.executeUpdate();
            System.out.println((rows > 0)? "Transaction inserted successfully." : "No Transaction found with this transactionID");
        }
    }

    public static void  updateLoan(Connection conn, int loanID, int accountId, double loanAmount, double interestRate, int loanPeriod) throws SQLException{
        String sql = "UPDATE Loans SET AccountID =?, LoanAmount =?, InterestRate =?, LoanPeriod =? WHERE loanID = ?";
        try (PreparedStatement p_stmt = conn.prepareStatement(sql)) {
            p_stmt.setInt(1, accountId);
            p_stmt.setDouble(2, loanAmount);
            p_stmt.setDouble(3, interestRate);
            p_stmt.setInt(5, loanPeriod);
            p_stmt.setInt(6, loanID);
            int rows = p_stmt.executeUpdate();
            System.out.println((rows > 0)? "Loan inserted successfully." : "No Loan found with this loanID");
        }
    }
}