package com.PG.testingapp.UI.RMRecivingDetails;

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

import com.PG.testingapp.Adapters.HeadLessGrading.HeadLessGradingGridAdapter;
import com.PG.testingapp.Adapters.RMReceivingAdapter.RmReceivingGrid_screen_1;
import com.PG.testingapp.Api.ApiService;
import com.PG.testingapp.Api.AppUrl;
import com.PG.testingapp.BaseActivity;
import com.PG.testingapp.InterFace.HeadlessGradinRadioClick;
import com.PG.testingapp.InterFace.RMReceivingRadioClick;
import com.PG.testingapp.R;
import com.PG.testingapp.UI.HeadLessGrading.HeadLessGrading;
import com.PG.testingapp.UI.HeadLessGrading.HeadLessGradingDetails;
import com.PG.testingapp.UI.MenuActivity;
import com.PG.testingapp.Utils.AppConstant;
import com.PG.testingapp.Utils.AppUtils;
import com.PG.testingapp.model.HeadLessGrading.LotNumbersStatus;
import com.PG.testingapp.model.RMReceiving.RMReceive_IGP_No;
import com.PG.testingapp.model.RMReceiving.RMReceive_IGP_No_Status;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RMReceiving extends BaseActivity implements RMReceivingRadioClick {

    private RecyclerView rmReceiving_rv;
    private ImageView back_button_rm_recive;
    private Button btn_next_rm_receive;
    private RmReceivingGrid_screen_1 adapter;
    private Context context;
    private ApiService apiService;
    private RMReceive_IGP_No rmReceive_igp_no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rmreceiving);
        context=RMReceiving.this;

        rmReceiving_rv=findViewById(R.id.rmReceiving_rv);
        back_button_rm_recive=findViewById(R.id.back_button_rm_recive);
        btn_next_rm_receive=findViewById(R.id.btn_next_rm_receive);

        back_button_rm_recive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btn_next_rm_receive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rmReceive_igp_no!=null){
                    Intent val_details=new Intent(RMReceiving.this, RmReceivingDetials.class);
                    val_details.putExtra("process",rmReceive_igp_no);
                    val_details.putExtra("status","RMR");
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
            Call<RMReceive_IGP_No_Status> call=apiService.getIGP_no();
            call.enqueue(new Callback<RMReceive_IGP_No_Status>() {
                @Override
                public void onResponse(Call<RMReceive_IGP_No_Status> call, Response<RMReceive_IGP_No_Status> response) {
                    AppUtils.dismissCustomProgress(mCustomProgressDialog);
                    if (response.body()!=null){
                        if (response.body().getStatus().contains(AppConstant.MESSAGE)){
                            AppUtils.showToast(context,response.body().getMessage());
                            if (response.body().getData().size()!=0){
                                adapter=new RmReceivingGrid_screen_1(getApplicationContext(),response.body().getData(),(RMReceivingRadioClick) context);
                                rmReceiving_rv.setHasFixedSize(true);
                                rmReceiving_rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                rmReceiving_rv.setAdapter(adapter);
                            }
                        }
                        else {
                            btn_next_rm_receive.setVisibility(View.GONE);
                            Log.e("status",response.body().getMessage());
                            AppUtils.showCustomOkDialog(context, "", response.body().getMessage(), "OK", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent goback=new Intent(RMReceiving.this, MenuActivity.class);
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
                                Intent goback=new Intent(RMReceiving.this, MenuActivity.class);
                                goback.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(goback);
                                finish();
                            }
                        });
                    }
                }

                @Override
                public void onFailure(Call<RMReceive_IGP_No_Status> call, Throwable t) {
                    Log.e("status",t.toString());
                    AppUtils.dismissCustomProgress(mCustomProgressDialog);
                    AppUtils.showCustomOkDialog(context,
                            "",
                           t.getMessage(),
                            "OK", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent goback=new Intent(RMReceiving.this, MenuActivity.class);
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
    public void onRadioClick(RMReceive_IGP_No rmReceive_igp_no) {
        this.rmReceive_igp_no=rmReceive_igp_no;

    }
}
