package com.bala.contactlistapp

import android.app.Application
import android.content.Context

/**
 * Created by BalaMurugan on 05-Sep-21.
 *
 *
 */

class MasterApplication : Application() {

    private var context: Context? = null

    private var mInstance: MasterApplication? = null
    var mLastPause: Long = 0

    override fun onCreate() {
        super.onCreate()
        context = this
        mInstance = this
    }

    fun getContext(): Context? {
        return context
    }

    @Synchronized
    fun getInstance(): MasterApplication? {
        return mInstance
    }


}