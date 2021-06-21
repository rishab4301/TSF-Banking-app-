package com.example.bankapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceControl;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

;import java.util.ArrayList;
import java.util.List;

public class TransactionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        Customer sender = (Customer) getIntent().getExtras().getSerializable("com.example.bankapp.finalsender");
        Customer reciever = (Customer) getIntent().getExtras().getSerializable("com.example.bankapp.finalreciever");
        ListView transListView = (ListView) findViewById(R.id.TransactionDetailsListView);
        Log.d("hfdsa",sender.toString());
        Log.d("sdhjf",reciever.toString());
        List<Customer> temp = new ArrayList<>();
        temp.add(sender);
        temp.add(reciever);
        TransactionAdapter transactionAdapter = new TransactionAdapter(this,temp);
        transListView.setAdapter(transactionAdapter);


        Button makeTransButton = (Button) findViewById(R.id.makeTranscBtn);

        makeTransButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText amtEditText = (EditText) TransactionActivity.this.findViewById(R.id.amountEditText);
                int transacAmt = 0;
                try {
                    transacAmt = Integer.parseInt(amtEditText.getText().toString());
                }
                catch (Exception e)
                {
                    Toast.makeText(TransactionActivity.this,"Enter a Valid Amount",Toast.LENGTH_LONG).show();
                    return;
                }
                Customer sender = (Customer) TransactionActivity.this.getIntent().getExtras().getSerializable("com.example.bankapp.finalsender");
                Customer reciever = (Customer) TransactionActivity.this.getIntent().getExtras().getSerializable("com.example.bankapp.finalreciever");
                if(transacAmt > sender.getAccBal()){
                    Toast.makeText(TransactionActivity.this,"Entered Amount is more than the Balance",Toast.LENGTH_LONG).show();
                    return;
                }
                else if(transacAmt<0)
                {
                    Toast.makeText(TransactionActivity.this,"Enter a Valid Amount",Toast.LENGTH_LONG).show();
                    return;
                }
                TransactionDatabase tdb = new TransactionDatabase(TransactionActivity.this);
                boolean x =tdb.addRow(sender,reciever,transacAmt);
                if (x) {
                    Toast.makeText(TransactionActivity.this, "Transaction Success", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(TransactionActivity.this, "Transaction Failed", Toast.LENGTH_LONG).show();
                }
                Intent mainActivityIntent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(mainActivityIntent);
            }
        });

        Button cancelTransaBtn = (Button) findViewById(R.id.cancelTransacBtn);
        cancelTransaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainActivityIntent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(mainActivityIntent);
            }
        });

    }
}