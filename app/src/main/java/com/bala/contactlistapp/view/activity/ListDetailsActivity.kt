package com.bala.contactlistapp.view.activity

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bala.contactlistapp.R
import com.bala.contactlistapp.model.ContactDataBaseModel
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class ListDetailsActivity : AppCompatActivity() {


    var dataItems: ContactDataBaseModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_details)

        val mobile_tv: TextView = findViewById(R.id.mobile_tv) as TextView
        val first_name_tv = findViewById<TextView>(R.id.first_name_tv)
        val user_data_image_iv = findViewById<ImageView>(R.id.user_data_image_iv)
        val user_email_tv = findViewById<TextView>(R.id.email_tv)

        dataItems = intent.getSerializableExtra("dataItems") as ContactDataBaseModel?

        if (dataItems != null) {
            mobile_tv.setText(dataItems!!.getPhone().toString())
            first_name_tv.setText(dataItems!!.getName().toString())
            user_email_tv.setText(dataItems!!.getEmail().toString())

            Glide.with(this@ListDetailsActivity).load(dataItems!!.getPhoto()).apply(
                RequestOptions.placeholderOf(R.drawable.logo_data).dontAnimate()
                    .error(R.drawable.logo_data)
            ).into(user_data_image_iv)
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(0, 0)
        finish()
    }
}