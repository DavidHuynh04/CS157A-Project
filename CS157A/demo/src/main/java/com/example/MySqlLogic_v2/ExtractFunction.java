
// JeremyLimKL : Last update 5/9
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

    // Converts the object input into the correct varriable type based on the table
    // & column name
    public static Object convertInput(String tableName, String columnName, String input) {
        switch (tableName) {
            case "Users":
                switch (columnName) {
                    case "UserID":
                        return Integer.parseInt(input);
                    default:
                        return input;
                }
            case "Accounts":
                switch (columnName) {
                    case "AccountID":
                    case "UserID":
                        return Integer.parseInt(input);
                    case "Balance":
                    case "InterestRate":
                        return Double.parseDouble(input);
                    case "AccountCreationDate":
                        return java.sql.Date.valueOf(input);
                    default:
                        return input.toString();
                }
            case "Transactions":
                switch (columnName) {
                    case "TransactionID":
                    case "SenderAccountID":
                    case "RecipientAccountID":
                        return Integer.parseInt(input);
                    case "TransactionAmount":
                        return Double.parseDouble(input);
                    case "TransactionDate":
                        return java.sql.Timestamp.valueOf(input);
                    default:
                        return input;
                }
            case "Loans":
                switch (columnName) {
                    case "LoanID":
                    case "AccountID":
                        return Integer.parseInt(input);
                    case "LoanOriginal":
                    case "InterestRate":
                    case "LoanRemaining":
                    case "LoanMonthly":
                        return Double.parseDouble(input);
                    case "LoanStartDate":
                        return java.sql.Date.valueOf(input);
                    case "LoanPeriod":
                        return Integer.parseInt(input);
                    default:
                        return input;
                }
        }
        return null;
    }

    // Extract any rows from Users table
    public static ArrayList<SessionInformation> getUserDashboard(Connection conn, String columnName, String input)
            throws SQLException {
        String sql = "SELECT * FROM Users WHERE " + columnName + " = ? ";
        ArrayList<SessionInformation> rows = new ArrayList<>();
        try (PreparedStatement p_stmt = conn.prepareStatement(sql)) {
            p_stmt.setObject(1, convertInput("Users", columnName, input));
            try (ResultSet rs = p_stmt.executeQuery()) {
                while (rs.next()) {
                    SessionInformation sessionInformation = new SessionInformation(rs.getInt("UserID"),
                            rs.getString("UserName"),
                            rs.getString("Password"), rs.getString("Name"), rs.getString("Email"),
                            rs.getString("Phone"), rs.getString("Address"), rs.getString("Role"));
                    rows.add(sessionInformation);
                }
            }
        }
        return rows;
    }

    // Extract any rows from Account table
    public static ArrayList<AccountClass> getAccountDashboard(Connection conn, String columnName, String input)
            throws SQLException {
        String sql = "SELECT * FROM Accounts WHERE " + columnName + " = ? ";
        ArrayList<AccountClass> rows = new ArrayList<>();
        try (PreparedStatement p_stmt = conn.prepareStatement(sql)) {
            p_stmt.setObject(1, convertInput("Accounts", columnName, input));
            try (ResultSet rs = p_stmt.executeQuery()) {
                while (rs.next()) {
                    AccountClass account = new AccountClass(rs.getInt("AccountID"), rs.getInt("UserID"),
                            rs.getDouble("Balance"), rs.getString("AccountType"), rs.getDouble("InterestRate"),
                            rs.getDate("AccountCreationDate"));
                    rows.add(account);
                }
            }
        }
        return rows;
    }

    // Extract any rows from Transactions table
    public static ArrayList<TransactionClass> getTransactionDashboard(Connection conn, String columnName, String input)
            throws SQLException {
        String sql = "SELECT * FROM Transactions WHERE " + columnName + " = ? ";
        ArrayList<TransactionClass> rows = new ArrayList<>();
        try (PreparedStatement p_stmt = conn.prepareStatement(sql)) {
            p_stmt.setObject(1, convertInput("Transactions", columnName, input));
            try (ResultSet rs = p_stmt.executeQuery()) {
                while (rs.next()) {
                    TransactionClass transaction = new TransactionClass(rs.getInt("TransactionID"),
                            rs.getInt("SenderAccountID"),
                            rs.getInt("RecipientAccountID"), rs.getDouble("TransactionAmount"),
                            rs.getTimestamp("TransactionDate"));
                    rows.add(transaction);
                }
            }
        }
        return rows;
    }

    // Extract any rows from Loans table
    public static ArrayList<LoanClass> getLoanDashboard(Connection conn, String columnName, String input)
            throws SQLException {
        String sql = "SELECT * FROM Loans WHERE " + columnName + " = ? ";
        ArrayList<LoanClass> rows = new ArrayList<>();
        try (PreparedStatement p_stmt = conn.prepareStatement(sql)) {
            p_stmt.setObject(1, convertInput("Loans", columnName, input));
            try (ResultSet rs = p_stmt.executeQuery()) {
                while (rs.next()) {
                    LoanClass loan = new LoanClass(rs.getInt("LoanID"), rs.getInt("AccountID"),
                            rs.getDouble("LoanOriginal"), rs.getDouble("InterestRate"), rs.getDouble("LoanRemaining"),
                            rs.getDouble("LoanMonthly"), rs.getDate("LoanStartDate"), rs.getInt("LoanPeriod"));
                    rows.add(loan);
                }
            }
        }
        return rows;
    }
}