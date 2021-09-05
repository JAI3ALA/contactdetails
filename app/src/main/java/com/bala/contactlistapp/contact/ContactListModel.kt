package com.bala.contactlistapp.contact

/**
 * Created by BalaMurugan on 05-Sep-21.
 *
 *
 */

class ContactListModel {

    private var name: String? = null
    private  var phone: String? = null
    private  var photo: String? = null
    private  var email: String? = null
    private var checkedBox = false

    fun getName(): String? {
        return name
    }

    fun setName(name: String?) {
        this.name = name
    }

    fun getPhone(): String? {
        return phone
    }

    fun setPhone(phone: String?) {
        this.phone = phone
    }

    fun getCheckedBox(): Boolean? {
        return checkedBox
    }


    fun getPhoto(): String? {
        return photo
    }

    fun setPhoto(photo: String?) {
        this.photo = photo
    }

    fun getEmail(): String? {
        return email
    }

    fun setEmail(email: String?) {
        this.email = email
    }

    fun setCheckedBox(checkedBox: Boolean) {
        this.checkedBox = checkedBox
    }
}