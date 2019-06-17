package com.PG.testingapp.Adapters.RmAnalysis;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.PG.testingapp.InterFace.RmAnalysis_RadioClick;
import com.PG.testingapp.R;
import com.PG.testingapp.model.RmAnalysis.RmAnalysisDetailsModel;

import java.util.ArrayList;

public class Rm_analysis_screen_1_adapter extends RecyclerView.Adapter<Rm_analysis_screen_1_adapter.ViewHolder> {

    private Context context;
    private ArrayList<RmAnalysisDetailsModel> details;
    private RmAnalysis_RadioClick rmAnalysis_radioClick;
    private int selected=-1;

    public Rm_analysis_screen_1_adapter(Context context, ArrayList<RmAnalysisDetailsModel> details, RmAnalysis_RadioClick rmAnalysis_radioClick) {
        this.context = context;
        this.details = details;
        this.rmAnalysis_radioClick = rmAnalysis_radioClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cus_rm_analysis_screen_one,viewGroup,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {

        holder.checkbox_rm_analysis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected=(Integer)v.getTag();
                rmAnalysis_radioClick.onRadioClickFT(details.get(i));
                notifyDataSetChanged();
            }
        });
        holder.checkbox_rm_analysis.setTag(i);
        holder.checkbox_rm_analysis.setChecked(i==selected);

        holder.rm_analysis_lot_no.setText(details.get(i).getLot_No());
        holder.rm_analysis_farmer_name.setText(details.get(i).getAqua_Farmer_Name());
        holder.rm_analysis_location.setText(details.get(i).getLocation());
        holder.rm_analysis_variety.setText(details.get(i).getProduct_Variety_Name());
        holder.rm_analysis_count.setText(details.get(i).getVariety_Count());
        holder.rm_analysis_quantity.setText(details.get(i).getQuantity());

    }

    @Override
    public int getItemCount() {
        return details.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private CheckBox checkbox_rm_analysis;
        private TextView rm_analysis_lot_no,rm_analysis_farmer_name,rm_analysis_location,rm_analysis_variety,rm_analysis_count,rm_analysis_quantity;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            checkbox_rm_analysis=itemView.findViewById(R.id.checkbox_rm_analysis);
            rm_analysis_lot_no=itemView.findViewById(R.id.rm_analysis_lot_no);
            rm_analysis_farmer_name=itemView.findViewById(R.id.rm_analysis_farmer_name);
            rm_analysis_location=itemView.findViewById(R.id.rm_analysis_location);
            rm_analysis_variety=itemView.findViewById(R.id.rm_analysis_variety);
            rm_analysis_count=itemView.findViewById(R.id.rm_analysis_count);
            rm_analysis_quantity=itemView.findViewById(R.id.rm_analysis_quantity);


        }
    }
}
