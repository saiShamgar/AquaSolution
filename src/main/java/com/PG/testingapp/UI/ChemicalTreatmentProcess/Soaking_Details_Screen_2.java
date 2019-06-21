package com.PG.testingapp.UI.ChemicalTreatmentProcess;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.PG.testingapp.Adapters.Soaking.Soaking_grid_adapter_for_screen_2;
import com.PG.testingapp.R;
import com.PG.testingapp.Utils.AppUtils;
import com.PG.testingapp.model.Soaking.Soaking_Grid_two_model;
import com.PG.testingapp.model.Soaking.Soaking_details;
import com.PG.testingapp.model.ValueEditionDetaillsModel;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Soaking_Details_Screen_2 extends AppCompatActivity implements View.OnClickListener {

    Calendar date;
    private Context context;
    private TextView process_start_time,process_end_time,txt_soaking_det_process_duration,txt_soaking_lotNo,txt_soaking_variety,
            txt_soaking_workingGrade,txt_soaking_grade,txt_soaking_recd_quantity,txt_soaking_process_details,txt_soaking_emp_name;
    private ImageView back_button_soaking_details;
    private TextView txt_soaking_date_time,txt_soaking_total_tare_wt,txt_soaking_net_weight,txt_soaking_btn_save,txt_soaking_btn_complete;
    private EditText txt_soaking_no_nets,txt_soaking_tare_weight,edt_soaking_total_weight_kgs,txt_soaking_tub_no;
    private RecyclerView soaking_process_recycler_view;


    private String startTime,endTime;
    private Soaking_details soaking_details;
    private String supervisor;
    private String TAG="Soaking_Details_Screen_2";

    private String numberOfNets = "0.0";
    private float tareWeight = 0;
    private float totalWeight = 0;
    private float totTareWeight = 0;
    private float netTotalWeight = 0;
    private boolean validate;
    private String difference;

    private Soaking_grid_adapter_for_screen_2 adapter;

    private ArrayList<Soaking_Grid_two_model> soaking_grid_two_models=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soaking__details__screen_2);

        context=Soaking_Details_Screen_2.this;
        soaking_details=(Soaking_details) getIntent().getSerializableExtra("process");
        supervisor=getIntent().getExtras().getString("supervisor");

        process_start_time=findViewById(R.id.process_start_time);
        process_end_time=findViewById(R.id.process_end_time);
        txt_soaking_det_process_duration=findViewById(R.id.txt_soaking_det_process_duration);

        txt_soaking_lotNo=findViewById(R.id.txt_soaking_lotNo);
        txt_soaking_variety=findViewById(R.id.txt_soaking_variety);
        txt_soaking_workingGrade=findViewById(R.id.txt_soaking_workingGrade);
        txt_soaking_grade=findViewById(R.id.txt_soaking_grade);
        txt_soaking_recd_quantity=findViewById(R.id.txt_soaking_recd_quantity);
        txt_soaking_process_details=findViewById(R.id.txt_soaking_process_details);
        txt_soaking_emp_name=findViewById(R.id.txt_soaking_emp_name);
        back_button_soaking_details=findViewById(R.id.back_button_soaking_details);

        txt_soaking_date_time=findViewById(R.id.txt_soaking_date_time);
        txt_soaking_tub_no=findViewById(R.id.txt_soaking_tub_no);
        txt_soaking_no_nets=findViewById(R.id.txt_soaking_no_nets);
        txt_soaking_tare_weight=findViewById(R.id.txt_soaking_tare_weight);
        edt_soaking_total_weight_kgs=findViewById(R.id.edt_soaking_total_weight_kgs);
        txt_soaking_total_tare_wt=findViewById(R.id.txt_soaking_total_tare_wt);
        txt_soaking_net_weight=findViewById(R.id.txt_soaking_net_weight);
        txt_soaking_btn_save=findViewById(R.id.txt_soaking_btn_save);
        txt_soaking_btn_complete=findViewById(R.id.txt_soaking_btn_complete);
        soaking_process_recycler_view=findViewById(R.id.soaking_process_recycler_view);

        if (soaking_details!=null){
            txt_soaking_lotNo.setText(soaking_details.getLot_No());
            txt_soaking_variety.setText(soaking_details.getFP_Variety_Name());
            txt_soaking_workingGrade.setText(soaking_details.getGrade());
            txt_soaking_grade.setText(soaking_details.getPacking_grades());
            txt_soaking_recd_quantity.setText(soaking_details.getSoaking_Allotted_Quantity());
            txt_soaking_process_details.setText(soaking_details.getSoaking_Process_Details());
            txt_soaking_emp_name.setText(supervisor);

        }

        CountDownTimer newtimer = new CountDownTimer(1000000000, 1000) {

            public void onTick(long millisUntilFinished) {
                String saveCurrentDate;
                Calendar c = Calendar.getInstance();
                SimpleDateFormat currentDate=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                saveCurrentDate=currentDate.format(c.getTime());
                txt_soaking_date_time.setText(saveCurrentDate);
            }
            public void onFinish() {

            }
        };
        newtimer.start();



        process_start_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateTimePickerForStartTime();
            }
        });

        process_end_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!process_start_time.getText().toString().contains("-Select-")){
                    showDateTimePickerForEndTime();
                }
                else {
                    AppUtils.showToast(context,"Set Start time first");
                }

            }
        });

        process_end_time.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString() != null && !s.toString().isEmpty()) {
                    calculateDifference(s);
                }

            }
        });

        txt_soaking_btn_save.setOnClickListener(this);
        txt_soaking_btn_complete.setOnClickListener(this);
        back_button_soaking_details.setOnClickListener(this);

        txt_soaking_no_nets.addTextChangedListener(new Soaking_Details_Screen_2.GenericTextWatcher(txt_soaking_no_nets));
        txt_soaking_tare_weight.addTextChangedListener(new Soaking_Details_Screen_2.GenericTextWatcher(txt_soaking_tare_weight));

        edt_soaking_total_weight_kgs.addTextChangedListener(new TextWatcher() {
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

    boolean doValidation() {
        validate = true;

        if (txt_soaking_det_process_duration.getText().toString().trim().length() == 0) {
            validate = false;
            txt_soaking_det_process_duration.requestFocus();
            txt_soaking_det_process_duration.setError("Check start and end time");

        }
        else if (txt_soaking_tub_no.getText().toString().trim().length() == 0) {
            validate = false;
             txt_soaking_tub_no.requestFocus();
             txt_soaking_tub_no.setError("Enter Tub No");

        }
         else if (txt_soaking_no_nets.getText().toString().trim().length() == 0) {
            validate = false;
            txt_soaking_no_nets.setError("Enter Number Of Net");
            txt_soaking_no_nets.requestFocus();

        } else if (txt_soaking_tare_weight.getText().toString().trim().length() == 0) {
            validate = false;
            txt_soaking_tare_weight.requestFocus();
            txt_soaking_tare_weight.setError("Enter Tare Weight");

        } else if (edt_soaking_total_weight_kgs.getText().toString().trim().length() == 0) {
            validate = false;
            edt_soaking_total_weight_kgs.requestFocus();
            edt_soaking_total_weight_kgs.setError("Enter Tare Weight");

        }else if (txt_soaking_net_weight.getText().toString().trim().length() == 0) {
            validate = false;
            txt_soaking_net_weight.requestFocus();
            // AppUtils.showToast(mContext,"Total tare wait cannot be less than total weight");
            Toast.makeText(context,"Total Weight should  be greater than total tare weight",Toast.LENGTH_SHORT).show();

        }

        return validate;
    }

    private void calculateDifference(Editable s) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss aa");
            Date date1 = null;

            date1 = format.parse(AppUtils.HourFormat(process_start_time.getText().toString()));
            Date date2 = format.parse(AppUtils.HourFormat(process_end_time.getText().toString()));
            long mills =  date2.getTime()-date1.getTime();
            Log.v("Data1", "" + date1.getTime());
            Log.v("Data2", "" + date2.getTime());
            int hours = (int) (mills / (1000 * 60 * 60));
            int mins = (int) (mills / (1000 * 60)) % 60;


            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date strDate = sdf.parse(process_start_time.getText().toString());
            Date endDate = sdf.parse(process_end_time.getText().toString());
            if (endDate.getTime() > strDate.getTime()) {
                String diff = hours + " hr : " + mins+" min"; // updated value every1 second
                difference=hours+":"+mins;
                txt_soaking_det_process_duration.setText(diff);
            }
            else {
                AppUtils.showToast(context,"End time should be greater than start time");
            }
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void showDateTimePickerForStartTime() {
        final Calendar currentDate = Calendar.getInstance();
        date = Calendar.getInstance();
        new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                date.set(year, monthOfYear, dayOfMonth);
                new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        date.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        date.set(Calendar.MINUTE, minute);

                        startTime=date.getTime().toString();
                        process_start_time.setText(AppUtils.covertGSTTonormalDateFormat(startTime));


                        Log.e("date", "The choosen one " + startTime);
                    }
                }, currentDate.get(Calendar.HOUR_OF_DAY), currentDate.get(Calendar.MINUTE), false).show();
            }
        }, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DATE)).show();
    }

    public void showDateTimePickerForEndTime() {
        final Calendar currentDate = Calendar.getInstance();
        date = Calendar.getInstance();
        new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                date.set(year, monthOfYear, dayOfMonth);
                new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        date.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        date.set(Calendar.MINUTE, minute);

                        endTime=date.getTime().toString();
                        process_end_time.setText(AppUtils.covertGSTTonormalDateFormat(endTime));

                        Log.e("date", "The choosen one " + endTime);
                    }
                }, currentDate.get(Calendar.HOUR_OF_DAY), currentDate.get(Calendar.MINUTE), false).show();
            }
        }, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DATE)).show();
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
                case R.id.txt_soaking_no_nets:
                    numberOfNets = text;
                    calculateWeight();
                    break;
                case R.id.txt_soaking_tare_weight:
                    tareWeight = Float.parseFloat(text);
                    calculateWeight();
                    break;

                case R.id.edt_soaking_total_weight_kgs:
                    Log.i(TAG, "afterTextChanged: " + text);
                    totalWeight = Float.parseFloat(text);
                    calculateWeight();
                    break;
            }
        }
    }

    private void calculateWeight() {
        totTareWeight = Float.parseFloat(numberOfNets) * tareWeight;
        txt_soaking_total_tare_wt.setText(String.valueOf(Math.round(totTareWeight * 100.0) / 100.0));

        Log.i(TAG, "calculateWeight: " + totalWeight);

        if (totalWeight >totTareWeight) {
            Log.i(TAG, "calculateWeight: Inside " + totalWeight);
            netTotalWeight =  totalWeight-totTareWeight;
            txt_soaking_net_weight.setText(String.valueOf(Math.round(netTotalWeight * 100.0) / 100.0));
        } else {
            txt_soaking_net_weight.setText("");
        }

        Log.d("TextType", "total: " + txt_soaking_total_tare_wt + " NetWeight: " + txt_soaking_net_weight);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txt_soaking_btn_save:
                            if (doValidation()) {
                                AppUtils.showCustomOkCancelDialog(this, "","Do you want to save weights?", "Yes", "No",
                                        new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                Soaking_Grid_two_model details=new Soaking_Grid_two_model();
                                                details.setDate(txt_soaking_date_time.getText().toString());
                                                details.setTub_no(Integer.parseInt(txt_soaking_tub_no.getText().toString()));
                                                details.setNo_of_nets(Integer.parseInt(txt_soaking_no_nets.getText().toString()));
                                                details.setTotal_weight(Float.parseFloat(edt_soaking_total_weight_kgs.getText().toString()));
                                                details.setNet_weight(Float.parseFloat(txt_soaking_net_weight.getText().toString()));
                                                details.setTotal_tare_weight(Float.parseFloat(txt_soaking_total_tare_wt.getText().toString()));
                                                details.setGrade(soaking_details.getGrade());
                                                soaking_grid_two_models.add(details);

                                                adapter=new Soaking_grid_adapter_for_screen_2(getApplicationContext(),soaking_grid_two_models);
                                                soaking_process_recycler_view.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                                soaking_process_recycler_view.setHasFixedSize(true);
                                                soaking_process_recycler_view.setAdapter(adapter);

                                                clearText();

                                                edt_soaking_total_weight_kgs.post(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        final InputMethodManager imm = (InputMethodManager) edt_soaking_total_weight_kgs.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                                                        imm.showSoftInput(edt_soaking_total_weight_kgs, InputMethodManager.SHOW_IMPLICIT);
                                                        edt_soaking_total_weight_kgs.requestFocus(); // needed if you have more then one input
                                                    }
                                                });



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

                break;

            case R.id.txt_soaking_btn_complete:

                if (soaking_grid_two_models.size()!=0){
                    Bundle b = new Bundle();
                    b.putSerializable("objNames", (Serializable) soaking_grid_two_models);
                    Intent insertDetails=new Intent(Soaking_Details_Screen_2.this, Soaking_details_screen_3.class);
                    insertDetails.putExtras(b);
                    insertDetails.putExtra("process",soaking_details);
                    insertDetails.putExtra("start",process_start_time.getText().toString());
                    insertDetails.putExtra("end",process_end_time.getText().toString());
                    insertDetails.putExtra("duration",difference);
                    startActivity(insertDetails);

                }else {
                    AppUtils.showToast(context,"please fill details");
                }



                break;

            case R.id.back_button_soaking_details:
                onBackPressed();
                break;
        }
    }

    private void clearText() {
        edt_soaking_total_weight_kgs.setText("");
        txt_soaking_total_tare_wt.setText("");
        txt_soaking_net_weight.setText("");
        edt_soaking_total_weight_kgs.requestFocus();

    }

    @Override
    public void onBackPressed() {
        //  super.onBackPressed();
        AppUtils.showCustomOkCancelDialog(this, "", "Do you want to go back without saving weights?", "No", "Yes",
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Soaking_Details_Screen_2.super.onBackPressed();
                    }
                });
    }
}
