package com.dennyschuldt.panic.database;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import com.dennyschuldt.panic.models.Email;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by denny on 2/7/16.
 */
public class DBHelper extends OrmLiteSqliteOpenHelper {

    private Context context;
    private static final String TAG = "DBHelper";

    private static final String DATABASE_NAME = "com.dennyschuldt.panic.db";
    private static final int DATABASE_VERSION = 1;

    private Dao<Email, Integer> emailDao;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Email.class);
        } catch (SQLException e) {
            Log.d(TAG, "ERROR: Could not create Database " + DATABASE_NAME);
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        onCreate(db, connectionSource);
    }

    public Dao<Email, Integer> getEmailDao() throws SQLException {
        if (emailDao == null) {
            emailDao = getDao(Email.class);
        }
        return emailDao;
    }

    @Override
    public void close() {
        super.close();
        emailDao = null;
    }

}
