package com.example.bankapp;

import java.io.Serializable;

public class Customer implements Serializable {
    private String accId;
    private String name;
    private String email;
    private int accBal;

    public Customer(String accId,String name,String email,int accBal)
    {
        this.accId = accId;
        this.name = name;
        this.email = email;
        this.accBal = accBal;
    }
    public  Customer(){

    }

    @Override
    public String toString() {
        return "Customer{" +
                "accId='" + accId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", accBal=" + accBal +
                '}';
    }

    //Getters and Setters(Created usiong generate after right click)
    public String getAccId() {
        return accId;
    }

    public void setAccId(String accId) {
        this.accId = accId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAccBal() {
        return accBal;
    }

    public void setAccBal(int accBal) {
        this.accBal = accBal;
    }
}
