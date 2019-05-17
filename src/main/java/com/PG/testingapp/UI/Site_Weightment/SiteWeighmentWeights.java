package com.PG.testingapp.UI.Site_Weightment;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.PG.testingapp.Adapters.ValueEditionDetailsAdapter;
import com.PG.testingapp.BaseActivity;
import com.PG.testingapp.R;
import com.PG.testingapp.UI.ValueEditionDetails;
import com.PG.testingapp.UI.ValueEditionDetailsInserted;
import com.PG.testingapp.Utils.AppUtils;
import com.PG.testingapp.model.ValueEditionDetaillsModel;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class SiteWeighmentWeights extends BaseActivity implements View.OnClickListener {

    private TextView txt_siteWeighment_weight_date_time,txt_siteWeighment_weight_total_tare_wt,txt_siteWeighment_weight_net_weight;
    private EditText txt_siteWeighment_weight_no_nets,edt_siteWeighment_total_weight_kgs,txt_siteWeighment_weight_tare_weight;
    private RecyclerView siteWeighment_weight_recycler_view;
    private TextView txt_siteWeighment_dts_weight_btn_save,txt_siteWeighment_weight_btn_complete;
    private ImageView back_button_site_wt_det;

    private String numberOfNets = "0.0";
    private float tareWeight = 0;
    private float totalWeight = 0;
    private float totTareWeight = 0;
    private float netTotalWeight = 0;
    private String TAG;
    private String count_code;
    private int position;
    private boolean validate;
    private Context mContext;
    private ValueEditionDetailsAdapter valueEditionDetailsAdapter;

    private ArrayList<ValueEditionDetaillsModel> valueEditionDetaillsModel=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_site_weighment_weights);

        mContext=SiteWeighmentWeights.this;

        txt_siteWeighment_weight_date_time=findViewById(R.id.txt_siteWeighment_weight_date_time);
        txt_siteWeighment_weight_total_tare_wt=findViewById(R.id.txt_siteWeighment_weight_total_tare_wt);
        txt_siteWeighment_weight_net_weight=findViewById(R.id.txt_siteWeighment_weight_net_weight);
        txt_siteWeighment_weight_no_nets=findViewById(R.id.txt_siteWeighment_weight_no_nets);
        edt_siteWeighment_total_weight_kgs=findViewById(R.id.edt_siteWeighment_total_weight_kgs);
        txt_siteWeighment_weight_tare_weight=findViewById(R.id.txt_siteWeighment_weight_tare_weight);
        siteWeighment_weight_recycler_view=findViewById(R.id.siteWeighment_weight_recycler_view);
        txt_siteWeighment_dts_weight_btn_save=findViewById(R.id.txt_siteWeighment_dts_weight_btn_save);
        siteWeighment_weight_recycler_view=findViewById(R.id.siteWeighment_weight_recycler_view);
        txt_siteWeighment_weight_btn_complete=findViewById(R.id.txt_siteWeighment_weight_btn_complete);
        back_button_site_wt_det=findViewById(R.id.back_button_site_wt_det);

        CountDownTimer newtimer = new CountDownTimer(1000000000, 1000) {
            public void onTick(long millisUntilFinished) {
                String saveCurrentDate;
                Calendar c = Calendar.getInstance();
                SimpleDateFormat currentDate=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                saveCurrentDate=currentDate.format(c.getTime());
                txt_siteWeighment_weight_date_time.setText(saveCurrentDate);
            }
            public void onFinish() {

            }
        };
        newtimer.start();

        txt_siteWeighment_dts_weight_btn_save.setOnClickListener(this);
        txt_siteWeighment_weight_btn_complete.setOnClickListener(this);
        back_button_site_wt_det.setOnClickListener(this);
        txt_siteWeighment_weight_no_nets.addTextChangedListener(new SiteWeighmentWeights.GenericTextWatcher(txt_siteWeighment_weight_no_nets));
        txt_siteWeighment_weight_tare_weight.addTextChangedListener(new SiteWeighmentWeights.GenericTextWatcher(txt_siteWeighment_weight_tare_weight));

        edt_siteWeighment_total_weight_kgs.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String manualTotalWeight = editable.toString().trim();
                if (manualTotalWeight != null && !manualTotalWeight.isEmpty()) {
                    totalWeight = Float.parseFloat(manualTotalWeight);
                    calculateWeight();
                }

            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.txt_siteWeighment_dts_weight_btn_save:
                    if (doValidation()){
                        AppUtils.showCustomOkCancelDialog(this, "", getString(R.string.siteWtAlert), "YES", "NO",
                                new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        ValueEditionDetaillsModel detaillsModel=new ValueEditionDetaillsModel();
                                        detaillsModel.setTime(txt_siteWeighment_weight_date_time.getText().toString());
                                        detaillsModel.setNo_of_nets(Integer.parseInt(txt_siteWeighment_weight_no_nets.getText().toString()));
                                        detaillsModel.setTotal_weight(Float.parseFloat(edt_siteWeighment_total_weight_kgs.getText().toString()));
                                        detaillsModel.setTotal_tare_weight(Float.parseFloat(txt_siteWeighment_weight_total_tare_wt.getText().toString()));
                                        detaillsModel.setNet_weight(Float.parseFloat(txt_siteWeighment_weight_net_weight.getText().toString()));
                                        detaillsModel.setCummulative_weight(0);
                                        detaillsModel.setCount_code(count_code);

                                        valueEditionDetaillsModel.add(detaillsModel);
                                        valueEditionDetailsAdapter=new ValueEditionDetailsAdapter(getApplicationContext(),valueEditionDetaillsModel);
                                        siteWeighment_weight_recycler_view.setHasFixedSize(true);
                                        siteWeighment_weight_recycler_view.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                        siteWeighment_weight_recycler_view.setAdapter(valueEditionDetailsAdapter);
                                        clearText();

                                    }
                                },
                                new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

//                                        ValueEditionDetaillsModel detaillsModel=new ValueEditionDetaillsModel();
//                                        detaillsModel.setTime(txt_siteWeighment_weight_date_time.getText().toString());
//                                        detaillsModel.setNo_of_nets(Integer.parseInt(txt_siteWeighment_weight_no_nets.getText().toString()));
//                                        detaillsModel.setTotal_weight(Float.parseFloat(edt_siteWeighment_total_weight_kgs.getText().toString()));
//                                        detaillsModel.setTotal_tare_weight(Float.parseFloat(txt_siteWeighment_weight_total_tare_wt.getText().toString()));
//                                        detaillsModel.setNet_weight(Float.parseFloat(txt_siteWeighment_weight_net_weight.getText().toString()));
//                                        detaillsModel.setCummulative_weight(0);
//                                        detaillsModel.setCount_code(count_code);
//
//                                        valueEditionDetaillsModel.add(detaillsModel);
//                                        valueEditionDetailsAdapter=new ValueEditionDetailsAdapter(getApplicationContext(),valueEditionDetaillsModel);
//                                        siteWeighment_weight_recycler_view.setHasFixedSize(true);
//                                        siteWeighment_weight_recycler_view.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//                                        siteWeighment_weight_recycler_view.setAdapter(valueEditionDetailsAdapter);
//
//                                        clearText();
                                    }
                                });
                    }
                    else {
                        AppUtils.showToast(mContext,"please check fields");
                    }

                break;

            case R.id.txt_siteWeighment_weight_btn_complete:
                if (valueEditionDetaillsModel.size()!=0){
                    Bundle b = new Bundle();
                    b.putSerializable("objNames", (Serializable) valueEditionDetaillsModel);
                    Intent insertDetails=new Intent(SiteWeighmentWeights.this, SiteWeighmentDetailsIserted.class);
                    insertDetails.putExtras(b);
                    startActivity(insertDetails);
                }else {
                    AppUtils.showToast(mContext,"please fill details");
                }
                break;

            case R.id.back_button_site_wt_det:
                onBackPressed();
                break;
        }

    }

    private void clearText() {
        edt_siteWeighment_total_weight_kgs.setText("");
        txt_siteWeighment_weight_total_tare_wt.setText("");
        txt_siteWeighment_weight_net_weight.setText("");
        edt_siteWeighment_total_weight_kgs.requestFocus();

    }

    boolean doValidation() {
        validate = true;
        if (txt_siteWeighment_weight_no_nets.getText().toString().trim().length() == 0) {
            validate = false;
            txt_siteWeighment_weight_no_nets.setError("Enter Number Of Net");
            txt_siteWeighment_weight_no_nets.requestFocus();

        } else if (txt_siteWeighment_weight_tare_weight.getText().toString().trim().length() == 0) {
            validate = false;
            txt_siteWeighment_weight_tare_weight.requestFocus();
            txt_siteWeighment_weight_tare_weight.setError("Enter Tare Weight");

        } else if (edt_siteWeighment_total_weight_kgs.getText().toString().trim().length() == 0) {
            validate = false;
            edt_siteWeighment_total_weight_kgs.requestFocus();
            edt_siteWeighment_total_weight_kgs.setError("Enter Tare Weight");

        }

        return validate;
    }

    // Handling multiple edit text
    class GenericTextWatcher implements TextWatcher {

        private View view;

        private GenericTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            Log.d("TextEmpty", "1: " + charSequence.toString() + "," + i + "," + i1 + "," + i2);
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            Log.d("TextEmpty", "2: " + charSequence.toString() + "," + i + "," + i1 + "," + i2);
        }

        public void afterTextChanged(Editable editable) {
            String text;
            if (editable.toString() != null && !editable.toString().equals("")) {
                text = editable.toString();
            } else {
                text = "0.0";
            }
            switch (view.getId()) {
                case R.id.txt_siteWeighment_weight_no_nets:
                    numberOfNets = text;
                    calculateWeight();
                    break;
                case R.id.txt_siteWeighment_weight_tare_weight:
                    tareWeight = Float.parseFloat(text);
                    calculateWeight();
                    break;

                case R.id.edt_siteWeighment_total_weight_kgs:
                    Log.i(TAG, "afterTextChanged: " + text);
                    totalWeight = Float.parseFloat(text);
                    calculateWeight();
                    break;
            }
        }
    }

    private void calculateWeight() {
        totTareWeight = Float.parseFloat(numberOfNets) * tareWeight;
        txt_siteWeighment_weight_total_tare_wt.setText(String.valueOf(Math.round(totTareWeight * 100.0) / 100.0));

        Log.i(TAG, "calculateWeight: " + totalWeight);

        if (totalWeight > 0) {
            Log.i(TAG, "calculateWeight: Inside " + totalWeight);
            netTotalWeight = totalWeight - totTareWeight;
            txt_siteWeighment_weight_net_weight.setText(String.valueOf(Math.round(netTotalWeight * 100.0) / 100.0));
        } else {
            txt_siteWeighment_weight_net_weight.setText("");
        }

        Log.d("TextType", "total: " + txt_siteWeighment_weight_total_tare_wt + " NetWeight: " + txt_siteWeighment_weight_net_weight);
    }
}
