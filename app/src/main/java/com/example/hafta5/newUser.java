package com.example.hafta5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class newUser extends AppCompatActivity{
    EditText editText_name, editText_surname, editText_mail,
             editText_username,editText_password, editText_password2;
    Button button_SignUp;
    private ArrayList<Person> personList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);
        loadData();
        editText_name = (EditText) findViewById(R.id.editText_name);
        editText_surname = (EditText) findViewById(R.id.editText_surname);
        editText_mail = (EditText) findViewById(R.id.editText_email);
        editText_username = (EditText) findViewById(R.id.editText_username);
        editText_password = (EditText) findViewById(R.id.editText_password);
        editText_password2 = (EditText) findViewById(R.id.editText_password2);

        button_SignUp = (Button) findViewById(R.id.button_signUp);
       // sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        button_SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Person person = new Person(editText_name.getText().toString(),editText_surname.getText().toString(),
                        editText_mail.getText().toString(),editText_username.getText().toString(),editText_password.getText().toString());
                personList.add(person);
                saveData();
                Toast.makeText(newUser.this, "Account is created succesfully.", Toast.LENGTH_SHORT).show();
            }
        });
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
}

