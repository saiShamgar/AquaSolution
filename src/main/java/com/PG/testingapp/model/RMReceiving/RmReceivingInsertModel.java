package com.PG.testingapp.model.RMReceiving;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class RmReceivingInsertModel implements Serializable {

    @SerializedName("RM_IGP_No")
    private String RM_IGP_No;

    @SerializedName("RM_Process_Number")
    private String RM_Process_Number;

    @SerializedName("Aqua_Farmer_No")
    private String Aqua_Farmer_No;

    @SerializedName("Lot_No")
    private String Lot_No;

    @SerializedName("RM_PS_Date")
    private String RM_PS_Date;

    @SerializedName("Aqua_Agent_No")
    private String Aqua_Agent_No;

    @SerializedName("Org_office_no")
    private String Org_office_no;

    @SerializedName("Procurement_Schedule_No")
    private String Procurement_Schedule_No;

    @SerializedName("Lot_Date")
    private String Lot_Date;

    @SerializedName("Material_Group_Code")
    private ArrayList<String> Material_Group_Code;

    @SerializedName("Product_Variety_Code")
    private ArrayList<String> Product_Variety_Code;

    @SerializedName("Variety_Count_Code")
    private ArrayList<String> Variety_Count_Code;

    @SerializedName("Received_Qty")
    private ArrayList<String> Received_Qty;

    @SerializedName("RM_Inward_Date")
    private String RM_Inward_Date;

    public RmReceivingInsertModel(String RM_IGP_No, String RM_Process_Number, String aqua_Farmer_No, String lot_No, String RM_PS_Date, String aqua_Agent_No, String org_office_no, String procurement_Schedule_No, String lot_Date, ArrayList<String> material_Group_Code, ArrayList<String> product_Variety_Code, ArrayList<String> variety_Count_Code, ArrayList<String> received_Qty, String RM_Inward_Date) {
        this.RM_IGP_No = RM_IGP_No;
        this.RM_Process_Number = RM_Process_Number;
        Aqua_Farmer_No = aqua_Farmer_No;
        Lot_No = lot_No;
        this.RM_PS_Date = RM_PS_Date;
        Aqua_Agent_No = aqua_Agent_No;
        Org_office_no = org_office_no;
        Procurement_Schedule_No = procurement_Schedule_No;
        Lot_Date = lot_Date;
        Material_Group_Code = material_Group_Code;
        Product_Variety_Code = product_Variety_Code;
        Variety_Count_Code = variety_Count_Code;
        Received_Qty = received_Qty;
        this.RM_Inward_Date = RM_Inward_Date;
    }

    public String getRM_IGP_No() {
        return RM_IGP_No;
    }

    public void setRM_IGP_No(String RM_IGP_No) {
        this.RM_IGP_No = RM_IGP_No;
    }

    public String getRM_Process_Number() {
        return RM_Process_Number;
    }

    public void setRM_Process_Number(String RM_Process_Number) {
        this.RM_Process_Number = RM_Process_Number;
    }

    public String getAqua_Farmer_No() {
        return Aqua_Farmer_No;
    }

    public void setAqua_Farmer_No(String aqua_Farmer_No) {
        Aqua_Farmer_No = aqua_Farmer_No;
    }

    public String getLot_No() {
        return Lot_No;
    }

    public void setLot_No(String lot_No) {
        Lot_No = lot_No;
    }

    public String getRM_PS_Date() {
        return RM_PS_Date;
    }

    public void setRM_PS_Date(String RM_PS_Date) {
        this.RM_PS_Date = RM_PS_Date;
    }

    public String getAqua_Agent_No() {
        return Aqua_Agent_No;
    }

    public void setAqua_Agent_No(String aqua_Agent_No) {
        Aqua_Agent_No = aqua_Agent_No;
    }

    public String getOrg_office_no() {
        return Org_office_no;
    }

    public void setOrg_office_no(String org_office_no) {
        Org_office_no = org_office_no;
    }

    public String getProcurement_Schedule_No() {
        return Procurement_Schedule_No;
    }

    public void setProcurement_Schedule_No(String procurement_Schedule_No) {
        Procurement_Schedule_No = procurement_Schedule_No;
    }

    public String getLot_Date() {
        return Lot_Date;
    }

    public void setLot_Date(String lot_Date) {
        Lot_Date = lot_Date;
    }

    public ArrayList<String> getMaterial_Group_Code() {
        return Material_Group_Code;
    }

    public void setMaterial_Group_Code(ArrayList<String> material_Group_Code) {
        Material_Group_Code = material_Group_Code;
    }

    public ArrayList<String> getProduct_Variety_Code() {
        return Product_Variety_Code;
    }

    public void setProduct_Variety_Code(ArrayList<String> product_Variety_Code) {
        Product_Variety_Code = product_Variety_Code;
    }

    public ArrayList<String> getVariety_Count_Code() {
        return Variety_Count_Code;
    }

    public void setVariety_Count_Code(ArrayList<String> variety_Count_Code) {
        Variety_Count_Code = variety_Count_Code;
    }

    public ArrayList<String> getReceived_Qty() {
        return Received_Qty;
    }

    public void setReceived_Qty(ArrayList<String> received_Qty) {
        Received_Qty = received_Qty;
    }

    public String getRM_Inward_Date() {
        return RM_Inward_Date;
    }

    public void setRM_Inward_Date(String RM_Inward_Date) {
        this.RM_Inward_Date = RM_Inward_Date;
    }
}
