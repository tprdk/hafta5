package com.example.hafta5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button_sendMail, button_note, button_sensor, button_personInfo, button_myInfo;
    private TextView textView_topic;
    private int position;
    private ArrayList<Person> personList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        loadData();

        Intent positionIntent = getIntent();
        position = positionIntent.getIntExtra(MainActivity.EXTRA_INT, 0);       //the position of user in the arraylist
        textView_topic = (TextView) findViewById(R.id.textView_menu_topic);
        textView_topic.setText("Welcome "+ personList.get(position).getName());

        button_myInfo = (Button) findViewById(R.id.button_menu_myInfo);
        button_sendMail = (Button) findViewById(R.id.button_menu_sendMail);
        button_note = (Button) findViewById(R.id.button_menu_notes);
        button_sensor = (Button) findViewById(R.id.button_menu_sensor);
        button_personInfo = (Button) findViewById(R.id.button_menu_personInfo);

        button_sensor.setOnClickListener(this);
        button_note.setOnClickListener(this);
        button_sendMail.setOnClickListener(this);
        button_personInfo.setOnClickListener(this);
        button_myInfo.setOnClickListener(this);
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
        }else{
            Intent noteIntent = new Intent(MenuActivity.this, noteActivity.class);
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
