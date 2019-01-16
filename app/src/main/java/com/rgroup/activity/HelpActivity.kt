package com.rgroup.activity

import android.databinding.DataBindingUtil
import android.os.Bundle
import com.rgroup.R
import com.rgroup.databinding.ActivityHelpBinding
import kotlinx.android.synthetic.main.back_footer.*

class HelpActivity : BaseActivity(){

    private var ctx : HelpActivity = this
    private lateinit var activityHelpBinding: ActivityHelpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityHelpBinding = DataBindingUtil.setContentView(ctx, R.layout.activity_help)
        initalize()
        setListenr()
    }

    private fun initalize() {

    }

    private fun setListenr() {
        iv_back_icon.setOnClickListener {
            finish()
        }
    }

}