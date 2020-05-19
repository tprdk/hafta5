package com.example.hafta5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Date;

public class AlarmActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

    Button button_set;
    Switch switch_on;
    TextView textView_alarm;
    Calendar calendar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        textView_alarm = findViewById(R.id.textView_alarm);
        switch_on = findViewById(R.id.switch_alarm_onOff);
        button_set = findViewById(R.id.button_alarm_setAlarm);

        textView_alarm.setVisibility(View.INVISIBLE);

        switch_on.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(textView_alarm.getVisibility() == View.VISIBLE) {
                    if (switch_on.isChecked()) {
                        Toast.makeText(AlarmActivity.this, "Alarm set.", Toast.LENGTH_LONG).show();
                        startAlarm(calendar);
                    } else {
                        Toast.makeText(AlarmActivity.this, "Alarm canceled.", Toast.LENGTH_LONG).show();
                        cancelAlarm();
                    }
                }
                else{
                    switch_on.setChecked(false);
                    Toast.makeText(AlarmActivity.this, "You have to set alarm first.", Toast.LENGTH_LONG).show();
                }
            }
        });

        button_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTimePicker();
            }
        });
    }

    public void startTimePicker() {
        DialogFragment timePicker = new TimePickerFragment();
        timePicker.show(getSupportFragmentManager(), "Time Picker");
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Calendar rightNow = Calendar.getInstance();
        int currentHour = rightNow.get(Calendar.HOUR_OF_DAY); // return the hour in 24 hrs format (ranging from 0-23)
        int currentMinute = rightNow.get(Calendar.MINUTE);
        int currentSecond = rightNow.get(Calendar.SECOND);


        calendar = Calendar.getInstance();
        calendar.set(java.util.Calendar.HOUR_OF_DAY, hourOfDay);
        calendar.set(java.util.Calendar.MINUTE, minute);

        if(currentHour == hourOfDay && currentMinute == minute){
            calendar.set(java.util.Calendar.SECOND, 59);
        }else{
            calendar.set(java.util.Calendar.SECOND, 0);
        }

        textView_alarm.setVisibility(View.VISIBLE);
        textView_alarm.setText(hourOfDay + " : " + minute);

        switch_on.setChecked(false);
    }

    private void startAlarm(Calendar c) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);
        if (c.before(Calendar.getInstance())) {
            c.add(Calendar.DATE, 1);
        }
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
    }

    private void cancelAlarm() {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);
        alarmManager.cancel(pendingIntent);
    }
}
