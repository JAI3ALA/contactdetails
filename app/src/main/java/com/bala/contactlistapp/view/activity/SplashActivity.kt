package com.bala.contactlistapp.view.activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bala.contactlistapp.R

class SplashActivity : AppCompatActivity() {

    private var handleCheckStatus: Handler? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        // Making notification bar transparent
        if (Build.VERSION.SDK_INT >= 21) {
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }
        handleCheckStatus = Handler()

        handleCheckStatus!!.postDelayed(object : Runnable {
            override fun run() {
                val mainIntent = Intent(this@SplashActivity, MainActivity::class.java)
                startActivity(mainIntent)
                overridePendingTransition(0, 0)
                finish()
            }
        }, 2000)

    }
}