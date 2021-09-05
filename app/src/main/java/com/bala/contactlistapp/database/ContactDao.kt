package com.bala.contactlistapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bala.contactlistapp.model.ContactDataBaseModel
import io.reactivex.Completable
import io.reactivex.Single


/**
 * Created by BalaMurugan on 05-Sep-21.
 *
 *
 */

@Dao
open interface ContactDao {
    @Insert
    fun insertUserData(contactDataBaseModel: ContactDataBaseModel?): Single<Long?>?

    @Query("SELECT * FROM contactDataBaseModel")
    fun getAllList(): Single<List<ContactDataBaseModel>>

    @Query("UPDATE contactDataBaseModel SET name =:name,phone =:phone,email =:email,photo =:photo WHERE dbid =:id")
    fun updateData(name: String?, phone: String?, email: String?, photo: String?, id: Int): Int

  /*  @Query("SELECT * FROM contactDataBaseModel WHERE dbid=:idData")
    fun getIdByResponseData(idData: Int)

    @Query("SELECT * FROM contactdatabasemodel WHERE dbid=:idData")
    fun checkDataPresent(idData: Int)*/

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertData(responseBaseClass: ContactDataBaseModel?): Long?

    @Query(" DELETE FROM contactDataBaseModel")
    fun deleteDataBase(): Completable


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPersonDataList(dataList: List<ContactDataBaseModel>): Completable



}