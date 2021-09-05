package com.bala.contactlistapp.database;

import android.os.AsyncTask;
import android.util.Log;

import com.bala.contactlistapp.model.ContactDataBaseModel;

/**
 * Created by BalaMurugan on 05-Sep-21.
 */

public class DataBaseDelete {

    public void deleteDatabase(AppDatabase appDatabase) {
        try {
            new AsyncTask<Void, Void, Integer>() {
                @Override
                protected Integer doInBackground(Void... voids) {
                    appDatabase.destroyInstance();
                    Log.v(" Deleted ", " AppDatabase::: ");
                    return 1;
                }
            }.execute();    //  .get Removed
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
