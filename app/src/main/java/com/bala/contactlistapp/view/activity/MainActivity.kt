package com.bala.contactlistapp.view.activity

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bala.contactlistapp.R
import com.bala.contactlistapp.model.ContactDataBaseModel
import com.bala.contactlistapp.view.adapter.MainListAdapter
import com.bala.contactlistapp.view.viewmodel.MainActivityViewModel


class MainActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener,
    MainListAdapter.ClickListener {

    var context: Context? = null

    var alreadyCalled: Boolean? = false

    var mainActivityViewModel: MainActivityViewModel? = null

    var mainListAdapter: MainListAdapter? = null

    private var swipeRefresh: SwipeRefreshLayout? = null
    private var contactListRv: RecyclerView? = null
    private var pbHeaderProgress: ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        context = this
        check()
        swipeRefresh = findViewById<SwipeRefreshLayout>(R.id.swipeRefresh)
        contactListRv = findViewById<RecyclerView>(R.id.contactListRv)
        pbHeaderProgress = findViewById<ProgressBar>(R.id.pbHeaderProgress)

        swipeRefresh!!.setOnRefreshListener(this)
        swipeRefresh!!.setColorSchemeColors(resources.getColor(R.color.purple_200))

        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        mainActivityViewModel!!.initContext(this)

    }


    private fun callFetchAndShowUI() {
        pbHeaderProgress!!.visibility = View.VISIBLE
        alreadyCalled = true
        mainActivityViewModel!!.callInternalContactList()
        observerViewModel()

        Handler(Looper.getMainLooper()).postDelayed({
            mainActivityViewModel!!.getPersonData()
            setToadapterData()
        }, 1000)

    }

    private fun observerViewModel() {
        mainActivityViewModel?.getLocalList?.observe(this, Observer {
            if (!it.isNullOrEmpty()) {
                mainActivityViewModel?.getLocalList?.removeObservers(this)
                mainActivityViewModel!!.insertTolocalDatabase(it)
            } else {
                Toast.makeText(
                    this@MainActivity,
                    "No Contact Found",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }


    private fun setToadapterData() {
        pbHeaderProgress!!.visibility = View.GONE
        mainActivityViewModel?.setListToAdapter?.observe(this, Observer {
            if (!it.isNullOrEmpty()) {
                mainActivityViewModel?.setListToAdapter?.removeObservers(this)
                handleUI(it)
                Log.d("DataList ", "Size " + it.size)
            } else {
                Toast.makeText(
                    this@MainActivity,
                    "No Contact Found",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })


    }

    private fun handleUI(list: List<ContactDataBaseModel>) {

        mainListAdapter = MainListAdapter(list, this@MainActivity, this)
        contactListRv!!.setLayoutManager(LinearLayoutManager(this@MainActivity))
        contactListRv!!.setAdapter(mainListAdapter)
    }

    override fun onResume() {
        super.onResume()
       /* if (!alreadyCalled!!) {
            callFetchAndShowUI()
        }*/
    }

    private fun check() {
        ActivityCompat.requestPermissions(
            this@MainActivity, arrayOf(Manifest.permission.READ_CONTACTS),
            1
        )
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            1 -> {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.i("TAG", "Agree Contact permission")
                    if (!alreadyCalled!!) {
                        callFetchAndShowUI()
                    }
                } else {
                    Log.i("TAG", "Not agree Contact permission")
                    Toast.makeText(
                        this@MainActivity,
                        "Read contacts permission is required to function app correctly",
                        Toast.LENGTH_SHORT
                    ).show();
                }

            }
        }
    }

    override fun selectData(contactDataBaseModel: ContactDataBaseModel, position: Int) {
        val mainIntent = Intent(this@MainActivity, ListDetailsActivity::class.java)
        mainIntent.putExtra("dataItems",contactDataBaseModel)
        startActivity(mainIntent)
        overridePendingTransition(0, 0)
    }

    override fun onRefresh() {
        if (swipeRefresh!!.isRefreshing()) {
            swipeRefresh!!.setRefreshing(false);
        }
        mainActivityViewModel!!.initContext(this)
        callFetchAndShowUI()

    }


}