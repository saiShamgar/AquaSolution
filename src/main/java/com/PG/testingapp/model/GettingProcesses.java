package com.PG.testingapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GettingProcesses {

    @SerializedName("status")
    private String status;

    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private ArrayList<Processes_data> data;

    @SerializedName("getdata")
    private ArrayList<Process_Location> getLocations;

    public GettingProcesses(String status, String message, ArrayList<Processes_data> data, ArrayList<Process_Location> getLocations) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.getLocations = getLocations;
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

    public ArrayList<Processes_data> getData() {
        return data;
    }

    public void setData(ArrayList<Processes_data> data) {
        this.data = data;
    }

    public ArrayList<Process_Location> getGetLocations() {
        return getLocations;
    }

    public void setGetLocations(ArrayList<Process_Location> getLocations) {
        this.getLocations = getLocations;
    }
}
