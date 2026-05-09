package com.example.MySqlLogic_v2;
// JeremyLimKL : Last update 5/6
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    // Database credentials
    static final String DB_URL = "jdbc:mysql://localhost:3306/BankingDB"; // Replace BankingDB with your database name
    static final String USER = "root"; // Your MySQL username
    static final String PASS = "PasswordRoot"; // Your MySQL password

    public static void main(String[] args) {
        // Open a connection
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            System.out.println("Connected to the database successfully!");

            // 1. Create Tables
            System.out.println("\n--- Initializing Database Tables ---");
            CreateFunction.createEmptyTable(conn);
            System.out.println("Tables created successfully.");

            // 2. Insert Users
            System.out.println("\n--- Inserting Data ---");
            InsertFunction.insertUser(conn, "jdoe", "pass123", "John Doe", "john@example.com", "1234567890",
                    "123 Main St", "Customer");
            InsertFunction.insertUser(conn, "jsmith", "pass456", "Jane Smith", "jane@example.com", "0987654321",
                    "456 Oak St", "Customer");

            // 3. Insert Accounts (Assuming John is UserID 1, Jane is UserID 2)
            long millis = System.currentTimeMillis();
            Date currentDate = new Date(millis);
            InsertFunction.insertAccount(conn, 1, 1500.00, "Checking", 0.0, currentDate);
            InsertFunction.insertAccount(conn, 2, 3000.00, "Savings", 2.5, currentDate);

            // 4. Extract Accounts
            System.out.println("\n--- Extracting Data ---");
            ExtractFunction.getAccountsByUserID(conn, 1);
            ExtractFunction.getAccountsByUserID(conn, 2);

            // 5. Test Transaction (Assuming John is Account 1, Jane is Account 2)
            System.out.println("\n--- Processing Transaction ---");
            System.out.println("John sends $500 to Jane.");
            InsertFunction.insertTransaction(conn, 1, 2, 500.00);

            // Extract Accounts again to verify balances
            System.out.println("\n--- Verifying Balances After Transaction ---");
            ExtractFunction.getAccountsByUserID(conn, 1); // Should be 1000
            ExtractFunction.getAccountsByUserID(conn, 2); // Should be 3500

            // 6. Insert and Test Loan
            System.out.println("\n--- Taking out a Loan ---");
            // Jane (Account 2) takes out a $10,000 loan, 5% interest, over 12 months
            InsertFunction.insertLoan(conn, 2, 10000.00, 5.0, currentDate, 12);
            ExtractFunction.getLoansByAccountID(conn, 2);

            // 7. Update Loan Monthly
            System.out.println("\n--- Simulating 1 Month Passing ---");
            UpdateFunction.updateLoanMonthly(conn);

            // Check loan status after month update
            ExtractFunction.getLoansByAccountID(conn, 2);
            // Check Jane's balance after loan payment deduction
            ExtractFunction.getAccountsByUserID(conn, 2);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
