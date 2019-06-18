package com.PG.testingapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GettingVeriatyCodes {

    @SerializedName("status")
    private String status;

    @SerializedName("message")
    private String message;

    @SerializedName("Location")
    private List<Codes> codes;

    public GettingVeriatyCodes(String status, String message, List<Codes> codes) {
        this.status = status;
        this.message = message;
        this.codes = codes;
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

    public List<Codes> getCodes() {
        return codes;
    }

    public void setCodes(List<Codes> codes) {
        this.codes = codes;
    }
}
