package com.rgroup.activity

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import com.rgroup.R
import com.rgroup.databinding.ActivityDataPurchaseBinding
import kotlinx.android.synthetic.main.activity_data_purchase.*

class AirtimeDataPurchaseActivity : BaseActivity(){

    private var ctx : AirtimeDataPurchaseActivity = this
    private lateinit var activityDataPurchaseBinding: ActivityDataPurchaseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityDataPurchaseBinding = DataBindingUtil.setContentView(ctx, R.layout.activity_data_purchase)
        // set the footer
        footer("")
        //call click listener
        setListenr()
    }

    //click listener handle the click on fields

    private fun setListenr() {
        vodacom.setOnClickListener {
            activityCall(1)
        }

        mtn.setOnClickListener {
            activityCall(2)
        }

        cell.setOnClickListener {
            activityCall(3)
        }


        telkom.setOnClickListener {
            activityCall(4)
        }


        neotel.setOnClickListener {
            // call the Activity and pass the select service value
            startActivity(Intent(ctx, AirtimeActivity::class.java).putExtra("select_service", 5))
        }

        virgin.setOnClickListener {
            // call the Activity and pass the select service value
            startActivity(Intent(ctx, AirtimeActivity::class.java).putExtra("select_service", 6))
        }

        reports.setOnClickListener {
            // call the Activity and pass the select service value
            startActivity(Intent(ctx, DataActivity::class.java).putExtra("select_service", 7))
        }

    }


    private fun activityCall(select_service : Int){
        startActivity(Intent(ctx, DatapurchaseServiceActivity::class.java).putExtra("select_service", select_service))
    }
}