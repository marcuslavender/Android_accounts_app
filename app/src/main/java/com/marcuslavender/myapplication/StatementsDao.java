package com.marcuslavender.myapplication;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by marcus.lavender on 13/09/2018.
 */

@Dao
public abstract class StatementsDao {



        @Insert(onConflict = OnConflictStrategy.REPLACE)
        public abstract void insertStatements(Statements statements);

        @Update
        public abstract void updateStatements(Statements... statements);


        @Delete
        public abstract void delete(Statements statements);


        @Query("SELECT * FROM Statements")
       public abstract List<Statements> getAll();

        @Query("SELECT * FROM Statements WHERE id IN (:StatementIds)")
        public abstract List<Statements> loadAllByIds(int[] StatementIds);

        @Query("SELECT * FROM Statements WHERE date LIKE :date LIMIT 1")
       public abstract Statements findByDate(double date);
}
