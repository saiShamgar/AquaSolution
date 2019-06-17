package com.PG.testingapp.UI.RMRecivingDetails;

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

import com.PG.testingapp.Adapters.RMReceivingAdapter.RmReceivingGrid_screen_2;
import com.PG.testingapp.Api.ApiService;
import com.PG.testingapp.Api.AppUrl;
import com.PG.testingapp.BaseActivity;
import com.PG.testingapp.R;
import com.PG.testingapp.UI.HeadLessGrading.HeadLessGradingDetailsInserted;
import com.PG.testingapp.UI.MenuActivity;
import com.PG.testingapp.Utils.AppConstant;
import com.PG.testingapp.Utils.AppUtils;
import com.PG.testingapp.model.RMReceiving.RMReceive_IGP_No;
import com.PG.testingapp.model.RMReceiving.RmReceivingInsertModel;
import com.PG.testingapp.model.RMReceiving.RmReceivingScreen_2_Grid;
import com.PG.testingapp.model.Status;
import com.PG.testingapp.model.ValueEditionDetaillsModel;
import com.PG.testingapp.model.headOnHeadLessGrading.Lot_details;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RmReceivingScreen_three extends BaseActivity {

    private RMReceive_IGP_No rmReceive_igp_no;
    private RecyclerView recycler_view_rm_receiving_ins;
    private ImageView back_button_rm_recive_details_ins;
    private FloatingActionButton btn_rm_receiving_inserted_Upload;

    private RmReceivingGrid_screen_2 adapter;

    private ArrayList<String> productCode=new ArrayList<>();
    private ArrayList<String> varietyCode=new ArrayList<>();
    private ArrayList<String> countCode=new ArrayList<>();
    private String locationCode;
    private ArrayList<String> quantity=new ArrayList<>();

    private String lot_no,lot_date,ps_no,ps_date;

    private Context context;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rm_receiving_screen_three);

        context=RmReceivingScreen_three.this;

        Bundle b = getIntent().getExtras();
        ArrayList<RmReceivingScreen_2_Grid> detaillsModels = (ArrayList<RmReceivingScreen_2_Grid>) b.getSerializable("objNames");
        rmReceive_igp_no=(RMReceive_IGP_No) getIntent().getSerializableExtra("process");
        lot_no=getIntent().getExtras().getString("lot_no");
        lot_date=getIntent().getExtras().getString("lot_date");
        ps_no=getIntent().getExtras().getString("ps_no");
        ps_date=getIntent().getExtras().getString("ps_date");


        recycler_view_rm_receiving_ins=findViewById(R.id.recycler_view_rm_receiving_ins);
        back_button_rm_recive_details_ins=findViewById(R.id.back_button_rm_recive_details_ins);
        btn_rm_receiving_inserted_Upload=findViewById(R.id.btn_rm_receiving_inserted_Upload);

        adapter=new RmReceivingGrid_screen_2(this,detaillsModels);
        recycler_view_rm_receiving_ins.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recycler_view_rm_receiving_ins.setHasFixedSize(true);
        recycler_view_rm_receiving_ins.setAdapter(adapter);

        btn_rm_receiving_inserted_Upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        for (int i=0;i<detaillsModels.size();i++){
            productCode.add(detaillsModels.get(i).getProduct_code());
            varietyCode.add(String.valueOf(detaillsModels.get(i).getVariety_code()));
            countCode.add(String.valueOf(detaillsModels.get(i).getCount_code()));
            quantity.add(String.valueOf(detaillsModels.get(i).getQuantity()));
            locationCode=detaillsModels.get(i).getLocationCode();
        }

        btn_rm_receiving_inserted_Upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppUtils.showCustomOkCancelDialog(context, "",getString(R.string.upload), "YES", "NO",
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (AppUtils.isNetworkAvailable(context)){
                                    callInsertService();
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

    private void callInsertService() {
        RmReceivingInsertModel jsonData=new RmReceivingInsertModel(
                rmReceive_igp_no.getRM_IGP_No(),
                ps_no,
                rmReceive_igp_no.getFK_Farmer_No(),
                lot_no,
                ps_date,
                rmReceive_igp_no.getAqua_Agent_No(),
                locationCode,
                rmReceive_igp_no.getProcurement_Schedule_No(),
                lot_date,
                productCode,
                varietyCode,
                countCode,
                quantity,
                rmReceive_igp_no.getRM_Inward_Date());

        Gson gson = new Gson();
        String json = gson.toJson(jsonData);
        Log.e("hohl data ",json);

        AppUtils.showCustomProgressDialog(mCustomProgressDialog,"Loading...");
        apiService= AppUrl.getApiClient().create(ApiService.class);
        Call<Status> call=apiService.saveRmReeceivingDetails(json);
        call.enqueue(new Callback<Status>() {
            @Override
            public void onResponse(Call<Status> call, Response<Status> response) {
                AppUtils.dismissCustomProgress(mCustomProgressDialog);
                Log.e("status","entered");
                if (response.body()!=null){
                    if (response.body().getStatus().contains(AppConstant.MESSAGE)){
                        Log.e("status",response.body().getMessage());
                        AppUtils.showToast(context,response.body().getMessage());
                        Intent goback=new Intent(RmReceivingScreen_three.this, MenuActivity.class);
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
