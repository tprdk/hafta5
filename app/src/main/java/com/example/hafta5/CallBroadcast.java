package com.example.hafta5;

import android.Manifest;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.ArrayAdapter;

import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.Calendar;


public class CallBroadcast extends BroadcastReceiver{

    private static final String TAG = "PhoneStatReceiver";
    private static ArrayList<String> logs , logm;
    private String date;


    @Override

    public void onReceive(Context context, Intent intent)
    {
        //logm = new ArrayList<String>();
        //LogIO.saveLogs(context, logm);                //temizlemek için

        String state=intent.getStringExtra(TelephonyManager.EXTRA_STATE);
        logs = LogIO.loadLogs(context);
        date = Calendar.getInstance().getTime().toString();

        if(state==null)
        {
            String number=intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
            Log.i(TAG,"Outgoing number : "+number);
            if(number != null)
                logs.add("Date : " + date + " Outgoing number : " + number);
        }
        else if (state.equals(TelephonyManager.EXTRA_STATE_RINGING))
        {
            String number = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
            Log.i(TAG,"Incoming number : "+number);
            if(number != null)
                logs.add("Date : " + date + " Incoming number : " + number);
        }

        LogIO.saveLogs(context, logs);
    }


}


