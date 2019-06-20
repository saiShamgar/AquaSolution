package com.PG.testingapp.model.Soaking;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Soaking_details implements Serializable {

    @SerializedName("Soaking_Process_Schedule_No")
    private String Soaking_Process_Schedule_No;

    @SerializedName("Lot_No")
    private String Lot_No;

    @SerializedName("Soaking_Process_Type")
    private String Soaking_Process_Type;

    @SerializedName("Soaking_Process_Details")
    private String Soaking_Process_Details;

    @SerializedName("FP_Production_Grade_Code")
    private String FP_Production_Grade_Code;

    @SerializedName("Soaking_Allotted_Quantity")
    private String Soaking_Allotted_Quantity;

    @SerializedName("FK_FP_Grade_Code")
    private String FK_FP_Grade_Code;

    @SerializedName("FP_Variety_Code")
    private String FP_Variety_Code;

    @SerializedName("FP_Variety_Name")
    private String FP_Variety_Name;

    @SerializedName("Grade")
    private String Grade;

    @SerializedName("Packing_grades")
    private String Packing_grades;

    public Soaking_details(String soaking_Process_Schedule_No, String lot_No, String soaking_Process_Type, String soaking_Process_Details, String FP_Production_Grade_Code, String soaking_Allotted_Quantity, String FK_FP_Grade_Code, String FP_Variety_Code, String FP_Variety_Name, String grade, String packing_grades) {
        Soaking_Process_Schedule_No = soaking_Process_Schedule_No;
        Lot_No = lot_No;
        Soaking_Process_Type = soaking_Process_Type;
        Soaking_Process_Details = soaking_Process_Details;
        this.FP_Production_Grade_Code = FP_Production_Grade_Code;
        Soaking_Allotted_Quantity = soaking_Allotted_Quantity;
        this.FK_FP_Grade_Code = FK_FP_Grade_Code;
        this.FP_Variety_Code = FP_Variety_Code;
        this.FP_Variety_Name = FP_Variety_Name;
        Grade = grade;
        Packing_grades = packing_grades;
    }

    public String getSoaking_Process_Schedule_No() {
        return Soaking_Process_Schedule_No;
    }

    public void setSoaking_Process_Schedule_No(String soaking_Process_Schedule_No) {
        Soaking_Process_Schedule_No = soaking_Process_Schedule_No;
    }

    public String getLot_No() {
        return Lot_No;
    }

    public void setLot_No(String lot_No) {
        Lot_No = lot_No;
    }

    public String getSoaking_Process_Type() {
        return Soaking_Process_Type;
    }

    public void setSoaking_Process_Type(String soaking_Process_Type) {
        Soaking_Process_Type = soaking_Process_Type;
    }

    public String getSoaking_Process_Details() {
        return Soaking_Process_Details;
    }

    public void setSoaking_Process_Details(String soaking_Process_Details) {
        Soaking_Process_Details = soaking_Process_Details;
    }

    public String getFP_Production_Grade_Code() {
        return FP_Production_Grade_Code;
    }

    public void setFP_Production_Grade_Code(String FP_Production_Grade_Code) {
        this.FP_Production_Grade_Code = FP_Production_Grade_Code;
    }

    public String getSoaking_Allotted_Quantity() {
        return Soaking_Allotted_Quantity;
    }

    public void setSoaking_Allotted_Quantity(String soaking_Allotted_Quantity) {
        Soaking_Allotted_Quantity = soaking_Allotted_Quantity;
    }

    public String getFK_FP_Grade_Code() {
        return FK_FP_Grade_Code;
    }

    public void setFK_FP_Grade_Code(String FK_FP_Grade_Code) {
        this.FK_FP_Grade_Code = FK_FP_Grade_Code;
    }

    public String getFP_Variety_Code() {
        return FP_Variety_Code;
    }

    public void setFP_Variety_Code(String FP_Variety_Code) {
        this.FP_Variety_Code = FP_Variety_Code;
    }

    public String getFP_Variety_Name() {
        return FP_Variety_Name;
    }

    public void setFP_Variety_Name(String FP_Variety_Name) {
        this.FP_Variety_Name = FP_Variety_Name;
    }

    public String getGrade() {
        return Grade;
    }

    public void setGrade(String grade) {
        Grade = grade;
    }

    public String getPacking_grades() {
        return Packing_grades;
    }

    public void setPacking_grades(String packing_grades) {
        Packing_grades = packing_grades;
    }
}
