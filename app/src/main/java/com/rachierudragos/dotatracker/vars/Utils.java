package com.rachierudragos.dotatracker.vars;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.format.DateFormat;
import android.util.DisplayMetrics;
import android.util.Log;

import com.google.gson.Gson;
import com.rachierudragos.dotatracker.Wrapper.database.HeroDatabase;
import com.rachierudragos.dotatracker.Wrapper.hero.HeroDetail;
import com.rachierudragos.dotatracker.Wrapper.item.ItemsResponse;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

/**
 * Created by Dragos on 02-Nov-17.
 */

public class Utils {
    public static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0.00");
    public static final DateFormat DATE_FORMAT = new DateFormat();

    public static final String HOME = "https://api.opendota.com/";
    public static final String IMG_ABILITY_HOME = HOME + "apps/dota2/images/abilities/";
    public static final String API_HOST = HOME + "api/";
    public static final String ITEMS_JSON = "items.json";
    public static final String ABILITIES_JSON = "hero_abilities.json";

    private static final String ID = "id";
    private static final String MATCHES = "matches";
    private static final String DATABASE = "database";
    private static SharedPreferences preferences;


    public static int getCount() {
        switch (App.getDisplayMetrics().densityDpi) {
            case DisplayMetrics.DENSITY_LOW:
                return 1;
            case DisplayMetrics.DENSITY_MEDIUM:
                return 2;
            case DisplayMetrics.DENSITY_HIGH:
                return 2;
            case DisplayMetrics.DENSITY_XHIGH:
                return 3;
            case DisplayMetrics.DENSITY_XXHIGH:
                return 4;
            case DisplayMetrics.DENSITY_XXXHIGH:
                return 4;
        }
        return 0;
    }

    static void setContext(App app) {
        preferences = PreferenceManager.getDefaultSharedPreferences(app);
    }

    public static void setDBCreated(boolean bool) {
        preferences.edit().putBoolean(DATABASE,bool).apply();
    }

    public static boolean getDBCreated() {
        return preferences.getBoolean(DATABASE,false);
    }

    public static void putId(int id) {
        preferences.edit().putInt(ID, id).apply();
    }

    public static int getId() {
        return preferences.getInt(ID, 0);
    }


    public static void putMatchesNumber(int nr) {
        preferences.edit().putInt(MATCHES, nr).apply();
    }

    public static int getMatchesNumber() {
        return preferences.getInt(MATCHES, 15);
    }

    public static int getPhotoResId(Context context, int hero_id) {
        return context.getResources().getIdentifier(HeroDatabase.getHeroIdName(hero_id), "drawable", context.getPackageName());
    }

    public static String readJson(Context context, String fileName) throws IOException {
        InputStream stream = context.getAssets().open(fileName);
        int size = stream.available();
        byte[] buffer = new byte[size];
        stream.read(buffer);
        stream.close();
        return new String(buffer, "UTF-8");
    }

}
