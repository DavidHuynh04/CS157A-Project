package com.example.MySqlLogic;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class SQLConnection {
    private static Connection conn;
    public static Connection getConnection() throws SQLException{
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root",
            "TestPassword123!");
            return conn;
            }
        catch (SQLException se) {
            System.out.println ("SQL Exception:" + se.getMessage() );
            se.printStackTrace ( System.out );
        }
        return null;
    }
}
