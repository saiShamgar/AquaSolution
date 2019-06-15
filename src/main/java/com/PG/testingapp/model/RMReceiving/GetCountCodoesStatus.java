package com.PG.testingapp.model.RMReceiving;

import com.PG.testingapp.model.FactoryWeighment.ActualCodes;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class GetCountCodoesStatus implements Serializable {

    @SerializedName("status")
    private String status;

    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private ArrayList<GetCountCodes> Codes;

    public GetCountCodoesStatus(String status, String message, ArrayList<GetCountCodes> codes) {
        this.status = status;
        this.message = message;
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

    public ArrayList<GetCountCodes> getCodes() {
        return Codes;
    }

    public void setCodes(ArrayList<GetCountCodes> codes) {
        Codes = codes;
    }
}
