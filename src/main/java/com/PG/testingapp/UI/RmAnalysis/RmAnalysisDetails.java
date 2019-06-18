package com.PG.testingapp.UI.RmAnalysis;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.PG.testingapp.R;
import com.PG.testingapp.Utils.AppUtils;
import com.PG.testingapp.model.RmAnalysis.RmAnalysisDetailsModel;

public class RmAnalysisDetails extends AppCompatActivity {

    private RmAnalysisDetailsModel rmAnalysisDetailsModel;
    private TextView txt_rm_analysis_date,txt_rm_lot_no,txt_rm_lot_date,txt_rm_farmer_name,txt_rm_location,
            txt_rm_received_count,txt_rm_quantity,txt_rm_no_of_pics,txt_rm_supervisor_name,
            txt_rm_remarks,cat_one,cat_two,cat_three,cat_four,cat_five,cat_six,cat_seven,cat_eight;

    private EditText txt_rm_ct_one_no_of_pics,txt_rm_ct_one_sample_quantity,txt_rm_ct_two_no_of_pics,txt_rm_ct_two_sample_quantity,
            txt_rm_ct_three_no_of_pics,txt_rm_ct_three_sample_quantity, txt_rm_ct_four_no_of_pics,txt_rm_ct_four_sample_quantity,
            txt_rm_ct_five_no_of_pics,txt_rm_ct_five_sample_quantity, txt_rm_ct_six_no_of_pics,txt_rm_ct_six_sample_quantity,
            txt_rm_ct_seven_no_of_pics,txt_rm_ct_seven_sample_quantity, txt_rm_ct_eight_no_of_pics,txt_rm_ct_eight_sample_quantity,
            txt_rm_ct_one_final_qty,txt_rm_ct_two_final_qty,txt_rm_ct_three_final_qty,txt_rm_ct_four_final_qty,txt_rm_ct_five_final_qty,
            txt_rm_ct_six_final_qty,txt_rm_ct_seven_final_qty,txt_rm_ct_eight_final_qty;

    private TextView txt_rm_ct_one_per_sam_qt,txt_rm_ct_one_recd_qty,txt_rm_ct_two_per_sam_qt,txt_rm_ct_two_recd_qty,
            txt_rm_ct_three_per_sam_qt,txt_rm_ct_three_recd_qty,txt_rm_ct_four_per_sam_qt,txt_rm_ct_four_recd_qty,
            txt_rm_ct_five_per_sam_qt,txt_rm_ct_five_recd_qty,txt_rm_ct_six_per_sam_qt,txt_rm_ct_six_recd_qty,
            txt_rm_ct_seven_per_sam_qt,txt_rm_ct_seven_recd_qty,txt_rm_ct_eight_per_sam_qt,txt_rm_ct_eight_recd_qty;

    private Context context;

    private EditText txt_rm_sample_quantity;
    private String supervisor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rm_analysis_details);

        context=RmAnalysisDetails.this;

        rmAnalysisDetailsModel=(RmAnalysisDetailsModel) getIntent().getSerializableExtra("process");
        supervisor=getIntent().getExtras().getString("supervisor");

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

        //editTexts
        txt_rm_ct_one_no_of_pics=findViewById(R.id.txt_rm_ct_one_no_of_pics);
        txt_rm_ct_one_sample_quantity=findViewById(R.id.txt_rm_ct_one_sample_quantity);
        txt_rm_ct_two_no_of_pics=findViewById(R.id.txt_rm_ct_two_no_of_pics);
        txt_rm_ct_two_sample_quantity=findViewById(R.id.txt_rm_ct_two_sample_quantity);
        txt_rm_ct_three_no_of_pics=findViewById(R.id.txt_rm_ct_three_no_of_pics);
        txt_rm_ct_three_sample_quantity=findViewById(R.id.txt_rm_ct_three_sample_quantity);
        txt_rm_ct_four_no_of_pics=findViewById(R.id.txt_rm_ct_four_no_of_pics);
        txt_rm_ct_four_sample_quantity=findViewById(R.id.txt_rm_ct_four_sample_quantity);
        txt_rm_ct_five_no_of_pics=findViewById(R.id.txt_rm_ct_five_no_of_pics);
        txt_rm_ct_five_sample_quantity=findViewById(R.id.txt_rm_ct_five_sample_quantity);
        txt_rm_ct_six_no_of_pics=findViewById(R.id.txt_rm_ct_six_no_of_pics);
        txt_rm_ct_six_sample_quantity=findViewById(R.id.txt_rm_ct_six_sample_quantity);
        txt_rm_ct_seven_no_of_pics=findViewById(R.id.txt_rm_ct_seven_no_of_pics);
        txt_rm_ct_seven_sample_quantity=findViewById(R.id.txt_rm_ct_seven_sample_quantity);
        txt_rm_ct_eight_no_of_pics=findViewById(R.id.txt_rm_ct_eight_no_of_pics);
        txt_rm_ct_eight_sample_quantity=findViewById(R.id.txt_rm_ct_eight_sample_quantity);
        txt_rm_ct_one_final_qty=findViewById(R.id.txt_rm_ct_one_final_qty);
        txt_rm_ct_two_final_qty=findViewById(R.id.txt_rm_ct_two_final_qty);
        txt_rm_ct_three_final_qty=findViewById(R.id.txt_rm_ct_three_final_qty);
        txt_rm_ct_four_final_qty=findViewById(R.id.txt_rm_ct_four_final_qty);
        txt_rm_ct_five_final_qty=findViewById(R.id.txt_rm_ct_five_final_qty);
        txt_rm_ct_six_final_qty=findViewById(R.id.txt_rm_ct_six_final_qty);
        txt_rm_ct_seven_final_qty=findViewById(R.id.txt_rm_ct_seven_final_qty);
        txt_rm_ct_eight_final_qty=findViewById(R.id.txt_rm_ct_eight_final_qty);

        //textViews
        txt_rm_ct_one_per_sam_qt=findViewById(R.id.txt_rm_ct_one_per_sam_qt);
        txt_rm_ct_one_recd_qty=findViewById(R.id.txt_rm_ct_one_recd_qty);
        txt_rm_ct_two_per_sam_qt=findViewById(R.id.txt_rm_ct_two_per_sam_qt);
        txt_rm_ct_two_recd_qty=findViewById(R.id.txt_rm_ct_two_recd_qty);
        txt_rm_ct_three_per_sam_qt=findViewById(R.id.txt_rm_ct_three_per_sam_qt);
        txt_rm_ct_three_recd_qty=findViewById(R.id.txt_rm_ct_three_recd_qty);
        txt_rm_ct_four_per_sam_qt=findViewById(R.id.txt_rm_ct_four_per_sam_qt);
        txt_rm_ct_four_recd_qty=findViewById(R.id.txt_rm_ct_four_recd_qty);
        txt_rm_ct_five_per_sam_qt=findViewById(R.id.txt_rm_ct_five_per_sam_qt);
        txt_rm_ct_five_recd_qty=findViewById(R.id.txt_rm_ct_five_recd_qty);
        txt_rm_ct_six_per_sam_qt=findViewById(R.id.txt_rm_ct_six_per_sam_qt);
        txt_rm_ct_six_recd_qty=findViewById(R.id.txt_rm_ct_six_recd_qty);
        txt_rm_ct_seven_per_sam_qt=findViewById(R.id.txt_rm_ct_seven_per_sam_qt);
        txt_rm_ct_seven_recd_qty=findViewById(R.id.txt_rm_ct_seven_recd_qty);
        txt_rm_ct_eight_per_sam_qt=findViewById(R.id.txt_rm_ct_eight_per_sam_qt);
        txt_rm_ct_eight_recd_qty=findViewById(R.id.txt_rm_ct_eight_recd_qty);

        //edt final cutting

        txt_rm_ct_one_no_of_pics.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!txt_rm_sample_quantity.getText().toString().isEmpty()){
                    txt_rm_ct_one_no_of_pics.addTextChangedListener(new RmAnalysisDetails.GenericTextWatcher(txt_rm_ct_one_no_of_pics));
                    txt_rm_ct_one_no_of_pics.requestFocus();
                }
                else {
                    AppUtils.showToast(context,"please enter Sample Quantity first");
                    txt_rm_sample_quantity.setError("please enter Sample Quantity first");
                }
            }
        });



        if (rmAnalysisDetailsModel!=null){
            txt_rm_lot_no.setText(rmAnalysisDetailsModel.getLot_No());
            txt_rm_lot_date.setText(rmAnalysisDetailsModel.getLot_Date());
            txt_rm_farmer_name.setText(rmAnalysisDetailsModel.getAqua_Farmer_Name());
            txt_rm_location.setText(rmAnalysisDetailsModel.getLocation());
            txt_rm_received_count.setText(rmAnalysisDetailsModel.getVariety_Count());
            txt_rm_quantity.setText(rmAnalysisDetailsModel.getQuantity());
            txt_rm_supervisor_name.setText(supervisor);
        }

        txt_rm_sample_quantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                setText(s);
            }
        });
    }

    private void setText(Editable s) {
        if (s.toString() != null && !s.toString().isEmpty()){
            int sample_qty= Integer.parseInt(s.toString());
            int count= (int) Float.parseFloat(rmAnalysisDetailsModel.getVariety_Count());
            int value=sample_qty*count;
           txt_rm_no_of_pics.setText(String.valueOf(value));
        }
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
                case R.id.txt_rm_ct_one_no_of_pics:

                    setTextForColumnThree(text,txt_rm_ct_one_per_sam_qt);

                    break;
                case R.id.txt_rm_ct_one_sample_quantity:

                    break;

                case R.id.txt_rm_ct_two_no_of_pics:

                    break;
                case R.id.txt_rm_ct_two_sample_quantity:

                    break;


                case R.id.txt_rm_ct_three_no_of_pics:

                    break;
                case R.id.txt_rm_ct_three_sample_quantity:

                    break;


                case R.id.txt_rm_ct_four_no_of_pics:

                    break;
                case R.id.txt_rm_ct_four_sample_quantity:

                    break;

                case R.id.txt_rm_ct_five_no_of_pics:

                    break;
                case R.id.txt_rm_ct_five_sample_quantity:

                    break;

                case R.id.txt_rm_ct_six_no_of_pics:

                    break;
                case R.id.txt_rm_ct_six_sample_quantity:

                    break;


                case R.id.txt_rm_ct_seven_no_of_pics:

                    break;
                case R.id.txt_rm_ct_seven_sample_quantity:

                    break;


                case R.id.txt_rm_ct_eight_no_of_pics:

                    break;
                case R.id.txt_rm_ct_eight_sample_quantity:

                    break;

            }
        }
    }

    private void setTextForColumnThree(String text, TextView txt_rm_ct_one_per_sam_qt) {

        if (txt_rm_no_of_pics.getText().toString()!=""){
            float no_pieces=Float.parseFloat(text);
            Log.e("no_pieces",String.valueOf(no_pieces));
            float totalPieces=Integer.parseInt(txt_rm_no_of_pics.getText().toString());
            Log.e("totalPieces",String.valueOf(totalPieces));
            float result=(no_pieces/totalPieces)*100;
            Log.e("result",String.valueOf(result));
            txt_rm_ct_one_per_sam_qt.setText(String.valueOf(result));
        }


    }
}
