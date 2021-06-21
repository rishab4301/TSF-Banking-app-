package com.example.bankapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class TransactionDatabase extends SQLiteOpenHelper {


    public static final String TRANSACTION_TABLE = "TRANSACTION_TABLE";
    public static final String COLUMN_TRANSACTION_ID = "transaction_id";
    public static final String COLUMN_SENDER = "sender";
    public static final String COLUMN_RECIEVER = "reciever";
    public static final String COLUMN_AMOUNT = "amount";
    public static final String COLUMN_SENDER_ACCOUNT_ID = "SENDER_ACCOUNT_ID";
    public static final String COLUMN_RECIEVER_ACCOUNT_ID = "RECIEVER_ACCOUNT_ID";
    private CustomerDataBase cdb;

    public TransactionDatabase(@Nullable Context context) {
        super(context, "transaction.db", null, 1);
        cdb = new CustomerDataBase(context);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "create table " + TRANSACTION_TABLE + " ( "
                + COLUMN_TRANSACTION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_SENDER_ACCOUNT_ID + " TEXT, " + COLUMN_SENDER + " TEXT, " +
                COLUMN_RECIEVER_ACCOUNT_ID + " TEXT, " +COLUMN_RECIEVER + " TEXT, " + COLUMN_AMOUNT + " INT )";
        db.execSQL(createTableQuery);
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addRow(Customer sender,Customer reciever,int transacAmt) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_SENDER_ACCOUNT_ID,sender.getAccId());
        cv.put(COLUMN_SENDER,sender.getName());
        cv.put(COLUMN_RECIEVER_ACCOUNT_ID,reciever.getAccId());
        cv.put(COLUMN_RECIEVER,reciever.getName());
        cv.put(COLUMN_AMOUNT,transacAmt);
        if(!cdb.postTransactionTableUpdation(sender,reciever,transacAmt)) {
            return false;
        }
        long insert = db.insert(TRANSACTION_TABLE,null,cv);
        db.close();
        if(insert==-1)
            return false;

        return true;
    }

    public List<TransactionModel> getAllTransactions() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<TransactionModel> listTM = new ArrayList<>();
        String query = "select * from " + TRANSACTION_TABLE;
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst())
        {
            do{
                int transacId = cursor.getInt(0);
                String senderAccId = cursor.getString(1);
                String senderName = cursor.getString(2);
                String recieverAccId = cursor.getString(3);
                String recieverName = cursor.getString(4);
                int transacAmt = cursor.getInt(5);
                TransactionModel tm = new TransactionModel(transacId,senderName,recieverName,senderAccId,recieverAccId,transacAmt);
                listTM.add(tm);

            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return listTM;
    }

}
