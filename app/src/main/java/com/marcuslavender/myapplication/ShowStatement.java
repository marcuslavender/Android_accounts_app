package com.marcuslavender.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.TreeMap;
import java.util.ArrayList;


import org.w3c.dom.Text;

/**
 * Created by marcus.lavender on 29/08/2018.
 */

public class ShowStatement extends AppCompatActivity {

    private SortedMap<String,String> datesBalances;
    private TextView statementBalance;
    private String currentDate;
    private String[] statementArray;
    private SortedSet<String> dates;
    private SortedSet<String> balances;
    ListView listView;








   public ShowStatement()
    {
        this.datesBalances = new TreeMap<>();
        this.dates = new TreeSet<>();
        this.balances = new TreeSet<>();
        this.statementArray = new String[50];




    }



    public void popArrayWithNull()
    {
        for(int i = 0; i < this.statementArray.length -1; i++ )
        {
            this.statementArray[i] = "empty";
            System.out.print(this.statementArray[i]);
        }
    }







    public String getCurrentDate()
    {
        return this.currentDate;
    }









    public void setCurrentDate()
    {
        Date dNow = new Date( );
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy.MM.dd hh-mm-ss");
        String date = ft.format(dNow);
        this.currentDate = date;


    }




    public TextView getStatement()
    {
        return this.statementBalance;
    }









    private String addDateAndConvertToString(String aDate, TextView aTextView)
    {

        String text = aDate + ","  + aTextView.getText().toString();
        //System.out.println(text);
        return text;
    }







    private String[] splitdateFromBalance(String aDateTimeBalance)
    {
        String[]  splitString;
        splitString = aDateTimeBalance.split(",");

        return splitString;
    }


    private void populateListView()
    {
        //Convert Sets to Array
        int count = 0;
        for(String aString1 : dates)
        {
            for(String aString2 : balances) {
                String date;
                String balance;

                date = aString1;
                balance = aString2;

                this.statementArray[count] = date + "       " + "£" + balance;
                //System.out.println(this.statementArray[0]);
            }
            count ++;
        }



        //Create ArrayAdapter object to set listview layout and pass in a array of strings.
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, this.statementArray);

        this.listView = (ListView) findViewById(R.id.listViewMain);
        listView.setAdapter(adapter);


    }




    private void writeStatementHistoryToFile(String dFileName, Context mcoContext)
    {
        File file = new File(mcoContext.getFilesDir(), "expenses");
        {
           if(!file.exists())
           {
               file.mkdir();
           }
        }

        try{
            File outputFile = new File(file, dFileName);
            BufferedWriter out = new BufferedWriter(new FileWriter(outputFile));
            out.write(addDateAndConvertToString(this.getCurrentDate(),this.getStatement()));
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }









    private void readFromFileAddtoDatesBalances(String sfileName, Context mcoContext)
    {

        File file = new File(mcoContext.getFilesDir(), "expenses");
        {
            if(!file.exists())
            {
                Toast.makeText(ShowStatement.this, "No file to read from!",
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
        setContentView(R.layout.showstatement);
        this.setCurrentDate();
        this.popArrayWithNull();





        this.readFromFileAddtoDatesBalances("statementHistory", getApplicationContext());



       Set<String> keys = this.datesBalances.keySet();

       int count = 0;
       for(String key : keys) {
           System.out.println(this.datesBalances.get(key));
           String date = key;
           System.out.println(this.datesBalances.get(key));
           String balance = this.datesBalances.get(key);

           this.statementArray[count] = date + "       " + "£" + balance;


           //dates.add(key);
           //balances.add(balance);
           count += 1;
       }

        //Create ArrayAdapter object to set listview layout and pass in a array of strings.
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, this.statementArray);

        this.listView = (ListView) findViewById(R.id.listViewMain);
        listView.setAdapter(adapter);

       //populateListView();

    }





    protected void onPause()
    {
        super.onPause();
       //this.writeStatementHistoryToFile("statementHistory", getApplicationContext());

    }





}
