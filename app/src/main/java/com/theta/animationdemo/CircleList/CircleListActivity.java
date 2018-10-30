package com.theta.animationdemo.CircleList;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.theta.animationdemo.R;

public class CircleListActivity extends AppCompatActivity {

    private ListView listview;
    private int[] images = new int[]{R.drawable.p1, R.drawable.p2};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_list);

        listview = (ListView) findViewById(R.id.lv);
        listview.setAdapter(new MyAdapter());
        listview.setClipToPadding(false);
        listview.setClipChildren(false);
        listview.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                for (int i = 0; i < listview.getChildCount(); i++) {
                    listview.getChildAt(i).invalidate();
                }
            }
        });
    }


    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 9;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            MatrixView m = (MatrixView) LayoutInflater.from(CircleListActivity.this).inflate(R.layout.view_list_item, null);
            m.setParentHeight(listview.getHeight());
            convertView = m;
            ImageView imageView = (ImageView) convertView.findViewById(R.id.image);
            imageView.setImageResource(images[position % images.length]);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getApplicationContext(), "item : " + position, Toast.LENGTH_LONG).show();
                }
            });
            return convertView;
        }
    }
}

