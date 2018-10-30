package com.theta.animationdemo.retrofit.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.theta.animationdemo.ParallaxViewPagerDemo.Log;

/**
 * Created by ashish on 27/4/18.
 */

public class NetworkChangeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        int status = NetworkUtil.getConnectivityStatusString(context);
        Log.e( "Network reciever");
        if (!"android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction())) {
            if(status==NetworkUtil.NETWORK_STATUS_NOT_CONNECTED){
               // new ForceExitPause(context).execute();
            }else{
             //   new ResumeForceExitPause(context).execute();
                Intent local = new Intent();
                local.setAction("internetState");
                local.putExtra("state", "true");
                context.sendBroadcast(local);
            }

        }
    }
}
