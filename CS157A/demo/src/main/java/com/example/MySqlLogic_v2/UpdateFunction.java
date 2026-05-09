package com.example.MySqlLogic_v2;
// JeremyLimKL : Last update 5/6
//package com.example.MySqlLogic;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// UpdateFunction is used to edit/update existing data/rows in the 4 main tables (Users, Accounts, Transactions, Loans) 
// and to update the monthly deduction of loans in Accounts table and Loans table.
public class UpdateFunction {
    // Update a user using UserID inUsers table
    public static void updateUser(Connection conn, int userID, String username, String password, String name,
            String email, String phone, String address, String role) throws SQLException {
        String sql = "UPDATE Users SET UserName = ?, Password = ?, Name = ?, Email = ?, Phone = ?, Address = ?, Role = ? WHERE UserID = ?";
        try (PreparedStatement p_stmt = conn.prepareStatement(sql)) {
            p_stmt.setString(1, username);
            p_stmt.setString(2, password);
            p_stmt.setString(3, name);
            p_stmt.setString(4, email);
            p_stmt.setString(5, phone);
            p_stmt.setString(6, address);
            p_stmt.setString(7, role);
            p_stmt.setInt(8, userID);
            int rows = p_stmt.executeUpdate();
            System.out.println((rows > 0) ? "User updated successfully." : "No User found with this userID");
        }
    }

    // Update an Account using UserID and AccountID in Accounts table
    public static void updateAccount(Connection conn, int userID, int accountID, double balance, String accountType,
            double interestRate) throws SQLException {
        String sql = "UPDATE Accounts SET Balance =?, AccountType =?, InterestRate =? WHERE UserID = ? AND AccountID = ?";
        try (PreparedStatement p_stmt = conn.prepareStatement(sql)) {
            p_stmt.setDouble(1, balance);
            p_stmt.setString(2, accountType);
            p_stmt.setDouble(3, interestRate);
            p_stmt.setInt(4, userID);
            p_stmt.setInt(5, accountID);
            int rows = p_stmt.executeUpdate();
            System.out.println((rows > 0) ? "Account updated successfully." : "No Account found with those IDs");
        }
    }

    // Update the balance of an Account by adding input balance using AccountID in
    // Accounts table
    public static void updateAccountAddBalance(Connection conn, int accountID, double balance) throws SQLException {
        String sql = "UPDATE Accounts SET Balance = Balance + ? WHERE  AccountID = ?";
        try (PreparedStatement p_stmt = conn.prepareStatement(sql)) {
            p_stmt.setDouble(1, balance);
            p_stmt.setInt(2, accountID);
            int rows = p_stmt.executeUpdate();
            System.out.println((rows > 0) ? "Account updated successfully." : "No Account found with those IDs");
        }
    }

    // Update a loan using loanID in Loans table
    public static void updateLoan(Connection conn, int loanID, int accountID, double loanAmount, double interestRate,
            int loanPeriod) throws SQLException {
        String sql = "UPDATE Loans SET AccountID =?, LoanOriginal =?, InterestRate =?, LoanPeriod =? WHERE loanID = ?";
        try (PreparedStatement p_stmt = conn.prepareStatement(sql)) {
            p_stmt.setInt(1, accountID);
            p_stmt.setDouble(2, loanAmount);
            p_stmt.setDouble(3, interestRate);
            p_stmt.setInt(4, loanPeriod);
            p_stmt.setInt(5, loanID);
            int rows = p_stmt.executeUpdate();
            System.out.println((rows > 0) ? "Loan updated successfully." : "No Loan found with this loanID");
        }
    }

    // Update all loans by calculating monthly payments, deducting from Account
    // balance, reducing loanRemaining from Loans table
    // and removing loans that are fully paid from Loans table.
    public static void updateLoanMonthly(Connection conn) throws SQLException {
        // Calculates the monthly payment
        String createTempPaymentCalculation = "CREATE TEMPORARY TABLE TempLoanPayments AS\n" +
                "SELECT LoanID, AccountID,\n" +
                "    (InterestRate / 12 / 100) AS MonthlyRate,\n" +
                "    LoanRemaining * (InterestRate / 12 / 100) AS InterestAmount,\n" +
                "    LEAST(LoanMonthly - (LoanRemaining * (InterestRate / 12 / 100)), LoanRemaining) AS PrincipalPaid,\n"
                +
                "    LEAST(LoanMonthly, LoanRemaining + (LoanRemaining * (InterestRate / 12 / 100))) AS AmountPaid\n" +
                "FROM Loans WHERE LoanRemaining > 0";

        // Deduct the users balance
        String deductUserBalance = "UPDATE Accounts acc " +
                "JOIN TempLoanPayments temp ON acc.AccountID = temp.AccountID " +
                "SET acc.Balance = acc.Balance - temp.AmountPaid";

        // Deduct the paid amount from the loanRemaining
        String updateLoanRemaining = "UPDATE Loans loan " +
                "JOIN TempLoanPayments temp ON loan.LoanID = temp.LoanID " +
                "SET loan.LoanRemaining = loan.LoanRemaining - temp.PrincipalPaid";

        // Removes all loans that are fully paid
        String deletePaidLoans = "DELETE FROM Loans WHERE LoanRemaining <= 0";
        // Drops the temporary table after use
        String dropTempTable = "DROP TEMPORARY TABLE IF EXISTS TempLoanPayments";
        try (java.sql.Statement stmt = conn.createStatement()) {
            // 1. Disable autocommit for transaction safety
            conn.setAutoCommit(false);
            // 2. Execute the sequence of queries
            stmt.execute(dropTempTable); // Drop just in case it already exists in the session
            stmt.execute(createTempPaymentCalculation);
            stmt.executeUpdate(deductUserBalance);
            stmt.executeUpdate(updateLoanRemaining);
            stmt.executeUpdate(deletePaidLoans);
            stmt.execute(dropTempTable); // Clean up the temp table
            // 3. Commit the transaction if everything succeeded
            conn.commit();
            System.out.println("Monthly loan update executed successfully.");
        } catch (java.sql.SQLException e) {
            // 4. Roll back the entire transaction if any error occurs
            System.err.println("Error occurred during monthly loan update. Rolling back transaction.");
            try {
                conn.rollback();
            } catch (java.sql.SQLException rollbackEx) {
                System.err.println("Failed to rollback transaction: " + rollbackEx.getMessage());
            }
            // Rethrow the exception to the caller
            throw e;
        } finally {
            // 5. Restore auto-commit behavior
            try {
                conn.setAutoCommit(true);
            } catch (java.sql.SQLException autoCommitEx) {
                autoCommitEx.printStackTrace();
            }
        }
    }
}
