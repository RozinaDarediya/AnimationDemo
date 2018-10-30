package com.theta.animationdemo.deepLinking;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.theta.animationdemo.R;

import java.util.List;

public class DeepLinkingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deep_linking);

        String prefix;
        Intent intent = getIntent();
//        Uri data1 = (Uri) intent.getExtras().get(Intent.ACTION_VIEW);
       // Log.e("data : " + data1);
        if (intent != null && intent.getData() != null && (intent.getData().getScheme().equals("http"))) {
            Uri data = intent.getData();
            List<String> pathSegments = data.getPathSegments();
            if (pathSegments.size() > 0)
                prefix = pathSegments.get(0); // This will give you prefix as path
        }
    }
}
