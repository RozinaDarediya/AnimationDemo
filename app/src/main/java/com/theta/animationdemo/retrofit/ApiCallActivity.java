package com.theta.animationdemo.retrofit;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.theta.animationdemo.ParallaxViewPagerDemo.Log;
import com.theta.animationdemo.R;
import com.theta.animationdemo.retrofit.global.AppDialog;
import com.theta.animationdemo.retrofit.global.Global;
import com.theta.animationdemo.retrofit.model.DataItemInstruction;
import com.theta.animationdemo.retrofit.model.DataItemStore;
import com.theta.animationdemo.retrofit.model.GetInstruction;
import com.theta.animationdemo.retrofit.model.MlsDataItem;
import com.theta.animationdemo.retrofit.model.MlsResponse;
import com.theta.animationdemo.retrofit.model.StoreModel;
import com.theta.animationdemo.retrofit.model.StoreModelRequest;
import com.theta.animationdemo.retrofit.model.UpdateProfile;
import com.theta.animationdemo.retrofit.model.VerifyUser;
import com.theta.animationdemo.retrofit.service.NetworkChangeReceiver;
import com.theta.animationdemo.retrofit.webservices.Api;
import com.theta.animationdemo.retrofit.webservices.ApiCalls;
import com.theta.animationdemo.retrofit.webservices.ApiClient;
import com.theta.animationdemo.retrofit.webservices.ApiInterface;
import com.theta.animationdemo.retrofit.webservices.OnApiCallListener;

import java.io.File;
import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.theta.animationdemo.retrofit.webservices.Api.ActionGetInstruction;
import static com.theta.animationdemo.retrofit.webservices.Api.ActionGetStore;
import static com.theta.animationdemo.retrofit.webservices.Api.BASE_URL;
import static com.theta.animationdemo.retrofit.webservices.Api.BASE_URL_PAGINATION;
import static com.theta.animationdemo.retrofit.webservices.Api.updateProfile;

public class ApiCallActivity extends AppCompatActivity implements OnApiCallListener, View.OnClickListener {

    private ApiInterface apiInterface;
    private StoreModelRequest storeModelRequest;
    private Call<StoreModel> callStoreModel;
    private Call<GetInstruction> getInstructionCall;

    private Button btnStoreApi;
    private Button btnInstructionApi;
    private Button btnUpdateProfileApi;
    private Button btnPaginationApi;
    private Button btnUploadProfileMultipart;
    private TextView textView;

    private ProgressBar main_progress;
    private RecyclerView recyclerView;

    private LinearLayoutManager linearLayoutManager;
    private PaginationAdapter adapter;
    private static final int PAGE_START = 1;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    // limiting to 5 for this tutorial, since total pages in actual API is very large. Feel free to modify.
    private int TOTAL_PAGES;
    private int currentPage = PAGE_START;

    private IntentFilter intentFilter;
    private NetworkChangeReceiver networkChangeReceiver;
    private ApiCalls apiCalls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api_call);

        //init();

        // TODO: 2/5/18 api call function to upload image from mobile image name = Pictures/1524721187247.jpg
        //uploadImage();

        // TODO: 2/5/18 make api call to get store details and on its success call instruction api
         makeApiCall();

        // TODO: 2/5/18 API call with structure
       /* List<Observable<?>> request = new ArrayList<>();
        request.add(apiInterface.getStoreModel1(storeModelRequest));

        try{


        Observable.zip(request, new Function<Object[], Object>() {
            @Override
            public Object apply(Object[] objects)throws Exception {
                // Objects[] is an array of combined results of completed requests

                // do something with those results and emit new event
                return new Object();
            }
        })
                // After all requests had been performed the next observer will receive the Object, returned from Function
                .subscribe(
                        // Will be triggered if all requests will end successfully (4xx and 5xx also are successful requests too)
                        new Consumer<Object>() {
                            @Override
                            public void accept(Object o) {
                                //Do something on successful completion of all requests
                            }
                        },

                        // Will be triggered if any error during requests will happen
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable e) {
                                //Do something on error completion of requests
                            }
                        }
                );
        }catch (Exception e){

        }*/

       /*
       todo : asynchronously
       Call<StoreModel> c = apiInterface.getStoreModel1(storeModelRequest);
        try {
            c.execute(new Callback<>() {
                @Override
                public void onResponse(Call call, Response response) {

                }

                @Override
                public void onFailure(Call call, Throwable t) {

                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }*/


    }

    private void init() {
        textView = findViewById(R.id.textView);
        btnStoreApi = findViewById(R.id.btnStoreApi);
        btnInstructionApi = findViewById(R.id.btnInstructionApi);
        btnUpdateProfileApi = findViewById(R.id.btnUpdateProfileApi);
        btnPaginationApi = findViewById(R.id.btnPaginationApi);
        btnUploadProfileMultipart = findViewById(R.id.btnUploadProfileMultipart);

        recyclerView = findViewById(R.id.recyclerView);
        main_progress = findViewById(R.id.main_progress);

        btnStoreApi.setOnClickListener(this);
        btnInstructionApi.setOnClickListener(this);
        btnUpdateProfileApi.setOnClickListener(this);
        btnPaginationApi.setOnClickListener(this);
        btnUploadProfileMultipart.setOnClickListener(this);

        //apiClient =new ApiClient(this,this);

        // TODO: 2/5/18 create client for retrofit
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        apiCalls = new ApiCalls(this, this);

        // TODO: 2/5/18 prepare model class to make a api call
        storeModelRequest = new StoreModelRequest(15, 23.0182677, 72.5297329);

        adapter = new PaginationAdapter(this);

        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        recyclerView.addOnScrollListener(new PaginationScrollListener(linearLayoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                currentPage += 1;

                // mocking network delay for API call
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadNextPage();
                    }
                }, 1000);
            }

            @Override
            public int getTotalPageCount() {
                return TOTAL_PAGES;
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });
    }

    private void loadNextPage() {
        if (Global.isNetworkAvailable(this)) {
            // TODO: 2/5/18 show progress dialog while api call is made
            AppDialog.showProgressDialog(this);
            apiCalls.callMlsApi(apiInterface, currentPage);
        } else {
            // TODO: 2/5/18 if internet is not on then request the broadcast to notify the application when the network state is change and internet is truned on...
            AppDialog.noNetworkDialog(this, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    // request broadcast
                    //Intent intent = new Intent("com.journaldev.broadcastreceiver.SOME_ACTION");
                    //sendBroadcast(intent);
                }
            });
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnStoreApi:
                makeApi(BASE_URL + ActionGetStore);
                break;
            case R.id.btnInstructionApi:
                makeApi(BASE_URL + ActionGetInstruction);
                break;
            case R.id.btnUpdateProfileApi:
                makeApi(BASE_URL + updateProfile);
                break;
            case R.id.btnPaginationApi:
                makeApi(BASE_URL_PAGINATION);
                break;
            case R.id.btnUploadProfileMultipart:
                makeApi("uploadFileMultipart");
                break;
        }
    }

    private void makeApi(String api) {

        if (Global.isNetworkAvailable(this)) {

            // TODO: 2/5/18 show progress dialog while api call is made
            AppDialog.showProgressDialog(this);

            if (api.equals(BASE_URL + ActionGetStore))
                apiCalls.getStoreModel(apiInterface, storeModelRequest);
            else if (api.equals(BASE_URL + ActionGetInstruction))
                apiCalls.getInstructionData(apiInterface);
            else if (api.equals(BASE_URL + updateProfile))
                apiCalls.uploadImage(apiInterface);
            else if (api.equals(BASE_URL_PAGINATION))
                apiCalls.callMlsApi(apiInterface, currentPage);
            else if (api.equals("uploadFileMultipart"))
                apiCalls.uploadFileMultipart(apiInterface);


        } else {
            // TODO: 2/5/18 if internet is not on then request the broadcast to notify the application when the network state is change and internet is truned on...
            AppDialog.noNetworkDialog(this, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    // request broadcast
                    //Intent intent = new Intent("com.journaldev.broadcastreceiver.SOME_ACTION");
                    //sendBroadcast(intent);
                }
            });
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        // initialize the receiver which handles the network state changes
        networkChangeReceiver = new NetworkChangeReceiver();
        intentFilter = new IntentFilter();
        intentFilter.addAction(getPackageName() + "android.net.conn.CONNECTIVITY_CHANGE");

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("internetState");

        // handles the response of receiver. Here it will toast the message if the internet is turned on and receiver method is hit
        networkChangeReceiver = new NetworkChangeReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                //super.onReceive(context, intent);
                if (intent != null) {
                    String a = String.valueOf(intent.getStringExtra("state"));
                    Toast.makeText(context, a, Toast.LENGTH_LONG).show();
                }
            }
        };
        // register the receiver which handles the network state changes
        registerReceiver(networkChangeReceiver, intentFilter);
    }


    private void uploadImage() {
        //your file location
        File fileimg = new File(Environment.getExternalStorageDirectory() + "/Pictures/1524721187247.jpg");
        final String IMG_JPEG = "image/jpeg";
        final String TXT_PLAIN = "text/plain";

        RequestBody fileBody;
        RequestBody fname;
        RequestBody lname;
        RequestBody emialid;
        File file = new File(fileimg.getPath());
        Call<UpdateProfile> requestCall;

        fileBody = RequestBody.create(okhttp3.MediaType.parse(IMG_JPEG), file);
        fname = RequestBody.create(okhttp3.MediaType.parse(TXT_PLAIN), "abc");
        lname = RequestBody.create(okhttp3.MediaType.parse(TXT_PLAIN), "abc");
        emialid = RequestBody.create(okhttp3.MediaType.parse(TXT_PLAIN), "abc@abc.com");

        /*requestCall = apiInterface.Upload(fileBody, fname, lname, emialid);
        requestCall.enqueue(new Callback<UpdateProfile>() {
            @Override
            public void onResponse(Call<UpdateProfile> call, Response<UpdateProfile> response) {
                Log.e("update profile : " + response.body().toString());
                String s = new Gson().toJson(response.body()); // whole response
                textView.setText(s);
            }
            @Override
            public void onFailure(Call<UpdateProfile> call, Throwable t) {
                Log.e("update profile : " + t.toString());
            }
        });*/

    }

    private void makeApiCall() {
        if (Global.isNetworkAvailable(this)) {

            // TODO: 2/5/18 show progress dialog while api call is made
            AppDialog.showProgressDialog(this);

            apiInterface = ApiClient.getClient().create(ApiInterface.class);
            storeModelRequest = new StoreModelRequest(15, 23.0182677, 72.5297329);
            // TODO: 2/5/18 api call to get store information and its call back
            callStoreModel = apiInterface.getStoreModel(storeModelRequest); // making api request
            callStoreModel.enqueue(new Callback<StoreModel>() {    // calling api
                @Override
                public void onResponse(Call<StoreModel> call, Response<StoreModel> response) {

                    // TODO: 2/5/18 stop progress dialog as we have got the response
                    AppDialog.dismissProgressDialog();

                    Log.e("call store model");
                    String s = new Gson().toJson(response.body()); // whole response
                    Log.e(s);
                    //textView.setText(s);

                    String a = String.valueOf(call.request().url());

                    // checked whether we have response of the requested api or not
                    if (a.equals(BASE_URL + ActionGetStore)) {
                        if (response.code() == 200 && response.isSuccessful()) {

                            Log.e("response : " + response);

                            // No need to create object of Store model because we already have the response in that model.
                            //StoreModel storeModel = new StoreModel();

                            List<DataItemStore> dataItems = response.body().getData();
                            // storeModel.setData(dataItems);
                            Log.e(" data size : " + dataItems.size() + " data : " + dataItems);

                            // TODO: 2/5/18 show progress dialog while api call is made
                            AppDialog.showProgressDialog(ApiCallActivity.this);
                            // TODO: 2/5/18 api call for instruction details and its call back
                            getInstructionCall = apiInterface.getInstructionModel();
                            getInstructionCall.enqueue(new Callback<GetInstruction>() {
                                @Override
                                public void onResponse(Call<GetInstruction> call, Response<GetInstruction> response) {

                                    AppDialog.dismissProgressDialog();
                                    Log.e("call get instruction");

                                    String s = new Gson().toJson(response.body()); // whole response
                                    textView.setText(s);
                                    Log.e(s.toString());

                                    String a = String.valueOf(call.request().url());
                                    if (a.equals(BASE_URL + Api.ActionGetInstruction)) {
                                        if (response.code() == 200 && response.isSuccessful()) {
                                            List<DataItemInstruction> instructionsList = response.body().getData();
                                            Log.e("instructionsList : " + instructionsList);
                                        }
                                    }
                                }

                                @Override
                                public void onFailure(Call<GetInstruction> call, Throwable t) {
                                    AppDialog.dismissProgressDialog();
                                    Log.e("error : " + t.toString());
                                }
                            });
                        }
                    }
                }

                @Override
                public void onFailure(Call<StoreModel> call, Throwable t) {
                    AppDialog.dismissProgressDialog();
                    Log.e("error : " + t.toString());
                }
            });
        } else {
            // TODO: 2/5/18 if internet is not on then request the broadcast to notify the application when the network state is change and internet is truned on...
            AppDialog.noNetworkDialog(this, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    dialogInterface.dismiss();

                    // request broadcast
                    Intent intent = new Intent("com.journaldev.broadcastreceiver.SOME_ACTION");
                    sendBroadcast(intent);
                }
            });
        }
    }


    @Override
    protected void onStop() {
        super.onStop();
        // unregister the receiver which handles the network state changes
        unregisterReceiver(networkChangeReceiver);
    }

    @Override
    public void onSuccess(Call call, Response response) {
        textView.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);

        AppDialog.dismissProgressDialog();
        String stringData = new Gson().toJson(response.body()); // whole response

        String a = String.valueOf(call.request().url());
        if (a.contains("%22"))
            a = a.replace("%22", "\"");
        String b = BASE_URL_PAGINATION + "getL4DataFromL3?id=5805a572f8c5e83a5113ccb8&userId=mohanlalsons2016@gmail.com&brand=58049247f8c5e8346a3ac387&size=&price=&color=&offer=&sort_type=\"relevance\"&page=" + 1;

        // TODO: 4/5/18 GetStore Api Response
        if (String.valueOf(call.request().url()).equals(BASE_URL + ActionGetStore)) {
            StoreModel storeModel = new Gson().fromJson(stringData, StoreModel.class);
            textView.setText("storeModel data : " + stringData);
            Log.e("call store model storeModel data : " + stringData);
        }
        // TODO: 4/5/18 GetInstruction Api Response
        else if (String.valueOf(call.request().url()).equals(BASE_URL + ActionGetInstruction)) {
            GetInstruction getInstruction = new Gson().fromJson(stringData, GetInstruction.class);
            textView.setText("getInstruction data : " + stringData);
            Log.e("call store model getInstruction data : " + stringData);
        }
        // TODO: 4/5/18 updateProfile Api Response
        else if (String.valueOf(call.request().url()).equals(BASE_URL + updateProfile)) {
            if (response.code() != 200) {
                Log.e(String.valueOf(response.code()) + " from response ");

                // TODO: 4/5/18 this will start a service which calls the authentication api but i have not used this approch
              /*  Intent intent = new Intent(ApiCallActivity.this, ApiAuthenticator.class);
                startService(intent);*/

                Global.MyAsyncTask1 myAsyncTask1 = new Global.MyAsyncTask1(BASE_URL + updateProfile, new Global.MyAsyncTask1.OnResultReceived() {
                    @Override
                    public void onGetResult(Response result) {
                        VerifyUser verifyUser = (VerifyUser) result.body();
                        makeApi(BASE_URL + updateProfile);
                    }
                });
                myAsyncTask1.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

            } else {
                UpdateProfile Upload1 = new Gson().fromJson(stringData, UpdateProfile.class);
                textView.setText("updateProfile data : " + stringData);
                Log.e("call updateProfile data : " + stringData);
            }
        }
        // TODO: 4/5/18 pagination api response
        else if (a.equals(BASE_URL_PAGINATION + "getL4DataFromL3?id=5805a572f8c5e83a5113ccb8&userId=mohanlalsons2016@gmail.com&brand=58049247f8c5e8346a3ac387&size=&price=&color=&offer=&sort_type=\"relevance\"&page=" + currentPage)) {
            if (response.code() == 200) {
                MlsResponse mlsResponse = new Gson().fromJson(stringData, MlsResponse.class);
                //textView.setText("pagination api data : " + stringData);
                Log.e("call pagination api data : " + stringData);
                passDataInAdapter(mlsResponse);
            }
        }
    }

    private void passDataInAdapter(MlsResponse mlsResponse) {
        textView.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);

        List<MlsDataItem> mlsDataItemList = mlsResponse.getData();
        if (currentPage == 1) {
            TOTAL_PAGES = mlsResponse.getTotalPages();

            main_progress.setVisibility(View.GONE);
            adapter.addAll(mlsDataItemList);

            if (currentPage <= TOTAL_PAGES)
                adapter.addLoadingFooter();
            else isLastPage = true;
        } else {
            adapter.removeLoadingFooter();
            isLoading = false;

            adapter.addAll(mlsDataItemList);

            if (currentPage != TOTAL_PAGES)
                adapter.addLoadingFooter();
            else isLastPage = true;
        }
    }

    @Override
    public void onFailure(Call call, Throwable t) {
        AppDialog.dismissProgressDialog();
        Log.e(t.getMessage());
        textView.setText(t.getMessage());
    }


}