package com.theta.animationdemo.global;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.graphics.Outline;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.support.annotation.RequiresApi;
import android.transition.Fade;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewPropertyAnimator;
import android.view.Window;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.PathInterpolator;

import com.theta.animationdemo.OnRevealAnimationListener;
import com.theta.animationdemo.R;


public class GUIUtils {

    public static final int SCALE_FACTOR = 30;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void configureWindowEnterExitExplodeTransition(Window w) {

        Fade ex = new Fade();
        ex.setInterpolator(new PathInterpolator(0.4f, 0, 1, 1));
        ex.setDuration(5000);
        w.setExitTransition(ex);
        w.setEnterTransition(ex);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void configureFab (View fabButton) {

        int fabSize = fabButton.getContext().getResources()
            .getDimensionPixelSize(R.dimen.fab_size);
        Outline fabOutLine = new Outline();
        fabOutLine.setOval(0, 0, fabSize, fabSize);
    }

    public static int getStatusBarHeight(Context c) {

        int result = 0;
        int resourceId = c.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = c.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void hideRevealEffect (final View v, int centerX, int centerY, int initialRadius) {

        v.setVisibility(View.VISIBLE);

        // create the animation (the final radius is zero)
        Animator anim = ViewAnimationUtils.createCircularReveal(
                v, centerX, centerY, initialRadius, 0);

        anim.setDuration(350);

        // make the view invisible when the animation is done
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                v.setVisibility(View.INVISIBLE);
            }
        });

        anim.start();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void showRevealEffect(final View v, int centerX, int centerY, Animator.AnimatorListener lis) {

        v.setVisibility(View.VISIBLE);

        int height = v.getHeight();

        Animator anim = ViewAnimationUtils.createCircularReveal(
                v, centerX, centerY, 0, height);

        anim.setDuration(350);

        anim.addListener(lis);
        anim.start();
    }



    public static void hideViewByScale(View view) {

        ViewPropertyAnimator propertyAnimator = view.animate().setStartDelay(SCALE_FACTOR)
                .scaleX(0).scaleY(0);

        propertyAnimator.start();
    }

    public static void showViewByScale(View view) {

        ViewPropertyAnimator propertyAnimator = view.animate().setStartDelay(SCALE_FACTOR)
                .scaleX(1).scaleY(1);

        propertyAnimator.start();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void animateRevealShow(final Context ctx, final View view, final int startRadius,
                                         @ColorRes final int color, int x, int y, final OnRevealAnimationListener listener) {
        float finalRadius = (float) Math.hypot(view.getWidth(), view.getHeight());

        Animator anim =
                ViewAnimationUtils.createCircularReveal(view, x, y, startRadius, finalRadius);
        anim.setDuration(1000);
        anim.setStartDelay(100);
        anim.setInterpolator(new AccelerateDecelerateInterpolator());
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                view.setBackgroundColor(ctx.getResources().getColor(R.color.colorPrimary));
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                view.setVisibility(View.VISIBLE);
                listener.onRevealShow();
            }
        });
        anim.start();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void animateRevealHide(final Context ctx, final View view, @ColorRes int color,
                                         final int finalRadius, OnRevealAnimationListener listener) {
        int cx = (view.getLeft() + view.getRight()) / 2;
        int cy = (view.getTop() + view.getBottom()) / 2;
        int initialRadius = view.getWidth();

        Animator anim =
                ViewAnimationUtils.createCircularReveal(view, cx, cy, initialRadius, finalRadius);
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                view.setBackgroundColor(ctx.getResources().getColor(color));
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                view.setVisibility(View.INVISIBLE);
                listener.onRevealHide();
            }
        });
        anim.setDuration(1000);
        anim.start();
    }

}
