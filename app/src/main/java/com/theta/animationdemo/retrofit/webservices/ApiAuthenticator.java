package com.theta.animationdemo.retrofit.webservices;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.theta.animationdemo.MainActivity;
import com.theta.animationdemo.ParallaxViewPagerDemo.Log;
import com.theta.animationdemo.retrofit.model.VerifyUser;

import java.io.IOException;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by ashish on 2/5/18.
 * To check Authentication
 */

public class ApiAuthenticator extends IntentService {


    public ApiAuthenticator() {
        super("ApiAuthenticator");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        final String TXT_PLAIN = "text/plain";
        RequestBody grant_type = RequestBody.create(okhttp3.MediaType.parse(TXT_PLAIN), "password");
        RequestBody username = RequestBody.create(okhttp3.MediaType.parse(TXT_PLAIN), "9722577596");
        RequestBody password = RequestBody.create(okhttp3.MediaType.parse(TXT_PLAIN), "123456");

        Call<VerifyUser> verifyUserCall = apiInterface.verifyUserCall(grant_type, username, password);
        try {
            Response<VerifyUser> result = verifyUserCall.execute();
            Log.e(result.toString());
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(e.toString());
            Intent intent1 = new Intent(this, MainActivity.class);
            startActivity(intent1);
        }


/*

        verifyUserCall.enqueue(new Callback<VerifyUser>() {
            @Override
            public void onResponse(Call<VerifyUser> call, Response<VerifyUser> response) {
                if (response.code() == 200)
                    Log.e(response.body().toString());
            }

            @Override
            public void onFailure(Call<VerifyUser> call, Throwable t) {
                Log.e(t.getMessage());
                Intent intent = new Intent(context, MainActivity.class);
                context.startActivity(intent);
            }
        });
*/

    }
}
/*
public class ApiAuthenticator {
 static Context context;

    public static String verifyUser(String apiURL, Context context1) {
        context = context1;
        String url = VerifyUser(apiURL);

        return url;
    }

    public static String VerifyUser(String apiURL) {

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        final String TXT_PLAIN = "text/plain";
        RequestBody grant_type = RequestBody.create(okhttp3.MediaType.parse(TXT_PLAIN), "password");
        RequestBody username = RequestBody.create(okhttp3.MediaType.parse(TXT_PLAIN), "9722577596");
        RequestBody password = RequestBody.create(okhttp3.MediaType.parse(TXT_PLAIN), "123456");

        Call<VerifyUser> verifyUserCall = apiInterface.verifyUserCall(grant_type, username, password);



        verifyUserCall.enqueue(new Callback<VerifyUser>() {
            @Override
            public void onResponse(Call<VerifyUser> call, Response<VerifyUser> response) {
                if (response.code() == 200)
                    Log.e(response.body().toString());
            }

            @Override
            public void onFailure(Call<VerifyUser> call, Throwable t) {
                Log.e(t.getMessage());
                Intent intent = new Intent(context, MainActivity.class);
                context.startActivity(intent);
            }
        });

        return apiURL;
    }
}
 */