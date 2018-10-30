package com.theta.animationdemo.retrofit.webservices;


import com.theta.animationdemo.ParallaxViewPagerDemo.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Dispatcher;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.theta.animationdemo.retrofit.webservices.Api.BASE_URL;

/**
 * Created by ashish on 26/4/18.
 *
 * https://freakycoder.com/android-notes-58-how-to-handle-401-error-on-retrofit-112820e254b7  :: Android Notes 58 : How to handle 401 error on RetroFit
 */

public class ApiClient {

    private static Retrofit retrofit = null;

    public static Retrofit getClient() {

        if (retrofit == null) {
            // TODO: 26/4/18 retrofit object without timeout object and pure retrofit
          /*  retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();*/

          /*  // TODO: 26/4/18 retrofit object without headers using OkHttp
           OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(Api.ConnectionTimeout, TimeUnit.SECONDS)
                    .readTimeout(Api.ConnectionTimeout, TimeUnit.SECONDS)
                    .writeTimeout(Api.ConnectionTimeout, TimeUnit.SECONDS)
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();*/

            // TODO: 26/4/18 retrofit object without headers using OkHttp
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

            Dispatcher dispatcher = new Dispatcher();
            dispatcher.setMaxRequests(1);
            httpClient.dispatcher(dispatcher); // Set the maximum number of requests to execute concurrently - simultaneously.

            httpClient.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request original = chain.request();
                    Request request = original.newBuilder()
                            .header("Accept", "application/json")
                            .method(original.method(), original.body())
                            .build();

                    Response response =  chain.proceed(request);

                    Log.e("Code : "+response.code());
                    if (response.code() != 200){
                        Log.e(String.valueOf((response.code())));
                        // Magic is here ( Handle the error as your way )
                        //response = ApiAuthenticator.VerifyUser();
                        return response;
                    }
                    return response;
                }
            });

            httpClient.connectTimeout(Api.ConnectionTimeout, TimeUnit.MILLISECONDS);
            httpClient.writeTimeout(Api.ConnectionTimeout, TimeUnit.MILLISECONDS);
            httpClient.readTimeout(Api.ConnectionTimeout, TimeUnit.MILLISECONDS);
            OkHttpClient client = httpClient.build();


            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();

        }
        return retrofit;
    }
}
