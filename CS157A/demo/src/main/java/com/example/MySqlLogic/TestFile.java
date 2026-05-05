package com.example.MySqlLogic;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class TestFile {
    public static void main(java.lang.String[]args){
        
        try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root",
            "TestPassword123!");
            System.out.println("Database Connection Success");
            InsertFunction.insertUser(
                    conn,
                    "user1",
                    "pass123",
                    "John Doe",
                    "john@email.com",
                    "1234567890",
                    "123 Main St",
                    "customer"
                );
                InsertFunction.insertUser(
                    conn,
                    "user2",
                    "pass123",
                    "jack",
                    "jack@email.com",
                    "1234567890",
                    "123 Main St",
                    "customer"
                );
            }
            
        catch (SQLException se) {
            System.out.println ("SQL Exception:" + se.getMessage() );
            se.printStackTrace ( System.out );
        }
    }
}
