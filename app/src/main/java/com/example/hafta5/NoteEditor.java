package com.example.hafta5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class NoteEditor extends AppCompatActivity {

    EditText editText_note ;
    int noteId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_editor);

        editText_note = (EditText) findViewById(R.id.editText_noteEditor_note);

        Intent editorIntent = getIntent();
        noteId = editorIntent.getIntExtra("noteId", -1);

        if (noteId != -1){
            editText_note.setText(noteActivity.notes.get(noteId));
        }else {
            noteActivity.notes.add("");
            noteId = noteActivity.notes.size()-1 ;
            noteActivity.arrayAdapter.notifyDataSetChanged();
        }

        editText_note.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                noteActivity.notes.set(noteId, s.toString());
                noteActivity.arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
