package com.theta.animationdemo.retrofit.global;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.theta.animationdemo.R;
import com.theta.animationdemo.retrofit.model.VerifyUser;
import com.theta.animationdemo.retrofit.webservices.ApiClient;
import com.theta.animationdemo.retrofit.webservices.ApiInterface;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;

import static android.Manifest.permission.READ_PHONE_STATE;
import static com.theta.animationdemo.retrofit.webservices.Api.AUTH_DYNAMIC_URL;
import static com.theta.animationdemo.retrofit.webservices.Api.auth_key1;
import static com.theta.animationdemo.retrofit.webservices.Api.imageFolderName;

/**
 * Created by ashish on 13/10/17.
 */

public class Global {

    private static final String JPEG_FILE_PREFIX = "IMG_";
    private static final String JPEG_FILE_SUFFIX = ".jpg";

    // Function to check Internet Connectivity
    public static synchronized boolean isNetworkAvailable(Context context) {
        boolean isConnected = false;
        if (context != null) {
            ConnectivityManager cm =
                    (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            isConnected = activeNetwork != null &&
                    activeNetwork.isConnectedOrConnecting();
        }

        return isConnected;
    }//isNetworkAvailable

    public static boolean checkPhoneStatePermission(final Context context) {
        int permissionCheck = ContextCompat.checkSelfPermission(context, READ_PHONE_STATE);

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) context, new String[]{READ_PHONE_STATE}, 123);
/*
            telephonyManager = (TelephonyManager) getSystemService(Context.
                    TELEPHONY_SERVICE);

            Constants.deviceId = telephonyManager.getDeviceId();*/
            return false;
        }
        return true;

    }

    //get key from value
    public static Object getKeyFromValue(Map hm, Object value) {
        for (Object o : hm.keySet()) {
            if (hm.get(o).equals(value)) {
                return o;
            }
        }
        return null;
    }

    // transiction moving to next activity
    public static void activityTransition(Activity activity) {
        activity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }


    // Function to hide keyboard
    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        }
    }

    //hideSoftKeyboard
    public static void hideSoftKeyboard1(Activity activity) {
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager inputManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
       /* activity.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);*/
        /*InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);*/
    }

    //getFileName
    public static String getFileName(String filePath) {
        try {
            if (!TextUtils.isEmpty(filePath)) {
                File file = new File(filePath);
                Log.v("Parent path : ", "Parent path : " + file.getParent());
                return file.getName();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    //getFileParent
    public static String getFileParent(String filePath) {
        try {
            if (!TextUtils.isEmpty(filePath)) {
                File file = new File(filePath);
                Log.v("Parent Path", "Parent Path " + file.getParent());
                return file.getParent();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    //getCommaSeperatedValues
    public static List<String> getCommaSeperatedValues(String str) {
        List<String> items = Arrays.asList(str.split("\\s*,\\s*"));
        return items;
    }

    public static double cmToMeter(double cm) {
        double meter = 0;
        try {
            meter = cm / 100;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return meter;
    }

    //calculateBmi
    public static double calculateBmi(double height, double weight) {

        BigDecimal bigDecimal = null;
        double bmi = 0;
        double heightMeter = cmToMeter(height);
        try {
            bmi = weight / (heightMeter * heightMeter);
            bigDecimal = new BigDecimal(bmi);
            bigDecimal = bigDecimal.setScale(2,
                    BigDecimal.ROUND_HALF_UP);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bigDecimal.doubleValue();
    }

    //getExternalStoragePath
    public static String getExternalStoragePath() {
        Boolean isSDPresent = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) && Environment.isExternalStorageRemovable();
        File file;

        isSDPresent = true;

        String abSolutePath = "";
        if (isSDPresent) {
            String secStore = System.getenv("SECONDARY_STORAGE");

            if (secStore == null) {
                String root_sd = Environment.getExternalStorageDirectory().getParent().toString();
                file = new File(root_sd + "/external_sd");
                secStore = file.getAbsolutePath();
            }

            if (secStore != null) {
                file = new File(secStore);
                if (file.exists()) {
                    abSolutePath = file.getAbsolutePath();
                    return abSolutePath;
                } else {
                    return abSolutePath;
                }
            } else {
                return abSolutePath;
            }


        } else {

            return abSolutePath;
        }
    }

    //For phone validation
    public static boolean isValidPhone(String phone) {
        return !TextUtils.isEmpty(phone) &&
                android.util.Patterns.PHONE.matcher(phone).matches();
    }

    public static boolean isValidMobile(String phone) {
        boolean check = false;
        if (!Pattern.matches("[a-zA-Z]+", phone)) {
            //if(phone.length() < 10 || phone.length() > 13) {
            if (phone.length() != 10) {
                // if(phone.length() != 10) {
                check = false;
                //txtPhone.setError("Not Valid Number");
            } else {
                check = true;
            }
        } else {
            check = false;
        }
        return check;
    }

    public static boolean isValidPincode(String pincode) {
        boolean check = false;
        if (!Pattern.matches("[a-zA-Z]+", pincode)) {
            //if(phone.length() < 10 || phone.length() > 13) {
            if (pincode.length() != 6) {
                // if(phone.length() != 10) {
                check = false;
                //txtPhone.setError("Not Valid Number");
            } else {
                check = true;
            }
        } else {
            check = false;
        }
        return check;
    }


    public static boolean isValidOTP(String phone) {
        boolean check = false;
        if (!Pattern.matches("[a-zA-Z]+", phone)) {
            //if(phone.length() < 10 || phone.length() > 13) {
            if (phone.length() != 6) {
                // if(phone.length() != 10) {
                check = false;
                //txtPhone.setError("Not Valid Number");
            } else {
                check = true;
            }
        } else {
            check = false;
        }
        return check;
    }

    public static File createImageFile(Context context) throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = JPEG_FILE_PREFIX + timeStamp + "_";

        File instanceRecordDirectory = new File(Environment.getExternalStorageDirectory() + File.separator + context.getString(R.string.app_name) + File.separator + imageFolderName);

        if (!instanceRecordDirectory.exists()) {
            try {
                instanceRecordDirectory.mkdirs();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        File instanceRecord = new File(instanceRecordDirectory.getAbsolutePath() + File.separator + imageFileName + JPEG_FILE_SUFFIX);
        if (!instanceRecord.exists()) {
            try {
                instanceRecord.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return instanceRecord;
    }

    public static Uri getUriFromFile(Context context, String mCurrentPhotoPath) {
        return FileProvider.getUriForFile(context,
                context.getApplicationContext().getPackageName() +
                        ".provider", new File(mCurrentPhotoPath));
    }


    public static String getRideTitle(String date) {
        String strServerDate = date.replace("T", " ").replace("Z", "");
        SimpleDateFormat serverDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date formatedDate = null;
        try {
            formatedDate = serverDF.parse(strServerDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        SimpleDateFormat finalDF = new SimpleDateFormat("EEE, d MMM yyyy");
        strServerDate = finalDF.format(formatedDate);
        Log.e("Date", strServerDate);
        return strServerDate;
    }

    //MyAsyncTask1 to get time and distance for selected location
    public static class MyAsyncTask1 extends AsyncTask<Void, Void, Response> {

        private OnResultReceived mListner;
        private String apiUrl;

        public MyAsyncTask1(String apiUrl, OnResultReceived listner) {
            this.apiUrl = apiUrl;
            this.mListner = listner;
        }

        @Override
        protected Response<VerifyUser> doInBackground(Void... params) {

            /*OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

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

                    okhttp3.Response response =  chain.proceed(request);

                    com.theta.animationdemo.ParallaxViewPagerDemo.Log.e("Code : "+response.code());
                    if (response.code() != 200){
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
                    .baseUrl(BASE_URL_VALIDATION)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();

            ApiInterface apiInterface = retrofit.create(ApiInterface.class);*/

            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

            final String TXT_PLAIN = "text/plain";
            RequestBody grant_type = RequestBody.create(okhttp3.MediaType.parse(TXT_PLAIN), "password");
            RequestBody username = RequestBody.create(okhttp3.MediaType.parse(TXT_PLAIN), "9722577596");
            RequestBody password = RequestBody.create(okhttp3.MediaType.parse(TXT_PLAIN), "123456");

            //Call<VerifyUser> verifyUserCall = apiInterface.verifyUserCall(grant_type, username, password);
            Call<VerifyUser> verifyUserCall = apiInterface.verifyUserCallDynamicUrl(AUTH_DYNAMIC_URL,grant_type, username, password);
            Response<VerifyUser> result = null;
            try {
                result = verifyUserCall.execute();
                auth_key1 = result.body().getAccessToken();
                com.theta.animationdemo.ParallaxViewPagerDemo.Log.e(result.toString());
            } catch (IOException e) {
                e.printStackTrace();
                com.theta.animationdemo.ParallaxViewPagerDemo.Log.e(e.toString());
            }
            return result;
        }

        @Override
        protected void onPostExecute(Response response) {
            if (mListner != null)
                mListner.onGetResult(response);
        }

        public interface OnResultReceived {
            public void onGetResult(Response result);
        }
    }
}
