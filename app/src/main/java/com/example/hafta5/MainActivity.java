package com.example.hafta5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
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
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextName , editTextPass;
    private Button buttonLogin,buttonSignIn;
    private String nameOfPerson,passOfPerson;
    private int position;
    private ArrayList<Person> personList;
    public static final String EXTRA_INT = "INDEX";
    private int iter=0;

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
    protected void onResume() {
        super.onResume();
        loadData();
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == buttonSignIn.getId()){
            Intent newUserIntent = new Intent(MainActivity.this, newUser.class);
            startActivity(newUserIntent);
        }
        else {
            nameOfPerson = editTextName.getText().toString().trim();
            passOfPerson = editTextPass.getText().toString().trim();
            position = checkPass(nameOfPerson, passOfPerson);

            if ( position != -1 ) {
                iter = 0;
                Intent menuIntent = new Intent(MainActivity.this, MenuActivity.class);
                menuIntent.putExtra(EXTRA_INT , position);
                startActivity(menuIntent);
            } else {
                if( iter == 2){
                    Toast.makeText(this, "App is closed due to 3 wrong password trials.", Toast.LENGTH_LONG).show();
                    finish();
                }else{
                    iter++;
                    editTextName.setText("Username");
                    editTextPass.setText("Password");
                    Toast.makeText(this, "Wrong username or password!",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public int checkPass(String nameOfPerson, String passOfPerson){
        int index = -1;
        int i=0;

        while (index == -1 && i < personList.size()){
            if (personList.get(i).getUsername().equals(nameOfPerson) && personList.get(i).getPassword().equals(passOfPerson))
                index = i;
            else
                i++;
        }
        return index;
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
