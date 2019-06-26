package com.PG.testingapp.model.HeadLessGrading;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PackingGradeDetails implements Serializable {

    @SerializedName("Packing_Grade")
    private String Packing_Grade;

    @SerializedName("FP_Grade_Code")
    private String FP_Grade_Code;

    public PackingGradeDetails(String packing_Grade, String FP_Grade_Code) {
        Packing_Grade = packing_Grade;
        this.FP_Grade_Code = FP_Grade_Code;
    }

    public String getPacking_Grade() {
        return Packing_Grade;
    }

    public void setPacking_Grade(String packing_Grade) {
        Packing_Grade = packing_Grade;
    }

    public String getFP_Grade_Code() {
        return FP_Grade_Code;
    }

    public void setFP_Grade_Code(String FP_Grade_Code) {
        this.FP_Grade_Code = FP_Grade_Code;
    }
}
