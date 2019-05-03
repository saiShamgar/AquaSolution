package com.PG.testingapp.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Log;

import com.PG.testingapp.R;

public class SharedPreferenceConfig {
    private SharedPreferences sharedPreferences;
    private Context context;

    public SharedPreferenceConfig(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences(context.getResources().getString(R.string.login_prferance), Context.MODE_PRIVATE);
    }
    public void writeLoginPreference(String url){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getResources().getString(R.string.check_login), String.valueOf(url));
        Log.i("SharedPreferenceWrite: ",""+url);
        editor.apply();
    }

    public String readLoginPrefernce(){
        String url;
        url = sharedPreferences.getString(context.getResources().getString(R.string.check_login),"no");
        Log.i("SharedPreferenceRead: ",""+url);
        return url;
    }

}