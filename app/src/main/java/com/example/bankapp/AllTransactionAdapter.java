package com.example.bankapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class AllTransactionAdapter extends BaseAdapter {
    LayoutInflater mInflator;
    List<TransactionModel> transacList;

    public AllTransactionAdapter(Context c,List<TransactionModel> transacList) {
        this.transacList = transacList;
        mInflator = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return transacList.size();
    }

    @Override
    public Object getItem(int position) {
        return transacList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = mInflator.inflate(R.layout.transaction_detail_layout,null);
        TextView senderNameTextView = (TextView) v.findViewById(R.id.send_name);
        TextView senderIdTextView = (TextView) v.findViewById(R.id.send_id);
        TextView recieverIdTextView = (TextView) v.findViewById(R.id.rec_id);
        TextView recieverNameTextView = (TextView) v.findViewById(R.id.rec_name);
        TextView transacAmtTextView = (TextView) v.findViewById(R.id.amtTxt);

        senderIdTextView.setText("Sender: "+transacList.get(position).getSenderAccNum());
        recieverIdTextView.setText("Reciever: "+transacList.get(position).getRecieverAccNum());
        senderNameTextView.setText(transacList.get(position).getSenderName());
        recieverNameTextView.setText(transacList.get(position).getRecieverName());
        transacAmtTextView.setText("Amount Transfered = " + transacList.get(position).getTransacAmt());

        return v;
    }
}
