package com.PG.testingapp.model.Updations;

import com.google.gson.annotations.SerializedName;

public class NewCountInsertJson {

    @SerializedName("Variety_Code")
    private String Variety_Code;

    @SerializedName("Variety_Count")
    private String Variety_Count;

    @SerializedName("fk_Material_Group")
    private String fk_Material_Group;

    public NewCountInsertJson(String variety_Code, String variety_Count, String fk_Material_Group) {
        Variety_Code = variety_Code;
        Variety_Count = variety_Count;
        this.fk_Material_Group = fk_Material_Group;
    }

    public String getVariety_Code() {
        return Variety_Code;
    }

    public void setVariety_Code(String variety_Code) {
        Variety_Code = variety_Code;
    }

    public String getVariety_Count() {
        return Variety_Count;
    }

    public void setVariety_Count(String variety_Count) {
        Variety_Count = variety_Count;
    }

    public String getFk_Material_Group() {
        return fk_Material_Group;
    }

    public void setFk_Material_Group(String fk_Material_Group) {
        this.fk_Material_Group = fk_Material_Group;
    }
}
