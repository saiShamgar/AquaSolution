package com.PG.testingapp.UI.ChemicalTreatmentProcess;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.PG.testingapp.R;
import com.PG.testingapp.Utils.AppUtils;
import com.PG.testingapp.model.Soaking.Soaking_details;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Soaking_Details_Screen_2 extends AppCompatActivity {

    Calendar date;
    private Context context;
    private TextView process_start_time,process_end_time,txt_soaking_det_process_duration,txt_soaking_lotNo,txt_soaking_variety,
            txt_soaking_workingGrade,txt_soaking_grade,txt_soaking_recd_quantity,txt_soaking_process_details,txt_soaking_emp_name;

    private String startTime,endTime;
    private Soaking_details soaking_details;
    private String supervisor;

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

        if (soaking_details!=null){
            txt_soaking_lotNo.setText(soaking_details.getLot_No());
            txt_soaking_variety.setText(soaking_details.getFP_Variety_Name());
            txt_soaking_workingGrade.setText(soaking_details.getGrade());
            txt_soaking_grade.setText(soaking_details.getPacking_grades());
            txt_soaking_recd_quantity.setText(soaking_details.getSoaking_Allotted_Quantity());
            txt_soaking_process_details.setText(soaking_details.getSoaking_Process_Details());
            txt_soaking_emp_name.setText(supervisor);

        }



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


            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
            Date strDate = sdf.parse(process_start_time.getText().toString());
            Date endDate = sdf.parse(process_end_time.getText().toString());
            if (endDate.getTime() > strDate.getTime()) {
                String diff = hours + " hr : " + mins+" min"; // updated value every1 second
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
}
