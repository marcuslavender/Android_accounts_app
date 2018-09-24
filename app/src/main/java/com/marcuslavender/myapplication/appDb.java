package com.marcuslavender.myapplication;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;



/**
 * Created by marcus.lavender on 13/09/2018.
 */
    @Database(version = 3, entities = {Statements.class})
    public abstract class appDb extends RoomDatabase {


    abstract public StatementsDao getStatementsDao();





    }









