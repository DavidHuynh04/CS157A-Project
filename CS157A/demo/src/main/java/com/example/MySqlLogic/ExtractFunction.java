package com.example.MySqlLogic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// ExtractFunction is used to extract useful data/rows of each individual main table (Users, Accounts, Transactions, Loans)
public class ExtractFunction {
    // Extract the Accounts belonging to one User
    public static void getAccountsByUserID(Connection conn, int userID) throws SQLException {
        String sql = "SELECT * FROM Accounts WHERE UserID = ?";
        try (PreparedStatement p_stmt = conn.prepareStatement(sql)) {
            p_stmt.setInt(1, userID);
            try (ResultSet rs = p_stmt.executeQuery()) {
                System.out.println("--- Accounts for User ID " +userID+ " ---");
                while (rs.next()) {
                    System.out.println("AccountID: "+rs.getInt("AccountID")+
                            ",\n Type: "+rs.getString("AccountType")+
                            ",\n Balance: "+rs.getDouble("Balance"));
                }
            }
        }
    }

    // Extract the Transactions belonging to one Account
    public static void getTransactionsByAccountID(Connection conn, int accountID) throws SQLException {
        String sql = "SELECT * FROM Transactions WHERE SenderAccountID = ? OR RecipientAccountID = ?";
        try (PreparedStatement p_stmt = conn.prepareStatement(sql)) {
            p_stmt.setInt(1, accountID);
            p_stmt.setInt(2, accountID);
            try (ResultSet rs = p_stmt.executeQuery()) {
                System.out.println("--- Transactions for Account ID " +accountID+ "---");
                while (rs.next()) {
                    System.out.println("TransacID: " + rs.getInt("TransactionID")+
                            ",\n Sender: "+rs.getInt("SenderAccountID")+
                            ",\n Recipient: "+rs.getInt("RecipientAccountID")+
                            ",\n Account: "+rs.getDouble("TransactionAmount")+
                            ",\n Date: "+rs.getTimestamp("TransactionDate"));
                }
            }
        }
    }

    public static void getLoansByAccountID(Connection conn, int accountID) throws SQLException {
        String sql =  "SELECT * FROM Loans WHERE AccountID = ?";
        try (PreparedStatement p_stmt = conn.prepareStatement(sql)) {
            p_stmt.setInt(1, accountID);
            try (ResultSet rs = p_stmt.executeQuery()) {
                System.out.println("--- Loans for Account ID " + accountID + " ---");
                while (rs.next()) {
                    System.out.println("LoansID: "+rs.getInt("LoanID")+
                            "\n, Amount: "+rs.getDouble("LoanAmount")+
                            "\n, Interest: "+rs.getDouble("InterestRate")+
                            "\n, Start Date: "+rs.getDate("LoanStartDate"));
                }
            }
        }
    }
}