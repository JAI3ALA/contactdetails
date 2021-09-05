package com.bala.contactlistapp.database

import androidx.room.TypeConverter
import com.bala.contactlistapp.model.ContactDataBaseModel
import com.google.gson.Gson

/**
 * Created by BalaMurugan on 05-Sep-21.
 *
 *
 */

class SampleConverter {
    @TypeConverter
    fun listToJson(value: List<ContactDataBaseModel>?): String? {
        value?.let {
            return Gson().toJson(value)
        }?: return null
    }

    @TypeConverter
    fun jsonToList(value: String?): List<ContactDataBaseModel>? {
        val objects = Gson().fromJson(value, Array<ContactDataBaseModel>::class.java)
        return objects?.toList()?:null
    }
}