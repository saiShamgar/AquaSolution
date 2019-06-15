package com.PG.testingapp.model.RMReceiving;

import android.widget.ArrayAdapter;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class RMRecivingLoationsStatus implements Serializable {

    @SerializedName("status")
    private String status;

    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private ArrayList<RMReceivingLocationDetails> data;

    @SerializedName("lotnum")
    private String lotnum;

    @SerializedName("processnum")
    private String processnum;

    @SerializedName("materialgroup")
    private ArrayList<RM_MetrialData> materialgroup;

    public RMRecivingLoationsStatus(String status, String message, ArrayList<RMReceivingLocationDetails> data, String lotnum, String processnum, ArrayList<RM_MetrialData> materialgroup) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.lotnum = lotnum;
        this.processnum = processnum;
        this.materialgroup = materialgroup;
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

    public ArrayList<RMReceivingLocationDetails> getData() {
        return data;
    }

    public void setData(ArrayList<RMReceivingLocationDetails> data) {
        this.data = data;
    }

    public String getLotnum() {
        return lotnum;
    }

    public void setLotnum(String lotnum) {
        this.lotnum = lotnum;
    }

    public String getProcessnum() {
        return processnum;
    }

    public void setProcessnum(String processnum) {
        this.processnum = processnum;
    }

    public ArrayList<RM_MetrialData> getMaterialgroup() {
        return materialgroup;
    }

    public void setMaterialgroup(ArrayList<RM_MetrialData> materialgroup) {
        this.materialgroup = materialgroup;
    }
}
