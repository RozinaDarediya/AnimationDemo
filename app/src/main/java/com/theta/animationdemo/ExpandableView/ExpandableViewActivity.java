package com.theta.animationdemo.ExpandableView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.theta.animationdemo.R;

import java.util.ArrayList;

public class ExpandableViewActivity extends AppCompatActivity
        implements View.OnClickListener, OnExpandableClickListener {

    /* TextView txtLable;
     TextView txtDetail;
     TextView txtLable1;
     TextView txtDetail1;
     int i = 0;
     int j = 0;*/
    RecyclerView recyclerView;
    ExpandableAdapter expandableAdapter;
    ArrayList<Data> dataList;
    Data data = new Data();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable_view);

        init();
      /*  txtLable.setOnClickListener(this);
        txtLable1.setOnClickListener(this);*/

        for (int i = 0; i < 10; i++) {

            data.setLable(getResources().getString(R.string.txtExpandableLAble));
            data.setDetail(getResources().getString(R.string.txtDetails));
            dataList.add(data);
        }
        expandableAdapter = new ExpandableAdapter(this, dataList, this);
        recyclerView.setAdapter(expandableAdapter);
    }

    private void init() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayout.VERTICAL, false));
        dataList = new ArrayList<>();
       /* txtLable = (TextView) findViewById(R.id.txtLable);
        txtDetail = (TextView) findViewById(R.id.txtDetail);
        txtLable1 = (TextView) findViewById(R.id.txtLable1);
        txtDetail1 = (TextView) findViewById(R.id.txtDetail1);*/
    }

    @Override
    public void onClick(View view) {
       /* if (view.getId() == R.id.txtLable) {
            i++;
            if (i % 2 != 0) {
                txtDetail.setVisibility(View.VISIBLE);
                Global.showAnimation(txtDetail);
            } else {
                txtDetail.setVisibility(View.GONE);
            }
        }
        if (view.getId() == R.id.txtLable1) {
            j++;
            if (j % 2 != 0) {
                txtDetail1.setVisibility(View.VISIBLE);
                Global.showAnimation(txtDetail1);
            } else {
                txtDetail1.setVisibility(View.GONE);
            }
        }*/
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onExpandableClick(View sharedView, String transitionName, int position) {
        Intent intent = new Intent(ExpandableViewActivity.this, CurrentPosition.class);
        //  Pair<View, String> p1 = Pair.create((View)sharedView, transitionName);
       /* ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation( ExpandableViewActivity.this,
                new Pair<View, String>(sharedView, ViewCompat.getTransitionName(sharedView)));
*/
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(this,
                new android.support.v4.util.Pair<View, String>(sharedView, transitionName));

        intent.putExtra("detailData", dataList.get(position).getDetail());

        startActivity(intent, options.toBundle());
    }
}

