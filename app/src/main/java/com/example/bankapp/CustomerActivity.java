package com.example.bankapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CustomerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);

        final Customer customer = (Customer) getIntent().getExtras().getSerializable("com.example.bankapp.selectedcustomer");

        TextView custNameTextView = (TextView) findViewById(R.id.custNameTextVIew);
        TextView custEmailIdTextView = (TextView) findViewById(R.id.custEmailIdTextView);
        TextView custAccIdTextView = (TextView) findViewById(R.id.custAccIdTextView);
        TextView custBalTextView = (TextView) findViewById(R.id.custBalTextView);

        custNameTextView.setText(customer.getName());
        custEmailIdTextView.setText(customer.getEmail());
        custAccIdTextView.setText(customer.getAccId());
        custBalTextView.setText(customer.getAccBal() + "");


        Button sendBtn = (Button) findViewById(R.id.sendButton);

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent recieverListIntent = new Intent(getApplicationContext(),RecieverListActivity.class);
                recieverListIntent.putExtra("com.example.bankapp.sender",customer);
                startActivity(recieverListIntent);
            }
        });

        Button homeBtn = (Button) findViewById(R.id.homeButton);
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainActivityIntent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(mainActivityIntent);
            }
        });
    }
}