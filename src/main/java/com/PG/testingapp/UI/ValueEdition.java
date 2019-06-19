package com.PG.testingapp.UI;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbInterface;
import android.hardware.usb.UsbManager;
import android.hardware.usb.UsbRequest;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.PG.testingapp.Adapters.GridViewAdapter;
import com.PG.testingapp.Api.ApiService;
import com.PG.testingapp.Api.AppUrl;
import com.PG.testingapp.BaseActivity;
import com.PG.testingapp.InterFace.OnRadioButtonClick;
import com.PG.testingapp.LoginActivity;
import com.PG.testingapp.R;
import com.PG.testingapp.UI.LocationPlacement.LocationPlacement;
import com.PG.testingapp.Utils.AppConstant;
import com.PG.testingapp.Utils.AppUtils;
import com.PG.testingapp.Utils.SharedPreferenceConfig;
import com.PG.testingapp.Utils.UsbDriver;
import com.PG.testingapp.model.FactoryWeighment.FTLotNumbers;
import com.PG.testingapp.model.GettingProcesses;
import com.PG.testingapp.model.LoginResponse;
import com.PG.testingapp.model.Process_Location;
import com.PG.testingapp.model.Processes_data;
import com.PG.testingapp.model.ValueEdition.GetLotDetails_VD;
import com.PG.testingapp.model.ValueEdition.LotNoDetails_VD;
import com.felhr.usbserial.UsbSerialDevice;
import com.felhr.usbserial.UsbSerialInterface;
import com.google.android.things.pio.PeripheralManager;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ValueEdition extends BaseActivity implements OnRadioButtonClick {

    //Widgets
    private RecyclerView valueEdition_recycler_view_bsd_lots;
    private TextView value_edition_next_btn;
    private ImageView back_button_val_edt;
    private TextView txt_value_edition_location,txt_value_edition_received_from;

    //Adapters
    private GridViewAdapter adapter;
    private Context mContext;
    private SharedPreferenceConfig config;

    //services
    private ApiService apiService;
    private LotNoDetails_VD processes_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_value_edition);

        mContext = ValueEdition.this;
        config=new SharedPreferenceConfig(this);
        //Initializing Variables
        valueEdition_recycler_view_bsd_lots = findViewById(R.id.valueEdition_recycler_view_bsd_lots);
        value_edition_next_btn = findViewById(R.id.value_edition_next_btn);
        back_button_val_edt = findViewById(R.id.back_button_val_edt);
        txt_value_edition_location = findViewById(R.id.txt_value_edition_location);
        txt_value_edition_received_from = findViewById(R.id.txt_value_edition_received_from);
        callService();

        value_edition_next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (processes_data!=null){
                    Intent val_details=new Intent(ValueEdition.this, ValueEditionDetails.class);
                    val_details.putExtra("process",processes_data);
                    val_details.putExtra("status","VD");
                    startActivity(val_details);
                }else {
                    AppUtils.showToast(mContext,"Please select existing lot number");
                }
            }
        });
    }

    private void callService() {
        if (AppUtils.isNetworkAvailable(mContext)){
            AppUtils.showCustomProgressDialog(mCustomProgressDialog,"Loading...");
            apiService= AppUrl.getApiClient().create(ApiService.class);
            Call<GetLotDetails_VD> call=apiService.getProcesses(config.readLoginEmpId());
            call.enqueue(new Callback<GetLotDetails_VD>() {
                @Override
                public void onResponse(Call<GetLotDetails_VD> call, Response<GetLotDetails_VD> response) {
                    AppUtils.dismissCustomProgress(mCustomProgressDialog);
                    if (response.body()!=null){
                        if (response.body().getStatus().contains(AppConstant.MESSAGE)){
                            AppUtils.showToast(mContext,response.body().getMessage());

                            if (response.body().getLotno().size()!=0){
                                txt_value_edition_location.setText(response.body().getLocation().getLocation_Office_Address());
                                txt_value_edition_received_from.setText(response.body().getLocation().getOrg_office_Name());
                                adapter=new GridViewAdapter(mContext, (OnRadioButtonClick) mContext,response.body().getLotno());
                                valueEdition_recycler_view_bsd_lots.setHasFixedSize(true);
                                valueEdition_recycler_view_bsd_lots.setLayoutManager(new LinearLayoutManager(mContext));
                                valueEdition_recycler_view_bsd_lots.setAdapter(adapter);
                            }

                          //  Log.e("locations",response.body().getLocations().toString());

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
                public void onFailure(Call<GetLotDetails_VD> call, Throwable t) {
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

    @Override
    public void onRadioClick(LotNoDetails_VD processes_data) {
        this.processes_data=processes_data;
    }

    @Override
    public void onRadioClickFT(FTLotNumbers processes_data) {

    }


}
