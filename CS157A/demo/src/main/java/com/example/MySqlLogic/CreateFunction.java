package com.example.MySqlLogic;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

// CreateFunction is used to create/clear the 4 main tables (Users, Accounts, Transactions, Loans) as an empty table.
public class CreateFunction {
    // MySQL code for creating tables as a string
    static String createUsersTable = "CREATE TABLE IF NOT EXISTS Users (" +
            "UserID INT AUTO_INCREMENT PRIMARY KEY, " +
            "UserName VARCHAR(255) UNIQUE NOT NULL," +
            "Password VARCHAR(255) NOT NULL, " +
            "Name VARCHAR(100) NOT NULL, " +
            "Email VARCHAR(100) UNIQUE NOT NULL, " +
            "Phone VARCHAR(20), " +
            "Address VARCHAR(255), " +
            "Role ENUM('Customer', 'Admin') NOT NULL)";
    static String createAccountsTable = "CREATE TABLE IF NOT EXISTS Accounts (" +
            "AccountID INT AUTO_INCREMENT PRIMARY KEY, " +
            "UserID INT, " +
            "Balance DECIMAL(15, 2) DEFAULT 0.00, " +
            "AccountType VARCHAR(50), " +
            "InterestRate DECIMAL(5, 2), " +
            "AccountCreationDate DATE, " +
            "FOREIGN KEY (UserID) REFERENCES Users(UserID) ON DELETE CASCADE)";
    static String createTransactionsTable = "CREATE TABLE IF NOT EXISTS Transactions (" +
            "TransactionID INT AUTO_INCREMENT PRIMARY KEY, " +
            "SenderAccountID INT, " +
            "RecipientAccountID INT, " +
            "TransactionDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
            "TransactionAmount DECIMAL(15, 2) NOT NULL, " +
            "FOREIGN KEY (SenderAccountID) REFERENCES Accounts(AccountID) ON DELETE SET NULL, " +
            "FOREIGN KEY (RecipientAccountID) REFERENCES Accounts(AccountID) ON DELETE SET NULL)";
    static String createLoansTable = "CREATE TABLE IF NOT EXISTS Loans (" +
            "LoanID INT AUTO_INCREMENT PRIMARY KEY, " +
            "AccountID INT, " +
            "LoanAmount DECIMAL(15, 2) NOT NULL, " +
            "InterestRate DECIMAL(5, 2), " +
            "LoanStartDate DATE, " +
            "LoanPeriod INT, " + // in months
            "FOREIGN KEY (AccountID) REFERENCES Accounts(AccountID) ON DELETE CASCADE)";


    // Allows the creation/clearance of Empty Table for all tables
    public static void createEmptyTable(Connection conn) throws SQLException{
        try (Statement stmt = conn.createStatement()) {
            // Ensures Tables can be cleared or deleted without foreign key interference.
            stmt.execute("SET FOREIGN_KEY_CHECKS = 0");
            // If table exist, it is cleared. If table not exist, it is created
            stmt.execute(createUsersTable);
            stmt.execute(createAccountsTable);
            stmt.execute(createTransactionsTable);
            stmt.execute(createLoansTable);
            // Ensures foreign keys are able to interfere again
            stmt.execute("SET FOREIGN_KEY_CHECKS = 1");
        }
    }
}