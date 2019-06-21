package com.PG.testingapp.UI.ChemicalTreatmentProcess;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.PG.testingapp.Adapters.RmAnalysis.Rm_analysis_screen_1_adapter;
import com.PG.testingapp.Adapters.Soaking.Soaking_grid_for_screen_one;
import com.PG.testingapp.Api.ApiService;
import com.PG.testingapp.Api.AppUrl;
import com.PG.testingapp.BaseActivity;
import com.PG.testingapp.InterFace.RmAnalysis_RadioClick;
import com.PG.testingapp.InterFace.Soaking_OnRadioClick;
import com.PG.testingapp.R;
import com.PG.testingapp.UI.MenuActivity;
import com.PG.testingapp.UI.RmAnalysis.RmAnalysis;
import com.PG.testingapp.Utils.AppConstant;
import com.PG.testingapp.Utils.AppUtils;
import com.PG.testingapp.Utils.SharedPreferenceConfig;
import com.PG.testingapp.model.RmAnalysis.RmAnaalysisDetailsStatus;
import com.PG.testingapp.model.Soaking.GetLotNo_Soaking_status;
import com.PG.testingapp.model.Soaking.Soaking_details;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChemicalTreatmentProcess extends BaseActivity implements Soaking_OnRadioClick {
    
    private Context context;
    private SharedPreferenceConfig config;
    private RecyclerView chemical_tm_pro_rv;
    private ImageView back_button_chemical_tm_pro;
    private Button btn_next_chemical_tm_pro;
    private Soaking_details soaking_details;
    private String supervisor_name;
    private ApiService apiService;
    private Soaking_grid_for_screen_one adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chemical_treatment_process);

        context=ChemicalTreatmentProcess.this;
        config=new SharedPreferenceConfig(this);

        chemical_tm_pro_rv=findViewById(R.id.chemical_tm_pro_rv);
        back_button_chemical_tm_pro=findViewById(R.id.back_button_chemical_tm_pro);
        btn_next_chemical_tm_pro=findViewById(R.id.btn_next_chemical_tm_pro);

        back_button_chemical_tm_pro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btn_next_chemical_tm_pro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (soaking_details!=null){
                    Intent val_details=new Intent(ChemicalTreatmentProcess.this, Soaking_Details_Screen_2.class);
                    val_details.putExtra("process",soaking_details);
                    val_details.putExtra("status","RMA");
                    val_details.putExtra("supervisor",supervisor_name);
                    startActivity(val_details);
                }else {
                    AppUtils.showToast(context,"Please select existing lot number");
                }
            }
        });

        callService();
    }

    private void callService() {

        if (AppUtils.isNetworkAvailable(context)){
            AppUtils.showCustomProgressDialog(mCustomProgressDialog,"Loading...");
            apiService= AppUrl.getApiClient().create(ApiService.class);
            Call<GetLotNo_Soaking_status> call=apiService.getSoakingLotNo(config.readLoginEmpId());
            call.enqueue(new Callback<GetLotNo_Soaking_status>() {
                @Override
                public void onResponse(Call<GetLotNo_Soaking_status> call, Response<GetLotNo_Soaking_status> response) {
                    AppUtils.dismissCustomProgress(mCustomProgressDialog);
                    if (response.body()!=null){
                        if (response.body().getStatus().contains(AppConstant.MESSAGE)){
                            AppUtils.showToast(context,response.body().getMessage());

                            supervisor_name=response.body().getEmpname();
                            if (response.body().getData().size()!=0){
                                adapter=new Soaking_grid_for_screen_one(getApplicationContext(),(Soaking_OnRadioClick) context,response.body().getData());
                                chemical_tm_pro_rv.setHasFixedSize(true);
                                chemical_tm_pro_rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                chemical_tm_pro_rv.setAdapter(adapter);
                            }
                        }
                        else {
                            btn_next_chemical_tm_pro.setVisibility(View.GONE);
                            Log.e("status",response.body().getMessage());
                            AppUtils.showCustomOkDialog(context, "", response.body().getMessage(), "OK", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent goback=new Intent(ChemicalTreatmentProcess.this, MenuActivity.class);
                                    goback.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(goback);
                                    finish();
                                }
                            });
                        }
                    }
                    else {
                        AppUtils.showCustomOkDialog(context, "", getResources().getString(R.string.error_default), "OK", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent goback=new Intent(ChemicalTreatmentProcess.this, MenuActivity.class);
                                goback.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(goback);
                                finish();
                            }
                        });
                    }
                }

                @Override
                public void onFailure(Call<GetLotNo_Soaking_status> call, Throwable t) {
                    Log.e("status",t.toString());
                    AppUtils.dismissCustomProgress(mCustomProgressDialog);
                    AppUtils.showCustomOkDialog(context,
                            "",
                            t.getMessage().toString(),
                            "OK", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent goback=new Intent(ChemicalTreatmentProcess.this, MenuActivity.class);
                                    goback.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(goback);
                                    finish();
                                }
                            });
                }
            });
        }else {
            AppUtils.showToast(context,getString(R.string.error_network));
        }
    }

    @Override
    public void onRadioClickFT(Soaking_details processes_data) {
        this.soaking_details=processes_data;

    }
}
