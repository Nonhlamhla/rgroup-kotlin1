package com.rgroup.activity

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import com.rgroup.R
import com.rgroup.databinding.ActivityAppsettingsBinding
import com.rgroup.util.ViewAnimationUtils
import kotlinx.android.synthetic.main.activity_appsettings.*

class AppSettingActivity : BaseActivity(){

    private var ctx : AppSettingActivity = this
    private lateinit var activityAppsettingsBinding: ActivityAppsettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityAppsettingsBinding = DataBindingUtil.setContentView(ctx, R.layout.activity_appsettings)
        initalize()
        setListenr()
    }

    // initalize the variable and call the footer method
    private fun initalize() {
        //call the footer method from base activity
        footer("")
    }


    // call the listener on the fields
    private fun setListenr() {
        layHelp.setOnClickListener {
            //call the help activity
            startActivity(Intent(ctx, HelpActivity::class.java))
        }

        layStaticContent.setOnClickListener {
            // call the method expand and collapse layout
            clickDownArrow(lay_static_content, static_content_arrow)
        }
    }

    // expand and collapse the layout and set the image.
    private fun clickDownArrow(lin_child_title: LinearLayout, header_plus_icon: ImageView) {
        // check the condition the layout are Gone
        if (lin_child_title.visibility == View.GONE) {
            // layout are expand the child layout
            ViewAnimationUtils.expand(lin_child_title)
            // set the image in drop down
            header_plus_icon.setImageResource(R.mipmap.droup_down_arrow_open)
        } else {
            // child layout are expand the collapse click
            ViewAnimationUtils.collapse(lin_child_title)
            // set the image on drop down close
            header_plus_icon.setImageResource(R.mipmap.droup_down_arrow)
        }
    }
}