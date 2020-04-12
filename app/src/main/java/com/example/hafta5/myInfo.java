package com.example.hafta5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class myInfo extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText editText_height, editText_weight, editText_age;
    private Spinner spinner_gender, spinner_mode;
    private String gender,mode;
    private Button button_save;
    private int age,weight,height;

    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_info);
        spinner_gender = (Spinner) findViewById(R.id.spinner_gender);
        spinner_mode = (Spinner) findViewById(R.id.spinner_mode);
        editText_age = (EditText) findViewById(R.id.editText_myInfo_age);
        editText_weight = (EditText) findViewById(R.id.editText_myInfo_weight);
        editText_height = (EditText) findViewById(R.id.editText_myInfo_height);
        button_save = (Button) findViewById(R.id.button_save);
        setSpinner();

        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getText()){
                    saveChanges();
                }
            }
        });
    }

    public boolean getText(){
        if(isNumeric(editText_age.toString()) && isNumeric(editText_weight.toString()) && isNumeric(editText_height.toString())){
            age = Integer.parseInt(editText_age.toString());
            weight = Integer.parseInt(editText_weight.toString());
            height = Integer.parseInt(editText_height.toString());
            return true;
        }else{
            Toast.makeText(this, "Age, weight, height can not contain any letter.",Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public void saveChanges(){



    }

    public void setSpinner(){

        ArrayAdapter<CharSequence> adapterGender = ArrayAdapter.createFromResource(this,R.array.myInfo_array_gender, android.R.layout.simple_spinner_item);
        adapterGender.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_gender.setAdapter(adapterGender);

        ArrayAdapter<CharSequence> adapterMode = ArrayAdapter.createFromResource(this,R.array.myInfo_array_mode,android.R.layout.simple_spinner_item);
        adapterMode.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_mode.setAdapter(adapterMode);

        spinner_mode.setOnItemSelectedListener(this);
        spinner_gender.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (view.getId() == spinner_mode.getId()){
            mode = parent.getItemAtPosition(position).toString();

        }else{
            gender = parent.getItemAtPosition(position).toString();
        }
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) { }

    public static boolean isNumeric(String str) {
        return str != null && str.matches("[-+]?\\d*\\.?\\d+");
    }
}
