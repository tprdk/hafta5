package com.example.hafta5;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class SensorActivity extends AppCompatActivity implements SensorEventListener {
    private TextView textView_sensors;
    private SensorManager sensorManager;
    private Sensor lightSensor,accSensor;
    long currentTime, estimatedTime;
    float x, x_old, y, y_old, z, z_old;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        textView_sensors = (TextView) findViewById(R.id.textView_sensorList);

        loadText(sensors);
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        accSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        if(event.sensor.getType() == Sensor.TYPE_LIGHT){
            float lux = event.values[0];
            if(lux < 10000){
                textView_sensors.setBackgroundColor(Color.BLACK);
                textView_sensors.setTextColor(Color.WHITE);
            }else{
                textView_sensors.setBackgroundColor(Color.WHITE);
                textView_sensors.setTextColor(Color.BLACK);
            }
        }
        else if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            x = event.values[0];
            y = event.values[1];
            z = event.values[2];

            if( x_old != x || y_old != y || z_old != z ){
                currentTime = System.currentTimeMillis();
                x_old=x;
                y_old=y;
                z_old=z;
            }else {
                estimatedTime = System.currentTimeMillis() - currentTime;
                if (estimatedTime > 5000){
                    Toast.makeText(this, "Reached 5 seconds", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, accSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void loadText(List<Sensor> sensors){

        StringBuffer allSensors = new StringBuffer();
        for (Sensor sens : sensors)
        {
            allSensors.append(sens.getName() + "\n");
        }
        textView_sensors.setText(allSensors);
    }
}
