package com.PG.testingapp.Adapters;


import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;

import com.PG.testingapp.InterFace.OnRadioButtonClick;
import com.PG.testingapp.R;
import com.PG.testingapp.model.Processes_data;
import com.PG.testingapp.model.ValueEdition.LotNoDetails_VD;

import java.util.ArrayList;

public class GridViewAdapter extends RecyclerView.Adapter<GridViewAdapter.ViewHolder> {

    private Context context;
    private int selected=-1;
    private OnRadioButtonClick onRadioButtonClick;
    private ArrayList<LotNoDetails_VD> processes_data;

    public GridViewAdapter(Context context, OnRadioButtonClick onRadioButtonClick, ArrayList<LotNoDetails_VD> processes_data) {
        this.context = context;
        this.onRadioButtonClick = onRadioButtonClick;
        this.processes_data = processes_data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.value_edition_grid_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int i) {
        holder.checkbox_value_edition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected=(Integer)v.getTag();
                onRadioButtonClick.onRadioClick(processes_data.get(i));
                notifyDataSetChanged();
            }
        });
        holder.checkbox_value_edition.setTag(i);
        holder.checkbox_value_edition.setChecked(i==selected);

        holder.value_edition_lot_no.setText(processes_data.get(i).getLot_No());
        holder.value_edition_grade.setText(processes_data.get(i).getFP_Production_Grade_No());
        holder.value_edition_required_quantity.setText(processes_data.get(i).getAllotted_Quantity());
        holder.value_edition_process_for.setText(processes_data.get(i).getProduct_Process_Name());
        holder.value_edition_received_from.setText("");



        if(i %2 == 1)
        {
            holder.itemView.setBackgroundColor(Color.parseColor("#F5F6F8"));
            //  holder.imageView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
        else
        {
            holder.itemView.setBackgroundColor(Color.parseColor("#FFFFFF"));
            //  holder.imageView.setBackgroundColor(Color.parseColor("#FFFAF8FD"));
        }

    }

    @Override
    public int getItemCount() {
        return processes_data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private CheckBox checkbox_value_edition;
        private TextView value_edition_lot_no,value_edition_grade,value_edition_required_quantity,value_edition_received_from,value_edition_process_for;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            checkbox_value_edition=itemView.findViewById(R.id.checkbox_value_edition);
            value_edition_lot_no=itemView.findViewById(R.id.value_edition_lot_no);
            value_edition_grade=itemView.findViewById(R.id.value_edition_grade);
            value_edition_required_quantity=itemView.findViewById(R.id.value_edition_required_quantity);
            value_edition_received_from=itemView.findViewById(R.id.value_edition_received_from);
            value_edition_process_for=itemView.findViewById(R.id.value_edition_process_for);
        }
    }
}


