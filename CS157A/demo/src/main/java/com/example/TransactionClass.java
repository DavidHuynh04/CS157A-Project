// David Huynh: Last Update 5/10
package com.example;
import java.sql.Timestamp;

// TransactionClass is used to store transaction information.
public class TransactionClass {
    private int transactionID;
    private int senderID;
    private int recipientID;
    private double transactionAmount;
    private Timestamp transactionDate;
    // Constructor for SessionInformation object.
    public TransactionClass(int transactionID, int senderID, int recipientID, double transactionAmount, Timestamp transactionDate){
        this.transactionID = transactionID;
        this.senderID = senderID;
        this.recipientID = recipientID;
        this.transactionAmount = transactionAmount;
        this.transactionDate = transactionDate;
    }
    // Returns the TransactionClass object's information.
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
