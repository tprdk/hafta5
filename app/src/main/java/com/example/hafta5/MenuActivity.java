package com.example.hafta5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {
    public static final int MY_PERMISSIONS_REQUEST_PROCESS_SEND_SMS = 1002;
    public static final int MY_PERMISSIONS_REQUEST_PROCESS_READ_SMS = 1001;
    public static final int MY_PERMISSIONS_REQUEST_PROCESS_RECEIVE_SMS = 1000;
    public static final int MY_PERMISSIONS_REQUEST_READ_PHONE_STATE = 999;
    public static final int MY_PERMISSIONS_REQUEST_READ_PHONE_NUMBER = 998;
    public static final int MY_PERMISSIONS_REQUEST_READ_CALL_LOG = 997;
    public static final int MY_PERMISSIONS_REQUEST_PROCESS_OUTGOING_CALLS = 996;

    private Button button_sendMail, button_note, button_sensor, button_location, button_logs, button_activity,
            button_personInfo, button_myInfo, button_progress,button_alarm;
    private TextView textView_topic;
    private int position;
    private ArrayList<Person> personList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        loadData();

        Log.d("Menu","Giris yapıldı");

        checkRequest();

        Intent positionIntent = getIntent();
        position = positionIntent.getIntExtra(MainActivity.EXTRA_INT, 0);       //the position of user in the arraylist
        textView_topic = (TextView) findViewById(R.id.textView_menu_topic);
        textView_topic.setText("Welcome "+ personList.get(position).getName());

        button_myInfo = (Button) findViewById(R.id.button_menu_myInfo);
        button_sendMail = (Button) findViewById(R.id.button_menu_sendMail);
        button_note = (Button) findViewById(R.id.button_menu_notes);
        button_sensor = (Button) findViewById(R.id.button_menu_sensor);
        button_personInfo = (Button) findViewById(R.id.button_menu_personInfo);
        button_progress = (Button) findViewById(R.id.button_menu_progress);
        button_alarm = (Button) findViewById(R.id.button_menu_alarm);
        button_location = (Button) findViewById(R.id.button_menu_location);
        button_logs = (Button) findViewById(R.id.button_menu_Logs);
        button_activity = (Button) findViewById(R.id.button_menu_activity);

        button_sensor.setOnClickListener(this);
        button_note.setOnClickListener(this);
        button_sendMail.setOnClickListener(this);
        button_personInfo.setOnClickListener(this);
        button_myInfo.setOnClickListener(this);
        button_progress.setOnClickListener(this);
        button_alarm.setOnClickListener(this);
        button_location.setOnClickListener(this);
        button_logs.setOnClickListener(this);
        button_activity.setOnClickListener(this);
    }

    public void checkRequest(){

        if (ContextCompat.checkSelfPermission(MenuActivity.this , Manifest.permission.READ_PHONE_STATE)!= PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MenuActivity.this,new String[]{Manifest.permission.READ_PHONE_STATE},MY_PERMISSIONS_REQUEST_READ_PHONE_STATE);
        }
        if(ContextCompat.checkSelfPermission(MenuActivity.this , Manifest.permission.READ_PHONE_NUMBERS)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MenuActivity.this,new String[]{Manifest.permission.READ_PHONE_NUMBERS},MY_PERMISSIONS_REQUEST_READ_PHONE_NUMBER);
        }
        if(ContextCompat.checkSelfPermission(MenuActivity.this , Manifest.permission.READ_CALL_LOG)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MenuActivity.this,new String[]{Manifest.permission.READ_CALL_LOG},MY_PERMISSIONS_REQUEST_READ_CALL_LOG);
        }
        if(ContextCompat.checkSelfPermission(MenuActivity.this , Manifest.permission.PROCESS_OUTGOING_CALLS)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MenuActivity.this,new String[]{Manifest.permission.PROCESS_OUTGOING_CALLS},MY_PERMISSIONS_REQUEST_PROCESS_OUTGOING_CALLS);
        }
        if(ContextCompat.checkSelfPermission(MenuActivity.this , Manifest.permission.RECEIVE_SMS)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MenuActivity.this,new String[]{Manifest.permission.RECEIVE_SMS},MY_PERMISSIONS_REQUEST_PROCESS_RECEIVE_SMS);
        }
        if(ContextCompat.checkSelfPermission(MenuActivity.this , Manifest.permission.READ_SMS)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MenuActivity.this,new String[]{Manifest.permission.READ_SMS},MY_PERMISSIONS_REQUEST_PROCESS_READ_SMS);

        }if(ContextCompat.checkSelfPermission(MenuActivity.this , Manifest.permission.SEND_SMS)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MenuActivity.this,new String[]{Manifest.permission.SEND_SMS},MY_PERMISSIONS_REQUEST_PROCESS_SEND_SMS);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == button_personInfo.getId()){
            Intent personIntent = new Intent(MenuActivity.this,UserInfo.class);
            startActivity(personIntent);
        }else if (v.getId() == button_sendMail.getId()){
            Intent noteActivity = new Intent(MenuActivity.this, emailCompose.class);
            startActivity(noteActivity);
        }else if(v.getId() == button_sensor.getId()){
            Intent sensorIntent = new Intent(MenuActivity.this, SensorActivity.class);
            startActivity(sensorIntent);
        }else if(v.getId() == button_myInfo.getId()){
            Intent myInfoIntent = new Intent(MenuActivity.this, myInfo.class);
            myInfoIntent.putExtra(MainActivity.EXTRA_INT , position);
            startActivity(myInfoIntent);
        }else if(v.getId() == button_progress.getId()){
            Intent myProgress = new Intent(MenuActivity.this, AsyncTaskActivity.class);
            startActivity(myProgress);
        }else if (v.getId() == (button_alarm.getId())){
            Intent alarm = new Intent(MenuActivity.this, AlarmActivity.class);
            startActivity(alarm);
        }else if (v.getId() == (button_location.getId())){
            Intent alarm = new Intent(MenuActivity.this, LocationActivity.class);
            startActivity(alarm);
        }else if (v.getId() == (button_logs.getId())){
            Intent alarm = new Intent(MenuActivity.this, LogActivity.class);
            startActivity(alarm);
        }else if (v.getId() == (button_activity.getId())){
            Intent alarm = new Intent(MenuActivity.this, DetectActivity.class);
            startActivity(alarm);
        }else{
            Intent noteIntent = new Intent(MenuActivity.this, LocationActivity.class);
            startActivity(noteIntent);
        }
    }

    public void loadData() {
        SharedPreferences sharedpreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedpreferences.getString("personList", null);
        Type type = new TypeToken<ArrayList<Person>>() {}.getType();
        personList = gson.fromJson(json, type);
        if(personList == null) {
            personList = new ArrayList<Person>();
        }
    }
}
