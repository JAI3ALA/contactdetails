package com.bala.contactlistapp.contact

import android.content.ContentResolver
import android.content.Context
import android.database.Cursor
import android.provider.ContactsContract
import android.util.Log
import android.widget.Toast
import com.bala.contactlistapp.model.ContactDataBaseModel
import java.util.*


/**
 * Created by BalaMurugan on 05-Sep-21.
 *
 *
 */

public class ContactDatabaseAdapter(private val context: Context) {

    var getPhoneNumber: Cursor? = null
    var getEmail: Cursor? = null
    var resolver: ContentResolver? = null

    val contactDataBaseModel = ContactDataBaseModel()
    /*  var context: Context? = null

       fun DatabaseAdapter(context: Context?) {
          this.context = context
      }*/

    fun getData(): List<ContactDataBaseModel?>? {
        var data: MutableList<ContactDataBaseModel?>? = null
        try {
            data = ArrayList<ContactDataBaseModel?>()
            getPhoneNumber = context!!.contentResolver.query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null,
                null,
                null,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC"
            )
            getEmail = context!!.contentResolver.query(
                ContactsContract.CommonDataKinds.Email.CONTENT_URI,
                null,
                null,
                null,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC"
            )

            if (getPhoneNumber != null) {
                Log.e("count", "" + getPhoneNumber!!.count)
                if (getPhoneNumber!!.count == 0) {
                    Toast.makeText(context, "No contacts in your contact list.", Toast.LENGTH_LONG)
                        .show()
                }
                while (getPhoneNumber!!.moveToNext()) {
                    val id =
                        getPhoneNumber!!.getString(getPhoneNumber!!.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID))
                    val name =
                        getPhoneNumber!!.getString(getPhoneNumber!!.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                    val phoneNumber =
                        getPhoneNumber!!.getString(getPhoneNumber!!.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                    val photo =
                        getPhoneNumber!!.getString(getPhoneNumber!!.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_URI))

                    val mobile = phoneNumber.replace("\\s+".toRegex(), "")
                    val contactDataBaseModel = ContactDataBaseModel()
                    contactDataBaseModel.setName(name)
                    contactDataBaseModel.setPhone(mobile)
                    contactDataBaseModel.setPhoto(photo)
                    if (getEmail != null) {
                        while (getEmail!!.moveToNext()) {
                            val email = getEmail!!.getString(getEmail!!.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA))
                            contactDataBaseModel.setEmail(email)
                            break
                        }
                    }
                    data!!.add(contactDataBaseModel)
                }
            } else {
                Log.e("Cursor close 1", "----")
            }
            getPhoneNumber!!.close()
            getEmail!!.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return data
    }
}