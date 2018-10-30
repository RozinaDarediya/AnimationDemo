package com.theta.animationdemo.RevealEffect;

import android.animation.Animator;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.theta.animationdemo.R;

public class RevealEffectActivity extends AppCompatActivity implements View.OnClickListener {

    FloatingActionButton fab;
    RelativeLayout layout;
    TextView txtRevealEffextLable;
    TextView txtRevealEffextDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reveal_effect);

        init();
        fab.setOnClickListener(this);
    }

    private void init() {
        fab = (FloatingActionButton) findViewById(R.id.fab);
        layout = (RelativeLayout) findViewById(R.id.layout);
        txtRevealEffextLable = (TextView)findViewById(R.id.txtRevealEffextLable);
        txtRevealEffextDetails = (TextView) findViewById(R.id.txtRevealEffextDetails);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.fab) {

           // createCircularReveal(layout);
            show(txtRevealEffextDetails);
            show(txtRevealEffextLable);
           // hideCircularReveal(layout);
           // hideCircularReveal(txtRevealEffextLable);
           // hideCircularReveal(txtRevealEffextDetails);
        }
    }


    // To reveal a previously invisible view using this effect:
    // This function does not hide the view and only shows the animation
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void show(final View view) {
        // get the center for the clipping circle
        int cx = (view.getWidth()) / 2;
        int cy = (view.getHeight()) / 2;

        // get the final radius for the clipping circle
        int finalRadius = Math.max(view.getWidth(), view.getHeight());

        // create the animator for this view (the start radius is zero)
        Animator anim = ViewAnimationUtils.createCircularReveal(view, cx, cy,
                0, finalRadius);
        anim.setDuration(1000);

        // make the view visible and start the animation
        view.setVisibility(View.VISIBLE);
        anim.start();
    }
}
