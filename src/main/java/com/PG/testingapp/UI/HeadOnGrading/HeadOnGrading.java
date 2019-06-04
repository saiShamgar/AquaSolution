package com.PG.testingapp.UI.HeadOnGrading;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.PG.testingapp.Adapters.HeadLessGrading.HeadLessGradingGridAdapter;
import com.PG.testingapp.Adapters.HeadOnHeadlessAdapters.HeadOnHeadlessGridAdapter;
import com.PG.testingapp.Api.ApiService;
import com.PG.testingapp.Api.AppUrl;
import com.PG.testingapp.BaseActivity;
import com.PG.testingapp.InterFace.HeadOnHeadLessRadioClick;
import com.PG.testingapp.InterFace.HeadlessGradinRadioClick;
import com.PG.testingapp.R;
import com.PG.testingapp.UI.HeadLessGrading.HeadLessGrading;
import com.PG.testingapp.UI.HeadLessGrading.HeadLessGradingDetails;
import com.PG.testingapp.Utils.AppConstant;
import com.PG.testingapp.Utils.AppUtils;
import com.PG.testingapp.Utils.SharedPreferenceConfig;
import com.PG.testingapp.model.HeadLessGrading.LotNumbersStatus;
import com.PG.testingapp.model.HeadLessGrading.Lot_numbers;
import com.PG.testingapp.model.headOnHeadLessGrading.GetLotNumbers_HOHL_status;
import com.PG.testingapp.model.headOnHeadLessGrading.Lot_details;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HeadOnGrading extends BaseActivity implements HeadlessGradinRadioClick {

    //Widgets
    private RecyclerView valueEdition_recycler_view_bsd_lots;
    private TextView value_edition_next_btn;
    private ImageView back_button_val_edt;
    private Context context;

    private HeadLessGradingGridAdapter adapter;
    private ApiService apiService;

    private SharedPreferenceConfig config;
    private Lot_numbers lotNumbers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_head_on_grading);

        context= HeadOnGrading.this;
        config=new SharedPreferenceConfig(this);

        valueEdition_recycler_view_bsd_lots=findViewById(R.id.h_on_recycler_view_bsd_lots);
        value_edition_next_btn=findViewById(R.id.h_on_next_btn);
        back_button_val_edt=findViewById(R.id.back_button_h_on);

        value_edition_next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lotNumbers!=null){
                    Intent val_details=new Intent(HeadOnGrading.this, HeadLessGradingDetails.class);
                    val_details.putExtra("process",lotNumbers);
                    val_details.putExtra("status","HO");
                    startActivity(val_details);
                }else {
                    AppUtils.showToast(context,"Please select existing lot number");
                }
            }
        });

        back_button_val_edt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        callService();
    }

    private void callService() {
        if (AppUtils.isNetworkAvailable(context)){
            AppUtils.showCustomProgressDialog(mCustomProgressDialog,"Loading...");
            apiService= AppUrl.getApiClient().create(ApiService.class);
            Call<LotNumbersStatus> call=apiService.getlotNum_h_on();
            call.enqueue(new Callback<LotNumbersStatus>() {
                @Override
                public void onResponse(Call<LotNumbersStatus> call, Response<LotNumbersStatus> response) {
                    AppUtils.dismissCustomProgress(mCustomProgressDialog);
                    if (response.body()!=null){
                        if (response.body().getStatus().contains(AppConstant.MESSAGE)){
                            AppUtils.showToast(context,response.body().getMessage());

                            if (response.body().getLot_No().size()!=0){
                                adapter=new HeadLessGradingGridAdapter(context, (HeadlessGradinRadioClick) context,response.body().getLot_No());
                                valueEdition_recycler_view_bsd_lots.setHasFixedSize(true);
                                valueEdition_recycler_view_bsd_lots.setLayoutManager(new LinearLayoutManager(context));
                                valueEdition_recycler_view_bsd_lots.setAdapter(adapter);
                            }
                            //  Log.e("locations",response.body().getLocations().toString());

                        }
                        else {
                            value_edition_next_btn.setVisibility(View.GONE);
                            Log.e("status",response.body().getMessage());
                            AppUtils.showCustomOkDialog(context,"",response.body().getMessage(),"OK",null);
                        }
                    }
                    else {
                        AppUtils.showCustomOkDialog(context,"",getResources().getString(R.string.error_default),"OK",null);
                    }
                }

                @Override
                public void onFailure(Call<LotNumbersStatus> call, Throwable t) {
                    Log.e("status",t.toString());
                    AppUtils.dismissCustomProgress(mCustomProgressDialog);
                    AppUtils.showCustomOkDialog(context,
                            "",
                            getString(R.string.error_default),
                            "OK", null);
                }
            });
        }else {
            AppUtils.showToast(context,getString(R.string.error_network));
        }
    }

    @Override
    public void onRadioClickFT(Lot_numbers processes_data) {
        this.lotNumbers=processes_data;
    }
}
