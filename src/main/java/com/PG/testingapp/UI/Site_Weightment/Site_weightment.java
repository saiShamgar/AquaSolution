package com.PG.testingapp.UI.Site_Weightment;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
import com.PG.testingapp.Utils.GpsLocation;
import com.PG.testingapp.model.Events;
import com.PG.testingapp.model.GetScheduleNo;
import com.PG.testingapp.model.GettingScheduleDetails;
import com.PG.testingapp.model.GettingVeriatyCodes;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStates;
import com.google.android.gms.location.LocationSettingsStatusCodes;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Site_weightment extends BaseActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener,GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    //widgets
    private Spinner spinner_siteweightment_scheduleno,spinner_siteweightment_enquiryno;
    private Button btnSiteWeightmentNext;
    private TextView edttxt_siteweighment_scheduledate,edttxt_siteweighment_grader,edttxt_siteweighment_vehicalno,
            edttxt_siteweighment_helper,edttxt_siteweighment_helper1,edttxt_siteweighment_agentdetails,edttxt_siteweighment_farmername
            ,edttxt_siteweighment_place,edttxt_siteweighment_navigationmap,date_siteweighment_boxs,nets_siteweighment_edttxt
            ,edttxt_siteweighment_materialgroup,edttxt_siteweighment_varietyname,edttxt_siteweighment_total_count,edttext_siteweighment_total;


    private RelativeLayout layout_sw_child_sch,layout_sw_enquiry_parent;
    private LinearLayout layout_sw_enquiry_child;
    private Context mContext;
    private ApiService apiService;
    private ArrayAdapter<String> countAdapter;
    private ArrayAdapter<String> enqueryAdapter;

    //Location
    private static final int REQUEST_CODE = 123;
    private GoogleApiClient googleApiClient = null;
    protected static final int REQUEST_CHECK_SETTINGS = 0x1;

    private Events.ActivityServiceMessage message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_site_weightment);
        checkLocation();
        init();
    }
    void checkLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_NETWORK_STATE}, 2);
            }
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
// Check for the integer request code originally supplied to startResolutionForResult().
            case REQUEST_CHECK_SETTINGS:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        checkLocation();
                        Log.d("myLocation", "onActivityResult");
                        break;
                    case Activity.RESULT_CANCELED:
                        AppUtils.showToast(mContext, "Please turn on location");
                        enableGps();
                        break;
                }
                break;
        }
    }

    private void init() {
        mContext=Site_weightment.this;
        enableGps();
        startService(new Intent(mContext, GpsLocation.class));
        spinner_siteweightment_scheduleno=findViewById(R.id.spinner_siteweightment_scheduleno);
        layout_sw_child_sch=findViewById(R.id.layout_sw_child_sch);
        layout_sw_enquiry_parent=findViewById(R.id.layout_sw_enquiry_parent);
        layout_sw_enquiry_child=findViewById(R.id.layout_sw_enquiry_child);
        edttxt_siteweighment_scheduledate=findViewById(R.id.edttxt_siteweighment_scheduledate);
        edttxt_siteweighment_grader=findViewById(R.id.edttxt_siteweighment_grader);
        edttxt_siteweighment_vehicalno=findViewById(R.id.edttxt_siteweighment_vehicalno);
        spinner_siteweightment_enquiryno=findViewById(R.id.spinner_siteweightment_enquiryno);
        edttxt_siteweighment_helper=findViewById(R.id.edttxt_siteweighment_helper);
        edttxt_siteweighment_helper1=findViewById(R.id.edttxt_siteweighment_helper1);
        btnSiteWeightmentNext=findViewById(R.id.btnSiteWeightmentNext);
        edttxt_siteweighment_agentdetails=findViewById(R.id.edttxt_siteweighment_agentdetails);
        edttxt_siteweighment_farmername=findViewById(R.id.edttxt_siteweighment_farmername);
        edttxt_siteweighment_place=findViewById(R.id.edttxt_siteweighment_place);
        edttxt_siteweighment_navigationmap=findViewById(R.id.edttxt_siteweighment_navigationmap);
        date_siteweighment_boxs=findViewById(R.id.date_siteweighment_boxs);
        nets_siteweighment_edttxt=findViewById(R.id.nets_siteweighment_edttxt);
        edttxt_siteweighment_materialgroup=findViewById(R.id.edttxt_siteweighment_materialgroup);
        edttxt_siteweighment_varietyname=findViewById(R.id.edttxt_siteweighment_varietyname);
        edttxt_siteweighment_total_count=findViewById(R.id.edttxt_siteweighment_total_count);
        edttext_siteweighment_total=findViewById(R.id.edttext_siteweighment_total);

        layout_sw_child_sch.setVisibility(View.GONE);
        layout_sw_enquiry_parent.setVisibility(View.GONE);
        layout_sw_enquiry_child.setVisibility(View.GONE);
        setSpinner();
    }
    public void enableGps() {
        googleApiClient = new GoogleApiClient.Builder(mContext)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this).build();
        googleApiClient.connect();

        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(30 * 1000);
        locationRequest.setFastestInterval(5 * 1000);
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);
        builder.setAlwaysShow(true); //this is the key ingredient


        PendingResult<LocationSettingsResult> result =
                LocationServices.SettingsApi.checkLocationSettings(googleApiClient, builder.build());
        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(LocationSettingsResult result) {
                final Status status = result.getStatus();
                final LocationSettingsStates state = result.getLocationSettingsStates();
                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.SUCCESS:
                        // All location settings are satisfied. The client can initialize location
                        // requests here.

                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        // Location settings are not satisfied. But could be fixed by showing the user
                        // a dialog.
                        try {
                            // Show the dialog by calling startResolutionForResult(),
                            // and check the result in onActivityResult().
                            status.startResolutionForResult(Site_weightment.this, REQUEST_CHECK_SETTINGS);

                        } catch (IntentSender.SendIntentException e) {
                            // Ignore the error.

                        }
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        // Location settings are not satisfied. However, we have no way to fix the
                        // settings so we won't show the dialog.

                        break;
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 2) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED && grantResults[2] == PackageManager.PERMISSION_GRANTED) {
                enableGps();
            } else {
                checkLocation();
            }
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void getMessage(Events.ActivityServiceMessage message){
        this.message=message;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getSelectedItem().toString()!="Select No"){
            if (AppUtils.isNetworkAvailable(mContext)){
                AppUtils.showCustomProgressDialog(mCustomProgressDialog,"Loading...");
                apiService=AppUrl.getApiClient().create(ApiService.class);
                Call<GettingScheduleDetails> call=apiService.getScheduleDetails(parent.getSelectedItem().toString());
                call.enqueue(new Callback<GettingScheduleDetails>() {
                    @Override
                    public void onResponse(Call<GettingScheduleDetails> call, Response<GettingScheduleDetails> response) {
                        AppUtils.dismissCustomProgress(mCustomProgressDialog);
                        if (response.body()!=null){
                            if (response.body().getStatus().contains(AppConstant.MESSAGE)){
                                AppUtils.showToast(mContext,response.body().getMessage());
                                layout_sw_child_sch.setVisibility(View.VISIBLE);
                                layout_sw_enquiry_parent.setVisibility(View.VISIBLE);
                                String emp_name="";
                                for (int i=0;i<response.body().getSchedule_details().getGrader_emp_id().size();i++){
                                    emp_name=emp_name+response.body().getSchedule_details().getGrader_emp_id().get(i)+" , ";
                                }
                                emp_name=emp_name.replaceAll(" , $","");
                                edttxt_siteweighment_scheduledate.setText(response.body().getSchedule_details().getDate());
                                edttxt_siteweighment_grader.setText(emp_name);
                                edttxt_siteweighment_vehicalno.setText(response.body().getSchedule_details().getVehecial_id());

                                if (response.body().getSchedule_details().getHelper_1().size()!=0){
                                    String helper1="";
                                    for (int i=0;i<response.body().getSchedule_details().getHelper_1().size();i++){
                                        helper1=helper1+response.body().getSchedule_details().getHelper_1().get(i)+" , ";
                                    }
                                    helper1=helper1.replaceAll(" , $","");
                                    edttxt_siteweighment_helper.setText(helper1);
                                }

                                if (response.body().getSchedule_details().getHelper_2().size()!=0){
                                    String helper1="";
                                    for (int i=0;i<response.body().getSchedule_details().getHelper_2().size();i++){
                                        helper1=helper1+response.body().getSchedule_details().getHelper_2().get(i)+" , ";
                                    }
                                    helper1=helper1.replaceAll(" , $","");
                                    edttxt_siteweighment_helper1.setText(helper1);
                                }

                                if (response.body().getSchedule_details().getEnqlist().size()!=0){
                                    final List<String> list = new ArrayList<>();
                                    list.clear();
                                    list.add("Select No");
                                    list.addAll(response.body().getSchedule_details().getEnqlist());
                                    enqueryAdapter = new ArrayAdapter<String>(mContext, R.layout.show_count,list);
                                    spinner_siteweightment_enquiryno.setAdapter(enqueryAdapter);
                                    spinner_siteweightment_enquiryno.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                        @Override
                                        public void onItemSelected(AdapterView<?> parent1, View view1, int position1, long id1) {
                                            if (parent1.getSelectedItem().toString()!="Select No"){
                                                layout_sw_enquiry_child.setVisibility(View.VISIBLE);
                                                btnSiteWeightmentNext.setVisibility(View.VISIBLE);
                                                edttxt_siteweighment_navigationmap.setText(message.getAddress());
                                                Log.e("site Weighment",message.getLatitude()+" Time "+message.getTime());
                                                Log.e("site weighment" ,message.getAddress());
                                            }
                                            else {
                                                layout_sw_enquiry_child.setVisibility(View.GONE);
                                                btnSiteWeightmentNext.setVisibility(View.GONE);
                                            }

                                        }

                                        @Override
                                        public void onNothingSelected(AdapterView<?> parent1) {

                                        }
                                    });
                                }
                                else {
                                    AppUtils.showToast(mContext,"Enquiry Numbers not found for this schedule number");
                                }


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
                    public void onFailure(Call<GettingScheduleDetails> call, Throwable t) {
                        Log.e("status",t.toString());
                        AppUtils.dismissCustomProgress(mCustomProgressDialog);
                        AppUtils.showCustomOkDialog(mContext,
                                "",
                                getString(R.string.error_default),
                                "OK", null);
                    }
                });
            }
            else {
                AppUtils.showCustomOkDialog(mContext,"",getResources().getString(R.string.error_default),"OK",null);
            }
        }else {
            layout_sw_child_sch.setVisibility(View.GONE);
            layout_sw_enquiry_parent.setVisibility(View.GONE);
            layout_sw_enquiry_child.setVisibility(View.GONE);
        }

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

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
