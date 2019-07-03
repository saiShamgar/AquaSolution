package com.PG.testingapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class LoginResponse {

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("data")
    @Expose
    private LoginData data;

    @SerializedName("Codes")
    @Expose
    private ArrayList<String> Codes;

    public LoginResponse(String status, String message, LoginData data, ArrayList<String> codes) {
        this.status = status;
        this.message = message;
        this.data = data;
        Codes = codes;
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

    public LoginData getData() {
        return data;
    }

    public ArrayList<String> getCodes() {
        return Codes;
    }

    public void setCodes(ArrayList<String> codes) {
        Codes = codes;
    }

    public void setData(LoginData data) {
        this.data = data;
    }
}
