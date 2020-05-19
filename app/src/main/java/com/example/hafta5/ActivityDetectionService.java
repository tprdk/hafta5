package com.example.hafta5;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;
import android.widget.Switch;


import androidx.annotation.Nullable;

import com.google.android.gms.location.ActivityRecognition;
import com.google.android.gms.location.ActivityRecognitionResult;
import com.google.android.gms.location.DetectedActivity;

import java.util.ArrayList;
import java.util.List;

public class ActivityDetectionService extends IntentService {
    protected static final String TAG = "ActivityRecognizer";
    public static String currentActivity = "EX";
    public static int counter = 0 ;
    private boolean flag = false;
    private int confTreshold = 50;

    public ActivityDetectionService(){
        super("ActivityDetectionService");
    }

    public ActivityDetectionService(String name){
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if(ActivityRecognitionResult.hasResult(intent)){
            ActivityRecognitionResult result = ActivityRecognitionResult.extractResult(intent);
            handleDetectedActivity(result.getProbableActivities());
        }
    }

    private void handleDetectedActivity (List<DetectedActivity> probableActivities){
        for (DetectedActivity activity : probableActivities){
            switch(activity.getType()){
                case DetectedActivity.ON_FOOT:{
                    Log.d(TAG , "IN_VEHICLE : " + activity.getConfidence()+ "Flag = " + flag + " Counter : " + counter);
                    currentActivity = "IN_VEHICLE : " + activity.getConfidence();
                    if(activity.getConfidence() > confTreshold)
                        flag = true;
                    Log.d(TAG , "IN_VEHICLE : " + activity.getConfidence()+ "Flag = " + flag + " Counter : " + counter);
                    break;
                }
                case DetectedActivity.STILL:{
                    Log.d(TAG , "STILL : " + activity.getConfidence() + "Flag = " + flag + " Counter : " + counter);
                    currentActivity = "STILL : " + activity.getConfidence();
                    if(flag){
                        counter++;
                        flag = false;
                    }
                    Log.d(TAG , "STILL : " + activity.getConfidence() + "Flag = " + flag + " Counter : " + counter);
                    break;
                }
                case DetectedActivity.RUNNING:{
                    Log.d(TAG , "RUNNING : " + activity.getConfidence()+ "Flag = " + flag + " Counter : " + counter);
                    currentActivity = "RUNNING : " + activity.getConfidence();
                    if(activity.getConfidence() > confTreshold)
                        flag = true;
                    Log.d(TAG , "RUNNING : " + activity.getConfidence()+ "Flag = " + flag + " Counter : " + counter);
                    break;
                }
                case DetectedActivity.WALKING:{
                    Log.d(TAG , "WALKING : " + activity.getConfidence()+ "Flag = " + flag + " Counter : " + counter);
                    currentActivity = "WALKING : " + activity.getConfidence();
                    if(activity.getConfidence() > confTreshold)
                        flag = true;
                    Log.d(TAG , "WALKING : " + activity.getConfidence()+ "Flag = " + flag + " Counter : " + counter);
                    break;
                }
                case DetectedActivity.TILTING:{
                    Log.d(TAG , "TILTING : " + activity.getConfidence()+ "Flag = " + flag + " Counter : " + counter);
                    currentActivity = "TILTING : " + activity.getConfidence();
                    if(activity.getConfidence() > confTreshold)
                        flag = true;
                    Log.d(TAG , "TILTING : " + activity.getConfidence()+ "Flag = " + flag + " Counter : " + counter);
                    break;
                }
            }
        }
    }

}
