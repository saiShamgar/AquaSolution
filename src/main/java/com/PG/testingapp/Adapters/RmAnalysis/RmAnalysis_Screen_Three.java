package com.PG.testingapp.Adapters.RmAnalysis;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.PG.testingapp.R;
import com.PG.testingapp.model.RmAnalysis.AnalysisModel;

import java.util.ArrayList;

public class RmAnalysis_Screen_Three extends RecyclerView.Adapter<RmAnalysis_Screen_Three.ViewHolder> {

    private Context context;
    private ArrayList<AnalysisModel> analysisModels;

    public RmAnalysis_Screen_Three(Context context, ArrayList<AnalysisModel> analysisModels) {
        this.context = context;
        this.analysisModels = analysisModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rm_analysis_cus_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        holder.txt_description.setText(analysisModels.get(i).getDescription());
        holder.txt_no_pieces.setText(analysisModels.get(i).getNo_of_pieces());
        holder.txt_cuttingQty.setText(analysisModels.get(i).getCuttingQty_sampleQty());
        holder.txt_recdQty.setText(analysisModels.get(i).getCutting_in_recd_qty());
        holder.txt_per_on_sample_quantity.setText(analysisModels.get(i).getPer_on_sample_qty());
        holder.txt_final_cutting.setText(analysisModels.get(i).getFinal_cutting());

    }

    @Override
    public int getItemCount() {
        return analysisModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txt_description,txt_no_pieces,txt_cuttingQty,txt_per_on_sample_quantity,txt_recdQty,txt_final_cutting;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_description=itemView.findViewById(R.id.txt_description);
            txt_no_pieces=itemView.findViewById(R.id.txt_no_pieces);
            txt_cuttingQty=itemView.findViewById(R.id.txt_cuttingQty);
            txt_per_on_sample_quantity=itemView.findViewById(R.id.txt_per_on_sample_quantity);
            txt_recdQty=itemView.findViewById(R.id.txt_recdQty);
            txt_final_cutting=itemView.findViewById(R.id.txt_final_cutting);

        }
    }
}
