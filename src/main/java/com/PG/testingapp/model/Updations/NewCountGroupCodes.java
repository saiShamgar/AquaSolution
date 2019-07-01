package com.PG.testingapp.model.Updations;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class NewCountGroupCodes implements Serializable {

    @SerializedName("status")
    private String status;

    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private ArrayList<NewCountGroupActualCodes> data;

    public NewCountGroupCodes(String status, String message, ArrayList<NewCountGroupActualCodes> data) {
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

    public ArrayList<NewCountGroupActualCodes> getData() {
        return data;
    }

    public void setData(ArrayList<NewCountGroupActualCodes> data) {
        this.data = data;
    }
}
