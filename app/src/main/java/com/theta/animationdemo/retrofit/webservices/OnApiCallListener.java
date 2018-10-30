package com.theta.animationdemo.retrofit.webservices;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by ashish on 13/10/17.
 */

public interface OnApiCallListener {

    //void onSuccess(int responseCode, String responseString, String requestType);
    //void onFailure(String errorMessage);


    void onSuccess(Call call, Response response);

    void onFailure(Call call, Throwable t);

}
