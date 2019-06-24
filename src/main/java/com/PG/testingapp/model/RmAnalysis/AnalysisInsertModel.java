package com.PG.testingapp.model.RmAnalysis;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class AnalysisInsertModel {

    @SerializedName("AnalysisData")
    private RmAnalysisDetailsModel status;

    @SerializedName("AnalysisArray")
    private ArrayList<AnalysisModel> message;

    @SerializedName("AnalysisSampleQty")
    private String Supervisor;

    @SerializedName("AnalysisSupervisorId")
    private String AnalysisSupervisorName;

    @SerializedName("AnalysisNoOfPieces")
    private String AnalysisNoOfPieces;

    @SerializedName("AnalysisDate")
    private String AnalysisDate;

    @SerializedName("AnalysisRemarks")
    private String AnalysisRemarks;

    public String getAnalysisDate() {
        return AnalysisDate;
    }

    public void setAnalysisDate(String analysisDate) {
        AnalysisDate = analysisDate;
    }

    public String getAnalysisRemarks() {
        return AnalysisRemarks;
    }

    public void setAnalysisRemarks(String analysisRemarks) {
        AnalysisRemarks = analysisRemarks;
    }

    public AnalysisInsertModel(RmAnalysisDetailsModel status, ArrayList<AnalysisModel> message, String supervisor, String analysisSupervisorName, String analysisNoOfPieces, String analysisDate, String analysisRemarks) {
        this.status = status;
        this.message = message;
        Supervisor = supervisor;
        AnalysisSupervisorName = analysisSupervisorName;
        AnalysisNoOfPieces = analysisNoOfPieces;
        AnalysisDate = analysisDate;
        AnalysisRemarks = analysisRemarks;
    }

    public RmAnalysisDetailsModel getStatus() {
        return status;
    }

    public void setStatus(RmAnalysisDetailsModel status) {
        this.status = status;
    }

    public ArrayList<AnalysisModel> getMessage() {
        return message;
    }

    public void setMessage(ArrayList<AnalysisModel> message) {
        this.message = message;
    }

    public String getSupervisor() {
        return Supervisor;
    }

    public void setSupervisor(String supervisor) {
        Supervisor = supervisor;
    }

    public String getAnalysisSupervisorName() {
        return AnalysisSupervisorName;
    }

    public void setAnalysisSupervisorName(String analysisSupervisorName) {
        AnalysisSupervisorName = analysisSupervisorName;
    }

    public String getAnalysisNoOfPieces() {
        return AnalysisNoOfPieces;
    }

    public void setAnalysisNoOfPieces(String analysisNoOfPieces) {
        AnalysisNoOfPieces = analysisNoOfPieces;
    }
}
