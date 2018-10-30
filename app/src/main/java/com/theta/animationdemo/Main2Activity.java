package com.theta.animationdemo;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;

import com.theta.animationdemo.CircleList.CircleListActivity;
import com.theta.animationdemo.Dialoganimation.DialogAnimationActivity;
import com.theta.animationdemo.ExpandableView.ExpandableViewActivity;
import com.theta.animationdemo.ParallaxViewPagerDemo.ParallaxActivity;
import com.theta.animationdemo.RecyclerViewWithCardview.RecyclerviewCardviewActivity;
import com.theta.animationdemo.RecylerViewAnimation.RecyclerViewActivity;
import com.theta.animationdemo.RevealEffect.RevealEffectActivity;
import com.theta.animationdemo.SharedElementTransitions.SharedElementTransitionsActivity;
import com.theta.animationdemo.TransitionAnimation.TransitionActivity;
import com.theta.animationdemo.ViewPagerAnimation.ViewPagerAnimationActivity;
import com.theta.animationdemo.parallaxAnimation.ParallaxAnimationActivity;
import com.theta.animationdemo.sparkleMotion.SparkleDemoActivity;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    Button btnTransitionsAnimation1;
    Button btnRevealEffect1;
    Button btnRecyclerviewAnimation1;
    Button btnExpandable1;
    Button btnRecyclerviewCardview1;
    Button btnCircleList1;
    Button btnTransitionAnimation1;
    Button btnViewPagerAnimation1;
    Button fab_button;
    Button SparkleAnimation;
    Button btnParallaxAnimation;
    Button btnParallaxAnimationViewpager;
    Button btnDialogAnimation;
    TextView txtStateListAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        init();
        btnTransitionsAnimation1.setOnClickListener(this);
        btnRevealEffect1.setOnClickListener(this);
        btnRecyclerviewAnimation1.setOnClickListener(this);
        btnExpandable1.setOnClickListener(this);
        btnRecyclerviewCardview1.setOnClickListener(this);
        btnCircleList1.setOnClickListener(this);
        btnTransitionAnimation1.setOnClickListener(this);
        btnViewPagerAnimation1.setOnClickListener(this);
        SparkleAnimation.setOnClickListener(this);
        btnParallaxAnimation.setOnClickListener(this);
        btnParallaxAnimationViewpager.setOnClickListener(this);
        btnDialogAnimation.setOnClickListener(this);

        Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setInterpolator(new DecelerateInterpolator()); //add this
        fadeIn.setDuration(1000);

        Animation fadeOut = new AlphaAnimation(1, 0);
        fadeOut.setInterpolator(new AccelerateInterpolator()); //and this
        fadeOut.setStartOffset(1000);
        fadeOut.setDuration(1000);

        AnimationSet animation = new AnimationSet(true); //change to false
        animation.addAnimation(fadeIn);
        animation.addAnimation(fadeOut);
        txtStateListAnimator.setAnimation(animation);
    }

    private void init() {
        btnTransitionsAnimation1 = (Button) findViewById(R.id.btnTransitionsAnimation1);
        btnRevealEffect1 = (Button) findViewById(R.id.btnRevealEffect1);
        btnRecyclerviewAnimation1 = (Button) findViewById(R.id.btnRecyclerviewAnimation1);
        btnExpandable1 = (Button) findViewById(R.id.btnExpandable1);
        btnRecyclerviewCardview1 = (Button) findViewById(R.id.btnRecyclerviewCardview1);
        btnCircleList1 = (Button) findViewById(R.id.btnCircleList1);
        btnTransitionAnimation1 = (Button) findViewById(R.id.btnTransitionAnimation1);
        btnViewPagerAnimation1 = (Button) findViewById(R.id.btnViewPagerAnimation1);
        fab_button = (Button) findViewById(R.id.fab_button);
        txtStateListAnimator = findViewById(R.id.txtStateListAnimator);
        SparkleAnimation = findViewById(R.id.SparkleAnimation);
        btnParallaxAnimation = findViewById(R.id.btnParallaxAnimation);
        btnParallaxAnimationViewpager = findViewById(R.id.btnParallaxAnimationViewpager);
        btnDialogAnimation = findViewById(R.id.btnDialogAnimation);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnTransitionsAnimation1) {
            startActivity(new Intent(this, SharedElementTransitionsActivity.class));
        }
        if (view.getId() == R.id.btnRevealEffect1) {
            startActivity(new Intent(this, RevealEffectActivity.class));
        }
        if (view.getId() == R.id.btnRecyclerviewAnimation1) {
            startActivity(new Intent(this, RecyclerViewActivity.class));
        }
        if (view.getId() == R.id.btnExpandable1) {
            startActivity(new Intent(this, ExpandableViewActivity.class));
        }
        if (view.getId() == R.id.btnRecyclerviewCardview1) {
            startActivity(new Intent(this, RecyclerviewCardviewActivity.class));
        }
        if (view.getId() == R.id.btnCircleList1) {
            startActivity(new Intent(this, CircleListActivity.class));
        }
        if (view.getId() == R.id.btnTransitionAnimation1) {
            Intent intent = new Intent(Main2Activity.this, TransitionActivity.class);
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this,
                    new Pair<View, String>(fab_button, ViewCompat.getTransitionName(fab_button))).toBundle());
        }
        if (view.getId() == R.id.btnViewPagerAnimation1) {
            startActivity(new Intent(this, ViewPagerAnimationActivity.class));
        }
        if (view.getId() == R.id.SparkleAnimation) {
            startActivity(new Intent(this, SparkleDemoActivity.class));
        }
        if (view.getId() == R.id.btnParallaxAnimation) {
            startActivity(new Intent(this, ParallaxAnimationActivity.class));
        }
        if (view.getId() == R.id.btnParallaxAnimationViewpager) {
            startActivity(new Intent(this, ParallaxActivity.class));
        }
        if (view.getId() == R.id.btnDialogAnimation) {
            startActivity(new Intent(this, DialogAnimationActivity.class));
        }

    }
}
