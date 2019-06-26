package com.PG.testingapp.UI.ChemicalTreatmentProcess;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.PG.testingapp.Adapters.Soaking.Soaking_grid_adapter_for_screen_2;
import com.PG.testingapp.Api.ApiService;
import com.PG.testingapp.Api.AppUrl;
import com.PG.testingapp.BaseActivity;
import com.PG.testingapp.R;
import com.PG.testingapp.UI.FactoryWeighment.FactoryWeighmentDetailsInserted;
import com.PG.testingapp.UI.MenuActivity;
import com.PG.testingapp.Utils.AppConstant;
import com.PG.testingapp.Utils.AppUtils;
import com.PG.testingapp.Utils.SharedPreferenceConfig;
import com.PG.testingapp.model.Soaking.SoakingInsertDetails;
import com.PG.testingapp.model.Soaking.Soaking_Grid_two_model;
import com.PG.testingapp.model.Soaking.Soaking_details;
import com.PG.testingapp.model.Status;
import com.google.gson.Gson;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Soaking_details_screen_3 extends BaseActivity {

    private Soaking_details soaking_details;
    private String startTime,endTime,duration;
    private RecyclerView soaking_process_insert_recycler_view;
    private ImageView back_button_soaking_details_ins;
    private FloatingActionButton btn_soaking_inserted_Upload;
    private Soaking_grid_adapter_for_screen_2 adapter;
    private int no_of_nets;
    private float tot_net_wt,tot_wt;

    private ArrayList<String> date_time=new ArrayList<>();
    private ArrayList<String> VAP_No_of_Nets=new ArrayList<>();
    private ArrayList<String> VAP_Net_Tare_Wt=new ArrayList<>();
    private ArrayList<String> VAP_Total_Weight=new ArrayList<>();
    private ArrayList<String> VAP_Total_Tare_Weight=new ArrayList<>();
    private ArrayList<String> VAP_Net_Weight=new ArrayList<>();
    private ArrayList<Integer> VAP_TubNO=new ArrayList<>();
    private TextView tot_no_nets,tot_net_weight,tot_weight;
    private Context context;
    private SharedPreferenceConfig config;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soaking_details_screen_3);

        Bundle b = getIntent().getExtras();
        ArrayList<Soaking_Grid_two_model> detaillsModels = (ArrayList<Soaking_Grid_two_model>) b.getSerializable("objNames");
        soaking_details=(Soaking_details) getIntent().getSerializableExtra("process");
        startTime=getIntent().getExtras().getString("start");
        endTime=getIntent().getExtras().getString("end");
        duration=getIntent().getExtras().getString("duration");

        context=Soaking_details_screen_3.this;
        config=new SharedPreferenceConfig(this);

        soaking_process_insert_recycler_view=findViewById(R.id.soaking_process_insert_recycler_view);
        back_button_soaking_details_ins=findViewById(R.id.back_button_soaking_details_ins);
        btn_soaking_inserted_Upload=findViewById(R.id.btn_soaking_inserted_Upload);

        tot_no_nets=findViewById(R.id.tot_no_nets);
        tot_net_weight=findViewById(R.id.tot_net_weight);
        tot_weight=findViewById(R.id.tot_weight);

        adapter=new Soaking_grid_adapter_for_screen_2(this,detaillsModels);
        soaking_process_insert_recycler_view.setHasFixedSize(true);
        soaking_process_insert_recycler_view.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        soaking_process_insert_recycler_view.setAdapter(adapter);

        back_button_soaking_details_ins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        for (int i=0;i<detaillsModels.size();i++){
            date_time.add(detaillsModels.get(i).getDate());
            VAP_No_of_Nets.add(String.valueOf(detaillsModels.get(i).getNo_of_nets()));
            VAP_Net_Tare_Wt.add(String.valueOf(detaillsModels.get(i).getNet_tare_weight()));
            VAP_Total_Weight.add(String.valueOf(detaillsModels.get(i).getTotal_weight()));
            VAP_Total_Tare_Weight.add(String.valueOf(detaillsModels.get(i).getTotal_tare_weight()));
            VAP_Net_Weight.add(String.valueOf(detaillsModels.get(i).getNet_weight()));
            VAP_TubNO.add(detaillsModels.get(i).getTub_no());


            no_of_nets=no_of_nets+detaillsModels.get(i).getNo_of_nets();
            tot_net_wt=tot_net_wt+detaillsModels.get(i).getNet_weight();
            tot_wt=tot_wt+detaillsModels.get(i).getTotal_weight();
        }

        BigDecimal bd = new BigDecimal(tot_wt).setScale(2, RoundingMode.HALF_UP);
        tot_wt= (float) bd.doubleValue();
        BigDecimal bd1 = new BigDecimal(tot_net_wt).setScale(2, RoundingMode.HALF_UP);
        tot_net_wt= (float) bd1.doubleValue();
        tot_no_nets.setText(String.valueOf(no_of_nets));
        tot_net_weight.setText(String.valueOf(tot_net_wt));
        tot_weight.setText(String.valueOf(tot_wt));

        btn_soaking_inserted_Upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppUtils.showCustomOkCancelDialog(context, "",getString(R.string.upload), "YES", "NO",
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (AppUtils.isNetworkAvailable(context)){
                                    callService();
                                }
                                else {
                                    AppUtils.showToast(context,getString(R.string.error_network));
                                }
                            }
                        },
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        });
            }
        });


    }

    private void callService() {
        SoakingInsertDetails jsonData=new SoakingInsertDetails(
                duration,
                soaking_details.getLot_No(),
                soaking_details.getSoaking_Process_Schedule_No(),
                soaking_details.getProduction_Process_Schedule_No(),
                soaking_details.getProduct_Process_Code(),
                startTime,
                endTime,
                date_time,
                soaking_details.getFP_Production_Grade_Code(),
                VAP_TubNO,
                VAP_No_of_Nets,
                VAP_Net_Tare_Wt,
                VAP_Total_Weight,
                VAP_Total_Tare_Weight,
                VAP_Net_Weight,
                config.readLoginEmpId(),
                soaking_details.getFK_FP_Grade_Code());

        Gson gson = new Gson();
        String json = gson.toJson(jsonData);
        Log.e("soaking data ",json);

        AppUtils.showCustomProgressDialog(mCustomProgressDialog,"Loading...");
        apiService= AppUrl.getApiClient().create(ApiService.class);
        Call<Status> call=apiService.insert_soaking_details(json);
        call.enqueue(new Callback<Status>() {
            @Override
            public void onResponse(Call<Status> call, Response<Status> response) {
                AppUtils.dismissCustomProgress(mCustomProgressDialog);
                Log.e("status","entered");
                if (response.body()!=null){
                    if (response.body().getStatus().contains(AppConstant.MESSAGE2)){
                        Log.e("status",response.body().getMessage());
                        AppUtils.showToast(context,response.body().getMessage());
                        Intent goback=new Intent(Soaking_details_screen_3.this, MenuActivity.class);
                        goback.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(goback);
                        finish();

                    }else {
                        Log.e("status",response.body().getMessage());
                        AppUtils.showCustomOkDialog(context,"",response.body().getMessage(),"OK",null);
                    }
                }
                else {
                    Log.e("status","response null");
                    AppUtils.showCustomOkDialog(context,"",getResources().getString(R.string.error_default),"OK",null);
                }
            }

            @Override
            public void onFailure(Call<Status> call, Throwable t) {
                Log.e("status",t.toString());
                AppUtils.dismissCustomProgress(mCustomProgressDialog);
                AppUtils.showCustomOkDialog(context,
                        "",
                        getString(R.string.error_default),
                        "OK", null);
            }
        });



    }


}
