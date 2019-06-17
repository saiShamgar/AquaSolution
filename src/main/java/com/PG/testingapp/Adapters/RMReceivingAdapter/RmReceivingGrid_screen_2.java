package com.PG.testingapp.Adapters.RMReceivingAdapter;

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
import com.PG.testingapp.model.RMReceiving.RmReceivingScreen_2_Grid;
import com.PG.testingapp.model.ValueEditionDetaillsModel;

import java.util.ArrayList;


public class RmReceivingGrid_screen_2 extends RecyclerView.Adapter<RmReceivingGrid_screen_2.ViewHodler> {

    private Context context;
    private ArrayList<RmReceivingScreen_2_Grid> details;

    public RmReceivingGrid_screen_2(Context context, ArrayList<RmReceivingScreen_2_Grid> details) {
        this.context = context;
        this.details = details;
    }

    @NonNull
    @Override
    public ViewHodler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cus_rm_receiving_screen_two,viewGroup,false);

        return new ViewHodler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHodler holder, int i) {
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

        final RmReceivingScreen_2_Grid valueEditionDetaillsModel1=details.get(i);
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
        String sl= String.valueOf(i+1);

        holder.rm_txt_sl_no.setText(sl);

        holder.rm_txt_count.setText(details.get(i).getCount());
        holder.rm_txt_product.setText(details.get(i).getProduct());
        holder.rm_txt_variety.setText(details.get(i).getVariety());
        holder.rm_txt_quantity.setText(details.get(i).getQuantity());

    }
    public void removeItem(RmReceivingScreen_2_Grid data) {
        int position=details.indexOf(data);
        details.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public int getItemCount() {
        return details.size();
    }

    public class ViewHodler extends RecyclerView.ViewHolder{
        private TextView rm_txt_sl_no,rm_txt_product,rm_txt_variety,rm_txt_count,rm_txt_quantity;

        public ViewHodler(@NonNull View itemView) {
            super(itemView);

            rm_txt_sl_no=itemView.findViewById(R.id.rm_txt_sl_no);
            rm_txt_product=itemView.findViewById(R.id.rm_txt_product);
            rm_txt_variety=itemView.findViewById(R.id.rm_txt_variety);
            rm_txt_count=itemView.findViewById(R.id.rm_txt_count);
            rm_txt_quantity=itemView.findViewById(R.id.rm_txt_quantity);
        }
    }
}
