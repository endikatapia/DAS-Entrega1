package com.example.das_entrega1;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

public class Preferencias extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        addPreferencesFromResource(R.xml.pref_config);
    }
}