package com.example.hafta5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class emailCompose extends AppCompatActivity implements View.OnClickListener {

    Button buttonSend;
    EditText editText_to, editText_from, editText_subject, editText_mail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_compose);

        editText_from = (EditText) findViewById(R.id.editText_from);
        editText_to = (EditText) findViewById(R.id.editText_to);
        editText_mail = (EditText) findViewById(R.id.editText_mail);
        editText_subject = (EditText) findViewById(R.id.editText_subject);


        buttonSend = (Button) findViewById(R.id.button_send);
        buttonSend.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        String sender = editText_from.getText().toString();
        String receiver = editText_to.getText().toString();
        String message = editText_mail.getText().toString();
        String subject = editText_subject.getText().toString();

        Intent mailIntent = new Intent(Intent.ACTION_SENDTO , Uri.fromParts(
                "mailto", receiver, null));
        mailIntent.putExtra(Intent.EXTRA_SUBJECT , subject);
        mailIntent.putExtra(Intent.EXTRA_TEXT , message);
        startActivity(Intent.createChooser(mailIntent, "Send email via..."));
    }
}
