package com.marcuslavender.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by marcus.lavender on 29/08/2018.
 */

public class ShowBalance extends AppCompatActivity {


   //private double balance;
   private TextView currentbalanceTextView;


    ShowBalance()
    {


    }

    public TextView getBalance()
    {
        return this.currentbalanceTextView;
    }

    public void setBalance(TextView aBalance)
    {
        this.currentbalanceTextView = aBalance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showbalance);
        this.currentbalanceTextView = (TextView)findViewById(R.id.editText);
        this.currentbalanceTextView.setText("0");



    }




}
