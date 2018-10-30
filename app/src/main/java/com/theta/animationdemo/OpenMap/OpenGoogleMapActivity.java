package com.theta.animationdemo.OpenMap;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.theta.animationdemo.R;

public class OpenGoogleMapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_google_map);

        //Uri gmmIntentUri = Uri.parse("google.navigation:q=Taronga+Zoo,+Sydney+Australia&mode=b");
        //Uri gmmIntentUri = Uri.parse("geo:23.018289,72.529691?q=google.navigation:Manek+Chowk+Food+Market,+Danapidth,+Khadia,+Ahmedabad,+Gujarat");
        Uri gmmIntentUri = Uri.parse("https://www.google.com/maps/dir/?api=1&origin=Paris,France&destination=Cherbourg,France&travelmode=driving&waypoints=Versailles,France%7CChartres,France%7CLe+Mans,France%7CCaen,France");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        if (mapIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mapIntent);
        }


    }
}
