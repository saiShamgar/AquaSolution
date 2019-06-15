package com.PG.testingapp.model.RMReceiving;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GetCountCodes implements Serializable {

    @SerializedName("Variety_Count")
    private String Variety_Count;

    @SerializedName("Variety_Count_Code")
    private String Variety_Count_Code;

    public GetCountCodes(String variety_Count, String variety_Count_Code) {
        Variety_Count = variety_Count;
        Variety_Count_Code = variety_Count_Code;
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
}
