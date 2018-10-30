package com.theta.animationdemo.TransitionAnimation;

import android.animation.Animator;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.transition.Transition;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.theta.animationdemo.AndroidPageCurl.CurlActivity;
import com.theta.animationdemo.R;
import com.theta.animationdemo.fabButtonRotation.FabButtonAnimationActivity;

public class TransitionActivity extends AppCompatActivity {

    private static final int SCALE_DELAY = 30;
    private LinearLayout rowContainer;
    private TextView sample_row1;
    private TextView sample_row2;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition);

        rowContainer = (LinearLayout) findViewById(R.id.row_container2);
        sample_row1 = findViewById(R.id.sample_row1);
        sample_row1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TransitionActivity.this, CurlActivity.class));
            }
        });
        sample_row2 = findViewById(R.id.sample_row2);
        sample_row2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TransitionActivity.this, FabButtonAnimationActivity.class));
            }
        });

       /* Slide slideExitTransition = new Slide(Gravity.BOTTOM);
        slideExitTransition.excludeTarget(android.R.id.navigationBarBackground, true);
        slideExitTransition.excludeTarget(android.R.id.statusBarBackground, true);
*/
        getWindow().getEnterTransition().addListener(new TransitionAdapter() {
            @Override
            public void onTransitionEnd(Transition transition) {
                super.onTransitionEnd(transition);
                getWindow().getEnterTransition().removeListener(this);
                for (int i = 0; i < rowContainer.getChildCount(); i++) {

                    View rowView = rowContainer.getChildAt(i);
                    rowView.animate().setStartDelay(i * SCALE_DELAY)
                            .scaleX(1).scaleY(1);

                }
            }
        });
    }

    @Override
    public void onBackPressed() {

        for (int i = 0; i < rowContainer.getChildCount(); i++) {

            View rowView = rowContainer.getChildAt(i);

            ViewPropertyAnimator propertyAnimator = rowView.animate()
                    .setStartDelay(i * SCALE_DELAY)
                    .scaleX(0).scaleY(0)
                    .setListener(new AnimatorAdapter() {

                        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                        @Override
                        public void onAnimationEnd(Animator animation) {

                            super.onAnimationEnd(animation);
                            finishAfterTransition();
                        }
                    });
        }
    }
}
