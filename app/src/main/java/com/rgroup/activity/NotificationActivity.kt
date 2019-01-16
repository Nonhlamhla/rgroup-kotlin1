package com.rgroup.activity

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.rgroup.R
import com.rgroup.adapter.NotificationAdapter
import com.rgroup.databinding.ActivityPushNotificationBinding
import com.rgroup.pojo.NotificationPojo
import kotlinx.android.synthetic.main.activity_push_notification.*

class NotificationActivity : BaseActivity(){

    private var ctx : NotificationActivity = this
    private lateinit var activityPushNotificationBinding: ActivityPushNotificationBinding
    private lateinit var notification_pojo : NotificationPojo
    private lateinit var notification_list : ArrayList<NotificationPojo>
    private lateinit var mLayoutManager: LinearLayoutManager
    private lateinit var adapter: NotificationAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityPushNotificationBinding = DataBindingUtil.setContentView(ctx, R.layout.activity_push_notification)
        initalize()
        setListenr()
    }

    private fun initalize() {
        //call the footer mehtod in base activity
        footer("")
        // initalize the array list
        notification_list = ArrayList()
        // initialize the layout manager
        mLayoutManager = LinearLayoutManager(this)
        // set layout manager on recycler view
        rv_notitication.layoutManager = mLayoutManager
        // call for loop for 5 times
        for(i in 0 until 5){
            // initalize the notification pojo Class
            notification_pojo = NotificationPojo()
            // set the value in notificaiton pojo class
            notification_pojo.title = "Hello"
            notification_pojo.date_time = "8 December, 6:34 PM"
            notification_pojo.description = "Create a ViewHolder class below the AnimalAdapter class. In the ViewHolder class, " +
                    "create a variable to hold the TextView (tv_animal_type) that we will be loading each animal into."
            // add the pojo to the array list
            notification_list.add(notification_pojo)
        }

        // call the notification adapter pass the class reference and notification list
        adapter = NotificationAdapter(ctx, notification_list)
        // set the adapter on recycler view
        rv_notitication.adapter = adapter
    }

    private fun setListenr() {

    }
}