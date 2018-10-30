package com.theta.animationdemo.Dialoganimation;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.theta.animationdemo.R;

public class DialogAnimationActivity extends AppCompatActivity implements View.OnClickListener {

    PopupWindow popup_window, popup_window2;
    View popup_view, popup_view2;
    LinearLayout layout;
    LayoutInflater inflator;
    Context context;

    private Button btnAnimation;
    private Button btnPopUpWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_animation);

        btnAnimation = findViewById(R.id.btnAnimation);
        btnPopUpWindow = findViewById(R.id.btnPopUpWindow);

        layout = new LinearLayout(this);
        context = this;
        inflator = (LayoutInflater) DialogAnimationActivity.this
                .getSystemService(LAYOUT_INFLATER_SERVICE);

        btnPopUpWindow.setOnClickListener(this);
        btnAnimation.setOnClickListener(this);
    }
    private void buildDialog(int animationSource, String type) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Animation Dialog");
        builder.setMessage(type);
        builder.setNegativeButton("OK", null);
        AlertDialog dialog = builder.create();
        dialog.getWindow().getAttributes().windowAnimations = animationSource;
        dialog.show();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnPopUpWindow){
            buildDialog(R.style.PopUpAnimation, "Pop in and out...");
        }
        if (view.getId() == R.id.btnAnimation){
            //buildDialog(R.style.DialogAnimation, "Fade In - Fade Out Animation..");

            PopupWindow popup = new PopupWindow(DialogAnimationActivity.this);
            View layout = getLayoutInflater().inflate(R.layout.popup_load_bible, null);
            popup.setContentView(layout);
            // Set content width and height
            popup.setHeight(200);
            popup.setWidth(200);
            // Closes the popup window when touch outside of it - when looses focus
            popup.setOutsideTouchable(true);
            popup.setFocusable(true);
            // Show anchored to button
            popup.setBackgroundDrawable(new BitmapDrawable());
            popup.showAsDropDown(view);
        }

    }
}
