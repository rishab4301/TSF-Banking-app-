package com.example.bankapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CustomerDataBase extends SQLiteOpenHelper {
    public static final String CUSTOMER_TABLE = "CUSTOMER_TABLE";
    public static final String COLUMN_CUSTOMER_NAME = "CUSTOMER_NAME";
    public static final String COLUMN_CUSTOMER_EMAIL = "CUSTOMER_EMAIL";
    public static final String COLUMN_CUSTOMER_BALANCE = "CUSTOMER_BALANCE";
    public static final String COLUMN_ACCOUNT_NUMBER = "ACCNUM";

    public CustomerDataBase(@Nullable Context context) {
        super(context, "customer.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "create table " + CUSTOMER_TABLE + " (" + COLUMN_ACCOUNT_NUMBER + " TEXT PRIMARY KEY, " +
                COLUMN_CUSTOMER_NAME + " TEXT, " + COLUMN_CUSTOMER_EMAIL + " TEXT, " + COLUMN_CUSTOMER_BALANCE + " INT)";
        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public  boolean addRow(Customer cust) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();  // Similar to hash map or similar put extras in intent
        cv.put(COLUMN_ACCOUNT_NUMBER,cust.getAccId());
        cv.put(COLUMN_CUSTOMER_NAME,cust.getName());
        cv.put(COLUMN_CUSTOMER_EMAIL,cust.getEmail());
        cv.put(COLUMN_CUSTOMER_BALANCE,cust.getAccBal());

        long insert=db.insert(CUSTOMER_TABLE,null,cv);

        db.close();
        if(insert == -1)
            return false;
        return true;
    }

    public List<Customer> getAllCustomers(){
        List<Customer> returnList = new ArrayList<>();
        //getting data from database
        String query ="Select * from " + CUSTOMER_TABLE;
        SQLiteDatabase db= this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst()) {
            do{
                String accNum = cursor.getString(0);
                String custName = cursor.getString(1);
                String custEmail = cursor.getString(2);
                int custBal = cursor.getInt(3);

                Customer newCust = new Customer(accNum,custName,custEmail,custBal);
                returnList.add(newCust);
            }while (cursor.moveToNext());
        }
        else {

        }

        cursor.close();
        db.close();
        return returnList;
    }

    public boolean postTransactionTableUpdation(Customer sender,Customer reciever,int transAmt) {
        String tableUpdationStatement = "Update " + CUSTOMER_TABLE + " set " + COLUMN_CUSTOMER_BALANCE + "=" + (sender.getAccBal() - transAmt) + " where "
                + COLUMN_ACCOUNT_NUMBER + " = " + sender.getAccId() ;
        SQLiteDatabase db= this.getWritableDatabase();
        db.execSQL(tableUpdationStatement);
        tableUpdationStatement = "Update " + CUSTOMER_TABLE + " set " + COLUMN_CUSTOMER_BALANCE + "=" + (reciever.getAccBal() + transAmt) + " where "
                + COLUMN_ACCOUNT_NUMBER + " = " + reciever.getAccId() ;
        db.execSQL(tableUpdationStatement);
        return true;
    }


}
