package com.example.bankapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

public class CustomersListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customers_list);

        CustomerDataBase cdb = new CustomerDataBase(this);
        List<Customer> allCustomers = cdb.getAllCustomers();
        ListView usersListView = (ListView) findViewById(R.id.usersList1);
        CustomerItemAdapter adapter = new CustomerItemAdapter(this,allCustomers);
        usersListView.setAdapter(adapter);

        usersListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CustomerItemAdapter customerAdapter = (CustomerItemAdapter) parent.getAdapter();
                Customer C = (Customer) customerAdapter.getItem(position);

                Intent customerActivityIntent = new Intent(getApplicationContext(),CustomerActivity.class);
                customerActivityIntent.putExtra("com.example.bankapp.selectedcustomer",C);
                startActivity(customerActivityIntent);
            }
        });



    }
}