package com.bala.contactlistapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bala.contactlistapp.model.ContactDataBaseModel

/**
 * Created by BalaMurugan on 05-Sep-21.
 *
 *
 */

@Database(entities = [ContactDataBaseModel::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    fun destroyInstance() {
        try {
            if (appDatabase != null) {
                appDatabase!!.clearAllTables()
            }
            appDatabase = null
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    abstract fun contactDao(): ContactDao?

    companion object {
        private var appDatabase: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase? {
            if (appDatabase == null) {
                appDatabase = Room.databaseBuilder(
                    context.getApplicationContext(),
                    AppDatabase::class.java, " contactlist.db "
                ).build()
            }
            return appDatabase
        }
    }
}