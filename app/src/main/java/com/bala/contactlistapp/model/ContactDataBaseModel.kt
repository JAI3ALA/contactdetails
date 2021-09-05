package com.bala.contactlistapp.model

import  androidx.room.ColumnInfo;
import  androidx.room.Entity;
import  androidx.room.PrimaryKey;
import com.google.gson.annotations.SerializedName
import java.io.Serializable


/**
 * Created by BalaMurugan on 05-Sep-21.
 *
 *
 */

@Entity
class ContactDataBaseModel : Serializable {

    @PrimaryKey(autoGenerate = true)
    private var dbid = 0

    @SerializedName("name")
    @ColumnInfo(name = "name")
    private var name: String? = null

    @SerializedName("phone")
    @ColumnInfo(name = "phone")
    private var phone: String? = null

    @SerializedName("photo")
    @ColumnInfo(name = "photo")
    private var photo: String? = null

    @SerializedName("email")
    @ColumnInfo(name = "email")
    private var email: String? = null


    fun getDbid(): Int {
        return dbid
    }

    fun setDbid(dbid: Int) {
        this.dbid = dbid
    }


    fun setName(name: String?) {
        this.name = name
    }

    fun getName(): String? {
        return name
    }

    fun setPhone(phone: String?) {
        this.phone = phone
    }

    fun getPhone(): String? {
        return phone
    }

    fun setEmail(email: String?) {
        this.email = email
    }

    fun getEmail(): String? {
        return email
    }

    fun setPhoto(photo: String?) {
        this.photo = photo
    }

    fun getPhoto(): String? {
        return photo
    }


}