package com.example.hafta5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.tasks.OnSuccessListener;

public class LocationActivity extends AppCompatActivity {

    public static final int MY_PERMISSIONS_REQUEST_GET_LOCATION = 1000;

    double latitude , longitude;
    TextView show_location;
    Button get_location, send_locationSMS, send_locationWp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        show_location = findViewById(R.id.textView_showLocation);
        send_locationSMS = findViewById(R.id.button_send_locationSMS);
        send_locationWp = findViewById(R.id.button_send_locationWp);
        get_location = findViewById(R.id.button_getLocation);

        send_locationWp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendLocationViaWp("+905072301141", latitude, longitude);
            }
        });

        send_locationSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendLocationViaSMS("+905072301141", latitude, longitude);
            }
        });

        get_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                        !=PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(LocationActivity.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION},MY_PERMISSIONS_REQUEST_GET_LOCATION);
                }else{
                    getCurrentLocation();
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults.length > 0 && requestCode == MY_PERMISSIONS_REQUEST_GET_LOCATION){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                getCurrentLocation();
            }
        }else{
            Toast.makeText(LocationActivity.this, "Permission Denied.", Toast.LENGTH_LONG).show();
        }
    }

    public void getCurrentLocation(){

        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(3000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        LocationServices.getFusedLocationProviderClient(LocationActivity.this)
                .requestLocationUpdates(locationRequest, new LocationCallback(){
                    @Override
                    public void onLocationResult(LocationResult locationResult) {
                        super.onLocationResult(locationResult);
                        LocationServices.getFusedLocationProviderClient(LocationActivity.this)
                                .removeLocationUpdates(this);
                        if(locationResult != null && locationResult.getLocations().size() > 0){
                            int lastLocationIndex = locationResult.getLocations().size() - 1 ;
                            latitude = locationResult.getLocations().get(lastLocationIndex).getLatitude();
                            longitude = locationResult.getLocations().get(lastLocationIndex).getLongitude();
                            show_location.setText("Latitude : " + latitude + " Longitude : " +longitude);
                        }
                    }
                }, Looper.myLooper());

    }

    public void sendLocationViaSMS(String phoneNumber, double latitude, double longitude){
        SmsManager smsManager = SmsManager.getDefault();
        StringBuffer smsBody = new StringBuffer();
        smsBody.append("http://maps.google.com?q=");
        smsBody.append(latitude);
        smsBody.append(",");
        smsBody.append(longitude);
        smsManager.sendTextMessage(phoneNumber, null, smsBody.toString(), null, null);
    }

    public void sendLocationViaWp(String phoneNumber, double latitude, double longitude){

        PackageManager pm=getPackageManager();
        try {

            Intent waIntent = new Intent(Intent.ACTION_SEND);
            waIntent.setType("text/plain");

            StringBuffer smsBody = new StringBuffer();
            smsBody.append("http://maps.google.com?q=");
            smsBody.append(latitude);
            smsBody.append(",");
            smsBody.append(longitude);

            PackageInfo info=pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
            //Check if package exists or not. If not then code
            //in catch block will be called
            waIntent.setPackage("com.whatsapp");

            waIntent.putExtra(Intent.EXTRA_TEXT, smsBody.toString());
            startActivity(Intent.createChooser(waIntent, "Share with"));

        } catch (PackageManager.NameNotFoundException e) {
            Toast.makeText(this, "WhatsApp not Installed", Toast.LENGTH_SHORT)
                    .show();
        }
    }
}
