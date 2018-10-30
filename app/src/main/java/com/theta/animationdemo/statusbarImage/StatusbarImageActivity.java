package com.theta.animationdemo.statusbarImage;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.theta.animationdemo.R;

public class StatusbarImageActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statusbar_image);


        // todo : This will make the status bar transparent but visible ex: play store app detail page
        getWindow().setStatusBarColor(Color.TRANSPARENT);

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        // todo : This will hide the status bar. It will no more visible if user scroll on the status bar it will visible
        //  Hiding Android Status Bar Using Theme Setting
         /*<!-- Base application theme. -->
         <style name="AppTheme" parent="@android:style/Theme.Holo.NoActionBar.Fullscreen">*/
        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

    }
}
