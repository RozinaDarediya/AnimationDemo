package com.theta.animationdemo;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.theta.animationdemo.ParallaxViewPagerDemo.Log;

public class Main3Activity extends AppCompatActivity {

    private ImageView img2;
    private FloatingActionButton fab;
    private int i = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("Activity in onCreate state");
        setContentView(R.layout.activity_main3);

        Log.e("i value in oncreate : " + i);

//        Settings.Secure.putInt(this.getContentResolver(), Settings.Secure.ADB_ENABLED, 1);
        //ActivityManagerNative.getDefault().setAlwaysFinish(true);

        img2 = findViewById(R.id.img2);
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent intent = new Intent(Main3Activity.this, ReturnActivity.class);
                startActivity(intent);*/
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options =
                            ActivityOptions.makeSceneTransitionAnimation(Main3Activity.this, fab, fab.getTransitionName());
                    startActivity(new Intent(Main3Activity.this, ReturnActivity.class), options.toBundle());
                } else {
                    startActivity(new Intent(Main3Activity.this, ReturnActivity.class));
                }
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("Activity in onActivityResult state");
        if (requestCode == 2) {
            String message = data.getStringExtra("MESSAGE");
            i = i + 5;
            Log.e("Activity in onActivityResult state result : " + message);
            Log.e("i value in onActivityResult : " + i);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("Activity in onResume state");
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("Activity in onPause state");
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Log.e("Activity in onPostResume state");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("Activity in onStart state");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("Activity in onStop state");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("Activity in onDestroy state");
    }
}
