package com.theta.animationdemo;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.theta.animationdemo.retrofit.webservices.OnApiCallListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button fab_button;
    Button btnTransitionsAnimation;
    Button btnRevealEffect;
    Button btnRecyclerviewAnimation;
    Button btnExpandable;
    Button btnRecyclerviewCardview;
    Button btnCircleList;
    Button btnTransitionAnimation;
    Button btnViewPagerAnimation;
    Button btnAndroidDemo;
    Animation animation;
    TextView relativeView;

    private OnApiCallListener acListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        animation = AnimationUtils.loadAnimation(this, R.anim.alpha_animation);
        btnAndroidDemo.startAnimation(animation);
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(Html.fromHtml(getString(R.string.register_terms3)));

        fab_button.setOnClickListener(this);
        btnAndroidDemo.setOnClickListener(this);
        relativeView.setOnClickListener(this);
    }

    private void init() {
        fab_button = findViewById(R.id.fab_button);
        btnTransitionsAnimation = (Button) findViewById(R.id.btnTransitionsAnimation);
        btnRevealEffect = (Button) findViewById(R.id.btnRevealEffect);
        btnRecyclerviewAnimation = (Button) findViewById(R.id.btnRecyclerviewAnimation);
        btnExpandable = (Button) findViewById(R.id.btnExpandable);
        btnRecyclerviewCardview = (Button) findViewById(R.id.btnRecyclerviewCardview);
        btnCircleList = (Button) findViewById(R.id.btnCircleList);
        btnTransitionAnimation = (Button) findViewById(R.id.btnTransitionAnimation);
        btnViewPagerAnimation = (Button) findViewById(R.id.btnViewPagerAnimation);
        btnAndroidDemo = (Button) findViewById(R.id.btnAndroidDemo);
        relativeView = (TextView) findViewById(R.id.relativeView);

        relativeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*"); //allows any image file type. Change * to specific extension to limit it
                //**These following line is the important one!
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 100); //SELECT_PICTURES is simply a global int used to check the calling intent in onActivityResult
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            if (resultCode == Activity.RESULT_OK) {
                if (data.getClipData() != null) {
                    int count = data.getClipData().getItemCount(); //evaluate the count before the for loop --- otherwise, the count is evaluated every loop.
                    ArrayList<Uri> uriArrayList = new ArrayList<>();
                    for (int i = 0; i < count; i++) {
                        Uri imageUri = data.getClipData().getItemAt(i).getUri();
                        uriArrayList.add(imageUri);
                        Log.e("image uri : ", imageUri.toString());
                    }
                    Log.e("image uri list : ", uriArrayList.toString());
                    //do something with the image (save it to some directory or whatever you need to do with it here)
                }
            } else if (data.getData() != null) {
                String imagePath = data.getData().getPath();
                Log.e("image uri : ", imagePath);
                //do something with the image (save it to some directory or whatever you need to do with it here)
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.fab_button) {

            Intent intent = new Intent(MainActivity.this, Main2Activity.class);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                final ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(
                        this,
                        new Pair[]{Pair.create(btnTransitionsAnimation, ViewCompat.getTransitionName(btnTransitionsAnimation)),
                                Pair.create(btnRevealEffect, ViewCompat.getTransitionName(btnRevealEffect)),
                                Pair.create(btnRecyclerviewAnimation, ViewCompat.getTransitionName(btnRecyclerviewAnimation)),
                                Pair.create(btnExpandable, ViewCompat.getTransitionName(btnExpandable)),
                                Pair.create(btnRecyclerviewCardview, ViewCompat.getTransitionName(btnRecyclerviewCardview)),
                                Pair.create(btnCircleList, ViewCompat.getTransitionName(btnCircleList)),
                                Pair.create(btnTransitionAnimation, ViewCompat.getTransitionName(btnTransitionAnimation)),
                                Pair.create(btnViewPagerAnimation, ViewCompat.getTransitionName(btnViewPagerAnimation)),
                                Pair.create(fab_button, ViewCompat.getTransitionName(fab_button)),});

                startActivity(intent, options.toBundle());

            } else {
                startActivity(intent);
            }
        }
        if ((view.getId() == R.id.btnAndroidDemo)) {
            Intent intent = new Intent(this, AndroidDemoActivity.class);
            startActivity(intent);
        }
        if ((view.getId() == R.id.relativeView)) {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*"); //allows any image file type. Change * to specific extension to limit it
            //**These following line is the important one!
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent,"Select Picture"),  100); //SELECT_PICTURES is simply a global int used to check the calling intent in onActivityResult

        }
    }
}
