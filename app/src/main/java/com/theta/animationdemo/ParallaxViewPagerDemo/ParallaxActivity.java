package com.theta.animationdemo.ParallaxViewPagerDemo;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.theta.animationdemo.R;

import org.greenrobot.eventbus.EventBus;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ParallaxActivity extends AppCompatActivity implements View.OnClickListener {

    public static boolean lodedFirstTime = true;
    ParallaxViewPager parallaxViewPager1;
    private TextView animatedView;
    ViewPagerCustomDuration viewPager;
    public static float distance;
    private TextView txtIntro;
    private Button btnPrevious;
    private Button btnNext;
    CircleIndicator tabIndicator;

    private int currentTab = 0;
    // Animation
    static Animation animMoveToTop, animationLeft, animationRight, slideInRight, slideInLeft, topBottomHalf, topHalf;
    LinearLayout bottomLayout;
    LinearLayout middleLayout;
    LinearLayout tabLayout;
    private Handler handler = new Handler();

    private String side;
    private boolean isLodedFromButton = false;
    private int mLastVisitedPageIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parallax);

        init();
        setFrameOne(0);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                Log.e("onPageselected");
                parallaxViewPager1.setCurrentItem(position, true);
                boolean isMovingForward = mLastVisitedPageIndex < position ? true : false;
                if (isMovingForward)
                    side = "toleft";
                else
                    side = "toright";
                mLastVisitedPageIndex = position;
                Log.e("onPageSelected : " + position + " side : " + side);
                currentTab = position;
                if (position == 0) {
                    EventBus.getDefault().post(new Events.LodedFromButtonFragment1(isLodedFromButton));
                    setFrameOne(position);
                }
                if (position == 1) {
                    EventBus.getDefault().post(new Events.LodedFromButtonFragment2(isLodedFromButton));
                    setFrameTwo(position);
                }
                if (position == 2) {
                    EventBus.getDefault().post(new Events.LodedFromButton(isLodedFromButton));
                    setFrameThree(position);
                }
                if (position == 3) {
                    EventBus.getDefault().post(new Events.LodedFromButtonFragment4(isLodedFromButton));
                    setFrameFour(position);
                }
                isLodedFromButton = false;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void init() {


        WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(metrics);
        final float screen_width_half = metrics.widthPixels / 2;
        distance = screen_width_half + 50;

        parallaxViewPager1 = findViewById(R.id.parallaxViewPager);
        parallaxViewPager1.setOverlapPercentage(0.60f);
        setupParallaxViewPager(parallaxViewPager1);

        viewPager = findViewById(R.id.viewPager);
        viewPager.setScrollDuration(1500);
        setupViewPager(viewPager);

        tabIndicator = findViewById(R.id.tabIndicator);
        tabIndicator.setViewPager(parallaxViewPager1);
        tabIndicator.setViewPager(viewPager);

        txtIntro = findViewById(R.id.txtIntro);
        animatedView = findViewById(R.id.animatedView);
        btnNext = findViewById(R.id.btnNext);
        btnPrevious = findViewById(R.id.btnPrevious);

        bottomLayout = findViewById(R.id.bottomLayout);
        middleLayout = findViewById(R.id.middleLayout);
        tabLayout = findViewById(R.id.tabLayout);

        // load the animation
        animMoveToTop = AnimationUtils.loadAnimation(this, R.anim.move);
        topBottomHalf = AnimationUtils.loadAnimation(this, R.anim.top_bottom_half);
        topHalf = AnimationUtils.loadAnimation(this, R.anim.top_half);
        slideInRight = AnimationUtils.loadAnimation(this, R.anim.slide_in_right);
        slideInLeft = AnimationUtils.loadAnimation(this, R.anim.slide_in_left);

        btnPrevious.setOnClickListener(this);
        btnNext.setOnClickListener(this);

        animationRight = new TranslateAnimation(-distance, 0, 0, 0); // from left to right
        animationRight.setDuration(1500);
        animationRight.setFillAfter(true);

        animationLeft = new TranslateAnimation(distance, 0, 0, 0); // from right to left
        animationLeft.setDuration(1500);
        animationLeft.setFillAfter(true);


        try {
            Field mScroller;
            mScroller = ViewPager.class.getDeclaredField("mScroller");
            mScroller.setAccessible(true);
            Interpolator sInterpolator = new AccelerateInterpolator();
            FixedSpeedScroller scroller = new FixedSpeedScroller(parallaxViewPager1.getContext(), sInterpolator, true);
            // scroller.setFixedDuration(5000);
            mScroller.set(parallaxViewPager1, scroller);
        } catch (NoSuchFieldException e) {
        } catch (IllegalArgumentException e) {
        } catch (IllegalAccessException e) {
        }

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new OnboardingScreen1(), "Tab 1");
        adapter.addFragment(new OnboardingScreen2(), "Tab 2");
        adapter.addFragment(new OnboardingScreen3(), "Tab 3");
        adapter.addFragment(new OnboardingScreen4(), "Tab 4");
        viewPager.setAdapter(adapter);
    }

    private void setupParallaxViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new OnboardingScreen(), "");
        adapter.addFragment(new OnboardingScreen(), "");
        adapter.addFragment(new OnboardingScreen(), "");
        adapter.addFragment(new OnboardingScreen(), "");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        Log.e("in on click()");
        isLodedFromButton = true;

        if (view.getId() == btnPrevious.getId()) {
            if (currentTab > 0 && currentTab <= 3) {
                viewPager.setCurrentItem(currentTab - 1, true);
            }
        } else if (view.getId() == btnNext.getId()) {
            if (currentTab >= 0 && currentTab < 3) {
                viewPager.setCurrentItem(currentTab + 1, true);
            } else if (currentTab == 3) {
                Toast.makeText(this, "You are at last fragment.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }

    }

    private void setFrameOne(final int position) {
        Log.e("setFrameOne");
        txtIntro.setVisibility(View.GONE);
        animatedView.setVisibility(View.GONE);
        tabIndicator.setVisibility(View.GONE);
        btnNext.setVisibility(View.GONE);
        topBottomHalf.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (position == 0) {
                            parallaxViewPager1.startAnimation(topHalf);
                        }
                    }
                }, 500);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

        topHalf.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (position == 0) {
                            txtIntro.setVisibility(View.VISIBLE);
                            animatedView.setVisibility(View.VISIBLE);
                            bottomLayout.setVisibility(View.VISIBLE);
                            middleLayout.setVisibility(View.VISIBLE);
                            tabIndicator.setVisibility(View.VISIBLE);
                            btnNext.setVisibility(View.VISIBLE);

                            middleLayout.startAnimation(animMoveToTop);
                            bottomLayout.startAnimation(animMoveToTop);
                        }
                    }
                }, 500);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                parallaxViewPager1.startAnimation(topBottomHalf);
            }
        }, 2000);

        btnNext.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
        btnPrevious.setVisibility(View.INVISIBLE);
        btnNext.setBackgroundColor(Color.TRANSPARENT);
        btnNext.setTextColor(getResources().getColor(R.color.light_purple));
        btnNext.setText(R.string.strNext);
        txtIntro.setText(R.string.strIntro1Title);
        animatedView.setText(R.string.strIntro1Text);
    }

    private void setFrameTwo(int position) {
        Log.e("setFrameTwo");
        Log.e(side);
        EventBus.getDefault().post(new Events.FragmentData(side));

        tabIndicator.setVisibility(View.GONE);
        txtIntro.setVisibility(View.GONE);

        Log.e(distance + " distance");
        if (side.equals("toright")) {
            tabIndicator.setVisibility(View.VISIBLE);
            txtIntro.setVisibility(View.VISIBLE);

            tabLayout.startAnimation(slideInRight);
            txtIntro.startAnimation(slideInRight);
        } else {
            tabIndicator.setVisibility(View.VISIBLE);
            txtIntro.setVisibility(View.VISIBLE);

            tabLayout.startAnimation(slideInLeft);
            txtIntro.startAnimation(slideInLeft);
        }
        btnNext.setVisibility(View.VISIBLE);
        btnNext.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
        btnNext.setBackgroundColor(Color.TRANSPARENT);
        btnNext.setTextColor(getResources().getColor(R.color.btnNext));
        btnNext.setText(R.string.strNext);
        btnPrevious.setVisibility(View.VISIBLE);
        txtIntro.setText(R.string.strIntro2Title);
        animatedView.setText(R.string.strIntro2Text);
    }

    private void setFrameThree(int position) {
        Log.e("setFrameThree");
        Log.e(side);
        EventBus.getDefault().post(new Events.FragmentThreeData(side));

        tabIndicator.setVisibility(View.GONE);
        txtIntro.setVisibility(View.GONE);
        Log.e(distance + " distance");
        if (side.equals("toright")) {
            tabIndicator.setVisibility(View.VISIBLE);
            txtIntro.setVisibility(View.VISIBLE);

            tabLayout.startAnimation(slideInRight);
            txtIntro.startAnimation(slideInRight);
        } else {
            tabIndicator.setVisibility(View.VISIBLE);
            txtIntro.setVisibility(View.VISIBLE);

            tabLayout.startAnimation(slideInLeft);
            txtIntro.startAnimation(slideInLeft);
        }
        btnNext.setVisibility(View.VISIBLE);
        btnNext.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
        btnNext.setBackgroundColor(Color.TRANSPARENT);
        btnNext.setTextColor(getResources().getColor(R.color.btnNext));
        btnNext.setText(R.string.strNext);
        txtIntro.setVisibility(View.VISIBLE);
        btnPrevious.setVisibility(View.VISIBLE);
        txtIntro.setText(R.string.strIntro3Title);
        animatedView.setText(R.string.strIntro3Text);
    }

    private void setFrameFour(int position) {


        Log.e("setFrameFour");
        tabIndicator.setVisibility(View.GONE);
        txtIntro.setVisibility(View.GONE);
        Log.e(distance + " distance");


        if (side.equals("toright")) {
            tabIndicator.setVisibility(View.VISIBLE);
            txtIntro.setVisibility(View.VISIBLE);

            tabLayout.startAnimation(slideInRight);
            txtIntro.startAnimation(slideInRight);
        } else {
            tabIndicator.setVisibility(View.VISIBLE);
            txtIntro.setVisibility(View.VISIBLE);

            tabLayout.startAnimation(slideInLeft);
            txtIntro.startAnimation(slideInLeft);
        }

        //bottomLayout.setVisibility(View.GONE);
        btnNext.setVisibility(View.VISIBLE);
        btnNext.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        btnNext.setText(R.string.strGetStarted);
        txtIntro.setText(R.string.strIntro4Title);
        animatedView.setText(R.string.strIntro4Text);

    }

}

