package com.PG.testingapp.model.Soaking;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class SoakingInsertDetails implements Serializable {

    @SerializedName("CTP_Duration")
    private String CTP_Duration;

    @SerializedName("Lot_No")
    private String Lot_No;

    @SerializedName("Soaking_Process_Schedule_No")
    private String Soaking_Process_Schedule_No;

    @SerializedName("Production_Process_Schedule_No")
    private String Production_Process_Schedule_No;

    @SerializedName("Product_Process_Code")
    private String Product_Process_Code;

    @SerializedName("CTP_Date_Time_Start")
    private String CTP_Date_Time_Start;

    @SerializedName("CTP_Date_Time_End")
    private String CTP_Date_Time_End;

    @SerializedName("CTP_Weighment_Date_Time")
    private ArrayList<String> CTP_Weighment_Date_Time;

    @SerializedName("FP_Production_Grade_Code")
    private String FP_Production_Grade_Code;


    @SerializedName("Tub_No")
    private ArrayList<String> Tub_No;

    @SerializedName("CTP_No_of_Nets")
    private ArrayList<String> CTP_No_of_Nets;

    @SerializedName("CTP_Net_Tare_Wt")
    private ArrayList<String> CTP_Net_Tare_Wt;


    @SerializedName("CTP_Total_Weight")
    private ArrayList<String> CTP_Total_Weight;

    @SerializedName("CTP_Total_Tare_Weight")
    private ArrayList<String> CTP_Total_Tare_Weight;

    @SerializedName("CTP_Net_Weight")
    private ArrayList<String> CTP_Net_Weight;

    @SerializedName("CTP_Supervisor_Emp_Id")
    private String CTP_Supervisor_Emp_Id;

    @SerializedName("FP_Grade_Code")
    private String FP_Grade_Code;


    public SoakingInsertDetails(String CTP_Duration, String lot_No, String soaking_Process_Schedule_No, String production_Process_Schedule_No, String product_Process_Code, String CTP_Date_Time_Start, String CTP_Date_Time_End, ArrayList<String> CTP_Weighment_Date_Time, String FP_Production_Grade_Code, ArrayList<String> tub_No, ArrayList<String> CTP_No_of_Nets, ArrayList<String> CTP_Net_Tare_Wt, ArrayList<String> CTP_Total_Weight, ArrayList<String> CTP_Total_Tare_Weight, ArrayList<String> CTP_Net_Weight, String CTP_Supervisor_Emp_Id, String FP_Grade_Code) {
        this.CTP_Duration = CTP_Duration;
        Lot_No = lot_No;
        Soaking_Process_Schedule_No = soaking_Process_Schedule_No;
        Production_Process_Schedule_No = production_Process_Schedule_No;
        Product_Process_Code = product_Process_Code;
        this.CTP_Date_Time_Start = CTP_Date_Time_Start;
        this.CTP_Date_Time_End = CTP_Date_Time_End;
        this.CTP_Weighment_Date_Time = CTP_Weighment_Date_Time;
        this.FP_Production_Grade_Code = FP_Production_Grade_Code;
        Tub_No = tub_No;
        this.CTP_No_of_Nets = CTP_No_of_Nets;
        this.CTP_Net_Tare_Wt = CTP_Net_Tare_Wt;
        this.CTP_Total_Weight = CTP_Total_Weight;
        this.CTP_Total_Tare_Weight = CTP_Total_Tare_Weight;
        this.CTP_Net_Weight = CTP_Net_Weight;
        this.CTP_Supervisor_Emp_Id = CTP_Supervisor_Emp_Id;
        this.FP_Grade_Code = FP_Grade_Code;
    }

    public String getCTP_Duration() {
        return CTP_Duration;
    }

    public void setCTP_Duration(String CTP_Duration) {
        this.CTP_Duration = CTP_Duration;
    }

    public String getLot_No() {
        return Lot_No;
    }

    public void setLot_No(String lot_No) {
        Lot_No = lot_No;
    }

    public String getSoaking_Process_Schedule_No() {
        return Soaking_Process_Schedule_No;
    }

    public void setSoaking_Process_Schedule_No(String soaking_Process_Schedule_No) {
        Soaking_Process_Schedule_No = soaking_Process_Schedule_No;
    }

    public String getProduction_Process_Schedule_No() {
        return Production_Process_Schedule_No;
    }

    public void setProduction_Process_Schedule_No(String production_Process_Schedule_No) {
        Production_Process_Schedule_No = production_Process_Schedule_No;
    }

    public String getProduct_Process_Code() {
        return Product_Process_Code;
    }

    public void setProduct_Process_Code(String product_Process_Code) {
        Product_Process_Code = product_Process_Code;
    }

    public String getCTP_Date_Time_Start() {
        return CTP_Date_Time_Start;
    }

    public void setCTP_Date_Time_Start(String CTP_Date_Time_Start) {
        this.CTP_Date_Time_Start = CTP_Date_Time_Start;
    }

    public String getCTP_Date_Time_End() {
        return CTP_Date_Time_End;
    }

    public void setCTP_Date_Time_End(String CTP_Date_Time_End) {
        this.CTP_Date_Time_End = CTP_Date_Time_End;
    }

    public ArrayList<String> getCTP_Weighment_Date_Time() {
        return CTP_Weighment_Date_Time;
    }

    public void setCTP_Weighment_Date_Time(ArrayList<String> CTP_Weighment_Date_Time) {
        this.CTP_Weighment_Date_Time = CTP_Weighment_Date_Time;
    }

    public String getFP_Production_Grade_Code() {
        return FP_Production_Grade_Code;
    }

    public void setFP_Production_Grade_Code(String FP_Production_Grade_Code) {
        this.FP_Production_Grade_Code = FP_Production_Grade_Code;
    }

    public ArrayList<String> getTub_No() {
        return Tub_No;
    }

    public void setTub_No(ArrayList<String> tub_No) {
        Tub_No = tub_No;
    }

    public ArrayList<String> getCTP_No_of_Nets() {
        return CTP_No_of_Nets;
    }

    public void setCTP_No_of_Nets(ArrayList<String> CTP_No_of_Nets) {
        this.CTP_No_of_Nets = CTP_No_of_Nets;
    }

    public ArrayList<String> getCTP_Net_Tare_Wt() {
        return CTP_Net_Tare_Wt;
    }

    public void setCTP_Net_Tare_Wt(ArrayList<String> CTP_Net_Tare_Wt) {
        this.CTP_Net_Tare_Wt = CTP_Net_Tare_Wt;
    }

    public ArrayList<String> getCTP_Total_Weight() {
        return CTP_Total_Weight;
    }

    public void setCTP_Total_Weight(ArrayList<String> CTP_Total_Weight) {
        this.CTP_Total_Weight = CTP_Total_Weight;
    }

    public ArrayList<String> getCTP_Total_Tare_Weight() {
        return CTP_Total_Tare_Weight;
    }

    public void setCTP_Total_Tare_Weight(ArrayList<String> CTP_Total_Tare_Weight) {
        this.CTP_Total_Tare_Weight = CTP_Total_Tare_Weight;
    }

    public ArrayList<String> getCTP_Net_Weight() {
        return CTP_Net_Weight;
    }

    public void setCTP_Net_Weight(ArrayList<String> CTP_Net_Weight) {
        this.CTP_Net_Weight = CTP_Net_Weight;
    }

    public String getCTP_Supervisor_Emp_Id() {
        return CTP_Supervisor_Emp_Id;
    }

    public void setCTP_Supervisor_Emp_Id(String CTP_Supervisor_Emp_Id) {
        this.CTP_Supervisor_Emp_Id = CTP_Supervisor_Emp_Id;
    }

    public String getFP_Grade_Code() {
        return FP_Grade_Code;
    }

    public void setFP_Grade_Code(String FP_Grade_Code) {
        this.FP_Grade_Code = FP_Grade_Code;
    }
}
