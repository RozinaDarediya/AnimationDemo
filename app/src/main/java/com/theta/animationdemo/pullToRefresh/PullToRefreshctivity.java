package com.theta.animationdemo.pullToRefresh;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.theta.animationdemo.R;

import java.lang.reflect.Field;

public class PullToRefreshctivity extends AppCompatActivity {

    private Handler handler;
    private SwipeRefreshLayout swipeRefreshLayout;
    private TextView text;
    private LinearLayout view;
    private ListView listview;
    private String[] listItem;
    private ArrayAdapter<String> adapter;
    private int cnt = 0;


    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_to_refreshctivity);

        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setColorSchemeColors(android.R.color.transparent);
        swipeRefreshLayout.setProgressBackgroundColorSchemeResource(android.R.color.transparent);
       // swipeRefreshLayout.setDistanceToTriggerSync(0);// in dips
        swipeRefreshLayout.setSize(0);// LARGE also can be used4
        swipeRefreshLayout.setRefreshing(false);
        //swipeRefreshLayout.setEnabled(false);

        /*swipeRefreshLayout.setProgressViewOffset(false, 0,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 0, getResources().getDisplayMetrics()));
        swipeRefreshLayout.setRefreshing(false);*/

        listview = findViewById(R.id.listview);
        text = findViewById(R.id.text);
        view = findViewById(R.id.view);
        // TODO: 21/4/18  set the custom drawable from here
        try {
            Field f = swipeRefreshLayout.getClass().getDeclaredField("mCircleView");
            f.setAccessible(true);
            ImageView img = (ImageView)f.get(swipeRefreshLayout);
            img.setImageResource(R.drawable.transperent);
            img.setVisibility(View.GONE);
            img.setBackgroundColor(Color.TRANSPARENT);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setEnabled(false);
                swipeRefreshLayout.setRefreshing(false);

                view.setVisibility(View.VISIBLE);
                getdata();
            }
        });

        swipeRefreshLayout.setOnChildScrollUpCallback(new SwipeRefreshLayout.OnChildScrollUpCallback() {
            @Override
            public boolean canChildScrollUp(SwipeRefreshLayout parent, @Nullable View child) {
                int cnt = swipeRefreshLayout.getChildCount();
                for(int i=0; i<cnt; i++) {
                    View ch = swipeRefreshLayout.getChildAt(i);
                    if(ch.getClass().getSimpleName().equals("CircleImageView")) {
                        ch.clearAnimation();
                        ch.setVisibility(View.GONE);
                        swipeRefreshLayout.setRefreshing(false);
                        ch.clearAnimation();
                        break;
                    }
                }
                return false;
            }
        });

        getdata();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, listItem);

        listview.setAdapter(adapter);

    }

    private void getdata() {
        handler = new Handler();
        if (cnt == 0) {
            cnt++;
            listItem = getResources().getStringArray(R.array.array_technology);
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItem);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    view.setVisibility(View.GONE);
                    swipeRefreshLayout.setRefreshing(false);
                    text.setText("Technology");
                    listview.setAdapter(adapter);
                }
            }, 5000);

        } else if (cnt == 1) {
            cnt++;
            listItem = getResources().getStringArray(R.array.days);
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItem);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    view.setVisibility(View.GONE);
                    swipeRefreshLayout.setRefreshing(false);
                    text.setText("Days");
                    listview.setAdapter(adapter);
                }
            }, 5000);
        } else if (cnt == 2) {
            cnt = 0;
            listItem = getResources().getStringArray(R.array.months);
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItem);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    view.setVisibility(View.GONE);
                    swipeRefreshLayout.setRefreshing(false);
                    text.setText("Months");
                    listview.setAdapter(adapter);
                }
            }, 5000);
        }
        swipeRefreshLayout.setEnabled(true);
    }
}
