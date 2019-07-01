package com.PG.testingapp.model.Updations;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class NewCountVarietyCodesStatus implements Serializable {

    @SerializedName("status")
    private String status;

    @SerializedName("message")
    private String message;

    @SerializedName("FPVariety")
    private ArrayList<NewCountVarietyCodes> FPVariety;

    public NewCountVarietyCodesStatus(String status, String message, ArrayList<NewCountVarietyCodes> FPVariety) {
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

    public ArrayList<NewCountVarietyCodes> getFPVariety() {
        return FPVariety;
    }

    public void setFPVariety(ArrayList<NewCountVarietyCodes> FPVariety) {
        this.FPVariety = FPVariety;
    }
}
