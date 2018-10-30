package com.theta.animationdemo.parallaxAnimation;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.SwipeDismissBehavior;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.Toast;

import com.theta.animationdemo.R;

public class ParallaxAnimationActivity extends AppCompatActivity {

    private AppBarLayout appbar;
    private CollapsingToolbarLayout cToolbar;
    private ImageView header;
    private Toolbar toolbar;
    private FloatingActionButton fab;
    private CardView mCardView;
    private boolean appBarxpandable = true;

    private ImageView img;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parallaxanimation);

        getWindow().setStatusBarColor(Color.TRANSPARENT);

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        cToolbar = findViewById(R.id.cToolbar);
        cToolbar.setTitle("Parallax Slide Animation");
        appbar = findViewById(R.id.appbar);
        header = findViewById(R.id.header);
        fab = findViewById(R.id.fab);
        mCardView = (CardView) findViewById(R.id.swype_card);

        img = findViewById(R.id.img);

        final SwipeDismissBehavior<CardView> swipe
                = new SwipeDismissBehavior();
        swipe.setSwipeDirection(
                SwipeDismissBehavior.SWIPE_DIRECTION_ANY);

        swipe.setListener(
                new SwipeDismissBehavior.OnDismissListener() {
                    @Override
                    public void onDismiss(View view) {
                        Toast.makeText(ParallaxAnimationActivity.this,
                                "Card swiped !!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onDragStateChanged(int state) {
                    }
                });

        LayoutParams layoutParams = (LayoutParams) mCardView.getLayoutParams();
        //layoutParams.setBehavior(swipe);

        /*
        LayoutParams coordinatorParams =
                (LayoutParams) mCardView.getLayoutParams();

        coordinatorParams.setBehavior(swipe);
*/
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.sunset);
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(@NonNull Palette palette) {
                @SuppressLint("ResourceAsColor") int vibrantColor = palette.getVibrantColor(R.color.colorAccent);
                @SuppressLint("ResourceAsColor") int vibrantDarkColor = palette.getDarkVibrantColor(R.color.colorAccent);
                cToolbar.setContentScrimColor(vibrantColor);
                cToolbar.setStatusBarScrimColor(vibrantDarkColor);

            }
        });

        appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (Math.abs(verticalOffset) > 200) {
                    img.setVisibility(View.INVISIBLE);
                    appBarxpandable = false;
                    invalidateOptionsMenu();
                } else {
                    img.setVisibility(View.INVISIBLE);
                    appBarxpandable = true;
                    invalidateOptionsMenu();
                }
            }
        });
    }
}
