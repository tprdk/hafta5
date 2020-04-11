package com.example.hafta5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    Button button_sendMail, button_note, button_sensor, button_personInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        button_sendMail = (Button) findViewById(R.id.button_sendMail);
        button_note = (Button) findViewById(R.id.button_NoteEditor);
        button_sensor = (Button) findViewById(R.id.button_sensor);
        button_personInfo = (Button) findViewById(R.id.button_PersonInfo);

        button_sensor.setOnClickListener(this);
        button_note.setOnClickListener(this);
        button_sendMail.setOnClickListener(this);
        button_personInfo.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == button_personInfo.getId()){
           // Intent infoIntent = new Intent(MenuActivity.this,);

        }else if (v.getId() == button_sendMail.getId()){
            Intent noteActivity = new Intent(MenuActivity.this, emailCompose.class);
            startActivity(noteActivity);
        }else if(v.getId() == button_sensor.getId()){
            Intent sensorIntent = new Intent(MenuActivity.this, SensorActivity.class);
            startActivity(sensorIntent);
        }else{
            Intent noteIntent = new Intent(MenuActivity.this, noteActivity.class);
            startActivity(noteIntent);
        }
    }
}
