package com.example.administrator.test.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by My on 2017/12/8.
 */

public class MyOutGoingCallReceiver extends BroadcastReceiver {

    private static final String TAG = "MyOutGoingCallReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e(TAG, "onReceive: ");
        SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        String ipNumber = sp.getString("ipNumber", "");
        String phone = getResultData();
        if (phone.startsWith("0")) {
            setResultData(ipNumber + phone);
        }
    }
}
