package com.theta.animationdemo.RecyclerViewWithCardview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.theta.animationdemo.R;

import java.util.ArrayList;

public class RecyclerviewCardviewActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etText;
    Button btnadd;
    RecyclerView recyclerview;
    ArrayList<String> arrayList;
    RecyclerViewAdapter adapter;
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview_cardview);

        init();
        addList();
    }

    private void addList() {
        arrayList = new ArrayList<>();
        arrayList.add("Linear layout");
        arrayList.add("Relative layout");
        arrayList.add("Constarint layout");
        arrayList.add("Coordinate layout");

        adapter = new RecyclerViewAdapter(this);
    }

    private void init() {
        etText = (EditText) findViewById(R.id.etText);
        btnadd = (Button) findViewById(R.id.btnadd);
        btnadd.setOnClickListener(this);
        i = 0;
        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnadd) {
            if (i != 0)
                i++;
            adapter.add(i, etText.getText().toString());
        }
    }
}
