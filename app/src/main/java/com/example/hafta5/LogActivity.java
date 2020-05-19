package com.example.hafta5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import static com.example.hafta5.noteActivity.arrayAdapter;

public class LogActivity extends AppCompatActivity {

    private ArrayList<String> logs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        logs = LogIO.loadLogs(this);
        ListView listView = (ListView) findViewById(R.id.listView_logs);
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,logs);
        listView.setAdapter(arrayAdapter);
    }
}
