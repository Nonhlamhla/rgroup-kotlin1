package com.rgroup.activity

import android.app.Dialog
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import com.rgroup.R
import com.rgroup.databinding.ActivityLoyalityPointsBinding
import com.rgroup.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_main_footer.*

class MainActivity : BaseActivity(){

    private var ctx : MainActivity = this
    private lateinit var activityMainBinding: ActivityMainBinding
    private var doubleBackToExitPressedOnce = false
    private lateinit var activityLoyalityPointsBinding: ActivityLoyalityPointsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = DataBindingUtil.setContentView(ctx, R.layout.activity_main)
        initalize()
        setListenr()
    }

    private fun initalize() {
        footer("")
        iv_back_icon.visibility = View.GONE
        view_footer.visibility = View.GONE
    }

    private fun setListenr() {
        airtime_data_purchase.setOnClickListener {
            startActivity(Intent(ctx, AirtimeDataPurchaseActivity::class.java))
        }

        prepaid_electricity.setOnClickListener {
            startActivity(Intent(ctx, ElectiricityActivity::class.java))
        }

        rica.setOnClickListener {
            startActivity(Intent(ctx, RicaActivity::class.java))
        }

        water_bill.setOnClickListener {
            startActivity(Intent(ctx,WaterBillActivity::class.java))
        }

        lotto_tickets.setOnClickListener {
            startActivity((Intent(ctx, LottoTicketsActivity::class.java)))
        }

        loyality_points.setOnClickListener {
            loyalityBox()
        }

        notification.setOnClickListener {
            startActivity(Intent(ctx, NotificationActivity::class.java))
        }

        setting.setOnClickListener {
            startActivity(Intent(ctx, AppSettingActivity::class.java))
        }
    }

    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        }
        this.doubleBackToExitPressedOnce = true
        displayToast(resources.getString(R.string.back_exit))
        Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
    }

    private fun loyalityBox() {
        // infalte the custom layout
        activityLoyalityPointsBinding = DataBindingUtil.inflate(LayoutInflater.from(ctx), R.layout.activity_loyality_points, null, false)
        // set dialog for no title show in top
        val dialog = Dialog(ctx, android.R.style.Theme_Translucent_NoTitleBar)
        // set view on dialog
        dialog.setContentView(activityLoyalityPointsBinding.root)
        // cancelation set false touch on outside the dialog
        dialog.setCanceledOnTouchOutside(false)

        //click the close button for dismiss the dialog
        activityLoyalityPointsBinding.closeLoyalty.setOnClickListener {
            dialog.dismiss()
        }
        // show the dialog
        dialog.show()
    }
}