package com.example.bankapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class TransactionAdapter extends BaseAdapter {
    List<Customer> customers;
    LayoutInflater mInflater;

    public TransactionAdapter(Context c,List<Customer> cc)
    {
        customers = cc;
        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return customers.size();
    }

    @Override
    public Object getItem(int position) {
        return customers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = mInflater.inflate(R.layout.transaction_adapter_detail,null);
        String Designation = "Reciever";
        if(position == 0) {
            Designation = "Sender";
        }
        TextView designationTextView = (TextView) v.findViewById(R.id.DesignationTextView);
        TextView desigIdTextView = (TextView) v.findViewById(R.id.TransDesigIdTextView);
        TextView desigNameTextView = (TextView) v.findViewById(R.id.TransDesigNameTextView);
        TextView desigBalTextView = (TextView) v.findViewById(R.id.TransDesigBalnceTextView);

        designationTextView.setText(Designation);
        desigIdTextView.setText(Designation + " Account Number: "+customers.get(position).getAccId());
        desigNameTextView.setText(Designation + " Name: "+customers.get(position).getName());
        desigBalTextView.setText(Designation + " Balance: "+customers.get(position).getAccBal());



        return v;
    }
}
