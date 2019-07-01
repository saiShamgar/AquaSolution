package com.PG.testingapp.LocalDataBase;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {HeadLessTable.class}, version = 1 , exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract DBOperations operations();
}

