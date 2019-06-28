package com.PG.testingapp.UI.HeadLessGrading;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.PG.testingapp.Api.ApiService;
import com.PG.testingapp.Api.AppUrl;
import com.PG.testingapp.BaseActivity;
import com.PG.testingapp.R;
import com.PG.testingapp.UI.MenuActivity;
import com.PG.testingapp.Utils.AppConstant;
import com.PG.testingapp.Utils.AppUtils;
import com.PG.testingapp.model.HeadLessGrading.AddingNewGrade;
import com.PG.testingapp.model.HeadLessGrading.GetGroupCodes;
import com.PG.testingapp.model.HeadLessGrading.GetVarietyDetails;
import com.PG.testingapp.model.HeadLessGrading.GroupCodes;
import com.PG.testingapp.model.HeadLessGrading.PackingGradeDetails;
import com.PG.testingapp.model.HeadLessGrading.PackingGradeStatus;
import com.PG.testingapp.model.HeadLessGrading.VarietyCodes;
import com.PG.testingapp.model.Status;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddNewGrade extends BaseActivity implements AdapterView.OnItemSelectedListener {

    private Spinner spinner_select_group,spinner_select_variety,spinner_select_packing_grade;
    private EditText edt_add_new_grade;
    private TextView cancel_add_grade,txt_add_new_grade;
    private Context mContext;
    private ApiService apiService;
    private boolean validate;

    private ArrayList<GroupCodes> codes =  new ArrayList<>();
    private ArrayList<VarietyCodes> varietyDetails =  new ArrayList<>();
    private ArrayList<PackingGradeDetails> packingGradeCodes =  new ArrayList<>();
    private ArrayAdapter<String> countAdapter;
    private ArrayAdapter<String> varietyAdapter;
    private ArrayAdapter<String> packingGradeAdapter;

    private String group_code,packing_grade_code,varaity_code,status;
    private String group_name,varietyName,packing_grade_no;
    private int group_position,variety_position,packing_grade_position;
    private LinearLayout spinner_layout_select_packing_grade,spinner_layout_select_variety,spinner_layout_select_group;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cus_layout_add_new_grade);

        mContext=AddNewGrade.this;

        status=getIntent().getExtras().get("status").toString();

         spinner_select_group=(Spinner)findViewById(R.id.spinner_select_group);
         spinner_select_variety=(Spinner)findViewById(R.id.spinner_select_variety);
         spinner_select_packing_grade=(Spinner)findViewById(R.id.spinner_select_packing_grade);
         edt_add_new_grade=(EditText)findViewById(R.id.edt_add_new_grade);
         cancel_add_grade=(TextView)findViewById(R.id.cancel_add_grade);
         txt_add_new_grade=(TextView)findViewById(R.id.txt_add_new_grade);
        spinner_layout_select_packing_grade=findViewById(R.id.spinner_layout_select_packing_grade);
        spinner_layout_select_variety=findViewById(R.id.spinner_layout_select_variety);
        spinner_layout_select_group=findViewById(R.id.spinner_layout_select_group);

         setSpinner();

        spinner_select_variety.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                varietyName = parent.getSelectedItem().toString();
                variety_position = position;
                if (varietyName != "Select Variety") {
                    if (AppUtils.isNetworkAvailable(mContext)) {
                        varaity_code = varietyDetails.get(position - 1).getFP_Variety_Code();
                        callServiceForPackingGrade(varaity_code);
                    } else {
                        AppUtils.showToast(mContext, getString(R.string.error_network));
                    }
                }
                else {
                   // spinner_h_l_Grade.setSelection(0);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner_select_packing_grade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                packing_grade_no = parent.getSelectedItem().toString();
                packing_grade_position = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        cancel_add_grade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        txt_add_new_grade.setOnClickListener(new View.OnClickListener() {
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
                        if (packing_grade_no != "Select Packing Grade") {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                spinner_layout_select_packing_grade.setBackground(getResources().getDrawable(R.drawable.boarder_shape));
                            }
                            if (doValidation()) {
                                AppUtils.showCustomOkCancelDialog(mContext, "", "Do you want to save grade?", "Yes", "No",
                                        new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                if (AppUtils.isNetworkAvailable(mContext)){
                                                    varaity_code=varietyDetails.get(variety_position-1).getFP_Variety_Code();
                                                    packing_grade_code=packingGradeCodes.get(packing_grade_position-1).getFP_Grade_Code();
                                                    insertNewGrade(varaity_code,packing_grade_code,edt_add_new_grade.getText().toString().trim());
                                                }
                                                else {
                                                    AppUtils.showToast(mContext,getString(R.string.error_network));
                                                }
                                            }
                                        },
                                        new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {

                                            }
                                        });
                            } else {
                                AppUtils.showToast(mContext, "please check fields");
                            }
                        }
                        else {
                            AppUtils.showToast(mContext, "please select packing Grade");
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                spinner_layout_select_packing_grade.setBackground(getResources().getDrawable(R.drawable.yellow_background));
                            }
                        }

                    }
                    else {
                        AppUtils.showToast(mContext, "please select Variety");
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            spinner_layout_select_variety.setBackground(getResources().getDrawable(R.drawable.yellow_background));
                        }
                    }

                } else {
                    AppUtils.showToast(mContext, "please select Group");
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        spinner_layout_select_group.setBackground(getResources().getDrawable(R.drawable.yellow_background));
                    }
                }
            }
        });
    }

    private void insertNewGrade(String varaity_code, String packing_grade_code, String grade_no) {

        AddingNewGrade jsonData=new AddingNewGrade(varaity_code, packing_grade_code,grade_no);
        Gson gson = new Gson();
        String json = gson.toJson(jsonData);
        Log.e("adding grade ", json);

        AppUtils.showCustomProgressDialog(mCustomProgressDialog,"Loading...");
        apiService= AppUrl.getApiClient().create(ApiService.class);
        Call<Status> call=apiService.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           HeadLeassInsertNewGrade(json);
        call.enqueue(new Callback<Status>() {
            @Override
            public void onResponse(Call<Status> call, Response<Status> response) {
                AppUtils.dismissCustomProgress(mCustomProgressDialog);
                Log.e("status","entered");
                if (response.body()!=null){
                    if (response.body().getStatus().contains(AppConstant.MESSAGE)){
                        Log.e("status",response.body().getMessage());
                        AppUtils.showToast(mContext,response.body().getMessage());
                       onBackPressed();

                    }else {
                        Log.e("status","not success");
                        AppUtils.showCustomOkDialog(mContext,"",response.body().getMessage(),"OK",null);
                    }
                }
                else {
                    Log.e("status","response null");
                    AppUtils.showCustomOkDialog(mContext,"",getResources().getString(R.string.error_default),"OK",null);
                }
            }

            @Override
            public void onFailure(Call<Status> call, Throwable t) {
                Log.e("status",t.toString());
                AppUtils.dismissCustomProgress(mCustomProgressDialog);
                AppUtils.showCustomOkDialog(mContext,
                        "",
                        t.getMessage(),
                        "OK", null);
            }
        });
    }

    boolean doValidation() {
        validate = true;
        if (edt_add_new_grade.getText().toString().trim().length() == 0) {
            validate = false;
            edt_add_new_grade.setError("Enter Grade No");
            edt_add_new_grade.requestFocus();
        }
        return validate;
    }


    private void callServiceForPackingGrade(String varaity_code) {

        final List<String> list1 = new ArrayList<>();
        list1.clear();
        list1.add("Select Packing Grade");

        AppUtils.showCustomProgressDialog(mCustomProgressDialog,"Loading...");
        apiService= AppUrl.getApiClient().create(ApiService.class);
        Call<PackingGradeStatus> call=apiService.HeadLeassgetPackingGrade(varaity_code);
        call.enqueue(new Callback<PackingGradeStatus>() {
            @Override
            public void onResponse(Call<PackingGradeStatus> call, Response<PackingGradeStatus> response) {
                AppUtils.dismissCustomProgress(mCustomProgressDialog);
                if (response.body()!=null){
                    if (response.body().getStatus().contains(AppConstant.MESSAGE)){
                      //  AppUtils.showToast(mContext,response.body().getMessage());

                        for (int i=0;i<response.body().getFPVariety().size();i++){
                            list1.add(response.body().getFPVariety().get(i).getPacking_Grade());
                        }
                        packingGradeCodes=response.body().getFPVariety();
                        packingGradeAdapter = new ArrayAdapter<String>(mContext, R.layout.show_count, list1);
                        spinner_select_packing_grade.setVisibility(View.VISIBLE);
                        spinner_select_packing_grade.setAdapter(packingGradeAdapter);
                    }
                    else {
                        Log.e("status",response.body().getMessage());
                        AppUtils.showCustomOkDialog(mContext,"",response.body().getMessage(),"OK",null);
                    }
                }
                else {
                    AppUtils.showCustomOkDialog(mContext,"",getResources().getString(R.string.error_default),"OK",null);
                }
            }

            @Override
            public void onFailure(Call<PackingGradeStatus> call, Throwable t) {
                Log.e("status",t.toString());
                AppUtils.dismissCustomProgress(mCustomProgressDialog);
                AppUtils.showCustomOkDialog(mContext,
                        "",
                        getString(R.string.error_default),
                        "OK", null);
            }
        });
    }

    private void setSpinner() {
        final List<String> list = new ArrayList<>();
        list.clear();
        list.add("Select Group");

        if (AppUtils.isNetworkAvailable(mContext)){
            AppUtils.showCustomProgressDialog(mCustomProgressDialog,"Loading...");
            apiService= AppUrl.getApiClient().create(ApiService.class);
            Call<GetGroupCodes> call=apiService.hlGetFinishedGroups();
            call.enqueue(new Callback<GetGroupCodes>() {
                @Override
                public void onResponse(Call<GetGroupCodes> call, Response<GetGroupCodes> response) {
                    AppUtils.dismissCustomProgress(mCustomProgressDialog);
                    if (response.body()!=null){
                        if (response.body().getStatus().contains(AppConstant.MESSAGE)){
                            //  AppUtils.showToast(mContext,response.body().getMessage());

                            for (int i=0;i<response.body().getCodes().size();i++){
                                list.add(response.body().getCodes().get(i).getFP_Group_Name());
                            }
                            codes=response.body().getCodes();
                            countAdapter = new ArrayAdapter<String>(mContext, R.layout.show_count, list);
                            spinner_select_group.setVisibility(View.VISIBLE);
                            spinner_select_group.setAdapter(countAdapter);
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
                public void onFailure(Call<GetGroupCodes> call, Throwable t) {
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

        spinner_select_group.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
        group_name = parent.getSelectedItem().toString();
        this.group_position = position;

        if (group_name != "Select Group") {
            if (AppUtils.isNetworkAvailable(mContext)) {
                group_code = codes.get(position - 1).getFP_Group_Code();
                callServiceForVariety(group_code);
            } else {
                AppUtils.showToast(mContext, getString(R.string.error_network));
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void callServiceForVariety(String group_id) {

        final List<String> list1 = new ArrayList<>();
        list1.clear();
        list1.add("Select Variety");
        AppUtils.showCustomProgressDialog(mCustomProgressDialog,"Loading...");
        apiService= AppUrl.getApiClient().create(ApiService.class);
        Call<GetVarietyDetails> call=apiService.hlGetVarityCodes(group_id);
        call.enqueue(new Callback<GetVarietyDetails>() {
            @Override
            public void onResponse(Call<GetVarietyDetails> call, Response<GetVarietyDetails> response) {
                AppUtils.dismissCustomProgress(mCustomProgressDialog);
                if (response.body()!=null){
                    if (response.body().getStatus().contains(AppConstant.MESSAGE)){
                      //  AppUtils.showToast(mContext,response.body().getMessage());

                        for (int i=0;i<response.body().getCodes().size();i++){
                            list1.add(response.body().getCodes().get(i).getFP_Variety_Name());
                        }
                        varietyDetails=response.body().getCodes();
                        varietyAdapter = new ArrayAdapter<String>(mContext, R.layout.show_count, list1);
                        spinner_select_variety.setVisibility(View.VISIBLE);
                        spinner_select_variety.setAdapter(varietyAdapter);
                    }
                    else {
                        Log.e("status",response.body().getMessage());
                        AppUtils.showCustomOkDialog(mContext,"",response.body().getMessage(),"OK",null);
                    }
                }
                else {
                    AppUtils.showCustomOkDialog(mContext,"",getResources().getString(R.string.error_default),"OK",null);
                }
            }

            @Override
            public void onFailure(Call<GetVarietyDetails> call, Throwable t) {
                Log.e("status",t.toString());
                AppUtils.dismissCustomProgress(mCustomProgressDialog);
                AppUtils.showCustomOkDialog(mContext,
                        "",
                        getString(R.string.error_default),
                        "OK", null);
            }
        });

    }
}
