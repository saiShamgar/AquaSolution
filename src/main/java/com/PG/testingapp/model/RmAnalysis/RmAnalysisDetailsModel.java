package com.PG.testingapp.model.RmAnalysis;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RmAnalysisDetailsModel implements Serializable {

    @SerializedName("FK_RM_IGP_No")
    private String FK_RM_IGP_No;

    @SerializedName("Lot_No")
    private String Lot_No;

    @SerializedName("Fk_Aqua_Farmer_No")
    private String Fk_Aqua_Farmer_No;

    @SerializedName("FK_Org_office_no")
    private String FK_Org_office_no;

    @SerializedName("Lot_Date")
    private String Lot_Date;

    @SerializedName("Aqua_Farmer_Name")
    private String Aqua_Farmer_Name;

    @SerializedName("Product_Variety_Name")
    private String Product_Variety_Name;

    @SerializedName("Variety_Count")
    private String Variety_Count;

    @SerializedName("Variety_Count_Code")
    private String Variety_Count_Code;

    @SerializedName("Location")
    private String Location;

    @SerializedName("Quantity")
    private String Quantity;

    public RmAnalysisDetailsModel(String FK_RM_IGP_No, String lot_No, String fk_Aqua_Farmer_No, String FK_Org_office_no, String lot_Date, String aqua_Farmer_Name, String product_Variety_Name, String variety_Count, String variety_Count_Code, String location, String quantity) {
        this.FK_RM_IGP_No = FK_RM_IGP_No;
        Lot_No = lot_No;
        Fk_Aqua_Farmer_No = fk_Aqua_Farmer_No;
        this.FK_Org_office_no = FK_Org_office_no;
        Lot_Date = lot_Date;
        Aqua_Farmer_Name = aqua_Farmer_Name;
        Product_Variety_Name = product_Variety_Name;
        Variety_Count = variety_Count;
        Variety_Count_Code = variety_Count_Code;
        Location = location;
        Quantity = quantity;
    }

    public String getFK_RM_IGP_No() {
        return FK_RM_IGP_No;
    }

    public void setFK_RM_IGP_No(String FK_RM_IGP_No) {
        this.FK_RM_IGP_No = FK_RM_IGP_No;
    }

    public String getLot_No() {
        return Lot_No;
    }

    public void setLot_No(String lot_No) {
        Lot_No = lot_No;
    }

    public String getFk_Aqua_Farmer_No() {
        return Fk_Aqua_Farmer_No;
    }

    public void setFk_Aqua_Farmer_No(String fk_Aqua_Farmer_No) {
        Fk_Aqua_Farmer_No = fk_Aqua_Farmer_No;
    }

    public String getFK_Org_office_no() {
        return FK_Org_office_no;
    }

    public void setFK_Org_office_no(String FK_Org_office_no) {
        this.FK_Org_office_no = FK_Org_office_no;
    }

    public String getLot_Date() {
        return Lot_Date;
    }

    public void setLot_Date(String lot_Date) {
        Lot_Date = lot_Date;
    }

    public String getAqua_Farmer_Name() {
        return Aqua_Farmer_Name;
    }

    public void setAqua_Farmer_Name(String aqua_Farmer_Name) {
        Aqua_Farmer_Name = aqua_Farmer_Name;
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

    public String getVariety_Count_Code() {
        return Variety_Count_Code;
    }

    public void setVariety_Count_Code(String variety_Count_Code) {
        Variety_Count_Code = variety_Count_Code;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }
}
