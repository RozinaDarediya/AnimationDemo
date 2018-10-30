package com.theta.animationdemo.ParallaxViewPagerDemo;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.theta.animationdemo.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by ashish on 19/4/18.
 */

public class OnboardingScreen4 extends Fragment {

    private ImageView imageView1;

    private Handler handler;
    private View view;
    private boolean isLoaded = false;
    private boolean isVisibleToUser = false;

    private boolean animationLoded = true;
    private Animation slide;
    private int DELAYTIME = 0;
    private String side = "";

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.e("isVisibleToUser fragment4 " + isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;

        try {
            Log.e("erase animation fragment4 : fragment4");
            if (imageView1 != null) {
                imageView1.setVisibility(View.GONE);
                imageView1.clearAnimation();
            }
            handler.removeCallbacksAndMessages(null);
        } catch (Exception e) {
            Log.e(e.toString());
        }

        if (isVisibleToUser && isLoaded && !(animationLoded)) {
            playAnimation();
            isLoaded = true;
        } else if (isVisibleToUser && (!isLoaded) && !(animationLoded)) {
            playAnimation();
            isLoaded = true;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.onboarding_screen4, container, false);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }

        handler = new Handler();

        if (isVisibleToUser) {
            Log.e(" on create view fragment4");

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    init();
                    callAnimation();
                    isLoaded = true;
                }
            }, DELAYTIME);

        }
        return view;
    }

    private void init() {
        imageView1 = view.findViewById(R.id.imageView1);

        handler = new Handler();
        slide = AnimationUtils.loadAnimation(getContext(), R.anim.slide);
    }

    private void callAnimation() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                imageView1.setVisibility(View.VISIBLE);
                imageView1.startAnimation(slide);
            }
        }, 100);


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (!isVisibleToUser) {
            try {
                if (imageView1 != null) {
                    imageView1.setVisibility(View.GONE);
                    imageView1.clearAnimation();
                }
            } catch (Exception e) {
                Log.e(e.toString());
            }
        }
    }

    @Subscribe
    public void getEvent(final Events.LodedFromButtonFragment4 lodedFromButton) {
        final boolean flag = lodedFromButton.getEventFragment4();
        Log.e("getEvent fragment4: " + flag);
        if (flag) {
            DELAYTIME = 1200;
            eraseAnimation();
            playAnimation();
        } else {
            DELAYTIME = 0;
            animationLoded = false;
            eraseAnimation();
            playAnimation();
        }

    }

    private void playAnimation() {
        handler = new Handler();

        if (isVisibleToUser) {
            Log.e(" on create view fragment4");

            if (isVisibleToUser)
                animationLoded = true;
            else {
                animationLoded = false;
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

    private void eraseAnimation() {
        try {
            if (imageView1 != null) {
                imageView1.setVisibility(View.GONE);
                imageView1.clearAnimation();
            }
        } catch (Exception e) {
            Log.e(e.toString());
        }
    }
}
