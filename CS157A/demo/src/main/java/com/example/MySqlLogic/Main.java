package com.example.MySqlLogic;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
public class Main {
    public static void main(java.lang.String[]args){
        try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root",
            "TestPassword123!");
            Statement stmt = conn.createStatement();
            System.out.println("Database Connection Success");
            }
        catch (SQLException se) {
            System.out.println ("SQL Exception:" + se.getMessage() );
            se.printStackTrace ( System.out );
        }
    }
}
