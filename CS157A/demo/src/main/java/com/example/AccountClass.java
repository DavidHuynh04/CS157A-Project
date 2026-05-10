package com.example;
// JeremyLimKL : Last update 5/7
import java.sql.Date;

// AccountClass is used to store the account information.
public class AccountClass {
    private int accountID;
    private int userID;
    private double balance;
    private String accountType;
    private double interestRate;
    private Date accountCreationDate;
    private static AccountClass checkingAccount = null;
    private static AccountClass savingsAccount = null;
    private static AccountClass creditAccount = null;
    private static AccountClass currentAccount;

    // Constructor for AccountClass object.
    public AccountClass(int accountID, int userID, double balance, String accountType, double interestRate,
            Date accountCreationDate) {
        this.accountID = accountID;
        this.userID = userID;
        this.balance = balance;
        this.accountType = accountType;
        this.interestRate = interestRate;
        this.accountCreationDate = accountCreationDate;
    }

    // Returns the AccountClass object's information.
    public int getAccountID() {
        return accountID;
    }

    public int getUserID() {
        return userID;
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountType() {
        return accountType;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public Date getAccountCreationDate() {
        return accountCreationDate;
    }

    public static AccountClass getCheckingAccount(){
        return checkingAccount;
    }
    public static AccountClass getSavingsAccount(){
        return savingsAccount;
    }
    public static AccountClass getCreditAccount(){
        return creditAccount;
    }
    public static AccountClass getCurrentAccount(){
        return currentAccount;
    }
    public static void setCheckingAccount(AccountClass account){
        checkingAccount = account;
    }
    public static void setSavingsAccount(AccountClass account){
        savingsAccount = account;
    }
    public static void setCreditAccount(AccountClass account){
        creditAccount = account;
    }
    public static void setCurrentAccount(AccountClass account){
        currentAccount = account;
    }
}