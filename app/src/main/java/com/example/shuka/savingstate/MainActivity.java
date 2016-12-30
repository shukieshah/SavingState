package com.example.shuka.savingstate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText notesEditText;
    Button btnSettings;
    private static final int SETTINGS_INFO = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notesEditText = (EditText) findViewById(R.id.notesEditText);

        if (savedInstanceState != null) {
            String notes = savedInstanceState.getString("NOTES");
            notesEditText.setText(notes);
        }

        String sPNotes = getPreferences(Context.MODE_PRIVATE).getString("NOTES", "EMPTY");

        if (!sPNotes.equals("EMPTY")) {
            notesEditText.setText(sPNotes);
        }

        btnSettings = (Button) findViewById(R.id.btnSettings);

        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentPreferences = new Intent(getApplicationContext(),
                        SettingsActivity.class);
                startActivityForResult(intentPreferences, SETTINGS_INFO);
            }
        });

        updateNoteText();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SETTINGS_INFO) {
            updateNoteText();
        }
    }

    private void updateNoteText() {
        // Shared key value pairs are here
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        // Check if the checkbox was clicked
        if (sharedPreferences.getBoolean("pref_text_bold", false)) {
            notesEditText.setTypeface(null, Typeface.BOLD);
        } else {
            notesEditText.setTypeface(null, Typeface.NORMAL);
        }


        // Get the value stored in the list preference or give a value of 16
        String textSizeStr = sharedPreferences.getString("pref_text_size", "16");
        float textSizeFloat = Float.parseFloat(textSizeStr);
        notesEditText.setTextSize(textSizeFloat);
    }

    @Override
    protected void onStop() {
        saveSettings();
        super.onStop();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("NOTES",
                notesEditText.getText().toString());
        super.onSaveInstanceState(outState);
    }

    private void saveSettings() {
        SharedPreferences.Editor spEditor = getPreferences(Context.MODE_PRIVATE).edit();
        spEditor.putString("NOTES",
                notesEditText.getText().toString());
        spEditor.commit();
    }

}
