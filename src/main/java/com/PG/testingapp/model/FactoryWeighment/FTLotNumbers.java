package com.PG.testingapp.model.FactoryWeighment;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class FTLotNumbers implements Serializable {
    @SerializedName("Lot_Date")
    private String Lot_Date;

    @SerializedName("Lot_No")
    private String Lot_No;

    @SerializedName("Product_Variety_Code")
    private String Product_Variety_Code;

    @SerializedName("Product_Variety_Name")
    private String Product_Variety_Name;

    @SerializedName("FK_Org_office_no")
    private String FK_Org_office_no;

    @SerializedName("Org_office_Name")
    private String Org_office_Name;

    @SerializedName("status")
    private String status;

    public FTLotNumbers(String lot_Date, String lot_No, String product_Variety_Code, String product_Variety_Name, String FK_Org_office_no, String org_office_Name, String status) {
        Lot_Date = lot_Date;
        Lot_No = lot_No;
        Product_Variety_Code = product_Variety_Code;
        Product_Variety_Name = product_Variety_Name;
        this.FK_Org_office_no = FK_Org_office_no;
        Org_office_Name = org_office_Name;
        this.status = status;
    }

    public String getLot_Date() {
        return Lot_Date;
    }

    public void setLot_Date(String lot_Date) {
        Lot_Date = lot_Date;
    }

    public String getLot_No() {
        return Lot_No;
    }

    public void setLot_No(String lot_No) {
        Lot_No = lot_No;
    }

    public String getProduct_Variety_Code() {
        return Product_Variety_Code;
    }

    public void setProduct_Variety_Code(String product_Variety_Code) {
        Product_Variety_Code = product_Variety_Code;
    }

    public String getProduct_Variety_Name() {
        return Product_Variety_Name;
    }

    public void setProduct_Variety_Name(String product_Variety_Name) {
        Product_Variety_Name = product_Variety_Name;
    }

    public String getFK_Org_office_no() {
        return FK_Org_office_no;
    }

    public void setFK_Org_office_no(String FK_Org_office_no) {
        this.FK_Org_office_no = FK_Org_office_no;
    }

    public String getOrg_office_Name() {
        return Org_office_Name;
    }

    public void setOrg_office_Name(String org_office_Name) {
        Org_office_Name = org_office_Name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
