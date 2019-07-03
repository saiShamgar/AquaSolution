package com.PG.testingapp.UI.RmAnalysis;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.PG.testingapp.R;
import com.PG.testingapp.Utils.AppUtils;
import com.PG.testingapp.model.RmAnalysis.AnalysisModel;
import com.PG.testingapp.model.RmAnalysis.RmAnalysisDetailsModel;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class RmAnalysisDetails extends AppCompatActivity {

    private RmAnalysisDetailsModel rmAnalysisDetailsModel;
    private TextView txt_rm_analysis_date,txt_rm_lot_no,txt_rm_lot_date,txt_rm_farmer_name,txt_rm_location,
            txt_rm_received_count,txt_rm_quantity,txt_rm_no_of_pics,txt_rm_supervisor_name,
            cat_one,cat_two,cat_three,cat_four,cat_five,cat_six,cat_seven,cat_eight;
    private ImageView back_button_rm_analysis;

    private EditText txt_rm_ct_one_no_of_pics,txt_rm_ct_one_sample_quantity,txt_rm_ct_two_no_of_pics,txt_rm_ct_two_sample_quantity,
            txt_rm_ct_three_no_of_pics,txt_rm_ct_three_sample_quantity, txt_rm_ct_four_no_of_pics,txt_rm_ct_four_sample_quantity,
            txt_rm_ct_five_no_of_pics,txt_rm_ct_five_sample_quantity, txt_rm_ct_six_no_of_pics,txt_rm_ct_six_sample_quantity,
            txt_rm_ct_seven_no_of_pics,txt_rm_ct_seven_sample_quantity, txt_rm_ct_eight_no_of_pics,txt_rm_ct_eight_sample_quantity,
            txt_rm_ct_one_final_qty,txt_rm_ct_two_final_qty,txt_rm_ct_three_final_qty,txt_rm_ct_four_final_qty,txt_rm_ct_five_final_qty,
            txt_rm_ct_six_final_qty,txt_rm_ct_seven_final_qty,txt_rm_ct_eight_final_qty,txt_rm_remarks;

    private TextView txt_rm_ct_one_per_sam_qt,txt_rm_ct_one_recd_qty,txt_rm_ct_two_per_sam_qt,txt_rm_ct_two_recd_qty,
            txt_rm_ct_three_per_sam_qt,txt_rm_ct_three_recd_qty,txt_rm_ct_four_per_sam_qt,txt_rm_ct_four_recd_qty,
            txt_rm_ct_five_per_sam_qt,txt_rm_ct_five_recd_qty,txt_rm_ct_six_per_sam_qt,txt_rm_ct_six_recd_qty,
            txt_rm_ct_seven_per_sam_qt,txt_rm_ct_seven_recd_qty,txt_rm_ct_eight_per_sam_qt,txt_rm_ct_eight_recd_qty;
    private Button go_to_summary;

    private ArrayList<AnalysisModel> analysisModels=new ArrayList<>();

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
        go_to_summary=findViewById(R.id.go_to_summary);
        back_button_rm_analysis=findViewById(R.id.back_button_rm_analysis);

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

        CountDownTimer newtimer = new CountDownTimer(1000000000, 1000) {

            public void onTick(long millisUntilFinished) {
                String saveCurrentDate;
                Calendar c = Calendar.getInstance();
                SimpleDateFormat currentDate=new SimpleDateFormat("dd-MM-yyyy");
                saveCurrentDate=currentDate.format(c.getTime());
                txt_rm_analysis_date.setText(saveCurrentDate);
            }
            public void onFinish() {

            }
        };
        newtimer.start();

        back_button_rm_analysis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        go_to_summary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AnalysisModel one=new AnalysisModel("1-Soft/Semi Soft/Paper Soft",
                        txt_rm_ct_one_no_of_pics.getText().toString(),
                        txt_rm_ct_one_sample_quantity.getText().toString(),
                        txt_rm_ct_one_per_sam_qt.getText().toString(),
                        txt_rm_ct_one_recd_qty.getText().toString(),
                        txt_rm_ct_one_final_qty.getText().toString());

                AnalysisModel two=new AnalysisModel("2-Loose Shell/Tp",
                        txt_rm_ct_two_no_of_pics.getText().toString(),
                        txt_rm_ct_two_sample_quantity.getText().toString(),
                        txt_rm_ct_two_per_sam_qt.getText().toString(),
                        txt_rm_ct_two_recd_qty.getText().toString(),
                        txt_rm_ct_two_final_qty.getText().toString());

                AnalysisModel thre=new AnalysisModel("3-Drooping Head",
                        txt_rm_ct_three_no_of_pics.getText().toString(),
                        txt_rm_ct_three_sample_quantity.getText().toString(),
                        txt_rm_ct_three_per_sam_qt.getText().toString(),
                        txt_rm_ct_three_recd_qty.getText().toString(),
                        txt_rm_ct_three_final_qty.getText().toString());

                AnalysisModel four=new AnalysisModel("4-Black spot/Black Dot",
                        txt_rm_ct_four_no_of_pics.getText().toString(),
                        txt_rm_ct_four_sample_quantity.getText().toString(),
                        txt_rm_ct_four_per_sam_qt.getText().toString(),
                        txt_rm_ct_four_recd_qty.getText().toString(),
                        txt_rm_ct_four_final_qty.getText().toString());

                AnalysisModel five=new AnalysisModel("5-Broken/Damage",
                        txt_rm_ct_five_no_of_pics.getText().toString(),
                        txt_rm_ct_five_sample_quantity.getText().toString(),
                        txt_rm_ct_five_per_sam_qt.getText().toString(),
                        txt_rm_ct_five_recd_qty.getText().toString(),
                        txt_rm_ct_five_final_qty.getText().toString());

                AnalysisModel six=new AnalysisModel("6-Decomposition/Discolouration",
                        txt_rm_ct_six_no_of_pics.getText().toString(),
                        txt_rm_ct_six_sample_quantity.getText().toString(),
                        txt_rm_ct_six_per_sam_qt.getText().toString(),
                        txt_rm_ct_six_recd_qty.getText().toString(),
                        txt_rm_ct_six_final_qty.getText().toString());

                AnalysisModel seven=new AnalysisModel("7-Bends/small Cuttings",
                        txt_rm_ct_seven_no_of_pics.getText().toString(),
                        txt_rm_ct_seven_sample_quantity.getText().toString(),
                        txt_rm_ct_seven_per_sam_qt.getText().toString(),
                        txt_rm_ct_seven_recd_qty.getText().toString(),
                        txt_rm_ct_seven_final_qty.getText().toString());

                AnalysisModel eight=new AnalysisModel("8-Red/Black gill",
                        txt_rm_ct_eight_no_of_pics.getText().toString(),
                        txt_rm_ct_eight_sample_quantity.getText().toString(),
                        txt_rm_ct_eight_per_sam_qt.getText().toString(),
                        txt_rm_ct_eight_recd_qty.getText().toString(),
                        txt_rm_ct_eight_final_qty.getText().toString());


                analysisModels.add(one);
                analysisModels.add(two);
                analysisModels.add(thre);
                analysisModels.add(four);
                analysisModels.add(five);
                analysisModels.add(six);
                analysisModels.add(seven);
                analysisModels.add(eight);

                Bundle b = new Bundle();
                b.putSerializable("objNames", (Serializable) analysisModels);
                Intent val_details=new Intent(RmAnalysisDetails.this,RmAnalysis_summary.class);
                val_details.putExtra("process",rmAnalysisDetailsModel);
                val_details.putExtra("status","RMA");
                val_details.putExtras(b);
                val_details.putExtra("sample_qty",txt_rm_sample_quantity.getText().toString());
                val_details.putExtra("no_of_pieces",txt_rm_no_of_pics.getText().toString());
                val_details.putExtra("supervisor",supervisor);
                val_details.putExtra("date",txt_rm_analysis_date.getText().toString());
                val_details.putExtra("remarks",txt_rm_remarks.getText().toString());
                startActivity(val_details);
            }
        });

        //edt final cutting

        txt_rm_ct_one_no_of_pics.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!txt_rm_sample_quantity.getText().toString().isEmpty()){
                    txt_rm_ct_one_no_of_pics.addTextChangedListener(new RmAnalysisDetails.GenericTextWatcher(txt_rm_ct_one_no_of_pics));
                  //  txt_rm_ct_one_no_of_pics.requestFocus();
                    txt_rm_ct_one_no_of_pics.post(new Runnable() {
                        @Override
                        public void run() {
                            final InputMethodManager imm = (InputMethodManager) txt_rm_ct_one_no_of_pics.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.showSoftInput(txt_rm_ct_one_no_of_pics, InputMethodManager.SHOW_IMPLICIT);// needed if you have more then one input
                        }
                    });
                }
                else {
                    AppUtils.showToast(context,"please enter Sample Quantity first");
                    txt_rm_sample_quantity.setError("please enter Sample Quantity first");
                    txt_rm_sample_quantity.requestFocus();
                    txt_rm_sample_quantity.post(new Runnable() {
                        @Override
                        public void run() {
                            final InputMethodManager imm = (InputMethodManager) txt_rm_sample_quantity.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.showSoftInput(txt_rm_sample_quantity, InputMethodManager.SHOW_IMPLICIT);
                          //  txt_rm_sample_quantity.requestFocus(); // needed if you have more then one input
                        }
                    });
                }
            }
        });

        txt_rm_ct_two_no_of_pics.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!txt_rm_sample_quantity.getText().toString().isEmpty()){
                    txt_rm_ct_two_no_of_pics.addTextChangedListener(new RmAnalysisDetails.GenericTextWatcher(txt_rm_ct_two_no_of_pics));
                   // txt_rm_ct_two_no_of_pics.requestFocus();
                    txt_rm_ct_two_no_of_pics.post(new Runnable() {
                        @Override
                        public void run() {
                            final InputMethodManager imm = (InputMethodManager) txt_rm_ct_two_no_of_pics.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.showSoftInput(txt_rm_ct_two_no_of_pics, InputMethodManager.SHOW_IMPLICIT);// needed if you have more then one input
                        }
                    });
                }
                else {
                    AppUtils.showToast(context,"please enter Sample Quantity first");
                    txt_rm_sample_quantity.setError("please enter Sample Quantity first");
                    txt_rm_sample_quantity.requestFocus();
                    txt_rm_sample_quantity.post(new Runnable() {
                        @Override
                        public void run() {
                            final InputMethodManager imm = (InputMethodManager) txt_rm_sample_quantity.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.showSoftInput(txt_rm_sample_quantity, InputMethodManager.SHOW_IMPLICIT);// needed if you have more then one input
                        }
                    });
                }
            }
        });

        txt_rm_ct_three_no_of_pics.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!txt_rm_sample_quantity.getText().toString().isEmpty()){
                    txt_rm_ct_three_no_of_pics.addTextChangedListener(new RmAnalysisDetails.GenericTextWatcher(txt_rm_ct_three_no_of_pics));
                   // txt_rm_ct_three_no_of_pics.requestFocus();
                    txt_rm_ct_three_no_of_pics.post(new Runnable() {
                        @Override
                        public void run() {
                            final InputMethodManager imm = (InputMethodManager) txt_rm_ct_three_no_of_pics.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.showSoftInput(txt_rm_ct_three_no_of_pics, InputMethodManager.SHOW_IMPLICIT);// needed if you have more then one input
                        }
                    });
                }
                else {
                    AppUtils.showToast(context,"please enter Sample Quantity first");
                    txt_rm_sample_quantity.setError("please enter Sample Quantity first");
                    txt_rm_sample_quantity.requestFocus();
                    txt_rm_sample_quantity.post(new Runnable() {
                        @Override
                        public void run() {
                            final InputMethodManager imm = (InputMethodManager) txt_rm_sample_quantity.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.showSoftInput(txt_rm_sample_quantity, InputMethodManager.SHOW_IMPLICIT);// needed if you have more then one input
                        }
                    });
                }
            }
        });

        txt_rm_ct_four_no_of_pics.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!txt_rm_sample_quantity.getText().toString().isEmpty()){
                    txt_rm_ct_four_no_of_pics.addTextChangedListener(new RmAnalysisDetails.GenericTextWatcher(txt_rm_ct_four_no_of_pics));
                   // txt_rm_ct_four_no_of_pics.requestFocus();
                    txt_rm_ct_four_no_of_pics.post(new Runnable() {
                        @Override
                        public void run() {
                            final InputMethodManager imm = (InputMethodManager) txt_rm_ct_four_no_of_pics.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.showSoftInput(txt_rm_ct_four_no_of_pics, InputMethodManager.SHOW_IMPLICIT);
                          //  txt_rm_ct_four_no_of_pics.requestFocus(); // needed if you have more then one input
                        }
                    });
                }
                else {
                    AppUtils.showToast(context,"please enter Sample Quantity first");
                    txt_rm_sample_quantity.setError("please enter Sample Quantity first");
                    txt_rm_sample_quantity.requestFocus();
                    txt_rm_sample_quantity.post(new Runnable() {
                        @Override
                        public void run() {
                            final InputMethodManager imm = (InputMethodManager) txt_rm_sample_quantity.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.showSoftInput(txt_rm_sample_quantity, InputMethodManager.SHOW_IMPLICIT);// needed if you have more then one input
                        }
                    });
                }
            }
        });

        txt_rm_ct_five_no_of_pics.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!txt_rm_sample_quantity.getText().toString().isEmpty()){
                    txt_rm_ct_five_no_of_pics.addTextChangedListener(new RmAnalysisDetails.GenericTextWatcher(txt_rm_ct_five_no_of_pics));
                  //  txt_rm_ct_five_no_of_pics.requestFocus();
                    txt_rm_ct_five_no_of_pics.post(new Runnable() {
                        @Override
                        public void run() {
                            final InputMethodManager imm = (InputMethodManager) txt_rm_ct_five_no_of_pics.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.showSoftInput(txt_rm_ct_five_no_of_pics, InputMethodManager.SHOW_IMPLICIT);// needed if you have more then one input
                        }
                    });
                }
                else {
                    AppUtils.showToast(context,"please enter Sample Quantity first");
                    txt_rm_sample_quantity.setError("please enter Sample Quantity first");
                    txt_rm_sample_quantity.requestFocus();
                    txt_rm_sample_quantity.post(new Runnable() {
                        @Override
                        public void run() {
                            final InputMethodManager imm = (InputMethodManager) txt_rm_sample_quantity.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.showSoftInput(txt_rm_sample_quantity, InputMethodManager.SHOW_IMPLICIT);// needed if you have more then one input
                        }
                    });
                }
            }
        });

        txt_rm_ct_six_no_of_pics.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!txt_rm_sample_quantity.getText().toString().isEmpty()){
                    txt_rm_ct_six_no_of_pics.addTextChangedListener(new RmAnalysisDetails.GenericTextWatcher(txt_rm_ct_six_no_of_pics));
                  //  txt_rm_ct_six_no_of_pics.requestFocus();
                    txt_rm_ct_six_no_of_pics.post(new Runnable() {
                        @Override
                        public void run() {
                            final InputMethodManager imm = (InputMethodManager) txt_rm_ct_six_no_of_pics.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.showSoftInput(txt_rm_ct_six_no_of_pics, InputMethodManager.SHOW_IMPLICIT);// needed if you have more then one input
                        }
                    });
                }
                else {
                    AppUtils.showToast(context,"please enter Sample Quantity first");
                    txt_rm_sample_quantity.setError("please enter Sample Quantity first");
                    txt_rm_sample_quantity.requestFocus();
                    txt_rm_sample_quantity.post(new Runnable() {
                        @Override
                        public void run() {
                            final InputMethodManager imm = (InputMethodManager) txt_rm_sample_quantity.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.showSoftInput(txt_rm_sample_quantity, InputMethodManager.SHOW_IMPLICIT);// needed if you have more then one input
                        }
                    });
                }
            }
        });

        txt_rm_ct_seven_no_of_pics.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!txt_rm_sample_quantity.getText().toString().isEmpty()){
                    txt_rm_ct_seven_no_of_pics.addTextChangedListener(new RmAnalysisDetails.GenericTextWatcher(txt_rm_ct_seven_no_of_pics));
                  //  txt_rm_ct_seven_no_of_pics.requestFocus();
                    txt_rm_ct_seven_no_of_pics.post(new Runnable() {
                        @Override
                        public void run() {
                            final InputMethodManager imm = (InputMethodManager) txt_rm_ct_seven_no_of_pics.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.showSoftInput(txt_rm_ct_seven_no_of_pics, InputMethodManager.SHOW_IMPLICIT);// needed if you have more then one input
                        }
                    });
                }
                else {
                    AppUtils.showToast(context,"please enter Sample Quantity first");
                    txt_rm_sample_quantity.setError("please enter Sample Quantity first");
                    txt_rm_sample_quantity.requestFocus();
                    txt_rm_sample_quantity.post(new Runnable() {
                        @Override
                        public void run() {
                            final InputMethodManager imm = (InputMethodManager) txt_rm_sample_quantity.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.showSoftInput(txt_rm_sample_quantity, InputMethodManager.SHOW_IMPLICIT);// needed if you have more then one input
                        }
                    });
                }
            }
        });

        txt_rm_ct_eight_no_of_pics.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!txt_rm_sample_quantity.getText().toString().isEmpty()){
                    txt_rm_ct_eight_no_of_pics.addTextChangedListener(new RmAnalysisDetails.GenericTextWatcher(txt_rm_ct_eight_no_of_pics));
                 //   txt_rm_ct_eight_no_of_pics.requestFocus();
                    txt_rm_ct_eight_no_of_pics.post(new Runnable() {
                        @Override
                        public void run() {
                            final InputMethodManager imm = (InputMethodManager) txt_rm_ct_eight_no_of_pics.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.showSoftInput(txt_rm_ct_eight_no_of_pics, InputMethodManager.SHOW_IMPLICIT);// needed if you have more then one input
                        }
                    });
                }
                else {
                    AppUtils.showToast(context,"please enter Sample Quantity first");
                    txt_rm_sample_quantity.setError("please enter Sample Quantity first");
                    txt_rm_sample_quantity.requestFocus();
                    txt_rm_sample_quantity.post(new Runnable() {
                        @Override
                        public void run() {
                            final InputMethodManager imm = (InputMethodManager) txt_rm_sample_quantity.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.showSoftInput(txt_rm_sample_quantity, InputMethodManager.SHOW_IMPLICIT);// needed if you have more then one input
                        }
                    });
                }
            }
        });

        txt_rm_ct_one_sample_quantity.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!txt_rm_sample_quantity.getText().toString().isEmpty()){
                    txt_rm_ct_one_sample_quantity.addTextChangedListener(new RmAnalysisDetails.GenericTextWatcher(txt_rm_ct_one_sample_quantity));
                 //   txt_rm_ct_one_sample_quantity.requestFocus();
                    txt_rm_ct_one_sample_quantity.post(new Runnable() {
                        @Override
                        public void run() {
                            final InputMethodManager imm = (InputMethodManager) txt_rm_ct_one_sample_quantity.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.showSoftInput(txt_rm_ct_one_sample_quantity, InputMethodManager.SHOW_IMPLICIT);// needed if you have more then one input
                        }
                    });
                }
                else {
                    AppUtils.showToast(context,"please enter Sample Quantity first");
                    txt_rm_sample_quantity.setError("please enter Sample Quantity first");
                    txt_rm_sample_quantity.requestFocus();
                    txt_rm_sample_quantity.post(new Runnable() {
                        @Override
                        public void run() {
                            final InputMethodManager imm = (InputMethodManager) txt_rm_sample_quantity.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.showSoftInput(txt_rm_sample_quantity, InputMethodManager.SHOW_IMPLICIT);// needed if you have more then one input
                        }
                    });
                }
            }
        });

        txt_rm_ct_two_sample_quantity.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!txt_rm_sample_quantity.getText().toString().isEmpty()){
                    txt_rm_ct_two_sample_quantity.addTextChangedListener(new RmAnalysisDetails.GenericTextWatcher(txt_rm_ct_two_sample_quantity));
                 //   txt_rm_ct_two_sample_quantity.requestFocus();
                    txt_rm_ct_two_sample_quantity.post(new Runnable() {
                        @Override
                        public void run() {
                            final InputMethodManager imm = (InputMethodManager) txt_rm_ct_two_sample_quantity.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.showSoftInput(txt_rm_ct_two_sample_quantity, InputMethodManager.SHOW_IMPLICIT);// needed if you have more then one input
                        }
                    });
                }
                else {
                    AppUtils.showToast(context,"please enter Sample Quantity first");
                    txt_rm_sample_quantity.setError("please enter Sample Quantity first");
                    txt_rm_sample_quantity.requestFocus();
                    txt_rm_sample_quantity.post(new Runnable() {
                        @Override
                        public void run() {
                            final InputMethodManager imm = (InputMethodManager) txt_rm_sample_quantity.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.showSoftInput(txt_rm_sample_quantity, InputMethodManager.SHOW_IMPLICIT);// needed if you have more then one input
                        }
                    });
                }
            }
        });

        txt_rm_ct_three_sample_quantity.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!txt_rm_sample_quantity.getText().toString().isEmpty()){
                    txt_rm_ct_three_sample_quantity.addTextChangedListener(new RmAnalysisDetails.GenericTextWatcher(txt_rm_ct_three_sample_quantity));
                 //   txt_rm_ct_three_sample_quantity.requestFocus();
                    txt_rm_ct_three_sample_quantity.post(new Runnable() {
                        @Override
                        public void run() {
                            final InputMethodManager imm = (InputMethodManager) txt_rm_ct_three_sample_quantity.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.showSoftInput(txt_rm_ct_three_sample_quantity, InputMethodManager.SHOW_IMPLICIT);
                           // txt_rm_ct_three_sample_quantity.requestFocus(); // needed if you have more then one input
                        }
                    });
                }
                else {
                    AppUtils.showToast(context,"please enter Sample Quantity first");
                    txt_rm_sample_quantity.setError("please enter Sample Quantity first");
                    txt_rm_sample_quantity.requestFocus();
                    txt_rm_sample_quantity.post(new Runnable() {
                        @Override
                        public void run() {
                            final InputMethodManager imm = (InputMethodManager) txt_rm_sample_quantity.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.showSoftInput(txt_rm_sample_quantity, InputMethodManager.SHOW_IMPLICIT);
                          //  txt_rm_sample_quantity.requestFocus(); // needed if you have more then one input
                        }
                    });
                }
            }
        });

        txt_rm_ct_four_sample_quantity.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!txt_rm_sample_quantity.getText().toString().isEmpty()){
                    txt_rm_ct_four_sample_quantity.addTextChangedListener(new RmAnalysisDetails.GenericTextWatcher(txt_rm_ct_four_sample_quantity));
                //    txt_rm_ct_four_sample_quantity.requestFocus();
                    txt_rm_ct_four_sample_quantity.post(new Runnable() {
                        @Override
                        public void run() {
                            final InputMethodManager imm = (InputMethodManager) txt_rm_ct_three_sample_quantity.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.showSoftInput(txt_rm_ct_three_sample_quantity, InputMethodManager.SHOW_IMPLICIT);
                            //txt_rm_ct_three_sample_quantity.requestFocus(); // needed if you have more then one input
                        }
                    });
                }
                else {
                    AppUtils.showToast(context,"please enter Sample Quantity first");
                    txt_rm_sample_quantity.setError("please enter Sample Quantity first");
                    txt_rm_sample_quantity.requestFocus();
                    txt_rm_sample_quantity.post(new Runnable() {
                        @Override
                        public void run() {
                            final InputMethodManager imm = (InputMethodManager) txt_rm_sample_quantity.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.showSoftInput(txt_rm_sample_quantity, InputMethodManager.SHOW_IMPLICIT);
                           // txt_rm_sample_quantity.requestFocus(); // needed if you have more then one input
                        }
                    });
                }
            }
        });

        txt_rm_ct_five_sample_quantity.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!txt_rm_sample_quantity.getText().toString().isEmpty()){
                    txt_rm_ct_five_sample_quantity.addTextChangedListener(new RmAnalysisDetails.GenericTextWatcher(txt_rm_ct_five_sample_quantity));
                //    txt_rm_ct_five_sample_quantity.requestFocus();
                    txt_rm_ct_five_sample_quantity.post(new Runnable() {
                        @Override
                        public void run() {
                            final InputMethodManager imm = (InputMethodManager) txt_rm_ct_five_sample_quantity.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.showSoftInput(txt_rm_ct_five_sample_quantity, InputMethodManager.SHOW_IMPLICIT);
                          //  txt_rm_ct_five_sample_quantity.requestFocus(); // needed if you have more then one input
                        }
                    });
                }
                else {
                    AppUtils.showToast(context,"please enter Sample Quantity first");
                    txt_rm_sample_quantity.setError("please enter Sample Quantity first");
                    txt_rm_sample_quantity.requestFocus();
                    txt_rm_sample_quantity.post(new Runnable() {
                        @Override
                        public void run() {
                            final InputMethodManager imm = (InputMethodManager) txt_rm_sample_quantity.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.showSoftInput(txt_rm_sample_quantity, InputMethodManager.SHOW_IMPLICIT);
                          //  txt_rm_sample_quantity.requestFocus(); // needed if you have more then one input
                        }
                    });
                }
            }
        });

        txt_rm_ct_six_sample_quantity.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!txt_rm_sample_quantity.getText().toString().isEmpty()){
                    txt_rm_ct_six_sample_quantity.addTextChangedListener(new RmAnalysisDetails.GenericTextWatcher(txt_rm_ct_six_sample_quantity));
                //    txt_rm_ct_six_sample_quantity.requestFocus();
                    txt_rm_ct_six_sample_quantity.post(new Runnable() {
                        @Override
                        public void run() {
                            final InputMethodManager imm = (InputMethodManager) txt_rm_ct_six_sample_quantity.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.showSoftInput(txt_rm_ct_six_sample_quantity, InputMethodManager.SHOW_IMPLICIT);
                           // txt_rm_ct_six_sample_quantity.requestFocus(); // needed if you have more then one input
                        }
                    });
                }
                else {
                    AppUtils.showToast(context,"please enter Sample Quantity first");
                    txt_rm_sample_quantity.setError("please enter Sample Quantity first");
                    txt_rm_sample_quantity.requestFocus();
                    txt_rm_sample_quantity.post(new Runnable() {
                        @Override
                        public void run() {
                            final InputMethodManager imm = (InputMethodManager) txt_rm_sample_quantity.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.showSoftInput(txt_rm_sample_quantity, InputMethodManager.SHOW_IMPLICIT);
                           // txt_rm_sample_quantity.requestFocus(); // needed if you have more then one input
                        }
                    });
                }
            }
        });

        txt_rm_ct_seven_sample_quantity.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!txt_rm_sample_quantity.getText().toString().isEmpty()){
                    txt_rm_ct_seven_sample_quantity.addTextChangedListener(new RmAnalysisDetails.GenericTextWatcher(txt_rm_ct_seven_sample_quantity));
                //    txt_rm_ct_seven_sample_quantity.requestFocus();
                    txt_rm_ct_seven_sample_quantity.post(new Runnable() {
                        @Override
                        public void run() {
                            final InputMethodManager imm = (InputMethodManager) txt_rm_ct_seven_sample_quantity.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.showSoftInput(txt_rm_ct_seven_sample_quantity, InputMethodManager.SHOW_IMPLICIT);
                           // txt_rm_ct_seven_sample_quantity.requestFocus(); // needed if you have more then one input
                        }
                    });
                }
                else {
                    AppUtils.showToast(context,"please enter Sample Quantity first");
                    txt_rm_sample_quantity.setError("please enter Sample Quantity first");
                    txt_rm_sample_quantity.requestFocus();
                    txt_rm_sample_quantity.post(new Runnable() {
                        @Override
                        public void run() {
                            final InputMethodManager imm = (InputMethodManager) txt_rm_sample_quantity.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.showSoftInput(txt_rm_sample_quantity, InputMethodManager.SHOW_IMPLICIT);
                          //  txt_rm_sample_quantity.requestFocus(); // needed if you have more then one input
                        }
                    });
                }
            }
        });

        txt_rm_ct_eight_sample_quantity.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!txt_rm_sample_quantity.getText().toString().isEmpty()){
                    txt_rm_ct_eight_sample_quantity.addTextChangedListener(new RmAnalysisDetails.GenericTextWatcher(txt_rm_ct_eight_sample_quantity));
                //    txt_rm_ct_eight_sample_quantity.requestFocus();
                    txt_rm_ct_eight_sample_quantity.post(new Runnable() {
                        @Override
                        public void run() {
                            final InputMethodManager imm = (InputMethodManager) txt_rm_ct_eight_sample_quantity.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.showSoftInput(txt_rm_ct_eight_sample_quantity, InputMethodManager.SHOW_IMPLICIT);
                          //  txt_rm_ct_eight_sample_quantity.requestFocus(); // needed if you have more then one input
                        }
                    });
                }
                else {
                    AppUtils.showToast(context,"please enter Sample Quantity first");
                    txt_rm_sample_quantity.setError("please enter Sample Quantity first");
                    txt_rm_sample_quantity.requestFocus();
                    txt_rm_sample_quantity.post(new Runnable() {
                        @Override
                        public void run() {
                            final InputMethodManager imm = (InputMethodManager) txt_rm_sample_quantity.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.showSoftInput(txt_rm_sample_quantity, InputMethodManager.SHOW_IMPLICIT);
                         //   txt_rm_sample_quantity.requestFocus(); // needed if you have more then one input
                        }
                    });
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

        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        public void afterTextChanged(Editable editable) {
            String text;
            if (editable.toString() != null && !editable.toString().equals("")) {
                text = editable.toString();
            } else {
                text = "0.0";
            }
            switch (view.getId()) {
                case R.id.txt_rm_ct_one_no_of_pics:
                    setTextForColumnThree(text,txt_rm_ct_one_per_sam_qt,txt_rm_ct_one_sample_quantity,txt_rm_ct_one_recd_qty,txt_rm_ct_one_final_qty,cat_one);
                    break;
                case R.id.txt_rm_ct_one_sample_quantity:
                    setTextForColumnThree_basedOncolumn2(text,txt_rm_ct_one_per_sam_qt,txt_rm_ct_one_no_of_pics,txt_rm_ct_one_recd_qty,txt_rm_ct_one_final_qty,cat_one);
                    break;
                case R.id.txt_rm_ct_two_no_of_pics:
                    setTextForColumnThree(text,txt_rm_ct_two_per_sam_qt, txt_rm_ct_two_sample_quantity, txt_rm_ct_two_recd_qty, txt_rm_ct_two_final_qty, cat_two);

                    break;
                case R.id.txt_rm_ct_two_sample_quantity:
                    setTextForColumnThree_basedOncolumn2(text,txt_rm_ct_two_per_sam_qt,txt_rm_ct_two_no_of_pics,txt_rm_ct_two_recd_qty,txt_rm_ct_two_final_qty, cat_two);
                    break;
                case R.id.txt_rm_ct_three_no_of_pics:
                    setTextForColumnThree(text,txt_rm_ct_three_per_sam_qt, txt_rm_ct_three_sample_quantity, txt_rm_ct_three_recd_qty, txt_rm_ct_three_final_qty, cat_three);

                    break;
                case R.id.txt_rm_ct_three_sample_quantity:
                    setTextForColumnThree_basedOncolumn2(text,txt_rm_ct_three_per_sam_qt,txt_rm_ct_three_no_of_pics,txt_rm_ct_three_recd_qty,txt_rm_ct_three_final_qty, cat_three);
                    break;

                case R.id.txt_rm_ct_four_no_of_pics:
                    setTextForColumnThree(text,txt_rm_ct_four_per_sam_qt, txt_rm_ct_four_sample_quantity, txt_rm_ct_four_recd_qty, txt_rm_ct_four_final_qty, cat_four);
                    break;
                case R.id.txt_rm_ct_four_sample_quantity:
                    setTextForColumnThree_basedOncolumn2(text,txt_rm_ct_four_per_sam_qt,txt_rm_ct_four_no_of_pics,txt_rm_ct_four_recd_qty,txt_rm_ct_four_final_qty, cat_four);
                    break;

                case R.id.txt_rm_ct_five_no_of_pics:
                    setTextForColumnThree(text,txt_rm_ct_five_per_sam_qt, txt_rm_ct_five_sample_quantity, txt_rm_ct_five_recd_qty, txt_rm_ct_five_final_qty, cat_five);

                    break;
                case R.id.txt_rm_ct_five_sample_quantity:
                    setTextForColumnThree_basedOncolumn2(text,txt_rm_ct_five_per_sam_qt,txt_rm_ct_five_no_of_pics,txt_rm_ct_five_recd_qty,txt_rm_ct_five_final_qty, cat_five);

                    break;

                case R.id.txt_rm_ct_six_no_of_pics:
                    setTextForColumnThree(text,txt_rm_ct_six_per_sam_qt, txt_rm_ct_six_sample_quantity, txt_rm_ct_six_recd_qty, txt_rm_ct_six_final_qty, cat_six);

                    break;
                case R.id.txt_rm_ct_six_sample_quantity:
                    setTextForColumnThree_basedOncolumn2(text,txt_rm_ct_six_per_sam_qt,txt_rm_ct_six_no_of_pics,txt_rm_ct_six_recd_qty,txt_rm_ct_six_final_qty, cat_six);

                    break;


                case R.id.txt_rm_ct_seven_no_of_pics:
                    setTextForColumnThree(text,txt_rm_ct_seven_per_sam_qt, txt_rm_ct_seven_sample_quantity, txt_rm_ct_seven_recd_qty, txt_rm_ct_seven_final_qty, cat_seven);

                    break;
                case R.id.txt_rm_ct_seven_sample_quantity:
                    setTextForColumnThree_basedOncolumn2(text,txt_rm_ct_seven_per_sam_qt,txt_rm_ct_seven_no_of_pics,txt_rm_ct_seven_recd_qty,txt_rm_ct_seven_final_qty, cat_seven);
                    break;


                case R.id.txt_rm_ct_eight_no_of_pics:
                    setTextForColumnThree(text,txt_rm_ct_eight_per_sam_qt, txt_rm_ct_eight_sample_quantity, txt_rm_ct_eight_recd_qty, txt_rm_ct_eight_final_qty, cat_eight);
                    break;
                case R.id.txt_rm_ct_eight_sample_quantity:
                    setTextForColumnThree_basedOncolumn2(text,txt_rm_ct_eight_per_sam_qt,txt_rm_ct_eight_no_of_pics,txt_rm_ct_eight_recd_qty,txt_rm_ct_eight_final_qty, cat_eight);

                    break;

            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void setTextForColumnThree(String text, TextView txt_rm_ct_one_per_sam_qt, EditText txt_rm_ct_one_sample_quantity, TextView txt_rm_ct_one_recd_qty, EditText txt_rm_ct_one_final_qty, TextView cat_one) {
        if (txt_rm_no_of_pics.getText().toString()!=""  && rmAnalysisDetailsModel.getQuantity()!=null){
            txt_rm_ct_one_sample_quantity.setEnabled(false);
            txt_rm_ct_one_sample_quantity.setText("0.000");
            float no_pieces=Float.parseFloat(text);
            Log.e("no_pieces",String.valueOf(no_pieces));
            float totalPieces=Integer.parseInt(txt_rm_no_of_pics.getText().toString());
            Log.e("totalPieces",String.valueOf(totalPieces));
            float result=(no_pieces/totalPieces)*100;
            Log.e("result",String.valueOf(result));
            float value_for_column4=(Float.parseFloat(rmAnalysisDetailsModel.getQuantity())*result)/100;
            Log.e("value_for_column4",String.valueOf(value_for_column4));
            txt_rm_ct_one_recd_qty.setText(AppUtils.roundValue(String.valueOf(value_for_column4)));
            txt_rm_ct_one_final_qty.setText(AppUtils.roundValue(String.valueOf(value_for_column4)));
            txt_rm_ct_one_per_sam_qt.setText(AppUtils.roundValue(String.valueOf(result)));
            cat_one.setBackground(getResources().getDrawable(R.drawable.round_back_ground_blue));
            cat_one.setTextColor(getResources().getColor(R.color.color_white));
        }
        else {
            AppUtils.showToast(context,"Please check field No of pieces or Quantity");
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void setTextForColumnThree_basedOncolumn2(String text, TextView txt_rm_ct_one_per_sam_qt, EditText txt_rm_ct_one_no_of_pics, TextView txt_rm_ct_one_recd_qty, EditText txt_rm_ct_one_final_qty, TextView cat_one) {
        if (txt_rm_sample_quantity.getText().toString()!="" && rmAnalysisDetailsModel.getQuantity()!=null){
            txt_rm_ct_one_no_of_pics.setEnabled(false);
            txt_rm_ct_one_no_of_pics.setText("0");
            float no_pieces=Float.parseFloat(text);
            Log.e("no_pieces",String.valueOf(no_pieces));
            float totalPieces=Float.parseFloat(txt_rm_sample_quantity.getText().toString());
            Log.e("totalPieces",String.valueOf(totalPieces));
            float result=(no_pieces/totalPieces)*100;
            Log.e("result",String.valueOf(result));
            float value_for_column4=(Float.parseFloat(rmAnalysisDetailsModel.getQuantity())*result)/100;
            Log.e("value_for_column4",String.valueOf(value_for_column4));
            txt_rm_ct_one_recd_qty.setText(AppUtils.roundValue(String.valueOf(value_for_column4)));
            txt_rm_ct_one_final_qty.setText(AppUtils.roundValue(String.valueOf(value_for_column4)));
            txt_rm_ct_one_per_sam_qt.setText(AppUtils.roundValue(String.valueOf(result)));
            cat_one.setBackground(getResources().getDrawable(R.drawable.round_back_ground_blue));
            cat_one.setTextColor(getResources().getColor(R.color.color_white));
        }
        else {
            AppUtils.showToast(context,"Please check field Sample Quantity or Quantity");
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
                        RmAnalysisDetails.super.onBackPressed();
                    }
                });
    }
}
