package com.theta.animationdemo.fabButtonRotation;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.transition.ChangeBounds;
import android.transition.ChangeImageTransform;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.theta.animationdemo.R;

public class FabButtonAnimationActivity extends AppCompatActivity implements View.OnClickListener {

    private FloatingActionButton btnFab;
    private FloatingActionButton btnFab1;
    private FloatingActionButton btnFab2;
    private ImageView image1;
    private ViewGroup transitionsContainer;
    private Animation fab_open, fab_close, rotate_forward, rotate_backward;
    private Boolean isFabOpen = false;
    boolean expanded = false;
    private Animation anim;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fab_button_animation);

        init();
        TransitionManager.beginDelayedTransition(transitionsContainer, new TransitionSet()
                .addTransition(new ChangeBounds())
                .addTransition(new ChangeImageTransform()));

        ViewGroup.LayoutParams params = image1.getLayoutParams();

        params.height = expanded ? ViewGroup.LayoutParams.MATCH_PARENT :
                ViewGroup.LayoutParams.WRAP_CONTENT;
        image1.setLayoutParams(params);

        image1.setScaleType(expanded ? ImageView.ScaleType.CENTER_CROP :
                ImageView.ScaleType.FIT_CENTER);
    }

    private void init() {
        transitionsContainer = (ViewGroup) findViewById(R.id.transitions_container);
        btnFab = transitionsContainer.findViewById(R.id.btnFab);
        btnFab1 = transitionsContainer.findViewById(R.id.btnFab1);
        btnFab2 = transitionsContainer.findViewById(R.id.btnFab2);
        image1 = transitionsContainer.findViewById(R.id.image1);

        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
        rotate_forward = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_forward);
        rotate_backward = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_backward);
        btnFab.setOnClickListener(this);
        btnFab1.setOnClickListener(this);

        anim = AnimationUtils.loadAnimation(this, R.anim.scale);


    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btnFab:
                animateFAB();
                break;
            case R.id.btnFab1:
                image1.startAnimation(anim);
                break;
        }
    }

    private void animateFAB() {
        if (isFabOpen) {

            btnFab.startAnimation(rotate_backward);
            btnFab1.startAnimation(fab_close);
            btnFab2.startAnimation(fab_close);
            btnFab1.setClickable(false);
            btnFab2.setClickable(false);
            isFabOpen = false;
            Log.d("Raj", "close");

        } else {

            btnFab.startAnimation(rotate_forward);
            btnFab1.startAnimation(fab_open);
            btnFab2.startAnimation(fab_open);
            btnFab1.setClickable(true);
            btnFab2.setClickable(true);
            isFabOpen = true;
            Log.d("Raj", "open");

        }
    }
}
