package com.PG.testingapp.model.RmAnalysis;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AnalysisModel implements Serializable {

    @SerializedName("Description")
    private String Description;

    @SerializedName("No_of_pieces")
    private String no_of_pieces;

    @SerializedName("cuttingQty_sampleQty")
    private String cuttingQty_sampleQty;

    @SerializedName("per_on_sample_qty")
    private String per_on_sample_qty;

    @SerializedName("cutting_in_recd_qty")
    private String cutting_in_recd_qty;

    @SerializedName("final_cutting")
    private String final_cutting;

    public AnalysisModel(String description, String no_of_pieces, String cuttingQty_sampleQty, String per_on_sample_qty, String cutting_in_recd_qty, String final_cutting) {
        Description = description;
        this.no_of_pieces = no_of_pieces;
        this.cuttingQty_sampleQty = cuttingQty_sampleQty;
        this.per_on_sample_qty = per_on_sample_qty;
        this.cutting_in_recd_qty = cutting_in_recd_qty;
        this.final_cutting = final_cutting;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getNo_of_pieces() {
        return no_of_pieces;
    }

    public void setNo_of_pieces(String no_of_pieces) {
        this.no_of_pieces = no_of_pieces;
    }

    public String getCuttingQty_sampleQty() {
        return cuttingQty_sampleQty;
    }

    public void setCuttingQty_sampleQty(String cuttingQty_sampleQty) {
        this.cuttingQty_sampleQty = cuttingQty_sampleQty;
    }

    public String getPer_on_sample_qty() {
        return per_on_sample_qty;
    }

    public void setPer_on_sample_qty(String per_on_sample_qty) {
        this.per_on_sample_qty = per_on_sample_qty;
    }

    public String getCutting_in_recd_qty() {
        return cutting_in_recd_qty;
    }

    public void setCutting_in_recd_qty(String cutting_in_recd_qty) {
        this.cutting_in_recd_qty = cutting_in_recd_qty;
    }

    public String getFinal_cutting() {
        return final_cutting;
    }

    public void setFinal_cutting(String final_cutting) {
        this.final_cutting = final_cutting;
    }
}
