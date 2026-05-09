
// JeremyLimKL : Last update 5/6
package com.example.MySqlLogic_v2;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// InsertFunction is used to insert new data/rows into the 4 main tables (Users, Accounts, Transactions, Loans)
public class InsertFunction {
    // Insert a user into Users table
    public static void insertUser(Connection conn, String username, String password, String name, String email,
            String phone, String address, String role) throws SQLException {
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

    // Insert an account into Accounts table
    public static void insertAccount(Connection conn, int userID, double balance, String accountType,
            double interestRate, Date creationDate) throws SQLException {
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

    // Insert a loan into Loans table
    public static void insertLoan(Connection conn, int accountId, double loanOriginal, double interestRate,
            Date startDate, int loanPeriod) throws SQLException {
        String sql = "INSERT INTO Loans (AccountID, LoanOriginal, InterestRate, LoanRemaining, LoanMonthly, LoanStartDate, LoanPeriod) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement p_stmt = conn.prepareStatement(sql)) {
            p_stmt.setInt(1, accountId);
            p_stmt.setDouble(2, loanOriginal);
            p_stmt.setDouble(3, interestRate);
            p_stmt.setDouble(4, loanOriginal);
            p_stmt.setDouble(5, (loanOriginal * (1 + (interestRate / 100)) / loanPeriod));
            p_stmt.setDate(6, startDate);
            p_stmt.setInt(7, loanPeriod);
            p_stmt.executeUpdate();
            System.out.println("Loan inserted successfully.");
        }
    }

    // Insert a transaction into Transactions table and auto deduct from sender
    // balance and add to recipient balance
    public static void insertTransaction(Connection conn, int senderAccountID, int recipientAccountID, double amount)
            throws SQLException {
        try {
            conn.setAutoCommit(false);

            String sql = "INSERT INTO Transactions (SenderAccountID, RecipientAccountID, TransactionAmount) VALUES (?, ?, ?)";
            try (PreparedStatement p_stmt = conn.prepareStatement(sql)) {
                p_stmt.setInt(1, senderAccountID);
                p_stmt.setInt(2, recipientAccountID);
                p_stmt.setDouble(3, amount);
                p_stmt.executeUpdate();
                System.out.println("Transaction inserted successfully.");
            }

            UpdateFunction.updateAccountAddBalance(conn, senderAccountID, (amount * -1));
            UpdateFunction.updateAccountAddBalance(conn, recipientAccountID, amount);

            conn.commit();
        } catch (SQLException e) {
            System.out.println("Error occurred during transaction. Rolling back changes.");
            try {
                conn.rollback();
            } catch (SQLException rollbackEx) {
                System.err.println("Failed to rollback transaction: " + rollbackEx.getMessage());
            }
            throw e;
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                System.err.println("Failed to reset auto-commit: " + e.getMessage());
            }
        }
    }
}
