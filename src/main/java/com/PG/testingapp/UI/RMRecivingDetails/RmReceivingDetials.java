package com.PG.testingapp.UI.RMRecivingDetails;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.method.CharacterPickerDialog;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.PG.testingapp.Adapters.HeadLessGrading.HeadLessGrading_2_Grid;
import com.PG.testingapp.Adapters.RMReceivingAdapter.RmReceivingGrid_screen_1;
import com.PG.testingapp.Adapters.RMReceivingAdapter.RmReceivingGrid_screen_2;
import com.PG.testingapp.Api.ApiService;
import com.PG.testingapp.Api.AppUrl;
import com.PG.testingapp.BaseActivity;
import com.PG.testingapp.InterFace.RMReceivingRadioClick;
import com.PG.testingapp.R;
import com.PG.testingapp.UI.AddingNewCounts;
import com.PG.testingapp.UI.HeadOnHeadLessGrading.HOHL_details_inserting;
import com.PG.testingapp.UI.HeadOnHeadLessGrading.HOHL_weights;
import com.PG.testingapp.UI.MenuActivity;
import com.PG.testingapp.UI.ValueEditionDetails;
import com.PG.testingapp.Utils.AppConstant;
import com.PG.testingapp.Utils.AppUtils;
import com.PG.testingapp.model.HeadLessGrading.GetVarietyDetails;
import com.PG.testingapp.model.HeadLessGrading.VarietyCodes;
import com.PG.testingapp.model.RMReceiving.GetCountCodes;
import com.PG.testingapp.model.RMReceiving.GetCountCodoesStatus;
import com.PG.testingapp.model.RMReceiving.RMReceive_IGP_No;
import com.PG.testingapp.model.RMReceiving.RMReceive_IGP_No_Status;
import com.PG.testingapp.model.RMReceiving.RMReceivingLocationDetails;
import com.PG.testingapp.model.RMReceiving.RMRecivingLoationsStatus;
import com.PG.testingapp.model.RMReceiving.RM_MetrialData;
import com.PG.testingapp.model.RMReceiving.RmReceivingScreen_2_Grid;
import com.PG.testingapp.model.ValueEditionDetaillsModel;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RmReceivingDetials extends BaseActivity {

    private TextView txt_rm_inwardDate,txt_rm_inward_pass_no,txt_rm_process_sch_date,txt_rm_process_no,txt_rm_farmer_name
            ,txt_rm_agent_name,txt_rm_Lot_no,txt_rm_lot_date;
    private LinearLayout spinner_layout_rm_recived_loc,spinner_layout_rm_product,spinner_layout_rm_variety,spinner_layout_rm_count;
    private Spinner spinner_rm_recived_loc,spinner_rm_product,spinner_rm_variety,spinner_rm_count;
    private EditText txt_rm_received_details_quantity;
    private TextView txt_rm_receiving_details_weight_btn_save,txt_rm_receiving_details_btn_complete,head_less_add_new_count;
    private RecyclerView rm_receiving_details_recycler_view;
    private RMReceive_IGP_No rmReceive_igp_no;
    private ImageView back_button_rm_recive_details;

    private Context context;
    private ApiService apiService;

    private ArrayAdapter<String> locationAdapter;
    private ArrayAdapter<String> productAdapter;
    private ArrayAdapter<String> varietyAdapter;
    private ArrayAdapter<String> countAdaapter;

    private ArrayList<RMReceivingLocationDetails> locationDetails=new ArrayList<>();
    private ArrayList<RM_MetrialData> productDetails=new ArrayList<>();
    private ArrayList<VarietyCodes> varietyDetails=new ArrayList<>();
    private ArrayList<GetCountCodes> countDetails=new ArrayList<>();

    private ArrayList<RmReceivingScreen_2_Grid> model=new ArrayList<>();

    private String loctationName,productName,varietyName,countName;
    private String locationCode,productCode,varietyCode,countcode;
    private int locationPosition,productPosition,varietyPosition,countPosition;
    private boolean validate;

    private RmReceivingGrid_screen_2 adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rm_receiving_detials);

        context=RmReceivingDetials.this;

        txt_rm_inwardDate=findViewById(R.id.txt_rm_inwardDate);
        txt_rm_inward_pass_no=findViewById(R.id.txt_rm_inward_pass_no);
        txt_rm_process_sch_date=findViewById(R.id.txt_rm_process_sch_date);
        txt_rm_process_no=findViewById(R.id.txt_rm_process_no);
        txt_rm_farmer_name=findViewById(R.id.txt_rm_farmer_name);
        txt_rm_agent_name=findViewById(R.id.txt_rm_agent_name);
        txt_rm_Lot_no=findViewById(R.id.txt_rm_Lot_no);
        txt_rm_lot_date=findViewById(R.id.txt_rm_lot_date);
        head_less_add_new_count=findViewById(R.id.head_less_add_new_count);

        spinner_layout_rm_recived_loc=findViewById(R.id.spinner_layout_rm_recived_loc);
        spinner_layout_rm_product=findViewById(R.id.spinner_layout_rm_product);
        spinner_layout_rm_variety=findViewById(R.id.spinner_layout_rm_variety);
        spinner_layout_rm_count=findViewById(R.id.spinner_layout_rm_count);

        spinner_rm_recived_loc=findViewById(R.id.spinner_rm_recived_loc);
        spinner_rm_product=findViewById(R.id.spinner_rm_product);
        spinner_rm_variety=findViewById(R.id.spinner_rm_variety);
        spinner_rm_count=findViewById(R.id.spinner_rm_count);

        back_button_rm_recive_details=findViewById(R.id.back_button_rm_recive_details);

        txt_rm_received_details_quantity=findViewById(R.id.txt_rm_received_details_quantity);
        rm_receiving_details_recycler_view=findViewById(R.id.rm_receiving_details_recycler_view);

        txt_rm_receiving_details_weight_btn_save=findViewById(R.id.txt_rm_receiving_details_weight_btn_save);
        txt_rm_receiving_details_btn_complete=findViewById(R.id.txt_rm_receiving_details_btn_complete);

        rmReceive_igp_no=(RMReceive_IGP_No) getIntent().getSerializableExtra("process");

        head_less_add_new_count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent new_count=new Intent(RmReceivingDetials.this, AddingNewCounts.class);
                startActivity(new_count);
            }
        });

        if (rmReceive_igp_no!=null){
            txt_rm_inwardDate.setText(AppUtils.dateFormat(rmReceive_igp_no.getRM_Inward_Date()));
            txt_rm_inward_pass_no.setText(rmReceive_igp_no.getRM_IGP_No());
            txt_rm_farmer_name.setText(rmReceive_igp_no.getAqua_Farmer_Name());
            txt_rm_agent_name.setText(rmReceive_igp_no.getAqua_Agent_Name());
        }

        callLocationService();

        spinner_rm_recived_loc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                loctationName = parent.getSelectedItem().toString();
                locationPosition = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner_rm_product.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                productName = parent.getSelectedItem().toString();
                productPosition = position;
                if (productName != "Select Product") {
                    if (AppUtils.isNetworkAvailable(context)) {
                        productCode=productDetails.get(position - 1).getMaterial_Group_Code();
                        callServiceForVariety(productCode);

                    } else {
                        AppUtils.showToast(context, getString(R.string.error_network));
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner_rm_variety.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                varietyName = parent.getSelectedItem().toString();
                varietyPosition = position;

                if (varietyName != "Select Variety") {
                    if (AppUtils.isNetworkAvailable(context)) {
                        varietyCode=varietyDetails.get(position - 1).getFP_Variety_Code();
                        callServiceForCount(varietyCode);

                    } else {
                        AppUtils.showToast(context, getString(R.string.error_network));
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner_rm_count.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                countName = parent.getSelectedItem().toString();
                countPosition = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        txt_rm_receiving_details_weight_btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (loctationName != "Select Location") {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        spinner_layout_rm_recived_loc.setBackground(getResources().getDrawable(R.drawable.white_border));
                    }
                    if (productName != "Select Product") {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            spinner_layout_rm_product.setBackground(getResources().getDrawable(R.drawable.stroke_back_ground_gray));
                        }
                        if (varietyName != "Select Variety") {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                spinner_layout_rm_variety.setBackground(getResources().getDrawable(R.drawable.stroke_back_ground_gray));
                            }

                            if (countName!="Select Count"){
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                    spinner_layout_rm_count.setBackground(getResources().getDrawable(R.drawable.stroke_back_ground_gray));
                                }
                                if (doValidation()) {
                                    AppUtils.showCustomOkCancelDialog(context, "", getString(R.string.next_count_alert), "No", "Yes",
                                            new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {

                                                    varietyCode=varietyDetails.get(varietyPosition-1).getFP_Variety_Code();
                                                    productCode=productDetails.get(productPosition-1).getMaterial_Group_Code();
                                                    countcode=countDetails.get(countPosition-1).getVariety_Count_Code();
                                                    locationCode=locationDetails.get(locationPosition-1).getOrg_office_no();
                                                    RmReceivingScreen_2_Grid details=new RmReceivingScreen_2_Grid();
                                                    details.setProduct(productName);
                                                    details.setVariety(varietyName);
                                                    details.setCount(countName);
                                                    details.setCount_code(countcode);
                                                    details.setProduct_code(productCode);
                                                    details.setVariety_code(varietyCode);
                                                    details.setQuantity(txt_rm_received_details_quantity.getText().toString().trim());
                                                    details.setLocation(loctationName);
                                                    details.setLocationCode(locationCode);

                                                    model.add(details);
                                                    adapter=new RmReceivingGrid_screen_2(getApplicationContext(),model);
                                                    rm_receiving_details_recycler_view.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                                    rm_receiving_details_recycler_view.setHasFixedSize(true);
                                                    rm_receiving_details_recycler_view.setAdapter(adapter);

                                                    spinner_rm_count.setSelection(countPosition);

                                                    txt_rm_received_details_quantity.setText("");
                                                    txt_rm_received_details_quantity.requestFocus();
                                                    txt_rm_received_details_quantity.post(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            final InputMethodManager imm = (InputMethodManager) txt_rm_received_details_quantity.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                                                            imm.showSoftInput(txt_rm_received_details_quantity, InputMethodManager.SHOW_IMPLICIT);
                                                            txt_rm_received_details_quantity.requestFocus(); // needed if you have more then one input
                                                        }
                                                    });
                                                }
                                            },
                                            new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {

                                                    varietyCode=varietyDetails.get(varietyPosition-1).getFP_Variety_Code();
                                                    productCode=productDetails.get(productPosition-1).getMaterial_Group_Code();
                                                    countcode=countDetails.get(countPosition-1).getVariety_Count_Code();
                                                    locationCode=locationDetails.get(locationPosition-1).getOrg_office_no();
                                                    RmReceivingScreen_2_Grid details=new RmReceivingScreen_2_Grid();
                                                    details.setProduct(productName);
                                                    details.setVariety(varietyName);
                                                    details.setCount(countName);
                                                    details.setCount_code(countcode);
                                                    details.setProduct_code(productCode);
                                                    details.setVariety_code(varietyCode);
                                                    details.setQuantity(txt_rm_received_details_quantity.getText().toString().trim());
                                                    details.setLocation(loctationName);
                                                    details.setLocationCode(locationCode);

                                                    model.add(details);
                                                    adapter=new RmReceivingGrid_screen_2(getApplicationContext(),model);
                                                    rm_receiving_details_recycler_view.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                                    rm_receiving_details_recycler_view.setHasFixedSize(true);
                                                    rm_receiving_details_recycler_view.setAdapter(adapter);

                                                    spinner_rm_count.setSelection(0);

                                                    txt_rm_received_details_quantity.setText("");
                                                    txt_rm_received_details_quantity.requestFocus();
                                                    txt_rm_received_details_quantity.post(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            final InputMethodManager imm = (InputMethodManager) txt_rm_received_details_quantity.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                                                            imm.showSoftInput(txt_rm_received_details_quantity, InputMethodManager.SHOW_IMPLICIT);
                                                            txt_rm_received_details_quantity.requestFocus(); // needed if you have more then one input
                                                        }
                                                    });
                                                }
                                            });
                                } else {
                                    AppUtils.showToast(context, "please check fields");
                                }

                            }
                            else {
                                AppUtils.showToast(context, "please select Grade");
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                    spinner_layout_rm_count.setBackground(getResources().getDrawable(R.drawable.yellow_background));
                                }
                            }


                        }
                        else {
                            AppUtils.showToast(context, "please select Grade");
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                spinner_layout_rm_variety.setBackground(getResources().getDrawable(R.drawable.yellow_background));
                            }
                        }

                    }
                    else {
                        AppUtils.showToast(context, "please select Variety");
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            spinner_layout_rm_product.setBackground(getResources().getDrawable(R.drawable.yellow_background));
                        }
                    }

                } else {
                    AppUtils.showToast(context, "please select count");
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        spinner_layout_rm_recived_loc.setBackground(getResources().getDrawable(R.drawable.yellow_background));
                    }
                }
            }
        });

        txt_rm_receiving_details_btn_complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (model.size()!=0){
                    Bundle b = new Bundle();
                    b.putSerializable("objNames", (Serializable) model);
                    Intent insertDetails=new Intent(RmReceivingDetials.this, RmReceivingScreen_three.class);
                    insertDetails.putExtras(b);
                    insertDetails.putExtra("process",rmReceive_igp_no);
                    insertDetails.putExtra("lot_no",txt_rm_Lot_no.getText().toString().trim());
                    insertDetails.putExtra("lot_date",txt_rm_lot_date.getText().toString().trim());
                    insertDetails.putExtra("ps_no",txt_rm_process_no.getText().toString().trim());
                    insertDetails.putExtra("ps_date",txt_rm_process_sch_date.getText().toString().trim());
                    startActivity(insertDetails);
                }else {
                    AppUtils.showToast(context,"please fill details");
                }
            }
        });

        back_button_rm_recive_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    private boolean doValidation() {
        validate = true;
        if (txt_rm_received_details_quantity.getText().toString().trim().length() == 0) {
            validate = false;
            txt_rm_received_details_quantity.setError("Enter Number Of Net");
            txt_rm_received_details_quantity.requestFocus();

        }

        return validate;
    }

    private void callServiceForCount(String varietyCode) {
        if (AppUtils.isNetworkAvailable(context)){
            final List<String> list = new ArrayList<>();
            list.clear();
            list.add("Select Count");
            AppUtils.showCustomProgressDialog(mCustomProgressDialog,"Loading...");
            apiService= AppUrl.getApiClient().create(ApiService.class);
            Call<GetCountCodoesStatus> call=apiService.getRMcountCodes(varietyCode);
            call.enqueue(new Callback<GetCountCodoesStatus>() {
                @Override
                public void onResponse(Call<GetCountCodoesStatus> call, Response<GetCountCodoesStatus> response) {
                    AppUtils.dismissCustomProgress(mCustomProgressDialog);
                    if (response.body()!=null){
                        if (response.body().getStatus().contains(AppConstant.MESSAGE)){
                            if (response.body().getCodes().size()!=0){
                                for (int i=0;i<response.body().getCodes().size();i++){
                                    list.add(response.body().getCodes().get(i).getVariety_Count());
                                }
                                countDetails=response.body().getCodes();
                                countAdaapter = new ArrayAdapter<String>(context, R.layout.show_count, list);
                                spinner_rm_count.setVisibility(View.VISIBLE);
                                spinner_rm_count.setAdapter(countAdaapter);

                            }
                        }
                        else {
                            Log.e("status",response.body().getMessage());
                            spinner_rm_count.setSelection(0);
                            AppUtils.showCustomOkDialog(context, "", response.body().getMessage(), "OK", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                }
                            });
                        }
                    }
                    else {
                        AppUtils.showCustomOkDialog(context,"",getResources().getString(R.string.error_default),"OK",null);
                    }
                }

                @Override
                public void onFailure(Call<GetCountCodoesStatus> call, Throwable t) {
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

    private void callServiceForVariety(String productCode) {
        if (AppUtils.isNetworkAvailable(context)){

            final List<String> list = new ArrayList<>();

            list.clear();
            list.add("Select Variety");
            AppUtils.showCustomProgressDialog(mCustomProgressDialog,"Loading...");
            apiService= AppUrl.getApiClient().create(ApiService.class);
            Call<GetVarietyDetails> call=apiService.getRMVarietyCodes(productCode);
            call.enqueue(new Callback<GetVarietyDetails>() {
                @Override
                public void onResponse(Call<GetVarietyDetails> call, Response<GetVarietyDetails> response) {
                    AppUtils.dismissCustomProgress(mCustomProgressDialog);
                    if (response.body()!=null){
                        if (response.body().getStatus().contains(AppConstant.MESSAGE)){
                            if (response.body().getCodes().size()!=0){

                                for (int i=0;i<response.body().getCodes().size();i++){
                                    list.add(response.body().getCodes().get(i).getFP_Variety_Name());
                                }
                                varietyDetails=response.body().getCodes();
                                varietyAdapter = new ArrayAdapter<String>(context, R.layout.show_count, list);
                                spinner_rm_variety.setVisibility(View.VISIBLE);
                                spinner_rm_variety.setAdapter(varietyAdapter);
                                spinner_rm_variety.setSelection(0);
                                spinner_rm_count.setSelection(0);

                            }
                        }
                        else {
                            Log.e("status",response.body().getMessage());
                            spinner_rm_variety.setSelection(0);
                            spinner_rm_count.setSelection(0);
                            AppUtils.showCustomOkDialog(context, "", response.body().getMessage(), "OK", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                }
                            });
                        }
                    }
                    else {
                        AppUtils.showCustomOkDialog(context,"",getResources().getString(R.string.error_default),"OK",null);
                    }
                }

                @Override
                public void onFailure(Call<GetVarietyDetails> call, Throwable t) {
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


    private void callLocationService() {
        if (AppUtils.isNetworkAvailable(context)){

            final List<String> list = new ArrayList<>();
            final List<String> list1 = new ArrayList<>();
            list.clear();
            list.add("Select Location");

            list1.clear();
            list1.add("Select Product");
            AppUtils.showCustomProgressDialog(mCustomProgressDialog,"Loading...");
            apiService= AppUrl.getApiClient().create(ApiService.class);
            Call<RMRecivingLoationsStatus> call=apiService.getRM_locations();
            call.enqueue(new Callback<RMRecivingLoationsStatus>() {
                @Override
                public void onResponse(Call<RMRecivingLoationsStatus> call, Response<RMRecivingLoationsStatus> response) {
                    AppUtils.dismissCustomProgress(mCustomProgressDialog);
                    if (response.body()!=null){
                        if (response.body().getStatus().contains(AppConstant.MESSAGE)){
                            txt_rm_Lot_no.setText(response.body().getLotnum());
                            txt_rm_process_no.setText(response.body().getProcessnum());

                            CountDownTimer newtimer = new CountDownTimer(1000000000, 1000) {
                                public void onTick(long millisUntilFinished) {
                                    String saveCurrentDate;
                                    Calendar c = Calendar.getInstance();
                                    SimpleDateFormat currentDate=new SimpleDateFormat("dd-MM-yyyy");
                                    saveCurrentDate=currentDate.format(c.getTime());
                                    txt_rm_lot_date.setText(saveCurrentDate);
                                    txt_rm_process_sch_date.setText(saveCurrentDate);
                                }
                                public void onFinish() {
                                }
                            };
                            newtimer.start();
                            if (response.body().getData().size()!=0){

                                for (int i=0;i<response.body().getData().size();i++){
                                    list.add(response.body().getData().get(i).getOrg_office_Name());
                                }
                                locationDetails=response.body().getData();
                                locationAdapter = new ArrayAdapter<String>(context, R.layout.show_count, list);
                                spinner_rm_recived_loc.setVisibility(View.VISIBLE);
                                spinner_rm_recived_loc.setAdapter(locationAdapter);

                                for (int i=0;i<response.body().getMaterialgroup().size();i++){
                                    list1.add(response.body().getMaterialgroup().get(i).getMaterial_Group_Name());
                                }
                                productDetails=response.body().getMaterialgroup();
                                productAdapter = new ArrayAdapter<String>(context, R.layout.show_count, list1);
                                spinner_rm_product.setVisibility(View.VISIBLE);
                                spinner_rm_product.setAdapter(productAdapter);

                              

                            }
                        }
                        else {
                            Log.e("status",response.body().getMessage());
                            AppUtils.showCustomOkDialog(context, "", response.body().getMessage(), "OK", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                }
                            });
                        }
                    }
                    else {
                        AppUtils.showCustomOkDialog(context,"",getResources().getString(R.string.error_default),"OK",null);
                    }
                }

                @Override
                public void onFailure(Call<RMRecivingLoationsStatus> call, Throwable t) {
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
    public void onBackPressed() {
        //  super.onBackPressed();
        AppUtils.showCustomOkCancelDialog(this, "", "Do you want to go back without saving data?", "No", "Yes",
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        RmReceivingDetials.super.onBackPressed();
                    }
                });
    }
}
