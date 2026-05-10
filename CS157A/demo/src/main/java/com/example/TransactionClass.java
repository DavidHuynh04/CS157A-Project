package com.example;
import java.sql.Timestamp;

public class TransactionClass {
    private int transactionID;
    private int senderID;
    private int recipientID;
    private double transactionAmount;
    private Timestamp transactionDate;
    public TransactionClass(int transactionID, int senderID, int recipientID, double transactionAmount, Timestamp transactionDate){
        this.transactionID = transactionID;
        this.senderID = senderID;
        this.recipientID = recipientID;
        this.transactionAmount = transactionAmount;
        this.transactionDate = transactionDate;
    }
    public int getTransactionID(){
        return transactionID;
    }
    public int getSenderID(){
        return senderID;
    }
    public int getRecipientID(){
        return recipientID;
    }
    public double getTransactionAmount(){
        return transactionAmount;
    }
    public Timestamp getTransactionDate(){
        return transactionDate;
    }
}
