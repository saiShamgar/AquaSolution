package com.PG.testingapp.LocalDataBase;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.ArrayList;
import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;

@Dao
public interface DBOperations {

    @Insert(onConflict = IGNORE)
    long insert(HeadLessTable task);

    @Query("DELETE FROM HeadLessTable")
    int delete();

    @Query("SELECT * FROM HeadLessTable")
    List<HeadLessTable> getHeadLessData();
}
