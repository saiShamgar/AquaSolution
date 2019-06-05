package com.PG.testingapp.Adapters.LocAdapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.PG.testingapp.Adapters.FactoryWeighmentAdapters.FactoryWeighmentDetailsInsertion;
import com.PG.testingapp.R;
import com.PG.testingapp.model.LocationPlacement.BarcodeResults;

import java.util.ArrayList;

public class PutAway_scanner_adapter extends RecyclerView.Adapter<PutAway_scanner_adapter.Viewholder> {

    private Context context;
    private ArrayList<BarcodeResults> barcodeResults;

    public PutAway_scanner_adapter(Context context, ArrayList<BarcodeResults> barcodeResults) {
        this.context = context;
        this.barcodeResults=barcodeResults;

    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cus_loc_layout,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        if(position %2 == 1)
        {
            holder.itemView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
        else
        {
            holder.itemView.setBackgroundColor(Color.parseColor("#F5F6F8"));
        }


            String sl= String.valueOf(position+1);
            holder.loc_s_no.setText(sl);
            holder.loc_date_time.setText(barcodeResults.get(position).getPP_Date());
            holder.loc_st_no.setText(barcodeResults.get(position).getPP_Number());
            holder.loc_loc_no.setText(barcodeResults.get(position).getFK_CS_Pellet_Location_Visible_No());
            holder.loc_status.setText(barcodeResults.get(position).getStatus());

            if (barcodeResults.get(position).getStatus().contains("Scanned")){

                holder.loc_confirm.setVisibility(View.GONE);
                holder.loc_pending.setVisibility(View.VISIBLE);
            }
            else {
                holder.loc_confirm.setVisibility(View.VISIBLE);
                holder.loc_pending.setVisibility(View.GONE);
            }


    }

    @Override
    public int getItemCount() {
        return barcodeResults.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{

        private TextView loc_s_no,loc_date_time,loc_st_no,loc_loc_no,loc_status,loc_confirm,loc_pending;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            loc_s_no=itemView.findViewById(R.id.loc_s_no);
            loc_date_time=itemView.findViewById(R.id.loc_date_time);
            loc_st_no=itemView.findViewById(R.id.loc_st_no);
            loc_loc_no=itemView.findViewById(R.id.loc_loc_no);
            loc_status=itemView.findViewById(R.id.loc_status);
            loc_confirm=itemView.findViewById(R.id.loc_confirm);
            loc_pending=itemView.findViewById(R.id.loc_pending);
        }
    }
}
