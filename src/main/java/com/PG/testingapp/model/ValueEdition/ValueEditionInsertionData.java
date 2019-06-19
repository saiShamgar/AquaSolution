package com.PG.testingapp.model.ValueEdition;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.StringTokenizer;

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
    private ArrayList<String> VAP_Group_Emp_id;

    @SerializedName("VAP_Net_Tare_Wt")
    private ArrayList<String> VAP_Net_Tare_Wt;

    @SerializedName("VAP_Net_Weight")
    private ArrayList<String> VAP_Net_Weight;


    @SerializedName("VAP_No_of_Nets")
    private ArrayList<String> VAP_No_of_Nets;

    @SerializedName("VAP_Supervisor_Emp_Id")
    private String VAP_Supervisor_Emp_Id;

    @SerializedName("VAP_Tables_No")
    private ArrayList<String> VAP_Tables_No;

    @SerializedName("VAP_Total_Tare_Weight")
    private ArrayList<String> VAP_Total_Tare_Weight;

    @SerializedName("VAP_Total_Weight")
    private ArrayList<String> VAP_Total_Weight;

    @SerializedName("VAP_Workers_Nos")
    private ArrayList<String> VAP_Workers_Nos;

    @SerializedName("FP_Production_Grade_Code")
    private String FP_Production_Grade_Code;

    public ValueEditionInsertionData(String lot_No, String org_office_no, String product_Process_Code, String production_Process_Schedule_No, ArrayList<String> VAP_Weighment_Date_Time, ArrayList<String> VAP_Group_Emp_id, ArrayList<String> VAP_Net_Tare_Wt, ArrayList<String> VAP_Net_Weight, ArrayList<String> VAP_No_of_Nets, String VAP_Supervisor_Emp_Id, ArrayList<String> VAP_Tables_No, ArrayList<String> VAP_Total_Tare_Weight, ArrayList<String> VAP_Total_Weight, ArrayList<String> VAP_Workers_Nos, String FP_Production_Grade_Code) {
        Lot_No = lot_No;
        Org_office_no = org_office_no;
        Product_Process_Code = product_Process_Code;
        Production_Process_Schedule_No = production_Process_Schedule_No;
        this.VAP_Weighment_Date_Time = VAP_Weighment_Date_Time;
        this.VAP_Group_Emp_id = VAP_Group_Emp_id;
        this.VAP_Net_Tare_Wt = VAP_Net_Tare_Wt;
        this.VAP_Net_Weight = VAP_Net_Weight;
        this.VAP_No_of_Nets = VAP_No_of_Nets;
        this.VAP_Supervisor_Emp_Id = VAP_Supervisor_Emp_Id;
        this.VAP_Tables_No = VAP_Tables_No;
        this.VAP_Total_Tare_Weight = VAP_Total_Tare_Weight;
        this.VAP_Total_Weight = VAP_Total_Weight;
        this.VAP_Workers_Nos = VAP_Workers_Nos;
        this.FP_Production_Grade_Code = FP_Production_Grade_Code;
    }

    public String getLot_No() {
        return Lot_No;
    }

    public void setLot_No(String lot_No) {
        Lot_No = lot_No;
    }

    public String getOrg_office_no() {
        return Org_office_no;
    }

    public void setOrg_office_no(String org_office_no) {
        Org_office_no = org_office_no;
    }

    public String getProduct_Process_Code() {
        return Product_Process_Code;
    }

    public void setProduct_Process_Code(String product_Process_Code) {
        Product_Process_Code = product_Process_Code;
    }

    public String getProduction_Process_Schedule_No() {
        return Production_Process_Schedule_No;
    }

    public void setProduction_Process_Schedule_No(String production_Process_Schedule_No) {
        Production_Process_Schedule_No = production_Process_Schedule_No;
    }

    public ArrayList<String> getVAP_Weighment_Date_Time() {
        return VAP_Weighment_Date_Time;
    }

    public void setVAP_Weighment_Date_Time(ArrayList<String> VAP_Weighment_Date_Time) {
        this.VAP_Weighment_Date_Time = VAP_Weighment_Date_Time;
    }

    public ArrayList<String> getVAP_Group_Emp_id() {
        return VAP_Group_Emp_id;
    }

    public void setVAP_Group_Emp_id(ArrayList<String> VAP_Group_Emp_id) {
        this.VAP_Group_Emp_id = VAP_Group_Emp_id;
    }

    public ArrayList<String> getVAP_Net_Tare_Wt() {
        return VAP_Net_Tare_Wt;
    }

    public void setVAP_Net_Tare_Wt(ArrayList<String> VAP_Net_Tare_Wt) {
        this.VAP_Net_Tare_Wt = VAP_Net_Tare_Wt;
    }

    public ArrayList<String> getVAP_Net_Weight() {
        return VAP_Net_Weight;
    }

    public void setVAP_Net_Weight(ArrayList<String> VAP_Net_Weight) {
        this.VAP_Net_Weight = VAP_Net_Weight;
    }

    public ArrayList<String> getVAP_No_of_Nets() {
        return VAP_No_of_Nets;
    }

    public void setVAP_No_of_Nets(ArrayList<String> VAP_No_of_Nets) {
        this.VAP_No_of_Nets = VAP_No_of_Nets;
    }

    public String getVAP_Supervisor_Emp_Id() {
        return VAP_Supervisor_Emp_Id;
    }

    public void setVAP_Supervisor_Emp_Id(String VAP_Supervisor_Emp_Id) {
        this.VAP_Supervisor_Emp_Id = VAP_Supervisor_Emp_Id;
    }

    public ArrayList<String> getVAP_Tables_No() {
        return VAP_Tables_No;
    }

    public void setVAP_Tables_No(ArrayList<String> VAP_Tables_No) {
        this.VAP_Tables_No = VAP_Tables_No;
    }

    public ArrayList<String> getVAP_Total_Tare_Weight() {
        return VAP_Total_Tare_Weight;
    }

    public void setVAP_Total_Tare_Weight(ArrayList<String> VAP_Total_Tare_Weight) {
        this.VAP_Total_Tare_Weight = VAP_Total_Tare_Weight;
    }

    public ArrayList<String> getVAP_Total_Weight() {
        return VAP_Total_Weight;
    }

    public void setVAP_Total_Weight(ArrayList<String> VAP_Total_Weight) {
        this.VAP_Total_Weight = VAP_Total_Weight;
    }

    public ArrayList<String> getVAP_Workers_Nos() {
        return VAP_Workers_Nos;
    }

    public void setVAP_Workers_Nos(ArrayList<String> VAP_Workers_Nos) {
        this.VAP_Workers_Nos = VAP_Workers_Nos;
    }

    public String getFP_Production_Grade_Code() {
        return FP_Production_Grade_Code;
    }

    public void setFP_Production_Grade_Code(String FP_Production_Grade_Code) {
        this.FP_Production_Grade_Code = FP_Production_Grade_Code;
    }
}
