package com.PG.testingapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GetEnquiryFullDetails {
    @SerializedName("material")
    private ArrayList<MetrialData> materialData;

    @SerializedName("former")
    private ArrayList<FarmerDetails> farmerDetails;

    @SerializedName("netwieght")
    private ArrayList<NetWeightDetails> netWeightDetails;

    @SerializedName("boxrequired")
    private ArrayList<BoxesDetails> boxesDetails;

    public GetEnquiryFullDetails(ArrayList<MetrialData> materialData, ArrayList<FarmerDetails> farmerDetails, ArrayList<NetWeightDetails> netWeightDetails, ArrayList<BoxesDetails> boxesDetails) {
        this.materialData = materialData;
        this.farmerDetails = farmerDetails;
        this.netWeightDetails = netWeightDetails;
        this.boxesDetails = boxesDetails;
    }

    public ArrayList<MetrialData> getMaterialData() {
        return materialData;
    }

    public void setMaterialData(ArrayList<MetrialData> materialData) {
        this.materialData = materialData;
    }

    public ArrayList<FarmerDetails> getFarmerDetails() {
        return farmerDetails;
    }

    public void setFarmerDetails(ArrayList<FarmerDetails> farmerDetails) {
        this.farmerDetails = farmerDetails;
    }

    public ArrayList<NetWeightDetails> getNetWeightDetails() {
        return netWeightDetails;
    }

    public void setNetWeightDetails(ArrayList<NetWeightDetails> netWeightDetails) {
        this.netWeightDetails = netWeightDetails;
    }

    public ArrayList<BoxesDetails> getBoxesDetails() {
        return boxesDetails;
    }

    public void setBoxesDetails(ArrayList<BoxesDetails> boxesDetails) {
        this.boxesDetails = boxesDetails;
    }
}
