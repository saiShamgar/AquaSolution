package com.PG.testingapp.LocalDataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "SQLiteList.db";
    private static final int DATABASE_VERSION = 1;
    public static final String PERSON_TABLE_NAME = "ValueEdition";
    public static final String Lot_No = "Lot_No";
    public static final String Org_office_no = "Org_office_no";
    public static final String Product_Process_Code = "Product_Process_Code";
    public static final String Production_Process_Schedule_No = "Production_Process_Schedule_No";
    public static final String VAP_Group_Emp_id = "VAP_Group_Emp_id";
    public static final String VAP_Group_Total_Qty = "VAP_Group_Total_Qty";
    public static final String VAP_Net_Tare_Wt = "VAP_Net_Tare_Wt";
    public static final String VAP_Net_Weight = "VAP_Net_Weight";
    public static final String VAP_No_of_Nets = "VAP_No_of_Nets";
    public static final String VAP_Process_No = "VAP_Process_No";
    public static final String VAP_Supervisor_Emp_Id = "VAP_Supervisor_Emp_Id";
    public static final String VAP_Tables_No = "VAP_Tables_No";
    public static final String VAP_Total_Tare_Weight = "VAP_Total_Tare_Weight";
    public static final String VAP_Total_Weight = "VAP_Total_Weight";
    public static final String VAP_Weighment_Date_Time = "VAP_Weighment_Date_Time";
    public static final String VAP_Weight_No = "VAP_Weight_No";
    public static final String VAP_Workers_Nos = "VAP_Workers_Nos";
    public static final String Variety_Count_Code = "Variety_Count_Code";
    public static final String c87 = "c87";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME , null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + PERSON_TABLE_NAME + "(" +
                Lot_No + " TEXT, " +
                Org_office_no + " TEXT, " +
                Product_Process_Code + " TEXT, " +
                Production_Process_Schedule_No + " TEXT, " +
                VAP_Group_Emp_id + " TEXT, " +
                VAP_Group_Total_Qty + " TEXT, " +
                VAP_Net_Tare_Wt + " TEXT, " +
                VAP_Net_Weight + " TEXT, " +
                VAP_No_of_Nets + " TEXT, " +
                VAP_Process_No + " TEXT, " +
                VAP_Supervisor_Emp_Id + " TEXT, " +
                VAP_Tables_No + " TEXT, " +
                VAP_Total_Tare_Weight + " TEXT, " +
                VAP_Total_Weight + " TEXT, " +
                VAP_Weighment_Date_Time + " TEXT, " +
                VAP_Weight_No + " TEXT, " +
                VAP_Workers_Nos + " TEXT, " +
                Variety_Count_Code + " TEXT, " +
                c87 + " TEXT )"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + PERSON_TABLE_NAME);
        onCreate(db);
    }

    public boolean insertDetails(String lot, String office, String process_code,String process_schedule, String grp_emp_id, String grp_tot_quntity,String net_tare_weight, String net_weight, String no_of_nets,
                            String process_no, String superior_emp_id, String table_no,String total_tare_weight, String total_weight, String date_time,String weight_no, String worker_nos, String veriety_count_code,String c7) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Lot_No, lot);
        contentValues.put(Org_office_no, office);
        contentValues.put(Product_Process_Code, process_code);
        contentValues.put(Production_Process_Schedule_No, process_schedule);
        contentValues.put(VAP_Group_Emp_id, grp_emp_id);
        contentValues.put(VAP_Group_Total_Qty, grp_tot_quntity);
        contentValues.put(VAP_Net_Tare_Wt, net_tare_weight);
        contentValues.put(VAP_Net_Weight, net_weight);
        contentValues.put(VAP_No_of_Nets, no_of_nets);
        contentValues.put(VAP_Process_No, process_no);
        contentValues.put(VAP_Supervisor_Emp_Id, superior_emp_id);
        contentValues.put(VAP_Tables_No, table_no);
        contentValues.put(VAP_Total_Tare_Weight, total_tare_weight);
        contentValues.put(VAP_Total_Weight, total_weight);
        contentValues.put(VAP_Weighment_Date_Time, date_time);
        contentValues.put(VAP_Weight_No, weight_no);
        contentValues.put(VAP_Workers_Nos, worker_nos);
        contentValues.put(Variety_Count_Code, veriety_count_code);
        contentValues.put(c87, c7);
        db.insert(PERSON_TABLE_NAME, null, contentValues);
        return true;
    }

    public boolean deleteTable() {
        SQLiteDatabase db = this.getWritableDatabase();
         db.execSQL("delete from "+ PERSON_TABLE_NAME);
         return true;
    }
    public Cursor getAllDetails() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery( "SELECT * FROM " + PERSON_TABLE_NAME, null );
        return res;
    }
}
