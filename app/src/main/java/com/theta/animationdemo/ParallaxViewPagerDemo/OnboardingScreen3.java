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

public class OnboardingScreen3 extends Fragment {

    private RelativeLayout relative;
    private ImageView imageView1;
    private ImageView imageView2;
    private ImageView imgLayout1;

    private Handler handler;
    private View view;
    private boolean isLoaded = false;
    private boolean isVisibleToUser = false;

    private Animation slide;
    private String side = "";
    private boolean animationLoded = true;
    private int DELAYTIME = 0;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        Log.e("isVisibleToUser  fragment3 " + isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;

        eraseAnimation();
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
        view =  inflater.inflate(R.layout.onboarding_screen3, container, false);
        // Register this fragment to listen to event.
        Log.e(" onCreateView fragment3 visible " + isVisibleToUser);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }

        if (isVisibleToUser && (!isLoaded)) {
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

    @Subscribe
    public void getFragmentPosition(final Events.FragmentThreeData fragmentThreeData) {
        init();
        playAnimation(fragmentThreeData);
    }

    private void playAnimation(final Events.FragmentThreeData fragmentThreeData) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                DELAYTIME = 0;
                side = fragmentThreeData.getSide();
                Log.e(" getmessage fragment3 visible " + isVisibleToUser);

                if (isVisibleToUser)
                    animationLoded = true;
                else {
                    animationLoded = false;
                }

                if (side.equals("toleft")) {
                    if (isVisibleToUser && isLoaded) {
                        //init();
                        callAnimationtoleft();
                        isLoaded = true;
                    } else if (isVisibleToUser && (!isLoaded)) {
                        callAnimationtoleft();
                        isLoaded = true;
                    }
                }
                if (side.equals("toright")) {
                    if (isVisibleToUser && isLoaded) {
                        callAnimationToRight();
                        isLoaded = true;
                    } else if (isVisibleToUser && (!isLoaded)) {
                        callAnimationToRight();
                        isLoaded = true;
                    }
                }
            }
        }, DELAYTIME);
    }

    @Subscribe
    public void getEvent(final Events.LodedFromButton lodedFromButton) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final boolean flag = lodedFromButton.getEvent();
                Log.e("getEvent fragment3: " + flag);
                if (flag) {
                    DELAYTIME = 1200;
                    eraseAnimation();
                }
            }
        });
    }

    private void init() {
        relative = view.findViewById(R.id.relative);
        imageView1 = view.findViewById(R.id.imageView1);
        imageView2 = view.findViewById(R.id.imageView2);
        imgLayout1 = view.findViewById(R.id.imgLayout1);

        handler = new Handler();

        imgLayout1.setVisibility(View.GONE);
        imageView2.setVisibility(View.GONE);
        relative.setVisibility(View.VISIBLE);

        Log.e(distance + " distance");

         slide = AnimationUtils.loadAnimation(getContext(), R.anim.slide);
    }

    private void callAnimationToRight() {
        imageView1.setVisibility(View.VISIBLE);
        imageView1.startAnimation(slide);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                imgLayout1.setVisibility(View.VISIBLE);
                imgLayout1.startAnimation(animationRight);
            }
        }, 2500);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                relative.setVisibility(View.GONE);
                imgLayout1.setVisibility(View.GONE);
                imageView1.setVisibility(View.GONE);
                imageView2.setVisibility(View.VISIBLE);

                imageView2.startAnimation(slide);

            }
        }, 4000);

    }

    private void callAnimationtoleft() {
        imageView1.setVisibility(View.VISIBLE);
        imageView1.startAnimation(slide);


        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                imgLayout1.setVisibility(View.VISIBLE);
                imgLayout1.startAnimation(animationLeft);
            }
        }, 2500);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                relative.setVisibility(View.GONE);
                imgLayout1.setVisibility(View.GONE);
                imageView1.setVisibility(View.GONE);
                imageView2.setVisibility(View.VISIBLE);

                imageView2.startAnimation(slide);

            }
        }, 4000);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.e("fragment3 onHiddenChanged");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("fragment3 onCreate");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e("fragment3 onActivitycreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("fragment3 onstart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("fragment3 onresume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("fragment3 onpause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("fragment3 onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e("fragment3 ondesstroy view");
        eraseAnimation();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("fragment3 ondestroy");

    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e("fragment3 ondetach");
    }

    private void eraseAnimation() {
        try {
            Log.e("erase animation : fragment3");
            if (imgLayout1 != null) {
                imgLayout1.setVisibility(View.GONE);
                imageView1.clearAnimation();
            }
            if (imageView1 != null) {
                imageView1.setVisibility(View.GONE);
                imgLayout1.clearAnimation();
            }
            if (imageView2 != null) {
                imageView2.setVisibility(View.GONE);
                imageView2.clearAnimation();
            }
            handler.removeCallbacksAndMessages(null);
        } catch (Exception e) {
            Log.e(e.toString());
        }

    }
}
