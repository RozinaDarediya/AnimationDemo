package com.theta.animationdemo.statusbarImage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.theta.animationdemo.ParallaxViewPagerDemo.CircleIndicator;
import com.theta.animationdemo.R;

import java.util.ArrayList;
import java.util.List;

public class HideStatusbarActivity extends AppCompatActivity {

    private CoordinatorLayout coordinator;
    private AppBarLayout appbar;
    private CollapsingToolbarLayout cToolbar;
    private TextView text;
    private Toolbar toolbar;
    private CircleIndicator tabIndicator;
    private ViewPager viewPager;
    private ImageView img1;
    private ImageView img2;

    CollapsingToolbarLayout.LayoutParams layoutParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hide_statusbar);

        // todo : This will hide the status bar. It will no more visible if user scroll on the status bar it will visible
        //  Hiding Android Status Bar Using Theme Setting
         /*<!-- Base application theme. -->
         <style name="AppTheme" parent="@android:style/Theme.Holo.NoActionBar.Fullscreen">*/
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        init();
    }

    private void init() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        coordinator = findViewById(R.id.coordinator);
        appbar = findViewById(R.id.appbar);
        cToolbar = findViewById(R.id.cToolbar);
        text = findViewById(R.id.text);

        tabIndicator = findViewById(R.id.tabIndicator);
        viewPager = findViewById(R.id.viewPager);

        layoutParams = (CollapsingToolbarLayout.LayoutParams) toolbar.getLayoutParams();


        img1 = findViewById(R.id.img1);
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HideStatusbarActivity.this, "Motion", Toast.LENGTH_SHORT).show();
            }
        });

        img2 = findViewById(R.id.img2);
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HideStatusbarActivity.this, "Sparkle", Toast.LENGTH_SHORT).show();
            }
        });

        setupViewPager(viewPager);
        tabIndicator.setViewPager(viewPager);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.sunset);
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(@NonNull Palette palette) {
                cToolbar.setStatusBarScrimColor(ContextCompat.getColor(HideStatusbarActivity.this, R.color.colorAccent));
                cToolbar.setContentScrimColor(ContextCompat.getColor(HideStatusbarActivity.this, R.color.colorAccent));
            }
        });

        appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            int scrollRange = -1;
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                cToolbar.setTitle(" "); // not to show title
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                } else {
                    cToolbar.setBackgroundColor(ContextCompat.getColor(HideStatusbarActivity.this, R.color.colorAccent));
                    cToolbar.setStatusBarScrimColor(ContextCompat.getColor(HideStatusbarActivity.this, R.color.colorAccent));
                    cToolbar.setContentScrimColor(ContextCompat.getColor(HideStatusbarActivity.this, R.color.colorAccent));
                }
            }
        });
    }

    private int getBottomMargin() {

        return getResources().getDimensionPixelSize(R.dimen.app_bar_logo_bottom_margin);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Fragment1(), "");
        adapter.addFragment(new Fragment2(), "");
        adapter.addFragment(new Fragment1(), "");
        adapter.addFragment(new Fragment2(), "");
        viewPager.setAdapter(adapter);
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

}
