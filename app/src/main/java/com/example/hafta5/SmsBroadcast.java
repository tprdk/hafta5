package com.example.hafta5;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;

public class SmsBroadcast extends BroadcastReceiver {
    private static final String TAG = "PhoneStatReceiver";

    private static ArrayList<String> logs ;
    private String date;
    private String message ="";
    private String number = "";

    @Override
    public void onReceive(Context context, Intent intent) {

        logs = LogIO.loadLogs(context);
        date = Calendar.getInstance().getTime().toString();

        if (Telephony.Sms.Intents.SMS_RECEIVED_ACTION.equals(intent.getAction())) {

            for (SmsMessage smsMessage : Telephony.Sms.Intents.getMessagesFromIntent(intent)) {
                if(smsMessage.getMessageBody() != null && !smsMessage.getMessageBody().equals("null"))
                    message += smsMessage.getMessageBody();
                number = smsMessage.getDisplayOriginatingAddress();
            }
            Log.d(TAG, "Date : " + date + " Incoming sms number : " + number +"\nMessage Content : ");
            logs.add("Date : " + date + " Incoming sms number : " + number +"\nMessage Content : " + message);

        }else if(Telephony.Sms.Intents.SMS_DELIVER_ACTION.equals(intent.getAction())){
            for (SmsMessage smsMessage : Telephony.Sms.Intents.getMessagesFromIntent(intent)) {
                String messageBody = smsMessage.getMessageBody();
                Log.d(TAG, messageBody);
            }
        }

        LogIO.saveLogs(context , logs);
    }
}
