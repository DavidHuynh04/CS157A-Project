// David Huynh: Last Update 5/10
package com.example.MySqlLogic;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

// Creates 15 inserts statements for each table
public class DataGenerator {
    // Gets the connection an generates the data
    public static void main(String[] args){
        try {
            Connection conn = SQLConnection.getConnection();
            CreateFunction.createEmptyTable(conn);
            generateData(conn);
        } catch (Exception e) {
        }
    }
    // Creates Insert statements by using for loops
    public static void generateData(Connection conn) {
        try {
            // Generate 15 Users
            for (int i = 1; i <= 15; i++) {
                String role = (i % 5 == 0) ? "Admin" : "Customer";
                InsertFunction.insertUser(conn, "user" + i, "password" + i, "Test User " + i,
                        "user" + i + "@example.com", "555-010" + String.format("%02d", i),
                        i + " Main St, City, Country", role);
            }

            // Generate 15 Accounts (1 per user)
            Date currentDate = Date.valueOf(LocalDate.now());
            for (int i = 1; i <= 15; i++) {
                String type;
                if (i % 3 == 0) type = "Credit Card";
                else if (i % 2 == 0) type = "Savings";
                else type = "Checking";
                
                double balance = 1000.0 + (i * 100);
                double interestRate = (type.equals("Savings")) ? 2.5 : (type.equals("Credit Card") ? 18.0 : 0.0);
                
                InsertFunction.insertAccount(conn, i, balance, type, interestRate, currentDate);
            }

            // Generate 15 Loans (1 per account)
            for (int i = 1; i <= 15; i++) {
                double loanOriginal = 5000.0 + (i * 500);
                double interestRate = 5.0 + (i % 3);
                int loanPeriod = 12 + (i * 2); // months
                
                InsertFunction.insertLoan(conn, i, loanOriginal, interestRate, currentDate, loanPeriod);
            }

            // Generate 15 Transactions
            for (int i = 1; i <= 15; i++) {
                int sender = i;
                int recipient = (i % 15) + 1; // 1 to 2, 2 to 3, ... 15 to 1
                double amount = 50.0 + i;
                
                InsertFunction.insertTransaction(conn, sender, recipient, amount);
            }

            System.out.println("Data generation completed successfully.");
        } catch (SQLException e) {
            System.err.println("Error generating data: " + e.getMessage());
        }
    }
}
