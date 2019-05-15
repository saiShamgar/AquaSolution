package com.PG.testingapp.UI.Site_Weightment;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.PG.testingapp.Api.ApiService;
import com.PG.testingapp.Api.AppUrl;
import com.PG.testingapp.BaseActivity;
import com.PG.testingapp.R;
import com.PG.testingapp.Utils.AppConstant;
import com.PG.testingapp.Utils.AppUtils;
import com.PG.testingapp.model.GetScheduleNo;
import com.PG.testingapp.model.GettingVeriatyCodes;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Site_weightment extends BaseActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    //widgets
    private Spinner spinner_siteweightment_scheduleno;


    private RelativeLayout layout_sw_child_sch,layout_sw_enquiry_parent;
    private LinearLayout layout_sw_enquiry_child;
    private Context mContext;
    private ApiService apiService;
    private ArrayAdapter<String> countAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_site_weightment);
        init();


    }

    private void init() {
        mContext=Site_weightment.this;
        spinner_siteweightment_scheduleno=findViewById(R.id.spinner_siteweightment_scheduleno);
        layout_sw_child_sch=findViewById(R.id.layout_sw_child_sch);
        layout_sw_enquiry_parent=findViewById(R.id.layout_sw_enquiry_parent);
        layout_sw_enquiry_child=findViewById(R.id.layout_sw_enquiry_child);

        layout_sw_child_sch.setVisibility(View.GONE);
        layout_sw_enquiry_parent.setVisibility(View.GONE);
        layout_sw_enquiry_child.setVisibility(View.GONE);
        setSpinner();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    private void setSpinner() {
        final List<String> list = new ArrayList<>();
        list.clear();
        list.add("Select No");
        if (AppUtils.isNetworkAvailable(mContext)){
            AppUtils.showCustomProgressDialog(mCustomProgressDialog,"Loading...");
            apiService= AppUrl.getApiClient().create(ApiService.class);
            Call<GetScheduleNo> call=apiService.getScheduleNo();
            call.enqueue(new Callback<GetScheduleNo>() {
                @Override
                public void onResponse(Call<GetScheduleNo> call, Response<GetScheduleNo> response) {
                    AppUtils.dismissCustomProgress(mCustomProgressDialog);
                    if (response.body()!=null){
                        if (response.body().getStatus().contains(AppConstant.MESSAGE)){
                            AppUtils.showToast(mContext,response.body().getMessage());
                            for (int i=0;i<response.body().getData().size();i++){
                                list.add(response.body().getData().get(i));
                            }
                            countAdapter = new ArrayAdapter<String>(mContext, R.layout.show_count, list);
                            spinner_siteweightment_scheduleno.setVisibility(View.VISIBLE);
                            spinner_siteweightment_scheduleno.setAdapter(countAdapter);

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
                public void onFailure(Call<GetScheduleNo> call, Throwable t) {
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

        spinner_siteweightment_scheduleno.setOnItemSelectedListener(this);


    }
}
