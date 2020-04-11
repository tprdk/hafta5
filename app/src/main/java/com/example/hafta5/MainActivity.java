package com.example.hafta5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editTextName , editTextPass;
    Button buttonLogin,buttonSignIn;
    String nameOfPerson,passOfPerson;

    ArrayList<Person> personArrayList = new ArrayList<Person>();
    Person p = new Person();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = (EditText) findViewById(R.id.editText_name);
        editTextPass = (EditText) findViewById(R.id.editText_pass);

        buttonLogin  = (Button)  findViewById(R.id.button);
        buttonSignIn = (Button)  findViewById(R.id.button_signUp);

        buttonLogin.setOnClickListener(this);
        buttonSignIn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == buttonSignIn.getId()){
            Intent newUserIntent = new Intent(MainActivity.this, newUser.class);
            startActivity(newUserIntent);
        }
        else {
            nameOfPerson = editTextName.getText().toString();
            passOfPerson = editTextPass.getText().toString();

            if (checkPass(nameOfPerson, passOfPerson)) {
                Intent menuIntent = new Intent(MainActivity.this, MenuActivity.class);
                startActivity(menuIntent);
            } else {
                editTextName.setText("");
                editTextPass.setText("");
            }
        }
    }

    public boolean checkPass(String name,String pass){
        if (!isExist(name,pass))
            return true;
        else
            return false;
    }

    public boolean isExist(String name,String pass){
        boolean flag = true;
        int i=0;
/*
        while (flag == true && i < personArrayList.size() ){
            if (personArrayList.get(i).getName().equals(nameOfPerson) && personArrayList.get(i).getName().equals(passOfPerson))
                flag = false;
            else
                i++;
        }
        */return false;//flag;
    }
}
