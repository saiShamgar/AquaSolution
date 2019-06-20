package com.PG.testingapp.model.Soaking;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class GetLotNo_Soaking_status implements Serializable {

    @SerializedName("status")
    private String status;

    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private ArrayList<Soaking_details> data;

    @SerializedName("empname")
    private String empname;

    public GetLotNo_Soaking_status(String status, String message, ArrayList<Soaking_details> data, String empname) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.empname = empname;
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

    public ArrayList<Soaking_details> getData() {
        return data;
    }

    public void setData(ArrayList<Soaking_details> data) {
        this.data = data;
    }

    public String getEmpname() {
        return empname;
    }

    public void setEmpname(String empname) {
        this.empname = empname;
    }
}
