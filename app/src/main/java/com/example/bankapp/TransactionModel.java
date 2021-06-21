package com.example.bankapp;

public class TransactionModel {
    private int transacId;
    private String senderName;
    private String recieverName;
    private String senderAccNum;
    private String recieverAccNum;
    private int transacAmt;

    public TransactionModel(int transacId, String senderName, String recieverName, String senderAccNum, String recieverAccNum, int transacAmt) {
        this.transacId = transacId;
        this.senderName = senderName;
        this.recieverName = recieverName;
        this.senderAccNum = senderAccNum;
        this.recieverAccNum = recieverAccNum;
        this.transacAmt = transacAmt;
    }

    public int getTransacId() {
        return transacId;
    }

    public void setTransacId(int transacId) {
        this.transacId = transacId;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getRecieverName() {
        return recieverName;
    }

    public void setRecieverName(String recieverName) {
        this.recieverName = recieverName;
    }

    public String getSenderAccNum() {
        return senderAccNum;
    }

    public void setSenderAccNum(String senderAccNum) {
        this.senderAccNum = senderAccNum;
    }

    public String getRecieverAccNum() {
        return recieverAccNum;
    }

    public void setRecieverAccNum(String recieverAccNum) {
        this.recieverAccNum = recieverAccNum;
    }

    public int getTransacAmt() {
        return transacAmt;
    }

    public void setTransacAmt(int transacAmt) {
        this.transacAmt = transacAmt;
    }
}
