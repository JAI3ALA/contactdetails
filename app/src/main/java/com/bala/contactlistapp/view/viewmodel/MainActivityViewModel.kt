package com.bala.contactlistapp.view.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bala.contactlistapp.contact.ContactDatabaseAdapter
import com.bala.contactlistapp.database.AppDatabase
import com.bala.contactlistapp.database.DataBaseDelete
import com.bala.contactlistapp.model.ContactDataBaseModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


/**
 * Created by BalaMurugan on 05-Sep-21.
 */

class MainActivityViewModel : ViewModel() {

    protected val compositeDisposable = CompositeDisposable()
    private val context: Context? = null
    var getLocalList: MutableLiveData<List<ContactDataBaseModel>>? = null
    var savedLiveData: MutableLiveData<Int>? = null
    var appDatabase: AppDatabase? = null

    var mydb: ContactDatabaseAdapter? = null
    var dataBaseDelete: DataBaseDelete? = null

    var setListToAdapter = MutableLiveData<List<ContactDataBaseModel>>()


    fun initContext(context: Context?) {
        try {
            appDatabase = context?.let { AppDatabase.getInstance(it) }
            dataBaseDelete = DataBaseDelete()
            dataBaseDelete!!.deleteDatabase(appDatabase)
            savedLiveData = MutableLiveData()
            getLocalList = MutableLiveData()
            mydb = ContactDatabaseAdapter(context!!)

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun callInternalContactList() {
        val listObservable: Observable<List<ContactDataBaseModel?>?> =
            Observable.just(mydb!!.getData())
        listObservable.subscribe({
            if (!it.isNullOrEmpty()) {
                getLocalList!!.postValue(it as List<ContactDataBaseModel>?)
            } else {
                getLocalList!!.postValue(listOf())
            }
            /*it?.forEach {

            }*/
        }, {
        })?.let {
            // compositeDisposable.add(it)
        }
    }


    fun insertTolocalDatabase(data: List<ContactDataBaseModel>) {
        try {
            appDatabase?.contactDao()?.insertPersonDataList(data)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({
                }, {

                })?.let {
                    //    compositeDisposable.add(it)
                }
        } catch (e: Exception) {
        }
    }

/*
    @SuppressLint(" StaticFieldLeak ")
    private fun deleteLocalDatabase() {
        //  Delete All Data to DB
        appDatabase?.contactDao()?.deleteDataBase()
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe({
                Log.v("Success", " Delete")
            }, {
            })?.let {
                //     compositeDisposable.add(it)
            }
    }
*/

    fun getPersonData() {
        appDatabase?.contactDao()?.getAllList()
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe({
                if (!it.isNullOrEmpty()) {
                    setListToAdapter.postValue(it as List<ContactDataBaseModel>?)
                } else {
                    setListToAdapter.postValue(listOf())
                }
                /*it?.forEach {
                    Log.v("Person Name", it.toString())
                }*/
            }, {
            })?.let {
                //     compositeDisposable.add(it)
            }
    }


    override fun onCleared() {
        compositeDisposable.dispose()
        compositeDisposable.clear()
        super.onCleared()
    }
}