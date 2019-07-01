package com.PG.testingapp.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.PG.testingapp.R;
import com.PG.testingapp.Utils.AppUtils;
import com.PG.testingapp.model.Processes_data;
import com.PG.testingapp.model.ValueEditionDetaillsModel;

import java.util.ArrayList;

public class ValueEditionInsertDetailsAdapter extends RecyclerView.Adapter<ValueEditionInsertDetailsAdapter.ViewHolder> {


    private Context context;
    private ArrayList<ValueEditionDetaillsModel> insertDetails;
    float cummulativeWeight;

    public ValueEditionInsertDetailsAdapter(Context context, ArrayList<ValueEditionDetaillsModel> insertDetails) {
        this.context = context;
        this.insertDetails = insertDetails;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.value_editon_insert_details_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        if(i %2 == 1)
        {
            holder.itemView.setBackgroundColor(Color.parseColor("#F5F6F8"));
        }
        else
        {
            holder.itemView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }

        cummulativeWeight= Float.parseFloat(cummulativeWeight+ insertDetails.get(i).getNet_weight());
        holder.txt_cummulative_weight.setText(AppUtils.roundValue(String.valueOf(cummulativeWeight)));


        holder.txt_count.setText(insertDetails.get(i).getGradeNo());
        holder.txt_time.setText(insertDetails.get(i).getTime());
        holder.txt_no_nets.setText(String.valueOf(insertDetails.get(i).getNo_of_nets()));
        holder.txt_total_tare_wt.setText( AppUtils.roundValue(String.valueOf(insertDetails.get(i).getTotal_tare_weight())));
        holder.txt_total_weight.setText( AppUtils.roundValue(String.valueOf(insertDetails.get(i).getTotal_weight())));
        holder.txt_net_weight.setText(AppUtils.roundValue(String.valueOf( insertDetails.get(i).getNet_weight())));
    }

    @Override
    public int getItemCount() {
        return insertDetails.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txt_count,txt_time,txt_no_nets,txt_total_weight,txt_total_tare_wt,txt_net_weight,txt_cummulative_weight;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_count=itemView.findViewById(R.id.txt_count);
            txt_time=itemView.findViewById(R.id.txt_time);
            txt_no_nets=itemView.findViewById(R.id.txt_no_nets);
            txt_total_tare_wt=itemView.findViewById(R.id.txt_total_tare_wt);
            txt_total_weight=itemView.findViewById(R.id.txt_total_weight);
            txt_net_weight=itemView.findViewById(R.id.txt_net_weight);
            txt_net_weight=itemView.findViewById(R.id.txt_net_weight);
            txt_cummulative_weight=itemView.findViewById(R.id.txt_cummulative_weight);
//            txt_person_name=itemView.findViewById(R.id.txt_person_name);
//            txt_team_no=itemView.findViewById(R.id.txt_team_no);
        }
    }
}
