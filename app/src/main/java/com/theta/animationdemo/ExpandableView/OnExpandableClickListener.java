package com.theta.animationdemo.ExpandableView;

import android.view.View;

/**
 * Created by ashish on 11/12/17.
 */

public interface OnExpandableClickListener {
    void onExpandableClick(View sharedView, String transitionName, final int position);
}
