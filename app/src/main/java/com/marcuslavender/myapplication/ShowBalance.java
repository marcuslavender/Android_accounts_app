package com.marcuslavender.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Created by marcus.lavender on 29/08/2018.
 */

public class ShowBalance extends AppCompatActivity {


   //private double balance;
   private TextView currentbalanceTextView;
   private SortedMap<String,String> datesBalances;




    ShowBalance()
    {

        this.datesBalances = new TreeMap<>();



    }

    public TextView getBalance()
    {
        return this.currentbalanceTextView;
    }

    public void setBalance(TextView aBalance)
    {
        this.currentbalanceTextView = aBalance;
    }


    private String[] splitdateFromBalance(String aDateTimeBalance)
    {
        String[]  splitString;
        splitString = aDateTimeBalance.split(",");

        return splitString;
    }



    private void readFromFileAddtoDatesBalances(String sfileName, Context mcoContext)
    {

        File file = new File(mcoContext.getFilesDir(), "expenses");
        {
            if(!file.exists())
            {
                Toast.makeText(ShowBalance.this, "No file to read from!",
                        Toast.LENGTH_SHORT).show();
            }
        }

        try {
            File inputFile = new File(file,sfileName);
            BufferedReader in = new BufferedReader(new FileReader(inputFile));
            Scanner aScanner = new Scanner(in);
            aScanner.useDelimiter(":");

            while(aScanner.hasNext())
            {
                //Need to add a delimiter to split string in to  date and balance
                String[] splitString;


                splitString = this.splitdateFromBalance(aScanner.next());

                //System.out.println(aScanner.next());






                this.datesBalances.put(splitString[0],splitString[1]);




            }

        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }



    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showbalance);
        this.readFromFileAddtoDatesBalances("statementHistory",getApplicationContext() );
        this.currentbalanceTextView = (TextView)findViewById(R.id.editText);


            String latestBalance = this.datesBalances.firstKey();


            this.currentbalanceTextView.setText(latestBalance);



    }




}
