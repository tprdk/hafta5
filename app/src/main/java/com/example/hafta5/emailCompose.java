package com.example.hafta5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class emailCompose extends AppCompatActivity implements View.OnClickListener {

    Button buttonSend;
    EditText editText_to, editText_from, editText_cc, editText_bcc, editText_subject, editText_mail;
    Uri URI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_compose);

        editText_from = (EditText) findViewById(R.id.editText_from);
        editText_to = (EditText) findViewById(R.id.editText_to);
        editText_mail = (EditText) findViewById(R.id.editText_mail);
        editText_subject = (EditText) findViewById(R.id.editText_subject);
        editText_cc = (EditText) findViewById(R.id.editText_cc);
        editText_bcc = (EditText) findViewById(R.id.editText_bcc);

        buttonSend = (Button) findViewById(R.id.button_send);
        buttonSend.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String receiver = editText_to.getText().toString();
        String cc = editText_cc.getText().toString();
        String bcc = editText_bcc.getText().toString();
        String subject = editText_subject.getText().toString();
        String message = editText_mail.getText().toString();

        Intent mailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto", receiver, null));
        mailIntent.putExtra(Intent.EXTRA_CC, cc);
        mailIntent.putExtra(Intent.EXTRA_BCC, bcc);
        mailIntent.putExtra(Intent.EXTRA_SUBJECT , subject);
        mailIntent.putExtra(Intent.EXTRA_TEXT , message);

        try{
            startActivity(Intent.createChooser(mailIntent, "Send email via..."));
        }catch (Exception e){
            Toast.makeText(emailCompose.this, "No email clients installed.", Toast.LENGTH_SHORT).show();
        }


       /* Intent mailIntent = new Intent(Intent.ACTION_SENDTO);
        mailIntent.setType("plain/text");
        mailIntent.putExtra(Intent.EXTRA_EMAIL, receiver);
        mailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        if (URI != null) {
            mailIntent.putExtra(Intent.EXTRA_STREAM, URI);
        }
        mailIntent.putExtra(Intent.EXTRA_TEXT, message);
        this.startActivity(Intent.createChooser(mailIntent, "Sending email..."));
        */
    }

}
