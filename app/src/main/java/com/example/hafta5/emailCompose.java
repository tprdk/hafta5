package com.example.hafta5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class emailCompose extends AppCompatActivity {

    Button buttonSend, buttonAttach;
    EditText editText_to, editText_from, editText_subject, editText_mail;
    TextView textView_attach;
    Uri URI = null;
    private static final int PICK_FROM_GALLERY = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_compose);

        editText_to = (EditText) findViewById(R.id.editText_to);
        editText_mail = (EditText) findViewById(R.id.editText_mail);
        editText_subject = (EditText) findViewById(R.id.editText_subject);
        textView_attach = (TextView) findViewById(R.id.textView_attach);
        buttonSend = (Button) findViewById(R.id.button_send);

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMail();
            }
        });

        buttonAttach = (Button) findViewById(R.id.button_attach);
        buttonAttach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAttach();
            }
        });
    }

    public void sendMail(){
        try {
            String to = editText_to.getText().toString();
            String subject = editText_subject.getText().toString();
            String mail = editText_mail.getText().toString();
            Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
            emailIntent.setType("plain/text");
            emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{to});
            emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, subject);
            if (URI != null) {
                emailIntent.putExtra(Intent.EXTRA_STREAM, URI);
            }
            emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, mail);
            this.startActivity(Intent.createChooser(emailIntent, "Sending email..."));
        } catch (Throwable t) {
            Toast.makeText(this, "Request failed try again: "+ t.toString(), Toast.LENGTH_LONG).show();
        }
    }

    public void addAttach(){
        Intent imageIntent = new Intent();
        imageIntent.setType("image/*");
        imageIntent.setAction(Intent.ACTION_GET_CONTENT);
        imageIntent.putExtra("return-data", true);
        startActivityForResult(Intent.createChooser(imageIntent, "Complete action using"), PICK_FROM_GALLERY);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_FROM_GALLERY && resultCode == RESULT_OK) {
            URI = data.getData();
            textView_attach.setText(URI.getLastPathSegment());
            textView_attach.setVisibility(View.VISIBLE);
        }
    }
}