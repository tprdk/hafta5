package com.example.hafta5;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.sql.Time;
import java.util.Random;

public class AsyncTaskActivity extends AppCompatActivity {

    Button button_download;
    ProgressBar progressBar_prg;
    ImageView imageView_complete;
    Random random = new Random();
    MediaPlayer mediaPlayer ;
    long currentTime, lasttime;
    int value;
    int progress=0;

    Uri notification ;
    Ringtone ringtone ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);

        button_download = (Button) findViewById(R.id.button_async_task_download);
        progressBar_prg = (ProgressBar) findViewById(R.id.progressBar_async_task_progress);
        imageView_complete = findViewById(R.id.imageView_async_task_image);
        imageView_complete.setVisibility(View.INVISIBLE);

        notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        ringtone = RingtoneManager.getRingtone(getApplicationContext(), notification);

        currentTime=System.currentTimeMillis();
        lasttime = currentTime;

        button_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RandomTask asyncTask=new RandomTask();
                asyncTask.execute();
            }
        });
    }

    private class RandomTask extends AsyncTask<Void , Integer , Boolean> {

        @Override
        protected Boolean doInBackground(Void... voids) {
            while (progress < 100){
                currentTime = System.currentTimeMillis();
                if(currentTime - lasttime >= 1000 && progress < 100 ){
                    lasttime = currentTime;
                    value = random.nextInt(10);
                    progress+=value;
                    publishProgress(progress);
                }
            }
            if(progress >= 100){
                return true;
            }else {
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if(aBoolean == true){
                imageView_complete.setVisibility(View.VISIBLE);
                ringtone.play();
            }
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressBar_prg.setProgress(values[0]);
        }
    }
}
