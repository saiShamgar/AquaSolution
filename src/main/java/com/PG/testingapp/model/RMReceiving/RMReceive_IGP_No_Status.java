package com.PG.testingapp.model.RMReceiving;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class RMReceive_IGP_No_Status implements Serializable {

    @SerializedName("status")
    private String status;

    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private ArrayList<RMReceive_IGP_No> data;

    public RMReceive_IGP_No_Status(String status, String message, ArrayList<RMReceive_IGP_No> data) {
        this.status = status;
        this.message = message;
        this.data = data;
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

    public ArrayList<RMReceive_IGP_No> getData() {
        return data;
    }

    public void setData(ArrayList<RMReceive_IGP_No> data) {
        this.data = data;
    }
}
