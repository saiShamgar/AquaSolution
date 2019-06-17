package com.PG.testingapp.model.RMReceiving;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RMReceive_IGP_No implements Serializable {

    @SerializedName("RM_Inward_Date")
    private String RM_Inward_Date;

    @SerializedName("RM_IGP_No")
    private String RM_IGP_No;

    @SerializedName("Procurement_Schedule_No")
    private String Procurement_Schedule_No;

    @SerializedName("Org_office_no")
    private String Org_office_no;

    @SerializedName("FK_Farmer_No")
    private String FK_Farmer_No;

    @SerializedName("Aqua_Agent_No")
    private String Aqua_Agent_No;

    @SerializedName("RM_Inward_Remarks")
    private String RM_Inward_Remarks;

    @SerializedName("Lot_No")
    private String Lot_No;

    @SerializedName("Material_Group_Code")
    private String Material_Group_Code;

    @SerializedName("Product_Variety_Code")
    private String Product_Variety_Code;

    @SerializedName("Variety_Count_Code")
    private String Variety_Count_Code;

    @SerializedName("Product_Variety_Name")
    private String Product_Variety_Name;

    @SerializedName("Variety_Count")
    private String Variety_Count;

    @SerializedName("Aqua_Farmer_No")
    private String Aqua_Farmer_No;

    @SerializedName("Aqua_Farmer_Name")
    private String Aqua_Farmer_Name;

    @SerializedName("Aqua_Agent_Name")
    private String Aqua_Agent_Name;

    @SerializedName("OfficeLoc")
    private String OfficeLoc;

    @SerializedName("Status")
    private String Status;

    @SerializedName("Lot_date")
    private String Lot_date;

    @SerializedName("quantity")
    private String quantity;

    public RMReceive_IGP_No(String RM_Inward_Date, String RM_IGP_No, String procurement_Schedule_No, String org_office_no, String FK_Farmer_No, String aqua_Agent_No, String RM_Inward_Remarks, String lot_No, String material_Group_Code, String product_Variety_Code, String variety_Count_Code, String product_Variety_Name, String variety_Count, String aqua_Farmer_No, String aqua_Farmer_Name, String aqua_Agent_Name, String officeLoc, String status, String lot_date, String quantity) {
        this.RM_Inward_Date = RM_Inward_Date;
        this.RM_IGP_No = RM_IGP_No;
        Procurement_Schedule_No = procurement_Schedule_No;
        Org_office_no = org_office_no;
        this.FK_Farmer_No = FK_Farmer_No;
        Aqua_Agent_No = aqua_Agent_No;
        this.RM_Inward_Remarks = RM_Inward_Remarks;
        Lot_No = lot_No;
        Material_Group_Code = material_Group_Code;
        Product_Variety_Code = product_Variety_Code;
        Variety_Count_Code = variety_Count_Code;
        Product_Variety_Name = product_Variety_Name;
        Variety_Count = variety_Count;
        Aqua_Farmer_No = aqua_Farmer_No;
        Aqua_Farmer_Name = aqua_Farmer_Name;
        Aqua_Agent_Name = aqua_Agent_Name;
        OfficeLoc = officeLoc;
        Status = status;
        Lot_date = lot_date;
        this.quantity = quantity;
    }

    public String getRM_Inward_Date() {
        return RM_Inward_Date;
    }

    public void setRM_Inward_Date(String RM_Inward_Date) {
        this.RM_Inward_Date = RM_Inward_Date;
    }

    public String getRM_IGP_No() {
        return RM_IGP_No;
    }

    public void setRM_IGP_No(String RM_IGP_No) {
        this.RM_IGP_No = RM_IGP_No;
    }

    public String getProcurement_Schedule_No() {
        return Procurement_Schedule_No;
    }

    public void setProcurement_Schedule_No(String procurement_Schedule_No) {
        Procurement_Schedule_No = procurement_Schedule_No;
    }

    public String getOrg_office_no() {
        return Org_office_no;
    }

    public void setOrg_office_no(String org_office_no) {
        Org_office_no = org_office_no;
    }

    public String getFK_Farmer_No() {
        return FK_Farmer_No;
    }

    public void setFK_Farmer_No(String FK_Farmer_No) {
        this.FK_Farmer_No = FK_Farmer_No;
    }

    public String getAqua_Agent_No() {
        return Aqua_Agent_No;
    }

    public void setAqua_Agent_No(String aqua_Agent_No) {
        Aqua_Agent_No = aqua_Agent_No;
    }

    public String getRM_Inward_Remarks() {
        return RM_Inward_Remarks;
    }

    public void setRM_Inward_Remarks(String RM_Inward_Remarks) {
        this.RM_Inward_Remarks = RM_Inward_Remarks;
    }

    public String getLot_No() {
        return Lot_No;
    }

    public void setLot_No(String lot_No) {
        Lot_No = lot_No;
    }

    public String getMaterial_Group_Code() {
        return Material_Group_Code;
    }

    public void setMaterial_Group_Code(String material_Group_Code) {
        Material_Group_Code = material_Group_Code;
    }

    public String getProduct_Variety_Code() {
        return Product_Variety_Code;
    }

    public void setProduct_Variety_Code(String product_Variety_Code) {
        Product_Variety_Code = product_Variety_Code;
    }

    public String getVariety_Count_Code() {
        return Variety_Count_Code;
    }

    public void setVariety_Count_Code(String variety_Count_Code) {
        Variety_Count_Code = variety_Count_Code;
    }

    public String getProduct_Variety_Name() {
        return Product_Variety_Name;
    }

    public void setProduct_Variety_Name(String product_Variety_Name) {
        Product_Variety_Name = product_Variety_Name;
    }

    public String getVariety_Count() {
        return Variety_Count;
    }

    public void setVariety_Count(String variety_Count) {
        Variety_Count = variety_Count;
    }

    public String getAqua_Farmer_No() {
        return Aqua_Farmer_No;
    }

    public void setAqua_Farmer_No(String aqua_Farmer_No) {
        Aqua_Farmer_No = aqua_Farmer_No;
    }

    public String getAqua_Farmer_Name() {
        return Aqua_Farmer_Name;
    }

    public void setAqua_Farmer_Name(String aqua_Farmer_Name) {
        Aqua_Farmer_Name = aqua_Farmer_Name;
    }

    public String getAqua_Agent_Name() {
        return Aqua_Agent_Name;
    }

    public void setAqua_Agent_Name(String aqua_Agent_Name) {
        Aqua_Agent_Name = aqua_Agent_Name;
    }

    public String getOfficeLoc() {
        return OfficeLoc;
    }

    public void setOfficeLoc(String officeLoc) {
        OfficeLoc = officeLoc;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getLot_date() {
        return Lot_date;
    }

    public void setLot_date(String lot_date) {
        Lot_date = lot_date;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
