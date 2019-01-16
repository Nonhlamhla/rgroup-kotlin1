package com.rgroup.activity

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.os.Handler
import com.rgroup.R
import com.rgroup.databinding.ActivitySplashTwoBinding

class SplashActivity : BaseActivity(){

    private lateinit var splashTwoBinding: ActivitySplashTwoBinding
    private var ctx : SplashActivity = this
    private var handler: Handler? = null
    private var runnable: Runnable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashTwoBinding = DataBindingUtil.setContentView(ctx, R.layout.activity_splash_two)
        initalize()
    }


    // set the handler for delay 3 sec then after call the login activity
    private fun initalize() {
        handler = Handler()
        runnable = Runnable {
            startActivity(Intent(ctx, LoginActivity::class.java))
            finish()
        }
        this.handler?.postDelayed(runnable, 3000)
    }
}