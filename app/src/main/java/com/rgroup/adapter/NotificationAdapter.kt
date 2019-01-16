package com.rgroup.adapter

import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.rgroup.R
import com.rgroup.activity.NotificationActivity
import com.rgroup.pojo.NotificationPojo

class NotificationAdapter(val context: Activity, var notificationList: ArrayList<NotificationPojo>) : RecyclerView.Adapter<NotificationAdapter.ViewHolder>() {

    var ctx: NotificationActivity = context as NotificationActivity

    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.notification_row_layout, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: NotificationAdapter.ViewHolder, position: Int) {
        holder.bindItems(notificationList[position], ctx)
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return notificationList.size
    }

    //the class is hodling the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(notificationData: NotificationPojo, ctx: NotificationActivity) {
            // initalize the fielda
            val notification_title: TextView = itemView.findViewById(R.id.notification_title)
            val notitfication_date: TextView = itemView.findViewById(R.id.notitfication_date)
            val notification_description: TextView = itemView.findViewById(R.id.notification_description)

            //set the data on the fields
            notification_title.text = notificationData.title
            notitfication_date.text = notificationData.date_time
            notification_description.text = notificationData.description

        }
    }
}