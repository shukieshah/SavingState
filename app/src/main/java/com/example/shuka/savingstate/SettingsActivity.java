package com.example.shuka.savingstate;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by shuka on 12/29/2016.
 */

public class SettingsActivity extends PreferenceActivity {
    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferences);
    }
}
