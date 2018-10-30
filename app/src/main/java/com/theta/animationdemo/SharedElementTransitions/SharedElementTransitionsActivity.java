package com.theta.animationdemo.SharedElementTransitions;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.theta.animationdemo.R;
import com.theta.animationdemo.global.Global;

public class SharedElementTransitionsActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnClick;
    private View reveal_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_element_transitions);

        init();


        reveal_view.post(new Runnable() {
            @Override
            public void run() {
                Global.showAnimation(reveal_view);
            }
        });
        btnClick.setOnClickListener(this);
    }

    private void init() {
        btnClick = (Button) findViewById(R.id.btnClick);
        reveal_view = findViewById(R.id.reveal_view);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnClick) {
            ImageView ivImage = (ImageView) findViewById(R.id.ivImage);
            Intent intent = new Intent(SharedElementTransitionsActivity.this, SharedElementTransitionsActivity2.class);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                startActivity(intent,
                        ActivityOptions.makeSceneTransitionAnimation(
                                this, new Pair<View, String>(ivImage, ViewCompat.getTransitionName(ivImage))).toBundle());
            } else {
                startActivity(intent);
            }
        }
    }


}
