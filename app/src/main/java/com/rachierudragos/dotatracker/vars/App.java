package com.rachierudragos.dotatracker.vars;

import android.app.Application;
import android.util.DisplayMetrics;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rachierudragos.dotatracker.R;
import com.rachierudragos.dotatracker.Wrapper.ODotaAPI2;
import com.rachierudragos.dotatracker.database.Ability;
import com.rachierudragos.dotatracker.database.AbilityDao;
import com.rachierudragos.dotatracker.database.DBHelper;
import com.rachierudragos.dotatracker.database.DaoMaster;
import com.rachierudragos.dotatracker.database.DaoSession;

import org.greenrobot.greendao.database.Database;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Dragos on 02-Nov-17.
 */

public class App extends Application {
    private static final String LOG_TAG = "screenres";

    private ODotaAPI2 api;

    private static DisplayMetrics displayMetrics;
    private DaoSession daoSession;

    public DaoSession getDaoSession() {
        return daoSession;
    }

    public static DisplayMetrics getDisplayMetrics() {
        return displayMetrics;
    }

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
        displayMetrics = getResources().getDisplayMetrics();

        DBHelper helper = new DBHelper(this, "projects-db");
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
        if(!Utils.getDBCreated()) {
            Gson gson = new Gson();
            try {
                Map<Long, String> abilityMap = gson.fromJson(Utils.readJson(this, getString(R.string.ability_ids)), new TypeToken<Map<Long, String>>() {
                }.getType());
                AbilityDao abilityDao = daoSession.getAbilityDao();
                for (Map.Entry<Long, String> entry : abilityMap.entrySet())
                    abilityDao.insertOrReplace(new Ability(entry.getKey(), entry.getValue()));
                Utils.setDBCreated(true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
