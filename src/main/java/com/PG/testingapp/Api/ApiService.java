package com.PG.testingapp.Api;

import com.PG.testingapp.model.FactoryWeighment.FactoryWeighmentCodes;
import com.PG.testingapp.model.FactoryWeighment.FactoryWeighmentGridModel;
import com.PG.testingapp.model.GetEnquiryRespone;
import com.PG.testingapp.model.GetScheduleNo;
import com.PG.testingapp.model.GettingProcesses;
import com.PG.testingapp.model.GettingScheduleDetails;
import com.PG.testingapp.model.GettingVeriatyCodes;
import com.PG.testingapp.model.HeadLessGrading.GetCodes;
import com.PG.testingapp.model.HeadLessGrading.GetGrades;
import com.PG.testingapp.model.HeadLessGrading.GetGroupCodes;
import com.PG.testingapp.model.HeadLessGrading.GetVarietyDetails;
import com.PG.testingapp.model.HeadLessGrading.LotNumbersStatus;
import com.PG.testingapp.model.LocationPlacement.BarcodeResponce;
import com.PG.testingapp.model.LoginResponse;
import com.PG.testingapp.model.RMReceiving.GetCountCodoesStatus;
import com.PG.testingapp.model.RMReceiving.RMReceive_IGP_No_Status;
import com.PG.testingapp.model.RMReceiving.RMRecivingLoationsStatus;
import com.PG.testingapp.model.RmAnalysis.RmAnaalysisDetailsStatus;
import com.PG.testingapp.model.SiteWTInsertResponce;
import com.PG.testingapp.model.Soaking.GetLotNo_Soaking_status;
import com.PG.testingapp.model.Status;
import com.PG.testingapp.model.ValueEdition.GetLotDetails_VD;
import com.PG.testingapp.model.headOnHeadLessGrading.GetLotNumbers_HOHL_status;
import com.PG.testingapp.model.headOnHeadLessGrading.Getcodes_Grouphead;
import com.PG.testingapp.model.headOnHeadLessGrading.HOHL_location;

import java.text.DecimalFormat;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    @FormUrlEncoded
    @POST("app_login/")
    Call<LoginResponse> agentLogin(
            @Field("User_Mail_Id")String username,
            @Field("User_Passward")String password);

    @FormUrlEncoded
    @POST("app_valueaddition_get_lotnum/")
    Call<GetLotDetails_VD> getProcesses(
            @Field("emp_id")String emp_id);


    @GET("app_valueaddition_get_emp_name/")
    Call<GettingVeriatyCodes> getGradeCodes();

    @FormUrlEncoded
    @POST("app_login/")
    Call<LoginResponse> uploadDetails(
            @Field("VAP_Weighment_Date_Time")String date,
            @Field("VAP_Group_Emp_id")String group_id,
            @Field("VAP_No_of_Nets")String no_nets,
            @Field("VAP_Net_Tare_Wt")String net_tare_wt,
            @Field("VAP_Total_Weight")String total_weight,
            @Field("VAP_Total_Tare_Weight")String total_tare_weight,
            @Field("VAP_Net_Weight")String net_wt,
            @Field("VAP_Supervisor_Emp_Id")String emp_id,
            @Field("Lot_No")String lt_no,
            @Field("Variety_Count_Code")String count_code,
            @Field("VAP_Net_Weight")String nt_wt,
            @Field("VAP_Process_No")String P_no,
            @Field("Lot_No")String lt_no2,
            @Field("c87")String c87);

    @FormUrlEncoded
    @POST("app_multi_procesregisters/")
    Call<Status> insertDetails(
                @Field("data")String json);

    @FormUrlEncoded
    @POST("app_valuaddition_save_registers/")
    Call<Status> inserValueEdt_details(
            @Field("data")String data);

    @FormUrlEncoded
    @POST("app_site_get/")
    Call<GetScheduleNo> getScheduleNo(
            @Field("emp_id") String emp_id );

    @FormUrlEncoded
    @POST("app_get_schedule_date/")
    Call<GettingScheduleDetails> getScheduleDetails(
            @Field("scheduledate")String json);

    @FormUrlEncoded
    @POST("app_get_details/")
    Call<GetEnquiryRespone> getEnQuiryDetails(
            @Field("sechduleno")String schNo,
            @Field("  ")String enquiryNo);

    @FormUrlEncoded
    @POST("app_site_map_registers/")
    Call<SiteWTInsertResponce> insertSiteData(
            @Field("sitedata")String schNo);

    //factory weighment

    @FormUrlEncoded
    @POST("app_lot_number_get/")
    Call<FactoryWeighmentGridModel> ftLotDetails(
            @Field("emp_id")String emp_id);

    @FormUrlEncoded
    @POST("app_get_variety/")
    Call<FactoryWeighmentCodes> ftgetCodes(
            @Field("lotno")String lot_no,
            @Field("emp_id")String emp_id);

    @FormUrlEncoded
    @POST("app_factory_weighment_registers/")
    Call<Status> factoryIsertionData(
            @Field("factorydata")String factorydata);

    //headLessGrading
    @FormUrlEncoded
    @POST("app_get_lot_num/")
    Call<LotNumbersStatus> HeadLeassLotData(
            @Field("Emp_id")String Emp_15);

    @FormUrlEncoded
    @POST("app_get_count_variety_code/")
    Call<GetCodes> HeadLeassCountData(
            @Field("VarietyCode")String VarietyCode);

    @FormUrlEncoded
    @POST("app_save_lot_details/")
    Call<Status> HeadLeassInsertData(
            @Field("data")String VarietyCode);

    @FormUrlEncoded
    @POST("app_get_variety_counts/")
    Call<GetCodes> getSiteCountCodes(
            @Field("varietycode")String VarietyCode);

    @GET("app_get_fp_group_codes/")
    Call<GetGroupCodes> hlGetFinishedGroups();

    @FormUrlEncoded
    @POST("app_get_fp_variety_details/")
    Call<GetVarietyDetails> hlGetVarityCodes(
            @Field("fpGroupCode")String VarietyCode);

    @FormUrlEncoded
    @POST("app_get_fp_production_grade/")
    Call<GetGrades> hlGetGradeCodes(
            @Field("fpVarietyCode")String fpVarietyCode);



    //Headon headless grading

    @FormUrlEncoded
    @POST("app_headon_headless_get_lot_No/")
    Call<GetLotNumbers_HOHL_status> getlotNum_hohl(
            @Field("Emp_id")String Emp);

    @FormUrlEncoded
    @POST("app_headon_headless_get_variety_count/")
    Call<Getcodes_Grouphead> getCodes_heads(
            @Field("VarietyCountCode")String VarietyCountCode,
            @Field("LotNo")String LotNo);

    @FormUrlEncoded
    @POST("app_headon_headless_get_group_loc/")
    Call<HOHL_location> getLocation(
            @Field("GroupEmpId")String GroupEmpId);

    @FormUrlEncoded
    @POST("app_headon_headless_save_headon_headless/")
    Call<Status> insertHOHLDetails(
            @Field("data")String GroupEmpId);

    //headon grading
    @GET("app_headon_get_lot_No/")
    Call<LotNumbersStatus> getlotNum_h_on();

    @FormUrlEncoded
    @POST("app_headon_save_details/")
    Call<Status> insert_h_on(
            @Field("data")String json);

    //location placement
    @FormUrlEncoded
    @POST("app_cs_scan_barcode/")
    Call<BarcodeResponce> scanbarcode(
            @Field("barcode")String barcode);

    @GET("app_cs_scanned_list/")
    Call<BarcodeResponce> getAllBarcodeResults();

    @FormUrlEncoded
    @POST("app_save_cs_put_away_details/")
    Call<BarcodeResponce> saveScanDetails(
            @Field("PP_Number")String PP_Number,
            @Field("CS_Pellet_Location_No")String CS_Pellet_Location_No,
            @Field("Operator_Emp_id")String Operator_Emp_id);

    //rm Receiving details
    @GET("app_raw_material_get_details/")
    Call<RMReceive_IGP_No_Status> getIGP_no();

    @GET("app_rmoffice_get_location/")
    Call<RMRecivingLoationsStatus> getRM_locations();

    @FormUrlEncoded
    @POST("app_rmvariety_get_details/")
    Call<GetVarietyDetails> getRMVarietyCodes(
            @Field("Material_Group_Code")String Material_Group_Code);

    @FormUrlEncoded
    @POST("app_rmvarietycount_get_details/")
    Call<GetCountCodoesStatus> getRMcountCodes(
            @Field("Product_Variety_Code")String Product_Variety_Code);

    @FormUrlEncoded
    @POST("app_rawmaterial_save_registers/")
    Call<Status> saveRmReeceivingDetails(
            @Field("data")String data);

    //rm analysis
    @FormUrlEncoded
    @POST("app_rm_analysis_get_data/")
    Call<RmAnaalysisDetailsStatus> get_rm_analysis_details(
            @Field("Emp_Id")String Emp_Id);

    //soaking

    @FormUrlEncoded
    @POST("app_soacking_get_details/")
    Call<GetLotNo_Soaking_status> getSoakingLotNo(
            @Field("empid")String Emp_Id);

    @FormUrlEncoded
    @POST("app_soacking_save_registers/")
    Call<Status> insert_soaking_details(
            @Field("data")String json);

}
