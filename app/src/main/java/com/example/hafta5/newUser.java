package com.example.hafta5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class newUser extends AppCompatActivity implements View.OnClickListener {
    EditText editText_name, editText_surname, editText_mail,
             editText_username,editText_password, editText_password2;
    Button button_SignUp;
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String sp_name = "nameKey";
    public static final String sp_surname = "surnameKey";
    public static final String sp_mail = "mailKey";
    public static final String sp_username = "usernameKey";
    public static final String sp_password = "passwordKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        editText_name = (EditText) findViewById(R.id.editText_name);
        editText_surname = (EditText) findViewById(R.id.editText_surname);
        editText_mail = (EditText) findViewById(R.id.editText_email);
        editText_username = (EditText) findViewById(R.id.editText_username);
        editText_password = (EditText) findViewById(R.id.editText_password);
        editText_password2 = (EditText) findViewById(R.id.editText_password2);

        button_SignUp = (Button) findViewById(R.id.button_signUp);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        button_SignUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (editText_password.getText().toString().equals(editText_password2.getText().toString())){
        /*
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString(sp_name, editText_name.getText().toString());
            editor.putString(sp_surname, editText_surname.getText().toString());
            editor.putString(sp_mail, editText_mail.getText().toString());
            editor.putString(sp_username, editText_username.getText().toString());
            editor.putString(sp_password, editText_password.getText().toString());
            editor.commit();
          */
            Toast.makeText(newUser.this, "Account is created successfully.", Toast.LENGTH_LONG).show();
        }else{
            editText_name.setText("");
            editText_surname.setText("");
            editText_mail.setText("");
            editText_username.setText("");
            editText_password.setText("");
            editText_password2.setText("");
        }
    }
}
