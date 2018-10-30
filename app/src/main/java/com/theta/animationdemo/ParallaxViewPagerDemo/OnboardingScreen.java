package com.theta.animationdemo.ParallaxViewPagerDemo;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.theta.animationdemo.R;

/**
 * Created by ashish on 19/4/18.
 */

public class OnboardingScreen extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.onboarding_screen, container, false);
        return view;
    }
}
