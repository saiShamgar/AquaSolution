package com.PG.testingapp.Adapters.Soaking;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.PG.testingapp.InterFace.Soaking_OnRadioClick;
import com.PG.testingapp.R;
import com.PG.testingapp.model.Soaking.Soaking_details;

import java.util.ArrayList;

public class Soaking_grid_for_screen_one extends RecyclerView.Adapter<Soaking_grid_for_screen_one.ViewHolder> {

    private Context context;
    private Soaking_OnRadioClick onRadioClick;
    private ArrayList<Soaking_details> soaking_details;
    private int selected=-1;

    public Soaking_grid_for_screen_one(Context context, Soaking_OnRadioClick onRadioClick, ArrayList<Soaking_details> soaking_details) {
        this.context = context;
        this.onRadioClick = onRadioClick;
        this.soaking_details = soaking_details;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cus_rm_analysis_screen_one,viewGroup,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        holder.checkbox_soaking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected=(Integer)v.getTag();
                onRadioClick.onRadioClickFT(soaking_details.get(i));
                notifyDataSetChanged();
            }
        });
        holder.checkbox_soaking.setTag(i);
        holder.checkbox_soaking.setChecked(i==selected);

        holder.soaking_lot_no.setText(soaking_details.get(i).getLot_No());
        holder.soaking_variety.setText(soaking_details.get(i).getFP_Variety_Name());
        holder.soaking_grade.setText(soaking_details.get(i).getGrade());
        holder.soaking_paking_grade.setText(soaking_details.get(i).getPacking_grades());
        holder.soaking_recd_qty.setText(soaking_details.get(i).getSoaking_Allotted_Quantity());
        holder.soaking_process_details.setText(soaking_details.get(i).getSoaking_Process_Details());
    }

    @Override
    public int getItemCount() {
        return soaking_details.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private CheckBox checkbox_soaking;
            private TextView soaking_lot_no,soaking_variety,soaking_grade,soaking_paking_grade,soaking_recd_qty,soaking_process_details;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            checkbox_soaking=itemView.findViewById(R.id.checkbox_rm_analysis);
            soaking_lot_no=itemView.findViewById(R.id.rm_analysis_lot_no);
            soaking_variety=itemView.findViewById(R.id.rm_analysis_farmer_name);
            soaking_grade=itemView.findViewById(R.id.rm_analysis_location);
            soaking_paking_grade=itemView.findViewById(R.id.rm_analysis_variety);
            soaking_recd_qty=itemView.findViewById(R.id.rm_analysis_count);
            soaking_process_details=itemView.findViewById(R.id.rm_analysis_quantity);
        }
    }
}
