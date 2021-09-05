package com.bala.contactlistapp.view.adapter;

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bala.contactlistapp.R
import com.bala.contactlistapp.model.ContactDataBaseModel
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

/**
 * Created by BalaMurugan on 05-Sep-21.
 *
 *
 */

public class MainListAdapter(
    contactDataBaseModel: List<ContactDataBaseModel>,
    mContext: Context?,
    clickedListener: ClickListener?
) :
    RecyclerView.Adapter<MainListAdapter.ContactListViewHolder?>() {

    private val contactListModel: List<ContactDataBaseModel> = contactDataBaseModel
    private val clickedListener: ClickListener = clickedListener!!
    private val context: Context = mContext!!

    var index = 0

    interface ClickListener {
        fun selectData(contactDataBaseModel: ContactDataBaseModel, position: Int)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int): ContactListViewHolder {
        val itemView: View =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.contact_list_adapter, parent, false)
        return ContactListViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ContactListViewHolder, position: Int) {
        try {
            val contactData: ContactDataBaseModel = contactListModel!![position]
            holder.mobile_tv.setText(contactData.getPhone())
            holder.first_name_tv.setText(contactData.getName())

            Glide.with(context).load(contactData.getPhoto()).apply(
                RequestOptions.placeholderOf(R.drawable.logo_data).dontAnimate()
                    .error(R.drawable.logo_data)
            ).into(holder.user_data_image_iv)

            holder.user_data_card.setOnClickListener {
                index = position
                if(clickedListener!=null){
                    clickedListener.selectData(contactData, position)
                }
            }
        } catch (e: Exception) {
        }

    }

    override fun getItemCount(): Int {
        return contactListModel!!.size
    }

    class ContactListViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var mobile_tv: TextView = itemView.findViewById<View>(R.id.mobile_tv) as TextView
        var first_name_tv: TextView = itemView.findViewById<View>(R.id.first_name_tv) as TextView
        var user_data_image_iv: ImageView =
            itemView.findViewById<View>(R.id.user_data_image_iv) as ImageView
        var user_data_card: CardView = itemView.findViewById<View>(R.id.user_data_card) as CardView

    }

}

