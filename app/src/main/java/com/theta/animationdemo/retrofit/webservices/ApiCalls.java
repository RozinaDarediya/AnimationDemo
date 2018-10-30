package com.theta.animationdemo.retrofit.webservices;

import android.content.Context;
import android.net.Uri;
import android.os.Environment;

import com.theta.animationdemo.retrofit.model.GetInstruction;
import com.theta.animationdemo.retrofit.model.MlsResponse;
import com.theta.animationdemo.retrofit.model.StoreModel;
import com.theta.animationdemo.retrofit.model.StoreModelRequest;
import com.theta.animationdemo.retrofit.model.UpdateProfile;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Dispatcher;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.theta.animationdemo.retrofit.webservices.Api.BASE_URL_PAGINATION;
import static com.theta.animationdemo.retrofit.webservices.Api.auth_key1;

/**
 * Created by ashish on 2/5/18.
 */

public class ApiCalls implements Serializable {

    private Context context;
    private OnApiCallListener acListener;

    public ApiCalls(Context context, OnApiCallListener acListener) {
        this.context = context;
        this.acListener = acListener;
    }

    public void execute(Call call) {
        call.clone().enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {

                /*String url = String.valueOf(call.request().url());
                int responseCode = response.code();
                String responseString = response.body().toString();
                acListener.onSuccess(responseCode, responseString, url);*/
                acListener.onSuccess(call, response);
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                // acListener.onFailure(t.getMessage());
                acListener.onFailure(call, t);
            }
        });
    }

    public void getStoreModel(ApiInterface apiInterface, StoreModelRequest storeModelRequest) {
        Call<StoreModel> callStoreModel1 = apiInterface.getStoreModel(storeModelRequest); // making api request
        execute(callStoreModel1);
    }

    public void getInstructionData(ApiInterface apiInterface) {
        Call<GetInstruction> getInstructionCall = apiInterface.getInstructionModel();
        execute(getInstructionCall);
    }

    public void uploadImage(ApiInterface apiInterface) {
        File fileimg = new File(Environment.getExternalStorageDirectory() + "/Pictures/1524721187247.jpg");
        final String IMG_JPEG = "image/jpeg";
        final String TXT_PLAIN = "text/plain";

        RequestBody fileBody;
        RequestBody fname;
        RequestBody lname;
        RequestBody emialid;
        File file = new File(fileimg.getPath());
        Call<UpdateProfile> uploadImage;

        fileBody = RequestBody.create(okhttp3.MediaType.parse(IMG_JPEG), file);
        fname = RequestBody.create(okhttp3.MediaType.parse(TXT_PLAIN), "abc");
        lname = RequestBody.create(okhttp3.MediaType.parse(TXT_PLAIN), "abc");
        emialid = RequestBody.create(okhttp3.MediaType.parse(TXT_PLAIN), "abc@abc.com");

        String token = "bearer " + auth_key1;

        uploadImage = apiInterface.Upload1(token, fileBody, fname, lname, emialid);
        //uploadImage = apiInterface.Upload( fileBody, fname, lname, emialid);
        execute(uploadImage);
    }

    public void uploadFileMultipart(ApiInterface apiInterface) {
        File fileimg = new File(Environment.getExternalStorageDirectory() + "/Pictures/1524721187247.jpg");

        // create RequestBody instance from file
        RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), fileimg);

        // MultipartBody.Part is used to send also the actual file name
        MultipartBody.Part body = MultipartBody.Part.createFormData("file", fileimg.getName(), requestFile);

        // add another part within the multipart request
        RequestBody username = RequestBody.create(okhttp3.MultipartBody.FORM, "abc");
        RequestBody lastname = RequestBody.create(okhttp3.MultipartBody.FORM, "abc");
        RequestBody emailid = RequestBody.create(okhttp3.MultipartBody.FORM, "abc@abc.com");

        // finally, execute the request
        // Call<UpdateProfile> updateProfileCall = apiInterface.updateProfileMultipart(body, username, lastname, emailid);
        //execute(updateProfileCall);

        MultipartBody.Builder multipartBody = new MultipartBody.Builder().setType(MediaType.parse("multipart/form-data; charset=utf-8"));
        final MediaType MEDIA_TYPE = MediaType.parse("image/*");
        if (fileimg.exists()) {
            multipartBody.addFormDataPart("file", fileimg.getName(), RequestBody.create(MEDIA_TYPE, fileimg));
        }
        RequestBody postData = multipartBody
                .addFormDataPart("firstName", "abc")
                .addFormDataPart("lastName", "abc")
                .addFormDataPart("emailId", "abc@abc.com").build();

        HashMap<String, RequestBody> map = new HashMap<>();
        map.put("firstName", username);
        map.put("lastName", lastname);
        map.put("emailId", emailid);

        Call<UpdateProfile> updateProfile = apiInterface.uploadFileWithPartMap(map, body);
        execute(updateProfile);
    }

    public void callMlsApi(ApiInterface apiInterface, int pageId) {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        Dispatcher dispatcher = new Dispatcher();
        dispatcher.setMaxRequests(1);
        httpClient.dispatcher(dispatcher); // Set the maximum number of requests to execute concurrently - simultaneously.

        httpClient.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request request = original.newBuilder()
                        .header("Accept", "application/json")
                        .method(original.method(), original.body())
                        .build();

                okhttp3.Response response = chain.proceed(request);

                com.theta.animationdemo.ParallaxViewPagerDemo.Log.e("Code : " + response.code());
                if (response.code() != 200) {
                    com.theta.animationdemo.ParallaxViewPagerDemo.Log.e(String.valueOf((response.code())));
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


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL_PAGINATION)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        ApiInterface apiInterface1 = retrofit.create(ApiInterface.class);

        Call<MlsResponse> mlsResponseCall = apiInterface1.callMlsApi(pageId);
        execute(mlsResponseCall);
    }

    public void uploadMutipleImage(ApiInterface apiInterface) {
        Uri file1Uri = Uri.parse(""); // get it from a file chooser or a camera intent
        Uri file2Uri = Uri.parse(""); // get it from a file chooser or a camera intent

        File file1 = new File(Environment.getExternalStorageDirectory() + "/Pictures/1524721187247.jpg");
        File file2 = new File(Environment.getExternalStorageDirectory() + "/Pictures/1524721187247.jpg");

        // create RequestBody instance from file
        RequestBody requestFile1 = RequestBody.create(MediaType.parse("image/*"), file1);
        MultipartBody.Part body1 = MultipartBody.Part.createFormData("file", file1.getName(), requestFile1);// MultipartBody.Part is used to send also the actual file name

        //RequestBody requestFile2 = RequestBody.create(MediaType.parse("image/*"), file2);
        RequestBody requestFile2 = RequestBody.create(MediaType.parse(String.valueOf(file2)), file2);
        MultipartBody.Part body2 = MultipartBody.Part.createFormData("video", file2.getName(), requestFile2);// MultipartBody.Part is used to send also the actual file name

        // add another part within the multipart request
        RequestBody username = RequestBody.create(okhttp3.MultipartBody.FORM, "abc");
        RequestBody lastname = RequestBody.create(okhttp3.MultipartBody.FORM, "abc");
        RequestBody emailid = RequestBody.create(okhttp3.MultipartBody.FORM, "abc@abc.com");

        // finally, execute the request
        Call<ResponseBody> call = apiInterface.uploadMultipleFiles(username, body1, body2);
        execute(call);
    }

    public void uploadMultipleFilesDynamic(ApiInterface apiInterface) {
        Uri photoUri = Uri.parse(""); // get it from a file chooser or a camera intent
        Uri videoUri = Uri.parse(""); // get it from a file chooser or a camera intent

        File photoFilen = new File(Environment.getExternalStorageDirectory() + "/Pictures/1524721187247.jpg");
        File videoFile2 = new File(Environment.getExternalStorageDirectory() + "/Pictures/1524721187247.jpg");

        // create list of file parts (photo, video, ...)
        List<MultipartBody.Part> parts = new ArrayList<>();
        // add dynamic amount
        if (photoFilen != null) {
            RequestBody requestFile1 = RequestBody.create(MediaType.parse("image/*"), photoFilen);
            MultipartBody.Part body1 = MultipartBody.Part.createFormData("file", photoFilen.getName(), requestFile1);// MultipartBody.Part is used to send also the actual file name
        }
        if (videoFile2 != null) {
            //RequestBody requestFile2 = RequestBody.create(MediaType.parse("image/*"), file2);
            RequestBody requestFile2 = RequestBody.create(MediaType.parse(String.valueOf(videoFile2)), videoFile2);
            MultipartBody.Part body2 = MultipartBody.Part.createFormData("video", videoFile2.getName(), requestFile2);// MultipartBody.Part is used to send also the actual file name
        }
        // add another part within the multipart request
        RequestBody username = RequestBody.create(okhttp3.MultipartBody.FORM, "abc");
        Call<ResponseBody> call = apiInterface.uploadMultipleFilesDynamic(username, parts);
        execute(call);
    }

}
 /* callStoreModel1.enqueue(new Callback<StoreModel>() {
            @Override
            public void onResponse(Call<StoreModel> call, Response<StoreModel> response) {

                String url = String.valueOf(call.request().url());
                int responseCode = response.code();
                String responseString = response.body().toString();

                acListener.onSuccess(responseCode, responseString, url);
            }

            @Override
            public void onFailure(Call<StoreModel> call, Throwable t) {
                acListener.onFailure(t.getMessage());
            }
        });*/