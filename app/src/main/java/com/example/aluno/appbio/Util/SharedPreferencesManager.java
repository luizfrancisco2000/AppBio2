package com.example.aluno.appbio.Util;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesManager {

    // Shared Preferences
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private Context _context;

    // Shared pref mode
    private final int PRIVATE_MODE_SHARED_PREF = 0;

    // Shared preferences file name
    private final String PREF_NAME = "histo";

    /*KEYS para o sharedpreferences*/
    private final String FONT_KEY = PREF_NAME + "setFont";


    public SharedPreferencesManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE_SHARED_PREF);
        editor = pref.edit();
    }

    public void setFont(float keySize) {
        editor.putFloat(FONT_KEY, keySize);
        editor.commit();
    }

    public float getFont() {
        return pref.getFloat(FONT_KEY, 15/*your default value is in here (in sp)*/);
    }

}
