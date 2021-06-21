package com.example.bankapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

public class TransactionListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_list);

        TransactionDatabase tdb = new TransactionDatabase(this);
        ListView transacListListView = (ListView) findViewById(R.id.transactionListListView);
        Button goBackBtn = (Button) findViewById(R.id.gobackButton);


        goBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainActivityIntent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(mainActivityIntent);
            }
        });

        List<TransactionModel> tList = tdb.getAllTransactions();
        AllTransactionAdapter atransAdapter = new AllTransactionAdapter(this,tList);
        transacListListView.setAdapter(atransAdapter);
    }
}