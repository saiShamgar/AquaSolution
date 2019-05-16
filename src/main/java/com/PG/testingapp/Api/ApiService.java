package com.PG.testingapp.Api;

import com.PG.testingapp.model.GetScheduleNo;
import com.PG.testingapp.model.GettingProcesses;
import com.PG.testingapp.model.GettingScheduleDetails;
import com.PG.testingapp.model.GettingVeriatyCodes;
import com.PG.testingapp.model.LoginResponse;
import com.PG.testingapp.model.Status;

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

    @GET("app_process_get/")
    Call<GettingProcesses> getProcesses();

    @GET("app_product_get/")
    Call<GettingVeriatyCodes> getCountCodes();

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

    @GET("app_site_get/")
    Call<GetScheduleNo> getScheduleNo();

    @FormUrlEncoded
    @POST("app_get_schedule_date/")
    Call<GettingScheduleDetails> getScheduleDetails(
            @Field("scheduledate")String json);

}
