package com.theta.animationdemo.global;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewAnimationUtils;

/**
 * Created by ashish on 6/12/17.
 */

public class Global {


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void showAnimation(final View view) {
        int cx = (view.getWidth()) / 2;
        int cy = (view.getHeight()) / 2;
        view.setVisibility(View.VISIBLE);
        // get the final radius for the clipping circle
        int finalRadius = Math.max(view.getWidth(), view.getHeight());

        // create the animator for this view (the start radius is zero)
        Animator anim = ViewAnimationUtils.createCircularReveal(view, cx, cy,
                0, finalRadius);
        anim.setDuration(1000);
        // make the view visible and start the animation
       /* view.setVisibility(View.VISIBLE);
        anim.start();*/
        // make the view invisible when the animation is done
        anim.addListener(new AnimatorListenerAdapter() {
            @SuppressLint("ResourceAsColor")
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                view.setVisibility(View.INVISIBLE);
            }
        });


        anim.start();
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void hideCircularReveal(final View myView) {
        // get the center for the clipping circle
        int cx = (int) myView.getX() + myView.getWidth() / 2;
        int cy = (int) myView.getY();
        // get the initial radius for the clipping circle
        float initialRadius = (float) Math.hypot(myView.getWidth(), myView.getHeight());
        // create the animation (the final radius is zero)
        Animator anim =
                ViewAnimationUtils.createCircularReveal(myView, cx, cy, initialRadius, 0);

        // make the view invisible when the animation is done
        anim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                myView.setVisibility(View.INVISIBLE);
                // startActivity(new Intent(RevealEffectActivity.this, MainActivity.class));
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
        // start the animation
        anim.start();
    }

    public static void startHideRevealEffect(final View myView) {
        // get the center for the clipping circle
        final int cx = (int) myView.getX() + myView.getWidth() / 2;
        final int cy = (int) myView.getY();
        if (cx != 0 && cy != 0) {
            myView.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void onViewAttachedToWindow(View view) {
                    // Get the accent color
                    TypedValue outValue = new TypedValue();
                    //getTheme().resolveAttribute(android.R.attr.colorPrimary, outValue, true);
                    myView.setBackgroundColor(outValue.data);

                    GUIUtils.hideRevealEffect(myView, cx, cy, 1920);
                }

                @Override
                public void onViewDetachedFromWindow(View view) {

                }
            });
        }
    }//

    /*
  create a new circular reaveal on the give view. This view is initially invisible. In this case the view covers full screen
*/
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void createCircularReveal(final View view) {

        // to get the center of FAB
        int centerX = (int) view.getX() + view.getWidth() / 2;
        int centerY = (int) view.getY();
        float finalRadius = (float) Math.hypot(view.getWidth(), view.getHeight());
        // starts the effect at centerX, center Y and covers final radius
        Animator revealAnimator = ViewAnimationUtils.createCircularReveal(view,
                centerX, centerY, 0, finalRadius);
        view.setVisibility(View.VISIBLE);
        revealAnimator.setDuration(1000);
        revealAnimator.start();

    }

}
