package com.PG.testingapp.UI.RmAnalysis;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.PG.testingapp.R;
import com.PG.testingapp.model.RmAnalysis.RmAnalysisDetailsModel;

public class RmAnalysisDetails extends AppCompatActivity {

    private RmAnalysisDetailsModel rmAnalysisDetailsModel;
    private TextView txt_rm_analysis_date,txt_rm_lot_no,txt_rm_lot_date,txt_rm_farmer_name,txt_rm_location,
            txt_rm_received_count,txt_rm_quantity,txt_rm_no_of_pics,txt_rm_supervisor_name,
            txt_rm_remarks,cat_one,cat_two,cat_three,cat_four,cat_five,cat_six,cat_seven,cat_eight;

    private EditText txt_rm_sample_quantity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rm_analysis_details);

        rmAnalysisDetailsModel=(RmAnalysisDetailsModel) getIntent().getSerializableExtra("process");

        txt_rm_analysis_date=findViewById(R.id.txt_rm_analysis_date);
        txt_rm_lot_no=findViewById(R.id.txt_rm_lot_no);
        txt_rm_lot_date=findViewById(R.id.txt_rm_lot_date);
        txt_rm_farmer_name=findViewById(R.id.txt_rm_farmer_name);
        txt_rm_location=findViewById(R.id.txt_rm_location);
        txt_rm_received_count=findViewById(R.id.txt_rm_received_count);
        txt_rm_quantity=findViewById(R.id.txt_rm_quantity);
        txt_rm_sample_quantity=findViewById(R.id.txt_rm_sample_quantity);
        txt_rm_no_of_pics=findViewById(R.id.txt_rm_no_of_pics);
        txt_rm_supervisor_name=findViewById(R.id.txt_rm_supervisor_name);
        txt_rm_remarks=findViewById(R.id.txt_rm_remarks);

        cat_one=findViewById(R.id.cat_one);
        cat_two=findViewById(R.id.cat_two);
        cat_three=findViewById(R.id.cat_three);
        cat_four=findViewById(R.id.cat_four);
        cat_five=findViewById(R.id.cat_five);
        cat_six=findViewById(R.id.cat_six);
        cat_seven=findViewById(R.id.cat_seven);
        cat_eight=findViewById(R.id.cat_eight);

        if (rmAnalysisDetailsModel!=null){
            txt_rm_lot_no.setText(rmAnalysisDetailsModel.getLot_No());
            txt_rm_lot_date.setText(rmAnalysisDetailsModel.getLot_Date());
            txt_rm_farmer_name.setText(rmAnalysisDetailsModel.getAqua_Farmer_Name());
            txt_rm_location.setText(rmAnalysisDetailsModel.getLocation());
            txt_rm_received_count.setText(rmAnalysisDetailsModel.getVariety_Count());
            txt_rm_quantity.setText(rmAnalysisDetailsModel.getQuantity());
        }











    }
}
