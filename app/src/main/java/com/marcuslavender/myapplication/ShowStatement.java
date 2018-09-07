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
import java.util.TreeSet;
import java.util.TreeMap;
import java.util.ArrayList;


import org.w3c.dom.Text;

/**
 * Created by marcus.lavender on 29/08/2018.
 */

public class ShowStatement extends AppCompatActivity {

    private List<TreeMap<String,String>> fromFile = new ArrayList<>();
    private TreeMap<String,String> dateTimeMap = new TreeMap<>();
    private TextView statementBalance;
    private TextView aStatementAsString;
    private Date date;
    private String currentDate;
    private String[] statementArray;
    private TreeSet<String> dates;
    private TreeSet<String> balances;




   public ShowStatement()
    {

        dates = new TreeSet<>();
        balances = new TreeSet<>();
        statementArray = new String[50];

    }







    public void setFromFile(List<TreeMap<String,String>> listOfStatements)
    {
        this.fromFile = listOfStatements;
    }


    public List<TreeMap<String,String>> getFromFile()
    {
        return this.fromFile;
    }


    public String getFromMap(String date)
    {
       return this.dateTimeMap.get(date);


    }




    public void setMapToList(TreeMap<String,String> aStatementHistory)
    {
        this.fromFile.add(aStatementHistory);
    }



    public void setDateTimeToMap(String date, String balance)
    {
        this.dateTimeMap.put(date, balance);
    }



    public TreeMap<String, String> getDateTimeMap()
    {
        return this.dateTimeMap;
    }



    public Date getDate()
    {
        return this.date;
    }


    public String getCurrentDate()
    {
        return this.currentDate;
    }


    public void setDate()
    {
        this.date = new Date();
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







    public void setaStatement(TextView aStatement)
    {
        this.statementBalance = aStatement;
    }







    private String addDateAndConvertToString(String aDate, TextView aTextView)
    {

        String text = aDate + ","  + aTextView.getText().toString();
        System.out.println(text);
        return text;
    }


    private String[] splitdateFromBalance(String aDateTimeBalance)
    {
        String[]  splitString;
        splitString = aDateTimeBalance.split(",");

        return splitString;
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









    private void readFromFileAddtoHistoryMap(String sfileName, Context mcoContext)
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



                this.setDateTimeToMap(splitString[0],splitString[1]);




            }

        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }

        this.setMapToList(this.getDateTimeMap());

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showstatement);
        this.setCurrentDate();

        //List<TreeMap<String,String>> list = this.getFromFile();
        //TreeMap<String,String> map = list.get(0);



        //this.statementBalance = (TextView)findViewById(R.id.textView4);
        this.readFromFileAddtoHistoryMap("statementHistory", getApplicationContext());

       Map<String,String> mapStatements =  this.getFromFile().get(0);
       Set<String> keys = mapStatements.keySet();

       for(String key : keys) {
           System.out.println(this.getFromMap(key));
           String balance = this.getFromMap(key);

           dates.add(key);
           balances.add(mapStatements.get(key));

           //this.getStatement().setText(balance);

       }

       //Convert Sets to Array's
        int count = 0;
        for(String aString1 : dates)
        {
            for(String aString2 : balances) {
                String date;
                String balance;

                date = aString1;
                balance = aString2;

                this.statementArray[count] = date + "," + balance;
            }
            count ++;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,  statementArray);

        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(adapter);

    }








    protected void onPause()
    {
        super.onPause();
        this.writeStatementHistoryToFile("statementHistory", getApplicationContext());

    }





}
