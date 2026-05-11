// David Huynh: Last Update 5/10
package com.example;

// SessionInformaton is used to store user information.
public class SessionInformation {
    private int userID;
    private String username;
    private String password;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String role;
    private static SessionInformation currentSession;
    // Constructor for SessionInformation object.
    public SessionInformation(int userID, String username, String password, String name, String email, String phone, String address, String role){
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.role = role;
    }
    // Returns the SessionInformation object's information.
    public int getUserID(){
        return userID;
    }
    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }
    public String getName(){
        return name;
    }
    public String getEmail(){
        return email;
    }
    public String getPhone(){
        return phone;
    }
    public String getAddress(){
        return address;
    }
    public String getRole(){
        return role;
    }
    // Sets and gets a reference to the current user that is logged in
    public static void setSession(SessionInformation session){
        currentSession = session;
    }
    public static SessionInformation getSession(){
        return currentSession;
    }
}