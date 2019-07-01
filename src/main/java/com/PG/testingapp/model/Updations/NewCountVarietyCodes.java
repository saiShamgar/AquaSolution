package com.PG.testingapp.model.Updations;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class NewCountVarietyCodes implements Serializable {

    @SerializedName("FP_Variety_Code")
    private String FP_Variety_Code;

    @SerializedName("FP_Variety_Name")
    private String FP_Variety_Name;

    public NewCountVarietyCodes(String FP_Variety_Code, String FP_Variety_Name) {
        this.FP_Variety_Code = FP_Variety_Code;
        this.FP_Variety_Name = FP_Variety_Name;
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
}
