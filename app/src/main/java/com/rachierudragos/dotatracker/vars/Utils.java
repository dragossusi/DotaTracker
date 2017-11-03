package com.rachierudragos.dotatracker.vars;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.text.DecimalFormat;

/**
 * Created by Dragos on 02-Nov-17.
 */

public class Utils {
    public static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0.00");
    public static final String API_HOST = "https://api.opendota.com/api/";
    private static final String ID = "id";
    private static final String MATCHES = "matches";
    private static SharedPreferences preferences;
    static void setContext(App app) {
        preferences = PreferenceManager.getDefaultSharedPreferences(app);
    }
    public static void putId(int id) {
        preferences.edit().putInt(ID,id).apply();
    }
    public static int getId() {
        return preferences.getInt(ID,0);
    }


    public static void putMatchesNumber(int nr) {
        preferences.edit().putInt(MATCHES,nr).apply();
    }
    public static int getMatchesNumber() {
        return preferences.getInt(MATCHES,15);
    }


}
