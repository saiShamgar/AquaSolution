package com.PG.testingapp.UI.RmAnalysis;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.PG.testingapp.Adapters.RmAnalysis.RmAnalysis_Screen_Three;
import com.PG.testingapp.R;
import com.PG.testingapp.Utils.AppUtils;
import com.PG.testingapp.Utils.SharedPreferenceConfig;
import com.PG.testingapp.model.RmAnalysis.AnalysisInsertModel;
import com.PG.testingapp.model.RmAnalysis.AnalysisModel;
import com.PG.testingapp.model.RmAnalysis.RmAnalysisDetailsModel;
import com.google.gson.Gson;

import java.util.ArrayList;

public class RmAnalysis_summary extends AppCompatActivity {

    private TextView txt_rm_analysis_date,txt_rm_lot_no,txt_rm_lot_date,txt_rm_farmer_name,txt_rm_location,
            txt_rm_received_count,txt_rm_quantity,txt_rm_no_of_pics,txt_rm_supervisor_name,
            txt_rm_remarks,txt_rm_sample_quantity;

    private RmAnalysisDetailsModel rmAnalysisDetailsModel;
    private String supervisor,sample_qty,no_of_pieces,date,remarks;
    private FloatingActionButton btn_rm_analysis_inserted_Upload;
    private Context context;
    private ArrayList<AnalysisModel> detaillsModels;
    private RmAnalysis_Screen_Three adapter;
    private RecyclerView rm_analysis_recycler_view;
    private SharedPreferenceConfig config;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rm_analysis_summary);

        context=RmAnalysis_summary.this;
        config=new SharedPreferenceConfig(this);

        Bundle b = getIntent().getExtras();
        detaillsModels = (ArrayList<AnalysisModel>) b.getSerializable("objNames");
        rmAnalysisDetailsModel=(RmAnalysisDetailsModel) getIntent().getSerializableExtra("process");
        supervisor=getIntent().getExtras().getString("supervisor");
        no_of_pieces=getIntent().getExtras().getString("no_of_pieces");
        sample_qty=getIntent().getExtras().getString("sample_qty");
        date=getIntent().getExtras().getString("date");
        remarks=getIntent().getExtras().getString("remarks");
        txt_rm_analysis_date=findViewById(R.id.txt_rm_analysis_date_summary);
        txt_rm_lot_no=findViewById(R.id.txt_rm_lot_no_summary);
        txt_rm_lot_date=findViewById(R.id.txt_rm_lot_date_summary);
        txt_rm_farmer_name=findViewById(R.id.txt_rm_farmer_name_summary);
        txt_rm_location=findViewById(R.id.txt_rm_location_summary);
        txt_rm_received_count=findViewById(R.id.txt_rm_received_count_summary);
        txt_rm_quantity=findViewById(R.id.txt_rm_quantity_summary);
        txt_rm_sample_quantity=findViewById(R.id.txt_rm_sample_quantity_summary);
        txt_rm_no_of_pics=findViewById(R.id.txt_rm_no_of_pics_summary);
        txt_rm_supervisor_name=findViewById(R.id.txt_rm_supervisor_name_summary);
        txt_rm_remarks=findViewById(R.id.txt_rm_remarks_summary);
        btn_rm_analysis_inserted_Upload=findViewById(R.id.btn_rm_analysis_inserted_Upload);
        rm_analysis_recycler_view=findViewById(R.id.rm_analysis_recycler_view);


        adapter=new RmAnalysis_Screen_Three(this,detaillsModels);
        DividerItemDecoration itemDecoration=new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        rm_analysis_recycler_view.addItemDecoration(itemDecoration);
        rm_analysis_recycler_view.setHasFixedSize(true);
        rm_analysis_recycler_view.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rm_analysis_recycler_view.setAdapter(adapter);

        if (rmAnalysisDetailsModel!=null){
            txt_rm_lot_no.setText(rmAnalysisDetailsModel.getLot_No());
            txt_rm_lot_date.setText(rmAnalysisDetailsModel.getLot_Date());
            txt_rm_farmer_name.setText(rmAnalysisDetailsModel.getAqua_Farmer_Name());
            txt_rm_location.setText(rmAnalysisDetailsModel.getLocation());
            txt_rm_received_count.setText(rmAnalysisDetailsModel.getVariety_Count());
            txt_rm_quantity.setText(rmAnalysisDetailsModel.getQuantity());
            txt_rm_supervisor_name.setText(supervisor);
            txt_rm_no_of_pics.setText(no_of_pieces);
            txt_rm_sample_quantity.setText(sample_qty);
            txt_rm_analysis_date.setText(date);
            txt_rm_remarks.setText(remarks);
        }



        btn_rm_analysis_inserted_Upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppUtils.showCustomOkCancelDialog(context, "",getString(R.string.upload), "YES", "NO",
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (AppUtils.isNetworkAvailable(context)){
                                    callService();
                                }
                                else {
                                    AppUtils.showToast(context,getString(R.string.error_network));
                                }
                            }
                        },
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        });
            }
        });
    }

    private void callService() {
        AnalysisInsertModel jsonData=new AnalysisInsertModel(
                rmAnalysisDetailsModel,
                detaillsModels,
                sample_qty,
                config.readLoginEmpId(),
                no_of_pieces,
                date,
                remarks);

        Gson gson = new Gson();
        String json = gson.toJson(jsonData);
        Log.e("data ",json);

    }
}
