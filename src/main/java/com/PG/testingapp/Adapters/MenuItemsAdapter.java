package com.PG.testingapp.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.PG.testingapp.R;
import com.PG.testingapp.UI.ValueEdition;
import com.google.zxing.integration.android.IntentIntegrator;

public class MenuItemsAdapter extends RecyclerView.Adapter<MenuItemsAdapter.ViewHolder> {

    private Context context;

    public MenuItemsAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.gridview_layout,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.gridImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position==0){
                   // new IntentIntegrator((Activity) context).initiateScan();
                    Intent valueEdition=new Intent(context, ValueEdition.class);
                    context.startActivity(valueEdition);
                }
                if (position==1){
                    Toast.makeText(context,"factory weighment",Toast.LENGTH_SHORT).show();
                }
            }
        });
        if (position==0){
            holder.item_name.setText("Value Edition");
            holder.gridImage.setImageResource(R.drawable.ic_value_edi);
        }


    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView gridImage;
        private TextView item_name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            gridImage=itemView.findViewById(R.id.gridImage);
            item_name=itemView.findViewById(R.id.item_name);
        }
    }
}
