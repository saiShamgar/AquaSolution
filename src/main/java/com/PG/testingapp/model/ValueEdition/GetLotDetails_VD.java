package com.PG.testingapp.model.ValueEdition;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class GetLotDetails_VD implements Serializable {

    @SerializedName("status")
    private String status;

    @SerializedName("message")
    private String message;

    @SerializedName("Lotno")
    private ArrayList<LotNoDetails_VD> Lotno;

    @SerializedName("location")
    private Location_VD location;

    public GetLotDetails_VD(String status, String message, ArrayList<LotNoDetails_VD> lotno, Location_VD location) {
        this.status = status;
        this.message = message;
        Lotno = lotno;
        this.location = location;
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

    public ArrayList<LotNoDetails_VD> getLotno() {
        return Lotno;
    }

    public void setLotno(ArrayList<LotNoDetails_VD> lotno) {
        Lotno = lotno;
    }

    public Location_VD getLocation() {
        return location;
    }

    public void setLocation(Location_VD location) {
        this.location = location;
    }
}
