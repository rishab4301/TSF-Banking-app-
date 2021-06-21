package com.example.bankapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class CustomerItemAdapter extends BaseAdapter {

    List<Customer> customers;
    LayoutInflater mInflater;

    public CustomerItemAdapter(Context c,List<Customer> cust) {
        customers = cust;
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
        View v = mInflater.inflate(R.layout.users_layout_detail,null);

        TextView nameTextView = (TextView) v.findViewById(R.id.nameTextView);
        //TextView emailTextView = (TextView) v.findViewById(R.id.emailidTextView);
        TextView accNoTextView = (TextView) v.findViewById(R.id.accnumTextView);
        TextView accBalTextView = (TextView) v.findViewById(R.id.balanceTextView);

        String name = customers.get(position).getName();
        //String emailid = customers.get(position).getEmail();
        String accNo = customers.get(position).getAccId();
        String accBal = customers.get(position).getAccBal() + "";

        nameTextView.setText("Name: "+name);
        //emailTextView.setText(emailid);
        accNoTextView.setText("Id: "+accNo);
        accBalTextView.setText("Balance: " + accBal);

        return v;
    }
}
