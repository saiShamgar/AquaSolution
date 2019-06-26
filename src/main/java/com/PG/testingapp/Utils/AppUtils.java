package com.PG.testingapp.Utils;

import android.app.Dialog;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.PG.testingapp.Api.ApiService;
import com.PG.testingapp.Api.AppUrl;
import com.PG.testingapp.BaseActivity;
import com.PG.testingapp.R;
import com.PG.testingapp.model.HeadLessGrading.GetGroupCodes;
import com.PG.testingapp.model.HeadLessGrading.GroupCodes;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Akash on 3/15/2018.
 */

public class AppUtils  {



    public static OnSelectedMyItem selectedMyItem = null;
    /**
     * Method to show short Toast message
     * @param context
     * @param message
     */
    public static void showToast(Context context, String message) {
        if (context != null) {
            Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }

    }

    /**
     * Method to dismiss custom progress dialog.
     */
    public static void dismissCustomProgress(CustomProgressDialog mProgressDialog) {
        if (mProgressDialog != null && mProgressDialog.getContext() != null) {
            mProgressDialog.dissMissDialog();
        }
    }

    public static Dialog showCustomOkDialog(Context context, String title, String message,
                                            String positiveBtnTxt,
                                            final View.OnClickListener positivecallback) {
        Log.i("String",message);
        Log.i("String1",title);

        final Dialog dialog_ok_dialog;
        dialog_ok_dialog = new Dialog(context,R.style.custompopup_style);

        dialog_ok_dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        dialog_ok_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        dialog_ok_dialog.setContentView(R.layout.custom_ok_layout);
        dialog_ok_dialog.setCancelable(false);

        Window window = dialog_ok_dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.CENTER;
        window.setAttributes(wlp);
        dialog_ok_dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

        TextView txtTitle = (TextView) dialog_ok_dialog.findViewById(R.id.tv_custompopup_title);
        TextView txtMessage = (TextView) dialog_ok_dialog.findViewById(R.id.tv_custompopup_msg);
        txtMessage.setMovementMethod(new ScrollingMovementMethod());
        TextView txtPositive = (TextView) dialog_ok_dialog.findViewById(R.id.txt_custompopup_postive);

        txtTitle.setText(title);
        txtMessage.setText(message);
        txtPositive.setText(positiveBtnTxt);


        txtPositive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_ok_dialog.dismiss();
                if (positivecallback != null) {
                    positivecallback.onClick(v);
                }
            }
        });

        dialog_ok_dialog.show();

        return null;
    }

    /**
     * Method to show the custom progress dialog.
     *
     * @param mProgressDialog
     * @param mMessage
     */
    public static void showCustomProgressDialog(CustomProgressDialog mProgressDialog, String mMessage) {
        try {
            mProgressDialog.show(mMessage, true, false, null);
            mProgressDialog.setCancelable(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Dialog showCustomOkCancelDialog_for_new_grade(Context context,
                                                  String positiveBtnTxt,
                                                  String negativeBtnTxt,
                                                  final View.OnClickListener positivecallback,
                                                  final View.OnClickListener negativecallback) {

        final Dialog dialog_ok_cancel_dialog;
        dialog_ok_cancel_dialog = new Dialog(context, R.style.custompopup_style);
        dialog_ok_cancel_dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        dialog_ok_cancel_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        dialog_ok_cancel_dialog.setContentView(R.layout.cus_layout_add_new_grade);
        dialog_ok_cancel_dialog.setCancelable(false);

        Window window = dialog_ok_cancel_dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.CENTER;
        window.setAttributes(wlp);
        dialog_ok_cancel_dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);


        



        dialog_ok_cancel_dialog.show();

        return null;
    }

    private static void setSpinnerForAddnewGrade(Context context, Spinner spinner_select_group) {
        final List<String> list = new ArrayList<>();
        final ArrayList<GroupCodes>[] codes = new ArrayList[]{new ArrayList<>()};
        final String[] group = new String[1];
        final String[] group_code = new String[1];
        final Integer[] position = new Integer[1];
        list.clear();
        list.add("Select Group");

        if (AppUtils.isNetworkAvailable(context)){
          ApiService  apiService= AppUrl.getApiClient().create(ApiService.class);
            Call<GetGroupCodes> call=apiService.hlGetFinishedGroups();
            call.enqueue(new Callback<GetGroupCodes>() {
                @Override
                public void onResponse(Call<GetGroupCodes> call, Response<GetGroupCodes> response) {
                    if (response.body()!=null){
                        if (response.body().getStatus().contains(AppConstant.MESSAGE)){
                           // AppUtils.showToast(context,response.body().getMessage());

                            for (int i=0;i<response.body().getCodes().size();i++){
                                list.add(response.body().getCodes().get(i).getFP_Group_Name());
                            }
                            codes[0] =response.body().getCodes();
                            ArrayAdapter<String> countAdapter = new ArrayAdapter<String>(context, R.layout.show_count, list);
                            spinner_select_group.setVisibility(View.VISIBLE);
                            spinner_select_group.setAdapter(countAdapter);
                        }
                        else {
                            Log.e("status",response.body().getMessage());
                            AppUtils.showCustomOkDialog(context,"",context.getResources().getString(R.string.error_default),"OK",null);
                        }
                    }
                    else {
                        AppUtils.showCustomOkDialog(context,"",context.getResources().getString(R.string.error_default),"OK",null);
                    }
                }

                @Override
                public void onFailure(Call<GetGroupCodes> call, Throwable t) {
                    Log.e("status",t.toString());
                    AppUtils.showCustomOkDialog(context,
                            "",
                           t.getMessage(),
                            "OK", null);
                }
            });
        }else {
            AppUtils.showToast(context,context.getString(R.string.error_network));
        }

        spinner_select_group.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                group[0] = parent.getSelectedItem().toString();
                position[0] = i;

                if (group[0] != "Select Group") {
                    if (AppUtils.isNetworkAvailable(context)) {
                        group_code[0] = codes[0].get(i-1).getFP_Group_Code();
                    //    callServiceForVariety(group_code);
                    } else {
                        AppUtils.showToast(context, context.getString(R.string.error_network));
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    public static Dialog showCustomOkCancelDialog(Context context, String title, String message,
                                                  String positiveBtnTxt,
                                                  String negativeBtnTxt,
                                                  final View.OnClickListener positivecallback,
                                                  final View.OnClickListener negativecallback) {

        final Dialog dialog_ok_cancel_dialog;
        dialog_ok_cancel_dialog = new Dialog(context, R.style.custompopup_style);
        dialog_ok_cancel_dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        dialog_ok_cancel_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        dialog_ok_cancel_dialog.setContentView(R.layout.custom_ok_cancel_layout);
        dialog_ok_cancel_dialog.setCancelable(false);

        Window window = dialog_ok_cancel_dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.CENTER;
        window.setAttributes(wlp);
        dialog_ok_cancel_dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

        TextView txtTitle = (TextView) dialog_ok_cancel_dialog.findViewById(R.id.tv_custompopup_title);
        TextView txtMessage = (TextView) dialog_ok_cancel_dialog.findViewById(R.id.tv_custompopup_msg);
        txtMessage.setMovementMethod(new ScrollingMovementMethod());
        TextView txtPositive = (TextView) dialog_ok_cancel_dialog.findViewById(R.id.txt_custompopup_postive);
        TextView txtNegativePositive = (TextView) dialog_ok_cancel_dialog.findViewById(R.id.txt_custompopup_negative);

        txtTitle.setText(title);
        txtMessage.setText(message);

        txtPositive.setText(positiveBtnTxt);
        txtNegativePositive.setText(negativeBtnTxt);


        txtPositive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_ok_cancel_dialog.dismiss();
                if (positivecallback != null) {
                    positivecallback.onClick(v);
                }
            }
        });

        txtNegativePositive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_ok_cancel_dialog.dismiss();
                if (negativecallback != null) {
                    negativecallback.onClick(v);
                }
            }
        });

        dialog_ok_cancel_dialog.show();

        return null;
    }

    public static String roundValue(String s){

        String s1="";
        BigDecimal bd1 = new BigDecimal(s).setScale(2, RoundingMode.HALF_UP);
        s1= String.valueOf(bd1.doubleValue());

        return s1;

    }


    public static String dateFormat(String time){
        String mydate="";

        SimpleDateFormat comigDate = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat displayDate = new SimpleDateFormat("dd-MM-yyyy");
        try {
             mydate = displayDate.format(comigDate.parse(time));

        } catch (ParseException e) {
            e.printStackTrace();
        }
        Log.d("ScheduleDate","server "+time+" android "+mydate);
        return mydate;
    }

    /**
     *  Method for display time in format
     * @param
     * @return
     */
    public static String covertGSTTonormalDateFormat(String timeStamp){

        SimpleDateFormat input = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
        SimpleDateFormat output = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        Date d = null;
        try
        {
            d = input.parse(timeStamp);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        String formatted = output.format(d);
        Log.i("DATE", "" + formatted);
        return formatted;
    }

    public static String HourFormat(String timeStamp){

        SimpleDateFormat input = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        SimpleDateFormat output = new SimpleDateFormat("HH:mm:ss aa");

        Date d = null;
        try
        {
            d = input.parse(timeStamp);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        String formatted = output.format(d);
        Log.i("DATE", "" + formatted);
        return formatted;
    }

    public static String convertServerFormatDateTime(Calendar calendar){
        SimpleDateFormat mdformat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
        String selectedDate =  mdformat.format(calendar.getTime());
//        String selectedDate =  mdformat.format(SaveDataSingletone.getCalendar().getTime());
//        String date =
        Log.d("Selected_date","convertServerFormatDateTime "+selectedDate);

        return selectedDate;
    }



    public static void hideKeyboard(Context context, EditText edittext) {
        if (edittext != null) {
            InputMethodManager imm =
                    (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(edittext.getWindowToken(), 0);
        }

    }

    public static void showkeyboard(Context context, EditText edittext) {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(edittext, InputMethodManager.SHOW_IMPLICIT);


    }


    public static String getAppVersion(Context context) {
        PackageInfo pInfo = null;
        String version = null;
        try {
            pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            version = pInfo.versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return version;
    }

    public static String getCurrentDate()
    {
        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat mdformat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
        String selectedDate =  mdformat.format(calendar.getTime());
//        String selectedDate =  mdformat.format(SaveDataSingletone.getCalendar().getTime());
//        String date =
        Log.d("Selected_date","convertServerFormatDateTime "+selectedDate);

        return selectedDate;
    }

    public interface OnSelectedMyItem{
        void onSelectionItem(View view);
    }

    /**
     * Method to check if a device is connected to internet or not
     *
     * @param context
     * @return true - if the device connected to internet; false - otherwise
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager
                .getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


}
