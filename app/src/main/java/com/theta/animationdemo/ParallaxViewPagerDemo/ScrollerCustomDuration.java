package com.theta.animationdemo.ParallaxViewPagerDemo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.Scroller;

/**
 * Created by ashish on 7/4/18.
 */

public class ScrollerCustomDuration extends Scroller {

    private double mScrollFactor = 1;
    // Default Value
    private int mScrollDuration = 1500;

    public ScrollerCustomDuration(Context context) {
        super(context);
    }

    public ScrollerCustomDuration(Context context, Interpolator interpolator) {
        super(context, interpolator);
    }

    @SuppressLint("NewApi")
    public ScrollerCustomDuration(Context context, Interpolator interpolator, boolean flywheel) {
        super(context, interpolator, flywheel);
    }

    /**
     * Set the factor by which the duration will change
     */
//    public void setScrollDurationFactor(double scrollFactor) {
//        mScrollFactor = scrollFactor;
//    }
    @Override
    public void startScroll(int startX, int startY, int dx, int dy, int duration) {
        super.startScroll(startX, startY, dx, dy, mScrollDuration);
    }

   /* @Override
    public void startScroll(int startX, int startY, int dx, int dy) {
        super.startScroll(startX, startY, dx, dy, mScrollDuration);
    }*/

    public void setScrollDuration(int scrollDuration) {
        mScrollDuration = scrollDuration;
    }

}