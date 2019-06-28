package com.PG.testingapp.UI.RmAnalysis;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.PG.testingapp.Adapters.RmAnalysis.Rm_analysis_screen_1_adapter;
import com.PG.testingapp.Api.ApiService;
import com.PG.testingapp.Api.AppUrl;
import com.PG.testingapp.BaseActivity;
import com.PG.testingapp.InterFace.RmAnalysis_RadioClick;
import com.PG.testingapp.R;
import com.PG.testingapp.UI.MenuActivity;
import com.PG.testingapp.Utils.AppConstant;
import com.PG.testingapp.Utils.AppUtils;
import com.PG.testingapp.Utils.SharedPreferenceConfig;
import com.PG.testingapp.model.RmAnalysis.RmAnaalysisDetailsStatus;
import com.PG.testingapp.model.RmAnalysis.RmAnalysisDetailsModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RmAnalysis extends BaseActivity implements RmAnalysis_RadioClick {

    private RecyclerView rm_analysis_rv;
    private ImageView back_button_rm_analysis;
    private Button btn_next_rm_analysis;
    private Context context;
    private ApiService apiService;
    private Rm_analysis_screen_1_adapter adapter;
    private RmAnalysisDetailsModel rmAnalysisDetails;
    private SharedPreferenceConfig config;
    private String supervisor_name;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rm_analysis);
        context=RmAnalysis.this;
        config=new SharedPreferenceConfig(this);

        rm_analysis_rv=findViewById(R.id.rm_analysis_rv);
        back_button_rm_analysis=findViewById(R.id.back_button_rm_analysis);
        btn_next_rm_analysis=findViewById(R.id.btn_next_rm_analysis);

        back_button_rm_analysis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btn_next_rm_analysis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rmAnalysisDetails!=null){
                    Intent val_details=new Intent(RmAnalysis.this, RmAnalysisDetails.class);
                    val_details.putExtra("process",rmAnalysisDetails);
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
            Call<RmAnaalysisDetailsStatus> call=apiService.get_rm_analysis_details(config.readLoginEmpId());
            call.enqueue(new Callback<RmAnaalysisDetailsStatus>() {
                @Override
                public void onResponse(Call<RmAnaalysisDetailsStatus> call, Response<RmAnaalysisDetailsStatus> response) {
                    AppUtils.dismissCustomProgress(mCustomProgressDialog);
                    if (response.body()!=null){
                        if (response.body().getStatus().contains(AppConstant.MESSAGE)){
                            AppUtils.showToast(context,response.body().getMessage());

                            supervisor_name=response.body().getSupervisor();
                            if (response.body().getData().size()!=0){
                                adapter=new Rm_analysis_screen_1_adapter(getApplicationContext(),response.body().getData(),(RmAnalysis_RadioClick) context);
                                rm_analysis_rv.setHasFixedSize(true);
                                rm_analysis_rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                rm_analysis_rv.setAdapter(adapter);
                            }
                        }
                        else {
                            btn_next_rm_analysis.setVisibility(View.GONE);
                            Log.e("status",response.body().getMessage());
                            AppUtils.showCustomOkDialog(context, "", response.body().getMessage(), "OK", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent goback=new Intent(RmAnalysis.this, MenuActivity.class);
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
                                Intent goback=new Intent(RmAnalysis.this, MenuActivity.class);
                                goback.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(goback);
                                finish();
                            }
                        });
                    }
                }

                @Override
                public void onFailure(Call<RmAnaalysisDetailsStatus> call, Throwable t) {
                    Log.e("status",t.toString());
                    AppUtils.dismissCustomProgress(mCustomProgressDialog);
                    AppUtils.showCustomOkDialog(context,
                            "",
                           t.getMessage(),
                            "OK", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent goback=new Intent(RmAnalysis.this, MenuActivity.class);
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
    public void onRadioClickFT(RmAnalysisDetailsModel processes_data) {
        this.rmAnalysisDetails=processes_data;

    }
}
