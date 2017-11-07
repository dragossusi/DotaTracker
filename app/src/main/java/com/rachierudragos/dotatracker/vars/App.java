package com.rachierudragos.dotatracker.vars;

import android.app.Application;
import android.util.DisplayMetrics;
import android.util.Log;

import com.rachierudragos.dotatracker.Wrapper.ODotaAPI2;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Dragos on 02-Nov-17.
 */

public class App extends Application {
    private static final String LOG_TAG = "screenres";

    private ODotaAPI2 api;

    private float dpHeight;
    float dpWidth;

    public ODotaAPI2 getApi() {
        return api;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Utils.API_HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(ODotaAPI2.class);
        Utils.setContext(this);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        dpHeight = displayMetrics.heightPixels / displayMetrics.density;
        dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        switch (displayMetrics.densityDpi) {
            case DisplayMetrics.DENSITY_LOW:

                Log.d(LOG_TAG, "ldpi");
                break;
            case DisplayMetrics.DENSITY_MEDIUM:

                Log.d(LOG_TAG, "mdpi");
                break;
            case DisplayMetrics.DENSITY_HIGH:

                Log.d(LOG_TAG, "hdpi");
                break;
            case DisplayMetrics.DENSITY_XHIGH:

                Log.d(LOG_TAG, "xhdpi");
                break;
            case DisplayMetrics.DENSITY_XXHIGH:

                Log.d(LOG_TAG, "xxhdpi");
                break;
            case DisplayMetrics.DENSITY_XXXHIGH:

                Log.d(LOG_TAG, "xxxhdpi");
                break;
        }
        Log.d(LOG_TAG, String.valueOf(getResources().getDisplayMetrics().densityDpi));
    }

    public float getDpWidth() {
        return dpWidth;
    }
}
