package com.PG.testingapp.UI;

import android.content.Context;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.CharacterPickerDialog;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.PG.testingapp.Api.ApiService;
import com.PG.testingapp.Api.AppUrl;
import com.PG.testingapp.BaseActivity;
import com.PG.testingapp.R;
import com.PG.testingapp.Utils.AppConstant;
import com.PG.testingapp.Utils.AppUtils;
import com.PG.testingapp.model.HeadLessGrading.GetGroupCodes;
import com.PG.testingapp.model.HeadLessGrading.GetVarietyDetails;
import com.PG.testingapp.model.Status;
import com.PG.testingapp.model.Updations.NewCountGroupActualCodes;
import com.PG.testingapp.model.Updations.NewCountGroupCodes;
import com.PG.testingapp.model.Updations.NewCountInsertJson;
import com.PG.testingapp.model.Updations.NewCountVarietyCodes;
import com.PG.testingapp.model.Updations.NewCountVarietyCodesStatus;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddingNewCounts extends BaseActivity implements AdapterView.OnItemSelectedListener {

    private LinearLayout spinner_layout_select_variety,spinner_layout_select_group;
    private Spinner spinner_select_group,spinner_select_variety;
    private EditText edt_add_new_count;
    private TextView cancel_add_count,txt_add_new_count;
    private Context context;
    private ApiService apiService;
    private String group_code,packing_grade_code,varaity_code,status;
    private String group_name,varietyName,packing_grade_no;
    private int group_position,variety_position,packing_grade_position;

    private ArrayList<NewCountGroupActualCodes> groupActualCodes=new ArrayList<>();
    private ArrayList<NewCountVarietyCodes> varietyDetails=new ArrayList<>();

    private ArrayAdapter<String> groupAdapter;
    private ArrayAdapter<String> varietyAdapter;
    private boolean validate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_new_counts);
        context=AddingNewCounts.this;

        spinner_select_group=findViewById(R.id.spinner_select_group);
        spinner_select_variety=findViewById(R.id.spinner_select_variety);
        edt_add_new_count=findViewById(R.id.edt_add_new_count);
        cancel_add_count=findViewById(R.id.cancel_add_count);
        txt_add_new_count=findViewById(R.id.txt_add_new_count);
        spinner_layout_select_variety=findViewById(R.id.spinner_layout_select_variety);
        spinner_layout_select_group=findViewById(R.id.spinner_layout_select_group);

        setSpinner();

        cancel_add_count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        spinner_select_variety.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                varietyName = parent.getSelectedItem().toString();
                variety_position = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        txt_add_new_count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (group_name != "Select Group") {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        spinner_layout_select_group.setBackground(getResources().getDrawable(R.drawable.boarder_shape));
                    }
                    if (varietyName != "Select Variety") {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            spinner_layout_select_variety.setBackground(getResources().getDrawable(R.drawable.boarder_shape));
                        }
                        if (doValidation()) {
                            AppUtils.showCustomOkCancelDialog(context, "", "Do you want to save grade?", "Yes", "No",
                                    new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            if (AppUtils.isNetworkAvailable(context)){
                                                varaity_code=varietyDetails.get(variety_position-1).getFP_Variety_Code();
                                                group_code=groupActualCodes.get(group_position-1).getMaterial_Group_Code();
                                                insertNewGrade(varaity_code,group_code,edt_add_new_count.getText().toString().trim());
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
                        } else {
                            AppUtils.showToast(context, "please check fields");
                        }
                    }
                    else {
                        AppUtils.showToast(context, "please select Variety");
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            spinner_layout_select_variety.setBackground(getResources().getDrawable(R.drawable.yellow_background));
                        }
                    }

                } else {
                    AppUtils.showToast(context, "please select Group");
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        spinner_layout_select_group.setBackground(getResources().getDrawable(R.drawable.yellow_background));
                    }
                }
            }
        });
    }

    private void insertNewGrade(String varaity_code, String group_code, String countCode) {

        NewCountInsertJson jsonData=new NewCountInsertJson(varaity_code,countCode,group_code);
        Gson gson = new Gson();
        String json = gson.toJson(jsonData);
        Log.e("adding count ", json);

        AppUtils.showCustomProgressDialog(mCustomProgressDialog,"Loading...");
        apiService= AppUrl.getApiClient().create(ApiService.class);
        Call<Status> call=apiService.insertNewCount(json);
        call.enqueue(new Callback<Status>() {
            @Override
            public void onResponse(Call<Status> call, Response<Status> response) {
                AppUtils.dismissCustomProgress(mCustomProgressDialog);
                Log.e("status","entered");
                if (response.body()!=null){
                    if (response.body().getStatus().contains(AppConstant.MESSAGE)){
                        Log.e("status",response.body().getMessage());
                        AppUtils.showToast(context,response.body().getMessage());
                        onBackPressed();

                    }else {
                        Log.e("status","not success");
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
                        t.getMessage(),
                        "OK", null);
            }
        });

    }

    boolean doValidation() {
        validate = true;
        if (edt_add_new_count.getText().toString().trim().length() == 0) {
            validate = false;
            edt_add_new_count.setError("Enter Count");
            edt_add_new_count.requestFocus();
        }
        return validate;
    }


    private void setSpinner() {
        final List<String> list = new ArrayList<>();
        list.clear();
        list.add("Select Group");

        if (AppUtils.isNetworkAvailable(context)){
            AppUtils.showCustomProgressDialog(mCustomProgressDialog,"Loading...");
            apiService= AppUrl.getApiClient().create(ApiService.class);
            Call<NewCountGroupCodes> call=apiService.getNewGroupCodes();
            call.enqueue(new Callback<NewCountGroupCodes>() {
                @Override
                public void onResponse(Call<NewCountGroupCodes> call, Response<NewCountGroupCodes> response) {
                    AppUtils.dismissCustomProgress(mCustomProgressDialog);
                    if (response.body()!=null){
                        if (response.body().getStatus().contains(AppConstant.MESSAGE)){
                            //  AppUtils.showToast(mContext,response.body().getMessage());

                            for (int i=0;i<response.body().getData().size();i++){
                                list.add(response.body().getData().get(i).getMaterial_Group_Name());
                            }
                            groupActualCodes=response.body().getData();
                            groupAdapter = new ArrayAdapter<String>(context, R.layout.show_count, list);
                            spinner_select_group.setVisibility(View.VISIBLE);
                            spinner_select_group.setAdapter(groupAdapter);
                        }
                        else {
                            Log.e("status",response.body().getMessage());
                            AppUtils.showCustomOkDialog(context,"",getResources().getString(R.string.error_default),"OK",null);
                        }
                    }
                    else {
                        AppUtils.showCustomOkDialog(context,"",getResources().getString(R.string.error_default),"OK",null);
                    }
                }

                @Override
                public void onFailure(Call<NewCountGroupCodes> call, Throwable t) {
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

        spinner_select_group.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
        group_name = parent.getSelectedItem().toString();
        this.group_position = position;

        if (group_name != "Select Group") {
            if (AppUtils.isNetworkAvailable(context)) {
                group_code = groupActualCodes.get(position - 1).getMaterial_Group_Code();
                callServiceForVariety(group_code);
            } else {
                AppUtils.showToast(context, getString(R.string.error_network));
            }
        }
    }

    private void callServiceForVariety(String group_code) {
        final List<String> list1 = new ArrayList<>();
        list1.clear();
        list1.add("Select Variety");
        AppUtils.showCustomProgressDialog(mCustomProgressDialog,"Loading...");
        apiService= AppUrl.getApiClient().create(ApiService.class);
        Call<NewCountVarietyCodesStatus> call=apiService.getNewVarietyCodes(group_code);
        call.enqueue(new Callback<NewCountVarietyCodesStatus>() {
            @Override
            public void onResponse(Call<NewCountVarietyCodesStatus> call, Response<NewCountVarietyCodesStatus> response) {
                AppUtils.dismissCustomProgress(mCustomProgressDialog);
                if (response.body()!=null){
                    if (response.body().getStatus().contains(AppConstant.MESSAGE)){
                        //  AppUtils.showToast(mContext,response.body().getMessage());

                        for (int i=0;i<response.body().getFPVariety().size();i++){
                            list1.add(response.body().getFPVariety().get(i).getFP_Variety_Name());
                        }
                        varietyDetails=response.body().getFPVariety();
                        varietyAdapter = new ArrayAdapter<String>(context, R.layout.show_count, list1);
                        spinner_select_variety.setVisibility(View.VISIBLE);
                        spinner_select_variety.setAdapter(varietyAdapter);
                    }
                    else {
                        Log.e("status",response.body().getMessage());
                        AppUtils.showCustomOkDialog(context,"",response.body().getMessage(),"OK",null);
                    }
                }
                else {
                    AppUtils.showCustomOkDialog(context,"",getResources().getString(R.string.error_default),"OK",null);
                }
            }

            @Override
            public void onFailure(Call<NewCountVarietyCodesStatus> call, Throwable t) {
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
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
