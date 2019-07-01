package com.PG.testingapp.LocalDataBase;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity
public class HeadLessTable implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "FP_Group_Code")
    private String FP_Group_Code;

    @ColumnInfo(name = "FP_Production_Grade_Code")
    private String FP_Production_Grade_Code;

    @ColumnInfo(name = "FP_Variety_Code")
    private String FP_Variety_Code;

    @ColumnInfo(name = "Variety_Count_Code")
    private String Variety_Count_Code;

    @ColumnInfo(name = "HLGS_Emp_id")
    private String HLGS_Emp_id;

    @ColumnInfo(name = "HLG_Net_Weight")
    private String HLG_Net_Weight;

    @ColumnInfo(name = "HLG_No_of_Nets")
    private String HLG_No_of_Nets;

    @ColumnInfo(name = "HLG_Tare_Weight")
    private String HLG_Tare_Weight;

    @ColumnInfo(name = "HLG_Total_Tare_Weight")
    private String HLG_Total_Tare_Weight;

    @ColumnInfo(name = "HLG_Total_Weight")
    private String HLG_Total_Weight;

    @ColumnInfo(name = "HLW_Weighment_Date_Time")
    private String HLW_Weighment_Date_Time;

    @ColumnInfo(name = "Lot_Date")
    private String Lot_Date;

    @ColumnInfo(name = "Lot_No")
    private String Lot_No;

    @ColumnInfo(name = "FP_Group_Name")
    private String FP_Group_Name;

    @ColumnInfo(name = "FP_Variety_Name")
    private String FP_Variety_Name;

    @ColumnInfo(name = "FP_Production_Grade_Name")
    private String FP_Production_Grade_Name;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFP_Group_Code() {
        return FP_Group_Code;
    }

    public void setFP_Group_Code(String FP_Group_Code) {
        this.FP_Group_Code = FP_Group_Code;
    }

    public String getFP_Production_Grade_Code() {
        return FP_Production_Grade_Code;
    }

    public void setFP_Production_Grade_Code(String FP_Production_Grade_Code) {
        this.FP_Production_Grade_Code = FP_Production_Grade_Code;
    }

    public String getFP_Variety_Code() {
        return FP_Variety_Code;
    }

    public void setFP_Variety_Code(String FP_Variety_Code) {
        this.FP_Variety_Code = FP_Variety_Code;
    }

    public String getVariety_Count_Code() {
        return Variety_Count_Code;
    }

    public void setVariety_Count_Code(String variety_Count_Code) {
        Variety_Count_Code = variety_Count_Code;
    }

    public String getHLGS_Emp_id() {
        return HLGS_Emp_id;
    }

    public void setHLGS_Emp_id(String HLGS_Emp_id) {
        this.HLGS_Emp_id = HLGS_Emp_id;
    }

    public String getHLG_Net_Weight() {
        return HLG_Net_Weight;
    }

    public void setHLG_Net_Weight(String HLG_Net_Weight) {
        this.HLG_Net_Weight = HLG_Net_Weight;
    }

    public String getHLG_No_of_Nets() {
        return HLG_No_of_Nets;
    }

    public void setHLG_No_of_Nets(String HLG_No_of_Nets) {
        this.HLG_No_of_Nets = HLG_No_of_Nets;
    }

    public String getHLG_Tare_Weight() {
        return HLG_Tare_Weight;
    }

    public void setHLG_Tare_Weight(String HLG_Tare_Weight) {
        this.HLG_Tare_Weight = HLG_Tare_Weight;
    }

    public String getHLG_Total_Tare_Weight() {
        return HLG_Total_Tare_Weight;
    }

    public void setHLG_Total_Tare_Weight(String HLG_Total_Tare_Weight) {
        this.HLG_Total_Tare_Weight = HLG_Total_Tare_Weight;
    }

    public String getHLG_Total_Weight() {
        return HLG_Total_Weight;
    }

    public void setHLG_Total_Weight(String HLG_Total_Weight) {
        this.HLG_Total_Weight = HLG_Total_Weight;
    }

    public String getHLW_Weighment_Date_Time() {
        return HLW_Weighment_Date_Time;
    }

    public void setHLW_Weighment_Date_Time(String HLW_Weighment_Date_Time) {
        this.HLW_Weighment_Date_Time = HLW_Weighment_Date_Time;
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

    public String getFP_Group_Name() {
        return FP_Group_Name;
    }

    public void setFP_Group_Name(String FP_Group_Name) {
        this.FP_Group_Name = FP_Group_Name;
    }

    public String getFP_Variety_Name() {
        return FP_Variety_Name;
    }

    public void setFP_Variety_Name(String FP_Variety_Name) {
        this.FP_Variety_Name = FP_Variety_Name;
    }

    public String getFP_Production_Grade_Name() {
        return FP_Production_Grade_Name;
    }

    public void setFP_Production_Grade_Name(String FP_Production_Grade_Name) {
        this.FP_Production_Grade_Name = FP_Production_Grade_Name;
    }
}
