package com.PG.testingapp.model.RMReceiving;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RM_MetrialData implements Serializable {

    @SerializedName("Material_Group_Code")
    private String Material_Group_Code;

    @SerializedName("Material_Group_Name")
    private String Material_Group_Name;

    public RM_MetrialData(String material_Group_Code, String material_Group_Name) {
        Material_Group_Code = material_Group_Code;
        Material_Group_Name = material_Group_Name;
    }

    public String getMaterial_Group_Code() {
        return Material_Group_Code;
    }

    public void setMaterial_Group_Code(String material_Group_Code) {
        Material_Group_Code = material_Group_Code;
    }

    public String getMaterial_Group_Name() {
        return Material_Group_Name;
    }

    public void setMaterial_Group_Name(String material_Group_Name) {
        Material_Group_Name = material_Group_Name;
    }
}
