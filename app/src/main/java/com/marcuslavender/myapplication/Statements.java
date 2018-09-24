package com.marcuslavender.myapplication;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;
import java.util.TreeMap;

//Indicies creates a index for balance, Value ID is set to be unique

@Entity(indices = {@Index("balance"), @Index(value = {"date", "ID"}), @Index(value = {"ID"}, unique = true )})
public class Statements {

    @PrimaryKey
    public int ID;


    @ColumnInfo(name = "date")
    public  String date;

    @ColumnInfo(name = "time")
    public String time;

    @ColumnInfo(name = "balance")
    public Double balance;


    public String getDate()
    {
     return this.date;
    }

    public void setDate(String aDate)
    {
        this.date = aDate;
    }

    public String getTime()
    {
        return this.time;
    }

    public void setTime(String time)
    {
        this.time = time;
    }


    public Double getBalance()
    {
        return this.balance;
    }

    public void setBalance(Double aBalance)
    {
        this.balance = aBalance;
    }

    public boolean autoGenerate()
    {
        return true;
    }





}