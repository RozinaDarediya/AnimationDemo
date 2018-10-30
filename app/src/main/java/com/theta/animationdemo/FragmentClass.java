package com.theta.animationdemo;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by ashish on 25/4/18.
 */

public class FragmentClass extends android.support.v4.app.Fragment {

    // Activity request codes
    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;
    private static final int CAMERA_CAPTURE_VIDEO_REQUEST_CODE = 200;
    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;

    // directory name to store captured images and videos
    private static final String IMAGE_DIRECTORY_NAME = "Hello Camera";


    private Button btn;
    private ImageView img;
    Uri mCapturedImageURI;
    private Uri fileUri; // file url to store image/video

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment, container, false);
        btn = view.findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.TITLE, "ImageFilename");
                mCapturedImageURI = getActivity().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                Intent intentPicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intentPicture.putExtra(MediaStore.EXTRA_OUTPUT, mCapturedImageURI);
                intentPicture.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                if (intentPicture.resolveActivity(getActivity().getApplicationContext().getPackageManager()) != null) {
                    startActivityForResult(intentPicture, CAMERA_CAPTURE_IMAGE_REQUEST_CODE);
                }
            }
        });

        img = view.findViewById(R.id.img);

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_CAPTURE_IMAGE_REQUEST_CODE) {

            Uri uri =mCapturedImageURI;

            Glide.with(this).load(uri)
                    .error(R.drawable.bg_gray).into(img);
        }
    }

}
