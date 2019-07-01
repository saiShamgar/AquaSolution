package com.PG.testingapp.Repository;

import android.arch.lifecycle.MutableLiveData;
import android.content.Context;

import com.PG.testingapp.LocalDataBase.DatabaseClient;
import com.PG.testingapp.LocalDataBase.HeadLessTable;
import com.PG.testingapp.model.ValueEditionDetaillsModel;

import java.util.ArrayList;
import java.util.List;

public class DataRepository  {

    private static DataRepository instance;
    private ArrayList<HeadLessTable> headLessGridData=new ArrayList<>();

    public static  DataRepository getInstance(){
        if (instance==null){
            instance=new DataRepository();
        }
        return instance;
    }

    public MutableLiveData<ArrayList<HeadLessTable>> getHeadLessData(Context context){
        setHeadLessData(context);
        MutableLiveData<ArrayList<HeadLessTable>> data=new MutableLiveData<>();
        data.setValue(headLessGridData);
        return data;
    }

    private void setHeadLessData(Context context) {
        headLessGridData.clear();
        List<HeadLessTable> taskList = DatabaseClient
                .getInstance(context)
                .getAppDatabase()
                .operations()
                .getHeadLessData();

        if (taskList.size()>0){
          headLessGridData.addAll(taskList);
        }

    }
}
