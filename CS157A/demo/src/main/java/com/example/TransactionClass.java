package com.example;
import java.sql.Date;

public class TransactionClass {
    private int transactionID;
    private int senderID;
    private int recipientID;
    private double transactionAmount;
    private Date date;
    public TransactionClass(int transactionID, int senderID, int recipientID, double transactionAmount, Date date){
        this.transactionID = transactionID;
        this.senderID = senderID;
        this.recipientID = recipientID;
        this.transactionAmount = transactionAmount;
        this.date = date;
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
    public Date getTransactionDate(){
        return date;
    }
}
