package com.PG.testingapp.model.HeadLessGrading;

import com.google.gson.annotations.SerializedName;

public class AddingNewGrade {

    @SerializedName("fpVarietyCode")
    private String fpVarietyCode;

    @SerializedName("fpPackingGrade")
    private String fpPackingGrade;

    @SerializedName("fpGradeNo")
    private String fpGradeNo;

    public AddingNewGrade(String fpVarietyCode, String fpPackingGrade, String fpGradeNo) {
        this.fpVarietyCode = fpVarietyCode;
        this.fpPackingGrade = fpPackingGrade;
        this.fpGradeNo = fpGradeNo;
    }

    public String getFpVarietyCode() {
        return fpVarietyCode;
    }

    public void setFpVarietyCode(String fpVarietyCode) {
        this.fpVarietyCode = fpVarietyCode;
    }

    public String getFpPackingGrade() {
        return fpPackingGrade;
    }

    public void setFpPackingGrade(String fpPackingGrade) {
        this.fpPackingGrade = fpPackingGrade;
    }

    public String getFpGradeNo() {
        return fpGradeNo;
    }

    public void setFpGradeNo(String fpGradeNo) {
        this.fpGradeNo = fpGradeNo;
    }
}
