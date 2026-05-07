package com.example;

public class SessionInformation {
    private int userID;
    private String name;
    private String email;
    private String phone;
    private String address;
    private Role role;
    public enum Role {
        Customer,
        Admin
    }
    public SessionInformation(int userID, String name, String email, String phone, String address, Role role){
        this.userID = userID;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.role = role;
    }
    public int getUserID(){
        return userID;
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
    public Role getRole(){
        return role;
    }
}
