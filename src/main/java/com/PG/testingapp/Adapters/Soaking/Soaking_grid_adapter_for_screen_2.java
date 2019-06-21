package com.PG.testingapp.Adapters.Soaking;

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
import com.PG.testingapp.model.Soaking.Soaking_Grid_two_model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class Soaking_grid_adapter_for_screen_2 extends RecyclerView.Adapter<Soaking_grid_adapter_for_screen_2.ViewHolder> {

    private Context context;
    private ArrayList<Soaking_Grid_two_model> details;
    float cummulativeWeight,total_weight;

    public Soaking_grid_adapter_for_screen_2(Context context, ArrayList<Soaking_Grid_two_model> details) {
        this.context = context;
        this.details = details;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cus_soaking_layout_grid_two,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txt_grade.setVisibility(View.VISIBLE);
        final Soaking_Grid_two_model valueEditionDetaillsModel1=details.get(position);
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AppUtils.showCustomOkCancelDialog(v.getContext(), "", "Do you want to delete this row?", "YES", "NO",
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                removeItem(valueEditionDetaillsModel1);
                            }
                        },
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        });
                return false;
            }
        });

        cummulativeWeight= cummulativeWeight+ valueEditionDetaillsModel1.getNet_weight();
        BigDecimal bd1 = new BigDecimal(cummulativeWeight).setScale(2, RoundingMode.HALF_UP);
        cummulativeWeight= (float) bd1.doubleValue();
        holder.txt_cumulative_wt.setText(String.valueOf(cummulativeWeight));

        if(position %2 == 1)
        {
            holder.itemView.setBackgroundColor(Color.parseColor("#F5F6F8"));
            //  holder.imageView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
        else
        {
            holder.itemView.setBackgroundColor(Color.parseColor("#FFFFFF"));
            //  holder.imageView.setBackgroundColor(Color.parseColor("#FFFAF8FD"));
        }

        String sl= String.valueOf(position+1);
        holder.txt_sl_no.setText(sl);
        holder.txt_time.setText(valueEditionDetaillsModel1.getDate());
        holder.txt_no_nets.setText(String.valueOf(valueEditionDetaillsModel1.getNo_of_nets()));
        holder.txt_total_weight.setText(AppUtils.roundValue(String.valueOf(valueEditionDetaillsModel1.getTotal_weight())));
        holder.txt_total_tare_wt.setText(AppUtils.roundValue(String.valueOf( valueEditionDetaillsModel1.getTotal_tare_weight())));
        holder.txt_net_weight.setText(AppUtils.roundValue(String.valueOf(valueEditionDetaillsModel1.getNet_weight())));
        holder.txt_tub_no.setText(String.valueOf(valueEditionDetaillsModel1.getTub_no()));
        holder.txt_grade.setText(String.valueOf(valueEditionDetaillsModel1.getGrade()));
    }

    @Override
    public int getItemCount() {
        return details.size();
    }
    public void removeItem(Soaking_Grid_two_model data) {
        int position=details.indexOf(data);
        details.remove(position);
        notifyItemRemoved(position);
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView txt_time,txt_no_nets,txt_total_weight,txt_total_tare_wt,txt_net_weight,txt_cumulative_wt,txt_sl_no,txt_tub_no,txt_grade;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_time=itemView.findViewById(R.id.txt_time);
            txt_no_nets=itemView.findViewById(R.id.txt_no_nets);
            txt_total_weight=itemView.findViewById(R.id.txt_total_weight);
            txt_total_tare_wt=itemView.findViewById(R.id.txt_total_tare_wt);
            txt_net_weight=itemView.findViewById(R.id.txt_net_weight);
            txt_cumulative_wt=itemView.findViewById(R.id.txt_cumulative_wt);
            txt_sl_no=itemView.findViewById(R.id.txt_sl_no);
            txt_tub_no=itemView.findViewById(R.id.txt_count);
            txt_grade=itemView.findViewById(R.id.txt_grade);
        }
    }
}
