package com.PG.testingapp.model.ValueEdition;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class ValueEditionInsertionData implements Serializable {

    @SerializedName("Lot_No")
    private String Lot_No;

    @SerializedName("Org_office_no")
    private String Org_office_no;

    @SerializedName("Product_Process_Code")
    private String Product_Process_Code;

    @SerializedName("Production_Process_Schedule_No")
    private String Production_Process_Schedule_No;


    @SerializedName("VAP_Weighment_Date_Time")
    private ArrayList<String> VAP_Weighment_Date_Time;

    @SerializedName("VAP_Group_Emp_id")
    private String VAP_Group_Emp_id;

    @SerializedName("VAP_Group_Total_Qty")
    private String VAP_Group_Total_Qty;

    @SerializedName("VAP_Net_Tare_Wt")
    private String VAP_Net_Tare_Wt;

    @SerializedName("VAP_Net_Weight")
    private ArrayList<String> VAP_Net_Weight;

    @SerializedName("VAP_No_of_Nets")
    private String VAP_No_of_Nets;

    @SerializedName("VAP_Process_No")
    private String VAP_Process_No;

    @SerializedName("VAP_Tables_No")
    private String VAP_Tables_No;

    @SerializedName("VAP_Total_Tare_Weight")
    private ArrayList<String> VAP_Total_Tare_Weight;

    @SerializedName("VAP_Total_Weight")
    private String VAP_Total_Weight;

    @SerializedName("VAP_Weight_No")
    private String VAP_Weight_No;

    @SerializedName("VAP_Workers_Nos")
    private String VAP_Workers_Nos;
}
