package com.marcuslavender.myapplication;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.widget.Toast;

import java.io.IOException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by marcus.lavender on 29/08/2018.
 */

public class DepositMoney extends AppCompatActivity {

   private double depositAmount;
   private String currentDate;
   private String currentTime;
   private static appDb db;





    DepositMoney()
    {
        this.currentDate = null;
        this.currentTime = null;
        this.depositAmount = 0;

    }


    public Double getDeposit()
    {
        return this.depositAmount;
    }


    public void setDeposit(Double aDepositAmount)
    {
        this.depositAmount = aDepositAmount;
    }


    public String getCurrentDate()
    {
        return this.currentDate;
    }

    public void setCurrentDate()
    {
        this.currentDate = CommonUtilityMethods.getCurrentDate();
    }

    public String getCurrentTime()
    {
        return this.currentTime;
    }

    public void setCurrentTime()
    {

        this.currentTime = CommonUtilityMethods.getCurrentTime();

    }


   public void getUserInputAmount(EditText atext)
    {

        String str = atext.getText().toString();
        Double amount = Double.parseDouble(str);

        this.setDeposit(amount);
    }



    public appDb getDbInstance(Context context)
    {
        if(DepositMoney.db == null) {
            appDb newdb = Room.databaseBuilder(getApplicationContext(),
                    appDb.class, "Statements.db").allowMainThreadQueries().fallbackToDestructiveMigration().build();
            DepositMoney.db = newdb;
            return DepositMoney.db;

        }
        else
        {
            return DepositMoney.db;
        }


    }

    public void closeDbInstance(appDb aDatabaseInstance)  {

        try {
            aDatabaseInstance.close();
        }
        catch( Exception e)
        {
            e.printStackTrace();
        }


    }

    //Safe Balance, date, time, to database
    public Statements buildStatement()  {
        final Statements statement = new Statements();
        statement.setBalance(this.getDeposit());
        statement.setDate(this.getCurrentDate());
        statement.setTime(this.getCurrentTime());

        return statement;

    }







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.depositmoney);

        this.setCurrentDate();
        this.setCurrentTime();

        final EditText text = (EditText)findViewById(R.id.edittextdeposit);


        Button b1 = (Button) findViewById(R.id.button2);
        b1.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {


                // Check user has input an amount
                boolean loop = false;
                while(!loop) {

                    if (text.getText().toString() == null) {
                        // change this to a toast
                        String description;
                        description = "No Value entered";
                        Toast.makeText(DepositMoney.this, description,
                                Toast.LENGTH_SHORT).show();


                    }
                    else {
                        getUserInputAmount(text);
                        getDbInstance(getApplicationContext());
                        Statements newStatement = buildStatement();


                        DepositMoney.db.getStatementsDao().insertStatements(newStatement);
                        try {

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        loop = true;

                        //closeDbInstance(DepositMoney.db);
                    }
                }



            }
        });


   }

}
