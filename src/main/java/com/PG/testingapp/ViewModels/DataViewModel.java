package com.PG.testingapp.ViewModels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import com.PG.testingapp.LocalDataBase.HeadLessTable;
import com.PG.testingapp.Repository.DataRepository;
import com.PG.testingapp.model.ValueEditionDetaillsModel;

import java.util.ArrayList;

public class DataViewModel extends ViewModel {

    private MutableLiveData<ArrayList<HeadLessTable>> HeadlessList;
    private DataRepository repository;

    public LiveData<ArrayList<HeadLessTable>> getHedlessData(){
        return HeadlessList;
    }

    public void init(Context context){

        if (HeadlessList!=null){
            return;
        }
        repository=DataRepository.getInstance();
        HeadlessList=repository.getHeadLessData(context);
    }
}
