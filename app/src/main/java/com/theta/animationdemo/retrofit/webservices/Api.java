package com.theta.animationdemo.retrofit.webservices;

/**
 * Created by ashish on 26/4/18.
 */

public class Api {

    public static final String imageFolderName = "Image";

    //Dump Condition
    public static final int ConnectionTimeout = 60000; // = 60 seconds
    public static final int ConnectionSoTimeout = 60000; // = 60 seconds

    public static String auth_key1 = "";

    // App URL
    public static final String BASE_URL_VALIDATION = "http://13.126.18.81:8081";

    public static final String AUTH_BASE_URL = "//13.126.18.81:8081/oauth-server/oauth/token";
    public static final String AUTH_DYNAMIC_URL = "http://13.126.18.81:8081/oauth-server/oauth/token";

    public static final String BASE_URL = "http://13.126.18.81:8080";

    public static final String BASE_URL_PAGINATION = "http://52.77.110.37/TaleSpin_v1/rest/";

    public static final String ActionGetStore = "/getStore";

    public static final String ActionGetInstruction = "/getInstruction";

    public static final String updateProfile = "/user/updateProfile";

    public static final String validateUser= "/oauth-server/oauth/token";
}
