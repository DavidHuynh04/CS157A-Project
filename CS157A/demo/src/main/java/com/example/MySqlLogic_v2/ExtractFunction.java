
// JeremyLimKL : Last update 5/7
//package com.example.MySqlLogic;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

// ExtractFunction is used to extract useful data/rows of each individual main table (Users, Accounts, Transactions, Loans)
public class ExtractFunction {
    // Extract the User by username and password
    public static SessionInformation getUserByLogin(Connection conn, String userName, String password)
            throws SQLException {
        String sql = "SELECT * FROM Users WHERE UserName = ? AND Password = ?";
        try (PreparedStatement p_stmt = conn.prepareStatement(sql)) {
            p_stmt.setString(1, userName);
            p_stmt.setString(2, password);
            try (ResultSet rs = p_stmt.executeQuery()) {
                if (rs.next()) {
                    return new SessionInformation(
                            rs.getInt("UserID"),
                            rs.getString("Name"),
                            rs.getString("Email"),
                            rs.getString("Phone"),
                            rs.getString("Address"),
                            SessionInformation.Role.valueOf(rs.getString("Role")));
                }
            }
        }
        return null;
    }

    // Extract the Accounts belonging to one User
    public static ArrayList<AccountClass> getAccountsByUserID(Connection conn, int userID) throws SQLException {
        String sql = "SELECT * FROM Accounts WHERE UserID = ?";
        ArrayList<AccountClass> accounts = new ArrayList<>();
        try (PreparedStatement p_stmt = conn.prepareStatement(sql)) {
            p_stmt.setInt(1, userID);
            try (ResultSet rs = p_stmt.executeQuery()) {
                while (rs.next()) {
                    AccountClass account = new AccountClass(rs.getInt("AccountID"), rs.getInt("UserID"),
                            rs.getDouble("Balance"), rs.getString("AccountType"), rs.getDouble("InterestRate"),
                            rs.getDate("AccountCreationDate"));
                    accounts.add(account);
                }
            }
        }
        return accounts;
    }

    // Extract the Transactions belonging to one Account
    public static ArrayList<TransactionClass> getTransactionsByAccountID(Connection conn, int accountID)
            throws SQLException {
        String sql = "SELECT * FROM Transactions WHERE SenderAccountID = ? OR RecipientAccountID = ?";
        ArrayList<TransactionClass> transactions = new ArrayList<>();
        try (PreparedStatement p_stmt = conn.prepareStatement(sql)) {
            p_stmt.setInt(1, accountID);
            p_stmt.setInt(2, accountID);
            try (ResultSet rs = p_stmt.executeQuery()) {
                while (rs.next()) {
                    TransactionClass transaction = new TransactionClass(rs.getInt("TransactionID"),
                            rs.getInt("SenderAccountID"), rs.getInt("RecipientAccountID"),
                            rs.getDouble("TransactionAmount"), rs.getTimestamp("TransactionDate"));
                    transactions.add(transaction);
                }
            }
        }
        return transactions;
    }

    // Extract the Loans belonging to one Account
    public static ArrayList<LoanClass> getLoansByAccountID(Connection conn, int accountID) throws SQLException {
        String sql = "SELECT * FROM Loans WHERE AccountID = ?";
        ArrayList<LoanClass> loans = new ArrayList<>();
        try (PreparedStatement p_stmt = conn.prepareStatement(sql)) {
            p_stmt.setInt(1, accountID);
            try (ResultSet rs = p_stmt.executeQuery()) {
                while (rs.next()) {
                    LoanClass loan = new LoanClass(rs.getInt("LoanID"), rs.getInt("AccountID"),
                            rs.getDouble("LoanOriginal"), rs.getDouble("InterestRate"), rs.getDouble("LoanRemaining"),
                            rs.getDouble("LoanMonthly"), rs.getDate("LoanStartDate"), rs.getInt("LoanPeriod"));
                    loans.add(loan);
                }
            }
        }
        return loans;
    }
}