package com.theta.animationdemo.ExpandableView;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.theta.animationdemo.R;

public class CurrentPosition extends AppCompatActivity {

    private ImageView ivPro;
    private TextView txtLable;
    private TextView txtDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_position);

        init();
        Intent intent = getIntent();
        String detailData = intent.getStringExtra("detailData");

        txtDetail.setText(detailData);
    }

    private void init() {
        ivPro = findViewById(R.id.ivPro);
        txtLable = findViewById(R.id.txtLable);
        txtDetail = findViewById(R.id.txtDetail);
    }
}
