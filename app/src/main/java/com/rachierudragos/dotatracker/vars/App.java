package com.rachierudragos.dotatracker.vars;

import android.app.Application;

import com.rachierudragos.dotatracker.Wrapper.ODotaAPI2;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Dragos on 02-Nov-17.
 */

public class App extends Application {
    private ODotaAPI2 api;

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
    }
}
