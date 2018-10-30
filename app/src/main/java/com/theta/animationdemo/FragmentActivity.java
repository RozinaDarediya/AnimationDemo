package com.theta.animationdemo;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class FragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

      /*  Fragment fragment = new FragmentClass();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment, fragment, fragment.getClass().getSimpleName()).addToBackStack(null).commit();*/

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        FragmentClass fragment1 = new FragmentClass();
        fragmentTransaction.add(R.id.fragment, fragment1, "fragment");
        fragmentTransaction.commit();




    }
}
