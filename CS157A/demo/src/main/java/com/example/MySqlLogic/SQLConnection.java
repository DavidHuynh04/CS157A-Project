// David Huynh: Last Update 5/10
package com.example.MySqlLogic;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
// A Class that returns the SQL connection to any function that calls it
public class SQLConnection {
    private static Connection conn;
    public static Connection getConnection() throws SQLException{
        try{
            // Gets the connection using Database Credentials and returns the connection
            String url = "jdbc:mysql://localhost:3306/mydb"; // Replace the URL with your database name
            String root = "root"; // Your MySQL username
            String password = "TestPassword123!"; // Your MySQL password
            conn = DriverManager.getConnection(url , root, password);
            return conn;
            }
        catch (SQLException se) {
            System.out.println ("SQL Exception:" + se.getMessage() );
            se.printStackTrace ( System.out );
        }
        return null;
    }
}
