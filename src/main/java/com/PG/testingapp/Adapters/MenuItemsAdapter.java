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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.PG.testingapp.R;
import com.PG.testingapp.UI.ChemicalTreatmentProcess.ChemicalTreatmentProcess;
import com.PG.testingapp.UI.FactoryWeighment.FactoryWeighment;
import com.PG.testingapp.UI.HeadLessGrading.HeadLessGrading;
import com.PG.testingapp.UI.HeadOnGrading.HeadOnGrading;
import com.PG.testingapp.UI.HeadOnHeadLessGrading.HeadOnHeadLessGrading;
import com.PG.testingapp.UI.LocationPlacement.LocationPlacement;
import com.PG.testingapp.UI.RMRecivingDetails.RMReceiving;
import com.PG.testingapp.UI.RmAnalysis.RmAnalysis;
import com.PG.testingapp.UI.Site_Weightment.Site_weightment;
import com.PG.testingapp.UI.ValueEdition;
import com.PG.testingapp.UI.WeightLoadMachine;
import com.PG.testingapp.Utils.AppUtils;
import com.PG.testingapp.Utils.SharedPreferenceConfig;
import com.google.zxing.integration.android.IntentIntegrator;

public class MenuItemsAdapter extends RecyclerView.Adapter<MenuItemsAdapter.ViewHolder> {
    private Context context;
    private SharedPreferenceConfig config;
    public MenuItemsAdapter(Context context, SharedPreferenceConfig config) {
        this.context = context;
        this.config=config;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.gridview_layout,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        holder.layout_grid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AppUtils.isNetworkAvailable(context)) {
                    if (position==0){
                        if (!(config.readMenuCodes().contains(context.getResources().getString(R.string.Site_Weighment)))){
                            AppUtils.showToast(context,"You Have No granted permissions");
                        }else {
                            Intent siteWeightment=new Intent(context, Site_weightment.class);
                            context.startActivity(siteWeightment);
                        }
                    }
                    else if (position==1){
                        if (!(config.readMenuCodes().contains(context.getResources().getString(R.string.Factory_Weighment)))){
                            AppUtils.showToast(context,"You Have No granted permissions");
                        }else {
                            Intent valueEdition=new Intent(context, FactoryWeighment.class);
                            context.startActivity(valueEdition);
                        }

                    }
                    else if (position==2){

                    }
                    else if (position==3){
                        if (!(config.readMenuCodes().contains(context.getResources().getString(R.string.Headon_Grading)))){
                            AppUtils.showToast(context,"You Have No granted permissions");
                        }else {
                        Intent valueEdition=new Intent(context, HeadOnGrading.class);
                        context.startActivity(valueEdition);
                        }
                    }
                    else  if (position==4){

                    }
                    else  if (position==5){
                        if (!(config.readMenuCodes().contains(context.getResources().getString(R.string.Headon_Headless_Grading)))){
                            AppUtils.showToast(context,"You Have No granted permissions");
                        }
                        else {
                            Intent valueEdition=new Intent(context, HeadOnHeadLessGrading.class);
                            context.startActivity(valueEdition);
                        }
                    }
                    else  if (position==6){

                    }
                    else  if (position==7){
                        if (!(config.readMenuCodes().contains(context.getResources().getString(R.string.Headless_Grading)))){
                            AppUtils.showToast(context,"You Have No granted permissions");
                        }
                        else {
                            Intent valueEdition=new Intent(context, HeadLessGrading.class);
                            context.startActivity(valueEdition);
                        }
                    }
                    else  if (position==8){

                    }
                    else  if (position==9){
                        if (!(config.readMenuCodes().contains(context.getResources().getString(R.string.Value_addition)))){
                            AppUtils.showToast(context,"You Have No granted permissions");
                        }
                        else {
                            Intent valueEdition=new Intent(context, ValueEdition.class);
                            context.startActivity(valueEdition);
                        }
                    }
                    else  if (position==10){

                    }
                    else  if (position==11){
                        if (!(config.readMenuCodes().contains(context.getResources().getString(R.string.Soaking_Process_Scheduling)))){
                            AppUtils.showToast(context,"You Have No granted permissions");
                        }else {
                            Intent valueEdition=new Intent(context, ChemicalTreatmentProcess.class);
                            context.startActivity(valueEdition);
                        }
                    }
                    else  if (position==12){

                    }
                    else  if (position==13){
                        if (!(config.readMenuCodes().contains(context.getResources().getString(R.string.Cold_Storage_Details)))){
                            AppUtils.showToast(context,"You Have No granted permissions");
                        }
                        else {
                            Intent valueEdition=new Intent(context, LocationPlacement.class);
                            context.startActivity(valueEdition);
                        }
                    }
                    else  if (position==14){
                        if (!(config.readMenuCodes().contains(context.getResources().getString(R.string.Raw_Material_Receiving)))){
                            AppUtils.showToast(context,"You Have No granted permissions");
                        }
                        else {
                            Intent valueEdition=new Intent(context, RMReceiving.class);
                            context.startActivity(valueEdition);
                        }
                    }
                    else  if (position==15){
                        if (!(config.readMenuCodes().contains(context.getResources().getString(R.string.RM_Receiving_Analysis)))){
                            AppUtils.showToast(context,"You Have No granted permissions");
                        }
                        else {
                            Intent valueEdition=new Intent(context, RmAnalysis.class);
                            context.startActivity(valueEdition);
                        }
                    }

                }
                else {
                    AppUtils.showToast(context,context.getString(R.string.error_network));
                }

            }
        });

        if (position==0){
            holder.item_name.setText("Site\n weighment");
            holder.gridImage.setImageResource(R.drawable.ic_site_weighment);
        }
        else  if (position==1){
            holder.item_name.setText("Factory\n Manual");
            holder.gridImage.setImageResource(R.drawable.ic_factory_weighment);
        }
        else  if (position==2){
            holder.item_name.setText("Factory\n WS");
            holder.gridImage.setImageResource(R.drawable.ic_factory_ws);
        }
        else  if (position==3){
            holder.item_name.setText("HeadOn\n Grading Manual");
            holder.gridImage.setImageResource(R.drawable.ic_head_on_grading);
        }
        else  if (position==4){
            holder.item_name.setText("HeadOn\n Grading WS");
            holder.gridImage.setImageResource(R.drawable.ic_headon_grading_ws);
        }
        else if (position==5){
            holder.item_name.setText("HOHL\n Manual");
            holder.gridImage.setImageResource(R.drawable.ic_hod_hd);
        }
        else  if (position==6){
            holder.item_name.setText("HOHL\nWS");
            holder.gridImage.setImageResource(R.drawable.ic_hohl_ws);
        }
        else  if (position==7){
            holder.item_name.setText("HeadLess\n Grading Manual");
            holder.gridImage.setImageResource(R.drawable.ic_headles_grading);
        }
        else  if (position==8){
            holder.item_name.setText("HeadLess\nGrading WS");
            holder.gridImage.setImageResource(R.drawable.ic_headless_grading_ws);
        }
        else  if (position==9){
            holder.item_name.setText("Value\n Edition Manual");
            holder.gridImage.setImageResource(R.drawable.ic_value_edi);
        }
        else if (position==10){
            holder.item_name.setText("Value\nEdition WS");
            holder.gridImage.setImageResource(R.drawable.ic_value_addition_ws);
        }
        else  if (position==11){
            holder.item_name.setText("Chemical\n Treatment Manual");
            holder.gridImage.setImageResource(R.drawable.ic_chemical_treatment);
        }
        else if (position==12){
            holder.item_name.setText("Chemical\nTreatment WS");
            holder.gridImage.setImageResource(R.drawable.ic_chemical_treatment_ws);
        }
        else if (position==13){
            holder.item_name.setText("Location\n Placement");
            holder.gridImage.setImageResource(R.drawable.ic_barcode);
        }
        else  if (position==14){
            holder.item_name.setText("RM\n Receiving");
            holder.gridImage.setImageResource(R.drawable.ic_raw_material);
        }
        else  if (position==15){
            holder.item_name.setText("RM\n Analysis");
            holder.gridImage.setImageResource(R.drawable.ic_raw_material_analysis);
        }

    }

    @Override
    public int getItemCount() {
        return 16;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView gridImage;
        private TextView item_name;
        private LinearLayout layout_grid;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            gridImage=itemView.findViewById(R.id.gridImage);
            item_name=itemView.findViewById(R.id.item_name);
            layout_grid=itemView.findViewById(R.id.layout_grid);
        }
    }
}
