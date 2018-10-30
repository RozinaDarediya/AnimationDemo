package com.theta.animationdemo.ParallaxViewPagerDemo;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.theta.animationdemo.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import static com.theta.animationdemo.ParallaxViewPagerDemo.ParallaxActivity.distance;
import static com.theta.animationdemo.ParallaxViewPagerDemo.ParallaxActivity.lodedFirstTime;

/**
 * Created by ashish on 19/4/18.
 */

public class OnboardingScreen1 extends Fragment {

    private ImageView bike_img;
    private LinearLayout bgLayout;
    private Handler handler;
    private View view;
    private boolean isLoaded = false;
    private boolean isVisibleToUser = false;

    private boolean animationLoded = true;
    private int DELAYTIME = 0;


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.e("isVisibleToUser  " + isVisibleToUser + " isLoaded : " + isLoaded);

        this.isVisibleToUser = isVisibleToUser;

        try {
            Log.e("erase animation : fragment1");
            if (bike_img != null) {
                bike_img.setVisibility(View.GONE);
                bike_img.clearAnimation();
            }
            handler.removeCallbacksAndMessages(null);
        } catch (Exception e) {
            Log.e(e.toString());
        }

        if (isVisibleToUser && isLoaded && !(animationLoded)) {
            playAnimation();
        }
    }

    private void playAnimation() {
        Log.e("delay time play animation  :  " + DELAYTIME);
        handler = new Handler();

        if (isVisibleToUser) {
            Log.e(" on create view fragment1");
            if (isVisibleToUser)
                animationLoded = true;
            else {
                animationLoded = false;
                // eraseAnimation();
            }
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    init();
                    callAnimation();
                    isLoaded = true;
                }
            }, DELAYTIME);
        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.onboarding_screen1, container, false);

        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }

        handler = new Handler();

        if (isVisibleToUser && (!isLoaded)) {
            if (lodedFirstTime) {
                lodedFirstTime = false;
                DELAYTIME = 2500;
            }
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Log.e("delay time oncreate :  " + DELAYTIME);
                    init();
                    callAnimation();
                    isLoaded = true;
                }
            }, DELAYTIME);

        }

        return view;
    }


    @Subscribe
    public void getEvent(final Events.LodedFromButtonFragment1 lodedFromButton) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final boolean flag = lodedFromButton.getEventFragment1();
                Log.e("getEvent fragment1: " + flag);
                if (lodedFirstTime) {
                    Log.e("delay time  load first:  " + DELAYTIME);
                    lodedFirstTime = false;
                    DELAYTIME = 2000;
                    eraseAnimation();
                    playAnimation();
                } else if (flag) {
                    Log.e("delay time  loaded frm btn :  " + DELAYTIME);
                    DELAYTIME = 2500;
                    eraseAnimation();
                    playAnimation();
                } else {
                    Log.e("delay time swiped :  " + DELAYTIME);
                    DELAYTIME = 0;
                    animationLoded = false;
                    eraseAnimation();
                    playAnimation();
                }
            }
        });
    }

    private void eraseAnimation() {
        try {
            Log.e("erase animation : fragment1");
            if (bike_img != null) {
                bike_img.setVisibility(View.GONE);
                bike_img.clearAnimation();
            }
            handler.removeCallbacksAndMessages(null);
        } catch (Exception e) {
            Log.e(e.toString());
        }

    }

    private void init() {
        bike_img = view.findViewById(R.id.bike_img);
        bike_img.setVisibility(View.GONE);
    }

    private void callAnimation() {
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.e(distance + " distance");

                final Animation anim = new TranslateAnimation(0, 0, distance, 0); // from left to right
                anim.setDuration(1500);
                anim.setFillAfter(true);

                bike_img.setVisibility(View.VISIBLE);
                bike_img.startAnimation(anim);
            }
        }, 1500);
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (!isVisibleToUser) {
            try {
                if (bike_img != null) {
                    bike_img.setVisibility(View.GONE);
                    bike_img.clearAnimation();
                }
                handler.removeCallbacksAndMessages(null);
            } catch (Exception e) {
                Log.e(e.toString());
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
