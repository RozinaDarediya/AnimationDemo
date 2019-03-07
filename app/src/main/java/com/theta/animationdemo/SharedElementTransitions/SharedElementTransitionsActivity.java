package com.theta.animationdemo.SharedElementTransitions;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.theta.animationdemo.R;
import com.theta.animationdemo.global.Global;

import java.util.ArrayList;

public class SharedElementTransitionsActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnClick;
    private View reveal_view;
    private ImageView ivImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_element_transitions);

        init();


        reveal_view.post(new Runnable() {
            @Override
            public void run() {
                Global.showAnimation(reveal_view);
            }
        });
        btnClick.setOnClickListener(this);
        ivImage.setOnClickListener(this);
    }

    private void init() {
        btnClick = (Button) findViewById(R.id.btnClick);
        reveal_view = findViewById(R.id.reveal_view);
        ivImage = findViewById(R.id.ivImage);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnClick) {
            ImageView ivImage = (ImageView) findViewById(R.id.ivImage);
            Intent intent = new Intent(SharedElementTransitionsActivity.this, SharedElementTransitionsActivity2.class);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                startActivity(intent,
                        ActivityOptions.makeSceneTransitionAnimation(
                                this, new Pair<View, String>(ivImage, ViewCompat.getTransitionName(ivImage))).toBundle());
            } else {
                startActivity(intent);
            }
        }
        if (view.getId() == R.id.ivImage) {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*"); //allows any image file type. Change * to specific extension to limit it
            //**These following line is the important one!
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), 100); //SELECT_PICTURES is simply a global int used to check the calling intent in onActivityResult
        }
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
                } else if (data.getData() != null) {
                    Uri selectedImage = data.getData();
                    String imagePath = data.getData().getPath();
                    String imagePath1 = data.getData().getPath();
                    Log.e("image uri : ", selectedImage.toString());
                    //do something with the image (save it to some directory or whatever you need to do with it here)
                }
            } else if (data.getData() != null) {
                String imagePath = data.getData().getPath();
                Log.e("image uri : ", imagePath);
                //do something with the image (save it to some directory or whatever you need to do with it here)
            }
        }
    }
}
