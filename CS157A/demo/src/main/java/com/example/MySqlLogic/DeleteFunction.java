// David Huynh: Last Update 5/10
package com.example.MySqlLogic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// Class with functions to delete tuples from tables given the ID
public class DeleteFunction {
    // DELETE statement for users with the input of userID
    public static void deleteUser(Connection conn, int userID) throws SQLException{
        String sql = "DELETE FROM Users WHERE UserID = ?";
        try (PreparedStatement p_stmt = conn.prepareStatement(sql)) {
            p_stmt.setInt(1, userID); 
            p_stmt.executeUpdate();
            System.out.println("User deleted successfully.");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // DELETE statement for accounts with the input of accountID
    public static void deleteAccount(Connection conn, int accountID) throws SQLException{
        String sql = "DELETE FROM Accounts WHERE AccountID = ?";
        try (PreparedStatement p_stmt = conn.prepareStatement(sql)) {
            p_stmt.setInt(1, accountID); 
            p_stmt.executeUpdate();
            System.out.println("Account deleted successfully.");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // DELETE statement for transactions with the input of trasactionID
    public static void deleteTransaction(Connection conn, int transactionID) throws SQLException{
        String sql = "DELETE FROM Transactions WHERE TransactionID = ?";
        try (PreparedStatement p_stmt = conn.prepareStatement(sql)) {
            p_stmt.setInt(1, transactionID); 
            p_stmt.executeUpdate();
            System.out.println("Transaction deleted successfully.");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // DELETE statement for loans with the input of loanID
    public static void deleteLoan(Connection conn, int loanID) throws SQLException{
        String sql = "DELETE FROM Loans WHERE LoanID = ?";
        try (PreparedStatement p_stmt = conn.prepareStatement(sql)) {
            p_stmt.setInt(1, loanID); 
            p_stmt.executeUpdate();
            System.out.println("Loan deleted successfully.");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
