package com.example.bankapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            Customer customer1 = new Customer("739364","Anuradha kashyap","anukash@gmail.com",99300);
        CustomerDataBase custdb = new CustomerDataBase(this);
       boolean success = custdb.addRow(customer1);
        Toast.makeText(this,""+success,Toast.LENGTH_LONG).show();

        Button viewCustBtn = (Button) findViewById(R.id.viewCustButton);
        viewCustBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent customersListIntent = new Intent(getApplicationContext(),CustomersListActivity.class);
                startActivity(customersListIntent);
            }
        });

        Button viewTransactionBtn = (Button) findViewById(R.id.viewTransBtn);
        viewTransactionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent transacListIntent = new Intent(MainActivity.this,TransactionListActivity.class);
                startActivity(transacListIntent);
            }
        });
    }
}