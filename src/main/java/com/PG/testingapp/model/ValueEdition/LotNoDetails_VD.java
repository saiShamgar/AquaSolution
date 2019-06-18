package com.PG.testingapp.model.ValueEdition;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LotNoDetails_VD implements Serializable {

    @SerializedName("Lot_No")
    private String Lot_No;

    @SerializedName("FP_Production_Grade_Code")
    private String FP_Production_Grade_Code;

    @SerializedName("Org_office_no")
    private String Org_office_no;

    @SerializedName("Issued_Org_Office_No")
    private String Issued_Org_Office_No;

    @SerializedName("FP_Production_Grade_No")
    private String FP_Production_Grade_No;

    @SerializedName("Production_Process_Schedule_No")
    private String Production_Process_Schedule_No;

    @SerializedName("Allotted_Quantity")
    private String Allotted_Quantity;

    @SerializedName("Product_Process_Name")
    private String Product_Process_Name;

    @SerializedName("Product_Process_Code")
    private String Product_Process_Code;

    public LotNoDetails_VD(String lot_No, String FP_Production_Grade_Code, String org_office_no, String issued_Org_Office_No, String FP_Production_Grade_No, String production_Process_Schedule_No, String allotted_Quantity, String product_Process_Name, String product_Process_Code) {
        Lot_No = lot_No;
        this.FP_Production_Grade_Code = FP_Production_Grade_Code;
        Org_office_no = org_office_no;
        Issued_Org_Office_No = issued_Org_Office_No;
        this.FP_Production_Grade_No = FP_Production_Grade_No;
        Production_Process_Schedule_No = production_Process_Schedule_No;
        Allotted_Quantity = allotted_Quantity;
        Product_Process_Name = product_Process_Name;
        Product_Process_Code = product_Process_Code;
    }

    public String getLot_No() {
        return Lot_No;
    }

    public void setLot_No(String lot_No) {
        Lot_No = lot_No;
    }

    public String getFP_Production_Grade_Code() {
        return FP_Production_Grade_Code;
    }

    public void setFP_Production_Grade_Code(String FP_Production_Grade_Code) {
        this.FP_Production_Grade_Code = FP_Production_Grade_Code;
    }

    public String getOrg_office_no() {
        return Org_office_no;
    }

    public void setOrg_office_no(String org_office_no) {
        Org_office_no = org_office_no;
    }

    public String getIssued_Org_Office_No() {
        return Issued_Org_Office_No;
    }

    public void setIssued_Org_Office_No(String issued_Org_Office_No) {
        Issued_Org_Office_No = issued_Org_Office_No;
    }

    public String getFP_Production_Grade_No() {
        return FP_Production_Grade_No;
    }

    public void setFP_Production_Grade_No(String FP_Production_Grade_No) {
        this.FP_Production_Grade_No = FP_Production_Grade_No;
    }

    public String getProduction_Process_Schedule_No() {
        return Production_Process_Schedule_No;
    }

    public void setProduction_Process_Schedule_No(String production_Process_Schedule_No) {
        Production_Process_Schedule_No = production_Process_Schedule_No;
    }

    public String getAllotted_Quantity() {
        return Allotted_Quantity;
    }

    public void setAllotted_Quantity(String allotted_Quantity) {
        Allotted_Quantity = allotted_Quantity;
    }

    public String getProduct_Process_Name() {
        return Product_Process_Name;
    }

    public void setProduct_Process_Name(String product_Process_Name) {
        Product_Process_Name = product_Process_Name;
    }

    public String getProduct_Process_Code() {
        return Product_Process_Code;
    }

    public void setProduct_Process_Code(String product_Process_Code) {
        Product_Process_Code = product_Process_Code;
    }
}
