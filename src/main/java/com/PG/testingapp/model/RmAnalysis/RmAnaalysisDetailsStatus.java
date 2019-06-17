package com.PG.testingapp.model.RmAnalysis;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class RmAnaalysisDetailsStatus implements Serializable {

    @SerializedName("status")
    private String status;

    @SerializedName("message")
    private String message;

    @SerializedName("Data")
    private ArrayList<RmAnalysisDetailsModel> Data;

    public RmAnaalysisDetailsStatus(String status, String message, ArrayList<RmAnalysisDetailsModel> data) {
        this.status = status;
        this.message = message;
        Data = data;
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

    public ArrayList<RmAnalysisDetailsModel> getData() {
        return Data;
    }

    public void setData(ArrayList<RmAnalysisDetailsModel> data) {
        Data = data;
    }
}
