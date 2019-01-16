package com.rgroup.activity

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import com.rgroup.R
import com.rgroup.databinding.ActivitySelectNetworkBinding
import kotlinx.android.synthetic.main.activity_select_datapurchase_service.*
import kotlinx.android.synthetic.main.activity_select_network.*

class DatapurchaseServiceActivity : BaseActivity(){

    private var ctx : DatapurchaseServiceActivity = this
    private lateinit var activitySelectNetworkBinding: ActivitySelectNetworkBinding
    private var select_service : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activitySelectNetworkBinding = DataBindingUtil.setContentView(ctx, R.layout.activity_select_network)
        initalize()
        setListenr()
    }

    private fun initalize() {
        // get the select data purchase value through the intent
        select_service = intent.getIntExtra("select_service", 0)
        // send the service
        when(select_service){
            1 -> setNetworkStyle(select_service, resources.getString(R.string.vodacom))
            2 -> setNetworkStyle(select_service, resources.getString(R.string.mtn))
            3 -> setNetworkStyle(select_service, resources.getString(R.string.cell_c))
            4 -> setNetworkStyle(select_service, resources.getString(R.string.telkom_mobile))
        }

    }

    // handle the click listener
    private fun setListenr() {
        /*tvdatapurchase_pay.setOnClickListener {
            //call the hide soft key board mehtod in base activity
            hideSoftKeyboard()
            // check the validate condition
            if(validate()){

            }
        }*/

        //click listener on airtime button
        airtime.setOnClickListener {
            // call the air time Activity and pass the select service value
            startActivity(Intent(ctx, AirtimeActivity::class.java).putExtra("select_service", select_service))
        }

        //click listener on own amount button
        own_amount.setOnClickListener {
            // call the own amount Activity and pass the select service value
            startActivity(Intent(ctx, OwnAmountActivity::class.java).putExtra("select_service", select_service))
        }

        //click listener on data button
        data.setOnClickListener {
            // call the own amount Activity and pass the select service value
            startActivity(Intent(ctx, DataActivity::class.java).putExtra("select_service", select_service))
        }
    }

    //check the empty and mobile number length validation.
    private fun validate(): Boolean {
        // get the number value in enter by user in local field
        val number = etDatapurchase_number.text.toString().trim()
        // get the amount value in enter by user in local field
        val amount = etdatapurchase_amount.text.toString()
        return when {
            //check the number empty field
            number.isEmpty() -> {
                // display the toast message
                displayToast(resources.getString(R.string.number_error_msg))
                // return the false
                false
            }
            // check the length less than to 8
            number.length < 8 -> {
                // display the toast message
                displayToast(resources.getString(R.string.valid_mobile))
                // return the false
                false
            }
            // check the amount field is empty
            amount.isEmpty() -> {
                // display the toast message
                displayToast(resources.getString(R.string.amount_error_msg))
                // return the false
                false
            }
            // check the amount filed is equal to dot
            etdatapurchase_amount.text.toString() == "." -> {
                // display the toast
                displayToast(resources.getString(R.string.amoutn_valid_msg))
                // return the false
                false
            }
            // return the true
            else -> true
        }
    }
}