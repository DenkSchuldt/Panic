package com.dennyschuldt.panic.database.db;

import android.util.Log;

import com.dennyschuldt.panic.database.DBHelper;
import com.dennyschuldt.panic.models.Email;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

/**
 * Created by denny on 2/7/16.
 */
public class EmailDB {

    private static final String TAG = "EntryDB";

    private Dao<Email, Integer> emailDao;

    public EmailDB(DBHelper dbHelper) {
        try {
            this.emailDao = dbHelper.getEmailDao();
        } catch (SQLException e) {
            Log.d(TAG, "ERROR: Could not get EmailDao.");
            e.printStackTrace();
        }
    }

    public synchronized boolean saveEmail(Email email) {
        try {
            emailDao.createOrUpdate(email);
            Log.d(TAG, "SAVED Email: " + email.getAddress());
            return true;
        } catch (SQLException e) {
            Log.d(TAG, "ERROR: Could not save Email: " + email.getAddress());
            e.printStackTrace();
        }
        return false;
    }

    public synchronized boolean deleteEmail(Email email) {
        try {
            emailDao.delete(email);
            Log.d(TAG, "DELETED Email: " + email.getAddress());
            return true;
        } catch (SQLException e) {
            Log.d(TAG, "ERROR: Could not delete Email: " + email.getAddress());
            e.printStackTrace();
        }
        return false;
    }

}