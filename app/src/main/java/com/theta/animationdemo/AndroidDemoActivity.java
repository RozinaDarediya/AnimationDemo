package com.theta.animationdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.theta.animationdemo.CoordinatorLayout.CoordinatorLayoutActivity;
import com.theta.animationdemo.OpenMap.OpenGoogleMapActivity;
import com.theta.animationdemo.deepLinking.DeepLinkingActivity;
import com.theta.animationdemo.pullToRefresh.PullToRefreshctivity;
import com.theta.animationdemo.retrofit.ApiCallActivity;
import com.theta.animationdemo.statusbarImage.HideStatusbarActivity;
import com.theta.animationdemo.statusbarImage.StatusbarImageActivity;
import com.theta.animationdemo.uploadFileOnDrive.UploadFileActivity;

public class AndroidDemoActivity extends AppCompatActivity implements View.OnClickListener {

    private Intent intent;
    private Button btnPullRefresh;
    private Button btnStatusbarImage;
    private Button btnCoordinatorLayout;
    private Button btnHideStatusbar;
    private Button btnRetrofit;
    private Button btnUploadFile;
    private Button btnOpenMap;
    private Button btnDeepLinking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_demo);

        init();
    }

    private void init() {
        btnPullRefresh = findViewById(R.id.btnPullRefresh);
        btnPullRefresh.setOnClickListener(this);
        btnStatusbarImage = findViewById(R.id.btnStatusbarImage);
        btnStatusbarImage.setOnClickListener(this);
        btnCoordinatorLayout = findViewById(R.id.btnCoordinatorLayout);
        btnCoordinatorLayout.setOnClickListener(this);
        btnHideStatusbar = findViewById(R.id.btnHideStatusbar);
        btnHideStatusbar.setOnClickListener(this);
        btnRetrofit = findViewById(R.id.btnRetrofit);
        btnRetrofit.setOnClickListener(this);
        btnUploadFile = findViewById(R.id.btnUploadFile);
        btnUploadFile.setOnClickListener(this);
        btnOpenMap = findViewById(R.id.btnOpenMap);
        btnOpenMap.setOnClickListener(this);
        btnDeepLinking = findViewById(R.id.btnDeepLinking);
        btnDeepLinking.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnPullRefresh:
                intent = new Intent(this, PullToRefreshctivity.class);
                startActivity(intent);
                break;
            case R.id.btnStatusbarImage:
                intent = new Intent(this, StatusbarImageActivity.class);
                startActivity(intent);
                break;
            case R.id.btnCoordinatorLayout:
                intent = new Intent(this, CoordinatorLayoutActivity.class);
                startActivity(intent);
                break;
            case R.id.btnHideStatusbar:
                intent = new Intent(this, HideStatusbarActivity.class);
                startActivity(intent);
                break;
            case R.id.btnRetrofit:
                intent = new Intent(this, ApiCallActivity.class);
                startActivity(intent);
                break;
            case R.id.btnUploadFile:
                intent = new Intent(this, UploadFileActivity.class);
                startActivity(intent);
                break;
            case R.id.btnOpenMap:
                intent = new Intent(this, OpenGoogleMapActivity.class);
                startActivity(intent);
                break;
            case R.id.btnDeepLinking:
                intent = new Intent(this, DeepLinkingActivity.class);
                startActivity(intent);
                break;

        }
    }

    @Override
    public void onBackPressed() {
        //this.overridePendingTransition(R.anim.hold, android.R.anim.fade_out);
        super.onBackPressed();
    }
}
