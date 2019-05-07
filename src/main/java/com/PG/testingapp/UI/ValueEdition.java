package com.PG.testingapp.UI;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.PG.testingapp.Adapters.GridViewAdapter;
import com.PG.testingapp.Api.ApiService;
import com.PG.testingapp.Api.AppUrl;
import com.PG.testingapp.BaseActivity;
import com.PG.testingapp.InterFace.OnRadioButtonClick;
import com.PG.testingapp.LoginActivity;
import com.PG.testingapp.R;
import com.PG.testingapp.Utils.AppConstant;
import com.PG.testingapp.Utils.AppUtils;
import com.PG.testingapp.model.GettingProcesses;
import com.PG.testingapp.model.LoginResponse;
import com.PG.testingapp.model.Process_Location;
import com.PG.testingapp.model.Processes_data;

import java.io.Serializable;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ValueEdition extends BaseActivity implements OnRadioButtonClick {

    //Widgets
    private RecyclerView valueEdition_recycler_view_bsd_lots;
    private TextView value_edition_next_btn;
    private ImageView back_button_val_edt;

    //Adapters
    private GridViewAdapter adapter;
    private Context mContext;

    //Strings
    String name,fullName;

    //services
    private ApiService apiService;
    private Processes_data processes_data;

    private Process_Location location;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_value_edition);


        mContext=ValueEdition.this;
        //Initializing Variables
        valueEdition_recycler_view_bsd_lots=findViewById(R.id.valueEdition_recycler_view_bsd_lots);
        value_edition_next_btn=findViewById(R.id.value_edition_next_btn);
        back_button_val_edt=findViewById(R.id.back_button_val_edt);

        callService();

//        adapter=new GridViewAdapter(this,this);
//        valueEdition_recycler_view_bsd_lots.setHasFixedSize(true);
//        valueEdition_recycler_view_bsd_lots.setLayoutManager(new LinearLayoutManager(this));
//        valueEdition_recycler_view_bsd_lots.setAdapter(adapter);

        value_edition_next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (processes_data!=null){
                    Intent val_details=new Intent(ValueEdition.this,ValueEditionDetails.class);
                    val_details.putExtra("process",processes_data);
                    startActivity(val_details);
                }else {
                    AppUtils.showToast(mContext,"Please select existing lot number");
                }
            }
        });

        back_button_val_edt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void callService() {
        if (AppUtils.isNetworkAvailable(mContext)){
            AppUtils.showCustomProgressDialog(mCustomProgressDialog,"Loading...");
            apiService= AppUrl.getApiClient().create(ApiService.class);
            Call<GettingProcesses> call=apiService.getProcesses();
            call.enqueue(new Callback<GettingProcesses>() {
                @Override
                public void onResponse(Call<GettingProcesses> call, Response<GettingProcesses> response) {
                    AppUtils.dismissCustomProgress(mCustomProgressDialog);
                    if (response.body()!=null){
                        if (response.body().getStatus().contains(AppConstant.MESSAGE)){
                            AppUtils.showToast(mContext,response.body().getMessage());


                            adapter=new GridViewAdapter(mContext, (OnRadioButtonClick) mContext,response.body().getData());
                            valueEdition_recycler_view_bsd_lots.setHasFixedSize(true);
                            valueEdition_recycler_view_bsd_lots.setLayoutManager(new LinearLayoutManager(mContext));
                            valueEdition_recycler_view_bsd_lots.setAdapter(adapter);
                        }
                        else {
                            Log.e("status",response.body().getMessage());
                            AppUtils.showCustomOkDialog(mContext,"",getResources().getString(R.string.error_default),"OK",null);
                        }
                    }
                    else {
                        AppUtils.showCustomOkDialog(mContext,"",getResources().getString(R.string.error_default),"OK",null);
                    }
                }

                @Override
                public void onFailure(Call<GettingProcesses> call, Throwable t) {
                    Log.e("status",t.toString());
                    AppUtils.dismissCustomProgress(mCustomProgressDialog);
                    AppUtils.showCustomOkDialog(mContext,
                            "",
                            getString(R.string.error_default),
                            "OK", null);
                }
            });
        }else {
            AppUtils.showToast(mContext,getString(R.string.error_network));
        }


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRadioClick(Processes_data processes_data) {
        this.processes_data=processes_data;
    }
}
