package com.example.hafta5;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.ActivityRecognition;
import com.google.android.gms.location.ActivityRecognitionClient;
import com.google.android.gms.location.DetectedActivity;
import com.google.android.gms.tasks.Task;

public class DetectActivity extends AppCompatActivity
    implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private GoogleApiClient mApiClient;
    public static TextView textView_activity, textView_counter, textView_prob;

    private Thread thread;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detect);

        textView_activity = findViewById(R.id.textView_activity);
        textView_counter = findViewById(R.id.textView_counter);
        textView_prob = findViewById(R.id.textView_probability);

        mApiClient = new GoogleApiClient.Builder(DetectActivity.this)
                .addApi(ActivityRecognition.API)
                .addConnectionCallbacks(DetectActivity.this)
                .addOnConnectionFailedListener(DetectActivity.this)
                .build();

        mApiClient.connect();

        thread = new Thread(){
            @Override
            public void run() {
                try {
                    synchronized (this) {

                        while (true){
                            wait(5000);

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    setActivityText("Activity :"+ActivityDetectionService.currentActivity);
                                    setCounterText("Counter : " +ActivityDetectionService.counter);
                                    setProbText("Confidence : " +ActivityDetectionService.currentConfidence);
                                }
                            });
                        }

                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            };
        };
        thread.start();
    }

    public void setActivityText(String str){
        textView_activity.setText(str);
    }
    public void setCounterText(String str){ textView_counter.setText(str); }
    public void setProbText(String str){
        textView_prob.setText(str);
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Intent intent = new Intent(DetectActivity.this, ActivityDetectionService.class);
        PendingIntent pendingIntent = PendingIntent.getService(DetectActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        ActivityRecognitionClient activityRecognitionClient = ActivityRecognition.getClient(DetectActivity.this);
        Task task = activityRecognitionClient.requestActivityUpdates(5000, pendingIntent);

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
