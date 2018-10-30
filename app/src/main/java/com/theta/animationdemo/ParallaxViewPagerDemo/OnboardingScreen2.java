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
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.theta.animationdemo.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import static com.theta.animationdemo.ParallaxViewPagerDemo.ParallaxActivity.animationLeft;
import static com.theta.animationdemo.ParallaxViewPagerDemo.ParallaxActivity.animationRight;
import static com.theta.animationdemo.ParallaxViewPagerDemo.ParallaxActivity.distance;

/**
 * Created by ashish on 19/4/18.
 */

public class OnboardingScreen2 extends Fragment {

    private Handler handler;
    private View view;
    private boolean isLoaded = false;
    private boolean isVisibleToUser = false;

    private RelativeLayout relative;
    private RelativeLayout layout2;
    private ImageView imgLayout;
    private ImageView imgLayout1;
    private ImageView img_bike;
    private ImageView img_battery;

    private Animation slide, slide_bike_right;

    private String side = "";
    private int DELAYTIME = 0;
    private boolean animationLoded = true;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        Log.e("isVisibleToUser fragment2  " + isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;

        try {
            Log.e("erase animation : fragment2");
            if (img_battery != null) {
                img_battery.clearAnimation();
                img_battery.setVisibility(View.INVISIBLE);
            }
            if (img_bike != null) {
                img_bike.setVisibility(View.INVISIBLE);
                img_bike.clearAnimation();
            }

            if (imgLayout != null) {
                imgLayout.setVisibility(View.INVISIBLE);
                imgLayout.clearAnimation();
            }

            if (imgLayout1 != null) {
                imgLayout1.setVisibility(View.INVISIBLE);
                imgLayout1.clearAnimation();
            }

            imgLayout.clearAnimation();
            imgLayout1.clearAnimation();
            handler.removeCallbacksAndMessages(null);
        } catch (Exception e) {
            Log.e(e.toString());
        }

        if (isVisibleToUser && !(animationLoded)) {
            if (side.equals("toleft")) {
                init();
                callAnimationtoleft();
                isLoaded = true;
            } else {
                init();
                callAnimationToRight();
                isLoaded = true;
            }
        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.onboarding_screen2, container, false);

        // Register this fragment to listen to event.
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        Log.e(" onCreateView fragment2 visible " + isVisibleToUser);

        if (isVisibleToUser && (!isLoaded) && !(animationLoded)) {
            if (side.equals("toleft")) {
                init();
                callAnimationtoleft();
                isLoaded = true;
            } else {
                init();
                callAnimationToRight();
                isLoaded = true;
            }
            isLoaded = true;
        }
        return view;
    }

    private void callAnimationtoleft() {

        imgLayout.setVisibility(View.VISIBLE);
        imgLayout.startAnimation(slide);


        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                imgLayout1.setVisibility(View.VISIBLE);
                imgLayout1.startAnimation(animationLeft);
            }
        }, 3000);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                relative.setVisibility(View.GONE);
                imgLayout1.setVisibility(View.GONE);
                imgLayout.setVisibility(View.GONE);
                layout2.setVisibility(View.VISIBLE);
                img_battery.setVisibility(View.VISIBLE);
                img_bike.setVisibility(View.VISIBLE);

                img_bike.startAnimation(slide_bike_right);
                img_battery.startAnimation(slide);

            }
        }, 5000);

    }

    private void callAnimationToRight() {
        Animation animation = new TranslateAnimation(0, 20, 0, 0);
        animation.setDuration(500);
        animation.setFillAfter(true);

        imgLayout.setVisibility(View.VISIBLE);
        imgLayout.startAnimation(slide);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                imgLayout1.setVisibility(View.VISIBLE);
                imgLayout1.startAnimation(animationRight);
            }
        }, 3000);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                relative.setVisibility(View.GONE);
                imgLayout1.setVisibility(View.GONE);
                imgLayout.setVisibility(View.GONE);
                layout2.setVisibility(View.VISIBLE);
                img_battery.setVisibility(View.VISIBLE);
                img_bike.setVisibility(View.VISIBLE);

                img_bike.startAnimation(slide_bike_right);
                img_battery.startAnimation(slide);

            }
        }, 5000);

    }

    private void init() {
        relative = view.findViewById(R.id.relative);
        layout2 = view.findViewById(R.id.layout2);
        imgLayout = view.findViewById(R.id.imgLayout);
        imgLayout1 = view.findViewById(R.id.imgLayout1);
        img_bike = view.findViewById(R.id.img_bike);
        img_battery = view.findViewById(R.id.img_battery);

        handler = new Handler();

        imgLayout1.setVisibility(View.GONE);
        img_battery.setVisibility(View.GONE);
        img_bike.setVisibility(View.GONE);
        relative.setVisibility(View.VISIBLE);

        Log.e(distance + " distance frag 2");

        slide = AnimationUtils.loadAnimation(getContext(), R.anim.slide);
        slide_bike_right = AnimationUtils.loadAnimation(getContext(), R.anim.slide_bike_right);
    }

    @Subscribe
    public void getFragmentPosition(final Events.FragmentData fragmentData) {

        init();
        playAnimation(fragmentData);
    }

    private void playAnimation(final Events.FragmentData fragmentData) {

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                DELAYTIME = 0;
                animationLoded = false;
                side = fragmentData.getFragmentPosition();

                Log.e(" getmessage fragment2 visible " + isVisibleToUser);

                if (isVisibleToUser)
                    animationLoded = true;
                else {
                    animationLoded = false;
                    eraseAnimation();
                }

                Log.e("data : fragment2  " + fragmentData.getFragmentPosition() + " visible : " + isVisibleToUser);
                if (side.equals("toleft")) {
                    if (isVisibleToUser && isLoaded) {
                        //init();
                        callAnimationtoleft();
                        isLoaded = true;
                    } else if (isVisibleToUser && (!isLoaded)) {
                        init();
                        callAnimationtoleft();
                        isLoaded = true;
                    }
                }
                if (side.equals("toright")) {
                    if (isVisibleToUser && isLoaded) {
                        Log.e(side + " from event bus");
                        callAnimationToRight();
                        isLoaded = true;
                    } else if (isVisibleToUser && (!isLoaded)) {
                        Log.e(side + " from event bus");
                        callAnimationToRight();
                        isLoaded = true;
                    }
                }
            }
        }, DELAYTIME);
    }

    @Subscribe
    public void getEvent(final Events.LodedFromButtonFragment2 lodedFromButton) {

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final boolean flag = lodedFromButton.getEventFragment2();
                Log.e("getEvent fragment2: " + flag);
                if (flag) {

                    DELAYTIME = 1200;
                    eraseAnimation();
                }
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("fragment2 onpause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("fragment2 onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e("fragment2 ondesstroy view");
        eraseAnimation();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("fragment2 ondestroy");

    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e("fragment2 ondetach");
    }

    private void eraseAnimation() {
        try {
            Log.e("erase animation : fragment2");
            if (img_battery != null) {
                img_battery.clearAnimation();
                img_battery.setVisibility(View.INVISIBLE);
            }
            if (img_bike != null) {
                img_bike.setVisibility(View.INVISIBLE);
                img_bike.clearAnimation();
            }

            imgLayout.clearAnimation();
            imgLayout1.clearAnimation();
            handler.removeCallbacksAndMessages(null);
        } catch (Exception e) {
            Log.e(e.toString());
        }
    }
}
