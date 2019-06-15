package com.PG.testingapp.model.RMReceiving;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RMReceivingLocationDetails implements Serializable {

    @SerializedName("Org_office_no")
    private String Org_office_no;

    @SerializedName("Org_office_Name")
    private String Org_office_Name;

    public RMReceivingLocationDetails(String org_office_no, String org_office_Name) {
        Org_office_no = org_office_no;
        Org_office_Name = org_office_Name;
    }

    public String getOrg_office_no() {
        return Org_office_no;
    }

    public void setOrg_office_no(String org_office_no) {
        Org_office_no = org_office_no;
    }

    public String getOrg_office_Name() {
        return Org_office_Name;
    }

    public void setOrg_office_Name(String org_office_Name) {
        Org_office_Name = org_office_Name;
    }
}
