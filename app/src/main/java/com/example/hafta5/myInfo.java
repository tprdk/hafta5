package com.example.hafta5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class myInfo extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText editText_height, editText_weight, editText_age;
    private TextView textView_name;
    private Spinner spinner_gender, spinner_mode;
    private Button button_save;
    private String name,gender,mode;
    private int age,weight,height;
    private ArrayList<Person> personList;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_info);

        textView_name = (TextView) findViewById(R.id.editText_myInfo_name);
        spinner_gender = (Spinner) findViewById(R.id.spinner_gender);
        spinner_mode = (Spinner) findViewById(R.id.spinner_mode);
        editText_age = (EditText) findViewById(R.id.editText_myInfo_age);
        editText_weight = (EditText) findViewById(R.id.editText_myInfo_weight);
        editText_height = (EditText) findViewById(R.id.editText_myInfo_height);
        button_save = (Button) findViewById(R.id.button_save);

        Intent positionIntent = getIntent();
        position = positionIntent.getIntExtra(MainActivity.EXTRA_INT, 0);
        loadData();
        setSpinner();
        loadViews();

        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getText()){
                    saveChanges();
                    saveData();
                    Toast.makeText(myInfo.this, "Your data saved successfully.", Toast.LENGTH_SHORT ).show();
                }
            }
        });
    }

    public boolean getText(){
        if(isNumeric(editText_age.getText().toString()) && isNumeric(editText_weight.getText().toString())
                && isNumeric(editText_height.getText().toString())){
            age = Integer.parseInt(editText_age.getText().toString());
            weight = Integer.parseInt(editText_weight.getText().toString());
            height = Integer.parseInt(editText_height.getText().toString());
            return true;
        }else{
            Toast.makeText(this, "Age, Height , Weight can not contain any letters.",Toast.LENGTH_SHORT ).show();
            return false;
        }
    }

    public void saveChanges(){
        personList.get(position).setAge(age);
        personList.get(position).setHeight(height);
        personList.get(position).setWeight(weight);
        personList.get(position).setGender(gender);
        personList.get(position).setMode(mode);
    }

    public void loadViews(){
        editText_height.setText(Integer.toString(personList.get(position).getHeight()));
        editText_weight.setText(Integer.toString(personList.get(position).getWeight()));
        editText_age.setText(Integer.toString(personList.get(position).getAge()));
        textView_name.setText(personList.get(position).getName());
    }

    public void saveData(){
        SharedPreferences sharedpreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(personList);
        editor.putString("personList",json);
        editor.apply();
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

    public void setSpinner(){

        ArrayAdapter<CharSequence> adapterGender = ArrayAdapter.createFromResource(this,R.array.myInfo_array_gender, android.R.layout.simple_spinner_item);
        adapterGender.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_gender.setAdapter(adapterGender);

        ArrayAdapter<CharSequence> adapterMode = ArrayAdapter.createFromResource(this,R.array.myInfo_array_mode,android.R.layout.simple_spinner_item);
        adapterMode.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_mode.setAdapter(adapterMode);

        String genderValue = personList.get(position).getGender();
        int spinnerPosition = adapterGender.getPosition(genderValue);
        spinner_gender.setSelection(spinnerPosition);

        String modeValue = personList.get(position).getMode();
        spinnerPosition = adapterMode.getPosition(modeValue);
        spinner_mode.setSelection(spinnerPosition);

        spinner_mode.setOnItemSelectedListener(this);
        spinner_gender.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getId() == spinner_mode.getId()){
            mode = parent.getItemAtPosition(position).toString();
        }else{
            gender = parent.getItemAtPosition(position).toString();
        }
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) { }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int i = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
