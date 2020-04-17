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
                if (checkExist(editText_username.getText().toString()) && checkSpace()
                && editText_password.getText().toString().trim().equals(editText_password2.getText().toString().trim())){
                    Person person = new Person(editText_name.getText().toString(),editText_surname.getText().toString(),
                            editText_mail.getText().toString(),editText_username.getText().toString(),editText_password.getText().toString());
                    personList.add(person);
                    saveData();
                    Toast.makeText(newUser.this, "Account is created successfully.", Toast.LENGTH_SHORT).show();
                }
                else{
                    if (!checkSpace()){
                        Toast.makeText(newUser.this, "Any information can not be space.", Toast.LENGTH_LONG).show();
                    }else if (!editText_password.getText().toString().trim().equals(editText_password2.getText().toString().trim())){
                        Toast.makeText(newUser.this, "Two passwords are not identical!", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(newUser.this, "This username is already in use.", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    private boolean checkSpace(){
        return (editText_name.getText().toString().trim() != "" && editText_password.getText().toString().trim() != ""&&
                editText_surname.getText().toString().trim() != "" && editText_password2.getText().toString().trim() != ""&&
                editText_username.getText().toString().trim() != "" && editText_mail.getText().toString().trim() != "");
    }

    private boolean checkExist(String username){
        boolean flag = true;
        int i=0;

        while (flag && i < personList.size()){
            if (personList.get(i).getUsername().equals(username))
                flag = false;
            else
                i++;
        }
        return flag;
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

