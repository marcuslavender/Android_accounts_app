package com.marcuslavender.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.util.Scanner;


import org.w3c.dom.Text;

/**
 * Created by marcus.lavender on 29/08/2018.
 */

public class ShowStatement extends AppCompatActivity {

    private Map<String, String> statementHistoryFromFile = new HashMap<>();
    private Map<String,TextView> statementHistory = new HashMap<>();
    private TextView aStatement;
    private TextView aStatementAsString;
    private Date date;
    private String currentDate;




   public ShowStatement()
    {



    }







    public TextView getHistoricalStatement(String date)
    {
       return this.statementHistory.get(date);


    }






    public void setStatementHistory(Map<String,TextView>aStatementHistory)
    {
        this.statementHistory = aStatementHistory;
    }






    public Date getDate()
    {
        return this.date;
    }






    public void setDate()
    {
        this.date = new Date();
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
        return this.aStatement;
    }







    public void setaStatement(TextView aStatement)
    {
        this.aStatement = aStatement;
    }







    private String addDateAndConvertToString(String aDate, TextView aTextView)
    {

        String text = aDate + ","  + aTextView.getText().toString();
        System.out.println(text);
        return text;
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
                // dateAndStatement.split(",");
                System.out.println(aScanner.next());
                //this.statementHistory.put()

            }

        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }



    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showstatement);
        this.statementHistory.put(this.getCurrentDate(), this.aStatement);
        this.aStatement = (TextView)findViewById(R.id.editText3);
        this.aStatement.setText("0");
        this.setCurrentDate();
        this.writeStatementHistoryToFile("statementHistory", getApplicationContext());
        //this.aStatement.setText("1");
        this.readFromFileAddtoHistoryMap("statementHistory", getApplicationContext());





    }


}
