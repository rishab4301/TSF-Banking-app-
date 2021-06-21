package com.example.bankapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class RecieverListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reciever_list);

        Customer sender = (Customer) getIntent().getExtras().getSerializable("com.example.bankapp.sender");
        CustomerDataBase cdb = new CustomerDataBase(this);
        List<Customer> allCustomers = cdb.getAllCustomers();
        List<Customer> possibleRecievers = new ArrayList<>();

        for(Customer c: allCustomers)
        {
            if(sender.getAccId().compareTo(c.getAccId())!=0)
            {
                possibleRecievers.add(c);
            }
            else{
            Log.d("MEssage", c.toString() + "\n" + sender.toString());
            }
        }
        ListView recieverListView = (ListView) findViewById(R.id.recieverListView);
        CustomerItemAdapter recieverList = new CustomerItemAdapter(this,possibleRecievers);
        recieverListView.setAdapter(recieverList);

        recieverListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CustomerItemAdapter recieverAdapter = (CustomerItemAdapter) parent.getAdapter();
                Customer reciever = (Customer) recieverAdapter.getItem(position);
                Customer sender = (Customer) RecieverListActivity.this.getIntent().getExtras().getSerializable("com.example.bankapp.sender");

                Intent transactionActivityIntent = new Intent(RecieverListActivity.this,TransactionActivity.class);
                transactionActivityIntent.putExtra("com.example.bankapp.finalsender",sender);
                transactionActivityIntent.putExtra("com.example.bankapp.finalreciever",reciever);
                startActivity(transactionActivityIntent);

            }
        });
    }
}