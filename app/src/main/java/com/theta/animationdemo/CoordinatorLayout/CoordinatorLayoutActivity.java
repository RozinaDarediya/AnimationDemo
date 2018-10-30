package com.theta.animationdemo.CoordinatorLayout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.theta.animationdemo.R;

public class CoordinatorLayoutActivity extends AppCompatActivity {

    private EditText editText1, editText2;
    private TextWatcher1 textWatcher1, textWatcher2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator_layout);

        editText1 = findViewById(R.id.editText1);

        editText2 = findViewById(R.id.editText2);

    }
}
