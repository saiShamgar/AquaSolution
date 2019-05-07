package com.PG.testingapp.UI;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.PG.testingapp.Adapters.ValueEditionDetailsAdapter;
import com.PG.testingapp.Adapters.ValueEditionInsertDetailsAdapter;
import com.PG.testingapp.Api.ApiService;
import com.PG.testingapp.Api.AppUrl;
import com.PG.testingapp.BaseActivity;
import com.PG.testingapp.LoginActivity;
import com.PG.testingapp.R;
import com.PG.testingapp.Utils.AppConstant;
import com.PG.testingapp.Utils.AppUtils;
import com.PG.testingapp.model.LoginResponse;
import com.PG.testingapp.model.Processes_data;
import com.PG.testingapp.model.Status;
import com.PG.testingapp.model.ValueEditionDetaillsModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ValueEditionDetailsInserted extends BaseActivity {
    //widgets
    private RecyclerView recycler_view_ho_hl_details;
    private ImageView back_button_val_edt_inserted;
    private ValueEditionInsertDetailsAdapter adapter;
    private Processes_data processes_data;
    private TextView tot_no_nets,tot_net_weight,tot_weight;
    private FloatingActionButton btnValue_edition_Upload;

    private ArrayList<String> list=new ArrayList<>();
    private int no_of_nets;
    private float tot_net_wt,tot_wt;

    private ArrayList<String> date_time=new ArrayList<>();
    private ArrayList<String> VAP_No_of_Nets=new ArrayList<>();
    private ArrayList<String> VAP_Net_Tare_Wt=new ArrayList<>();
    private ArrayList<String> VAP_Total_Weight=new ArrayList<>();
    private ArrayList<String> VAP_Total_Tare_Weight=new ArrayList<>();
    private ArrayList<String> VAP_Net_Weight=new ArrayList<>();
    private ArrayList<String> VAP_Group_Emp_id=new ArrayList<>();
    private ArrayList<String> Variety_Count_Code=new ArrayList<>();

    private Context context;
    private ApiService apiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_value_edition_details_inserted);

        context=ValueEditionDetailsInserted.this;

        Bundle b = getIntent().getExtras();
        ArrayList<ValueEditionDetaillsModel> detaillsModels = (ArrayList<ValueEditionDetaillsModel>) b.getSerializable("objNames");
        processes_data=(Processes_data) getIntent().getSerializableExtra("process");

        recycler_view_ho_hl_details=findViewById(R.id.recycler_view_ho_hl_details);
        back_button_val_edt_inserted=findViewById(R.id.back_button_val_edt_inserted);
        btnValue_edition_Upload=findViewById(R.id.btnValue_edition_Upload);

        tot_no_nets=findViewById(R.id.tot_no_nets);
        tot_net_weight=findViewById(R.id.tot_net_weight);
        tot_weight=findViewById(R.id.tot_weight);

        adapter=new ValueEditionInsertDetailsAdapter(this,detaillsModels);
        recycler_view_ho_hl_details.setHasFixedSize(true);
        recycler_view_ho_hl_details.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recycler_view_ho_hl_details.setAdapter(adapter);

        back_button_val_edt_inserted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        for (int i=0;i<detaillsModels.size();i++){
            date_time.add(detaillsModels.get(i).getTime());
            VAP_No_of_Nets.add(String.valueOf(detaillsModels.get(i).getNo_of_nets()));
            VAP_Net_Tare_Wt.add(String.valueOf(detaillsModels.get(i).getTotal_weight()));
            VAP_Total_Weight.add(String.valueOf(detaillsModels.get(i).getTotal_weight()));
            VAP_Total_Tare_Weight.add(String.valueOf(detaillsModels.get(i).getTotal_tare_weight()));
            VAP_Net_Weight.add(String.valueOf(detaillsModels.get(i).getNet_weight()));
            VAP_Group_Emp_id.add(detaillsModels.get(i).getGroup_person());
            Variety_Count_Code.add(detaillsModels.get(i).getCount_code());


            no_of_nets=no_of_nets+detaillsModels.get(i).getNo_of_nets();
            tot_net_wt=tot_net_wt+detaillsModels.get(i).getNet_weight();
            tot_wt=tot_wt+detaillsModels.get(i).getTotal_weight();
        }
        tot_no_nets.setText(String.valueOf(no_of_nets));
        tot_net_weight.setText(String.valueOf(tot_net_wt));
        tot_weight.setText(String.valueOf(tot_wt));

        btnValue_edition_Upload.setOnClickListener(new View.OnClickListener() {
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
        AppUtils.showCustomProgressDialog(mCustomProgressDialog,"Loading...");
        apiService= AppUrl.getApiClient().create(ApiService.class);
        Call<Status> call=apiService.insertDetails(
                    processes_data.getProcess_schedule_no(),
                    processes_data.getLot_no(),
                    processes_data.getOffice_no(),
                    processes_data.getProcess_code(),
                    processes_data.getWeight_no(),
                    date_time,
                    processes_data.getWorkers_nos(),
                    processes_data.getTables_no(),
                    VAP_No_of_Nets,
                    VAP_Net_Tare_Wt,
                    VAP_Total_Weight,
                VAP_Total_Tare_Weight,
                "",
                processes_data.getProcess_no(),
                VAP_Net_Weight,
                "",
                VAP_Group_Emp_id,
                tot_wt,
                Variety_Count_Code);
        call.enqueue(new Callback<Status>() {
            @Override
            public void onResponse(Call<Status> call, Response<Status> response) {
                AppUtils.dismissCustomProgress(mCustomProgressDialog);
                if (response.body()!=null){
                    if (response.body().getStatus().contains(AppConstant.MESSAGE)){
                        Log.e("status",response.body().getMessage());
                        AppUtils.showToast(context,response.body().getMessage());

                    }else {
                        Log.e("status",response.body().getMessage());
                        AppUtils.showCustomOkDialog(context,"",getResources().getString(R.string.error_default),"OK",null);
                    }
                }
                else {
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
