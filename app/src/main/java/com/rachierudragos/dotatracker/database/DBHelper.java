package com.rachierudragos.dotatracker.database;

import android.content.Context;

import com.google.gson.Gson;
import com.rachierudragos.dotatracker.vars.Utils;

import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseOpenHelper;

/**
 * Created by Dragos on 08-Nov-17.
 */

public class DBHelper extends DaoMaster.DevOpenHelper {

    public DBHelper(Context context, String name) {
        super(context, name);
    }

}
