package com.PG.testingapp.UI.LocationPlacement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.PG.testingapp.R;

public class LocationPlacement extends AppCompatActivity {

    //declaring variables
    private ImageView loc_place_scanner_line;
    private RecyclerView scanning_recyclerView;
    private EditText scanResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_placement);

        loc_place_scanner_line=findViewById(R.id.loc_place_scanner_line);
        scanning_recyclerView=findViewById(R.id.scanning_recyclerView);
        scanResult=findViewById(R.id.scanResult);
        scanResult.requestFocus();





//        Animation slideUp = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
//        Animation slidedown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);
//
//        loc_place_scanner_line.startAnimation(slideUp);
//        loc_place_scanner_line.startAnimation(slidedown);



    }
}
