package com.PG.testingapp.model.HeadLessGrading;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class PackingGradeStatus implements Serializable {

    @SerializedName("status")
    private String status;

    @SerializedName("message")
    private String message;

    @SerializedName("FPVariety")
    private ArrayList<PackingGradeDetails> FPVariety;

    public PackingGradeStatus(String status, String message, ArrayList<PackingGradeDetails> FPVariety) {
        this.status = status;
        this.message = message;
        this.FPVariety = FPVariety;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<PackingGradeDetails> getFPVariety() {
        return FPVariety;
    }

    public void setFPVariety(ArrayList<PackingGradeDetails> FPVariety) {
        this.FPVariety = FPVariety;
    }
}
