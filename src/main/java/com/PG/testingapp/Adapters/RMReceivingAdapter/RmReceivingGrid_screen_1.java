package com.PG.testingapp.Adapters.RMReceivingAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.PG.testingapp.InterFace.RMReceivingRadioClick;
import com.PG.testingapp.R;
import com.PG.testingapp.Utils.AppUtils;
import com.PG.testingapp.model.RMReceiving.RMReceive_IGP_No;

import java.util.ArrayList;

public class RmReceivingGrid_screen_1 extends RecyclerView.Adapter<RmReceivingGrid_screen_1.viewHolder> {

    private Context context;
    private ArrayList<RMReceive_IGP_No> details;
    private RMReceivingRadioClick rmReceivingRadioClick;
    private int selected=-1;

    public RmReceivingGrid_screen_1(Context context, ArrayList<RMReceive_IGP_No> details, RMReceivingRadioClick rmReceivingRadioClick) {
        this.context = context;
        this.details = details;
        this.rmReceivingRadioClick = rmReceivingRadioClick;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_rm_receiving_screen_one,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int i) {
        holder.checkbox_rm_receive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected=(Integer)v.getTag();
                rmReceivingRadioClick.onRadioClick(details.get(i));
                notifyDataSetChanged();
            }
        });
        holder.checkbox_rm_receive.setTag(i);
        holder.checkbox_rm_receive.setChecked(i==selected);

        String sl= String.valueOf(i+1);
        holder.txt_sl_no.setText(sl);

        holder.txt_inward_date.setText(AppUtils.dateFormat(details.get(i).getRM_Inward_Date()));
        holder.txt_IGP_no.setText(details.get(i).getRM_IGP_No());
        holder.txt_procurement_no.setText(details.get(i).getProcurement_Schedule_No());
        holder.txt_farmer_name.setText(details.get(i).getAqua_Farmer_Name());
        holder.txt_status.setText(details.get(i).getStatus());
        holder.txt_variety.setText(details.get(i).getProduct_Variety_Name());
        holder.txt_count.setText(details.get(i).getVariety_Count());
        holder.txt_quantity.setText(details.get(i).getQuantity()  );

    }

    @Override
    public int getItemCount() {
        return details.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        private CheckBox checkbox_rm_receive;
        private TextView txt_sl_no,txt_inward_date,txt_IGP_no,txt_procurement_no,txt_farmer_name,txt_status
                ,txt_variety,txt_count,txt_quantity;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            checkbox_rm_receive=itemView.findViewById(R.id.checkbox_rm_receive);
            txt_sl_no=itemView.findViewById(R.id.txt_sl_no);
            txt_inward_date=itemView.findViewById(R.id.txt_inward_date);
            txt_IGP_no=itemView.findViewById(R.id.txt_IGP_no);
            txt_procurement_no=itemView.findViewById(R.id.txt_procurement_no);
            txt_farmer_name=itemView.findViewById(R.id.txt_farmer_name);
            txt_status=itemView.findViewById(R.id.txt_status);
            txt_variety=itemView.findViewById(R.id.txt_variety);
            txt_count=itemView.findViewById(R.id.txt_count);
            txt_quantity=itemView.findViewById(R.id.txt_quantity);
        }
    }
}
