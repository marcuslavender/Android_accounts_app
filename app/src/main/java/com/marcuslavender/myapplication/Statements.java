package com.marcuslavender.myapplication;

import java.util.Date;
import java.util.TreeMap;

public class Statements {

    TreeMap<String,String> statements = new TreeMap<>();

    public Statements(String aDateTime, String aBalance) {

        this.statements.put(aDateTime, aBalance);


    }

    public TreeMap<String,String> getStatementsTreeMap()
    {
        return this.statements;
    }



}